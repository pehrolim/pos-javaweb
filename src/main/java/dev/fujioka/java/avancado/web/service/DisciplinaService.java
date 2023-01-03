package dev.fujioka.java.avancado.web.service;

import dev.fujioka.java.avancado.web.exception.EntidadeNaoEncontradaException;
import dev.fujioka.java.avancado.web.model.Disciplina;
import dev.fujioka.java.avancado.web.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {

    @Autowired
    DisciplinaRepository disciplinaRepository;

    public Disciplina salvar(Disciplina disciplina){
        return disciplinaRepository.save(disciplina);
    }

    public List<Disciplina> listarDisciplinas(){
        return disciplinaRepository.findAll();
    }

    public Disciplina consultarPorId(int id){
        return disciplinaRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Não existe disciplina com o id: %d", id)));
    }

    public void excluir(int id){

        try {
            consultarPorId(id);
            disciplinaRepository.deleteById(id);
        }catch (EntidadeNaoEncontradaException e){
            throw new  EntidadeNaoEncontradaException(String.format("Não existe disciplina com o id: %d", id));
        }
    }

    public Disciplina alterar(Integer id, Disciplina novaDisciplina){
        Disciplina disciplina = consultarPorId(id);
        atualizaDisciplina(disciplina, novaDisciplina);
        return this.salvar(disciplina);
    }

    private void atualizaDisciplina(Disciplina disciplina, Disciplina novaDisciplina){
        disciplina.setNome(novaDisciplina.getNome());
        disciplina.setCargaHoraria(novaDisciplina.getCargaHoraria());
        disciplina.setProfessor(novaDisciplina.getProfessor());
        disciplina.setObservacao(novaDisciplina.getObservacao());
    }


}
