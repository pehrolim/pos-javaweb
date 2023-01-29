package dev.fujioka.java.avancado.web.service;


import dev.fujioka.java.avancado.web.dto.ProfessorDTO;
import dev.fujioka.java.avancado.web.exception.EntidadeNaoEncontradaException;
import dev.fujioka.java.avancado.web.model.Professor;
import dev.fujioka.java.avancado.web.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Transactional
    public ProfessorDTO salvar(Professor professor){
        professor = professorRepository.save(professor);
        jmsTemplate.convertAndSend("nome_professor_queue", professor);
        return ProfessorDTO.builder()
                .nome(professor.getNome())
                .email(professor.getEmail())
                .build();
    }

    public List<Professor> listarProfessores(){
        return professorRepository.findAll();
    }

    public Professor consultarPorId(int id){
        return professorRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Não existe professor com o id: %d", id)));
    }

    @Transactional
    public void excluir(int id){

        try {
            this.consultarPorId(id);
            professorRepository.deleteById(id);
        }catch (EntidadeNaoEncontradaException e){
            throw new  EntidadeNaoEncontradaException(String.format("Não existe professor com o id: %d", id));
        }
    }

    @Transactional
    public ProfessorDTO alterar(Integer id, Professor novoProfessor){
        Professor professor = consultarPorId(id);
        this.atualizaProfessor(professor, novoProfessor);
        return this.salvar(professor);
    }

    private void atualizaProfessor(Professor professor, Professor novoProfessor) {
        professor.setNome(novoProfessor.getNome());
        professor.setEmail(novoProfessor.getEmail());
    }

}
