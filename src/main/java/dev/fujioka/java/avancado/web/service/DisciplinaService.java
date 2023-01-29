package dev.fujioka.java.avancado.web.service;

import dev.fujioka.java.avancado.web.dto.DisciplinaDTO;
import dev.fujioka.java.avancado.web.exception.EntidadeNaoEncontradaException;
import dev.fujioka.java.avancado.web.model.Disciplina;
import dev.fujioka.java.avancado.web.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DisciplinaService {

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Transactional
    public DisciplinaDTO salvar(Disciplina disciplina){
        disciplinaRepository.save(disciplina);
        jmsTemplate.convertAndSend("disciplina_queue", disciplina);
        return DisciplinaDTO.builder().nome(disciplina.getNome())
                .professor(disciplina.getProfessor())
                .statusDisciplina(disciplina.getStatusDisciplina())
                .cargaHoraria(disciplina.getCargaHoraria())
                .observacao(disciplina.getObservacao())
                .build();
    }

    public List<Disciplina> listarDisciplinas(){
        return disciplinaRepository.findAll();
    }

    public Disciplina consultarPorId(int id){
        return disciplinaRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Não existe disciplina com o id: %d", id)));
    }

    @Transactional
    public void excluir(int id){

        try {
            this.consultarPorId(id);
            disciplinaRepository.deleteById(id);
        }catch (EntidadeNaoEncontradaException e){
            throw new  EntidadeNaoEncontradaException(String.format("Não existe disciplina com o id: %d", id));
        }
    }

    @Transactional
    public DisciplinaDTO alterar(Integer id, Disciplina novaDisciplina){
        Disciplina disciplina = consultarPorId(id);
        this.atualizaDisciplina(disciplina, novaDisciplina);
        return this.salvar(disciplina);
    }

    private void atualizaDisciplina(Disciplina disciplina, Disciplina novaDisciplina){
        disciplina.setNome(novaDisciplina.getNome());
        disciplina.setCargaHoraria(novaDisciplina.getCargaHoraria());
        disciplina.setProfessor(novaDisciplina.getProfessor());
        disciplina.setObservacao(novaDisciplina.getObservacao());
    }


}
