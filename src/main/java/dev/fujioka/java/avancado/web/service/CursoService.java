package dev.fujioka.java.avancado.web.service;


import dev.fujioka.java.avancado.web.exception.EntidadeNaoEncontradaException;
import dev.fujioka.java.avancado.web.model.Curso;
import dev.fujioka.java.avancado.web.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    @Transactional
    public Curso salvar(Curso curso){
        return cursoRepository.save(curso);
    }

    public List<Curso> listarCursos(){
        return cursoRepository.findAll();
    }

    public Curso consultarPorId(int id){

        return cursoRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Não existe curso com o id: %d", id)));

    }

    @Transactional
    public void excluir(int id){
        try {
            this.consultarPorId(id);
            cursoRepository.deleteById(id);
        }catch (EntidadeNaoEncontradaException e){
            throw new  EntidadeNaoEncontradaException(String.format("Não existe curso com o id: %d", id));
        }

    }

    @Transactional
    public Curso alterar(Integer id, Curso novoCurso){
        Curso curso = consultarPorId(id);
        this.atualizaCurso(curso, novoCurso);
        return this.salvar(curso);
    }

    private void atualizaCurso(Curso curso, Curso novoCurso) {
        curso.setNome(novoCurso.getNome());
        curso.setDescricao(novoCurso.getDescricao());
    }

}
