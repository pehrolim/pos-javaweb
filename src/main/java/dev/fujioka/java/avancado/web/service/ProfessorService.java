package dev.fujioka.java.avancado.web.service;


import dev.fujioka.java.avancado.web.exception.EntidadeNaoEncontradaException;
import dev.fujioka.java.avancado.web.model.Professor;
import dev.fujioka.java.avancado.web.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    public Professor salvar(Professor professor){
        return professorRepository.save(professor);
    }

    public List<Professor> listarProfessores(){
        return professorRepository.findAll();
    }

    public Professor consultarPorId(int id){
        return professorRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Não existe professor com o id: %d", id)));
    }

    public void excluir(int id){

        try {
            consultarPorId(id);
            professorRepository.deleteById(id);
        }catch (EntidadeNaoEncontradaException e){
            throw new  EntidadeNaoEncontradaException(String.format("Não existe professor com o id: %d", id));
        }
    }

    public Professor alterar(Integer id, Professor novoProfessor){
        Professor professor = consultarPorId(id);
        atualizaProfessor(professor, novoProfessor);
        return this.salvar(professor);
    }

    private void atualizaProfessor(Professor professor, Professor novoProfessor) {
        professor.setNome(novoProfessor.getNome());
        professor.setEmail(novoProfessor.getEmail());
    }

}
