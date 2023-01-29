package dev.fujioka.java.avancado.web.service;


import dev.fujioka.java.avancado.web.dto.CursoDTO;
import dev.fujioka.java.avancado.web.exception.EntidadeNaoEncontradaException;
import dev.fujioka.java.avancado.web.model.Curso;
import dev.fujioka.java.avancado.web.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Transactional
    public CursoDTO salvar(Curso curso){
        curso = cursoRepository.save(curso);
        jmsTemplate.convertAndSend("curso_queue", curso);
        return CursoDTO.builder().nome(curso.getNome()).descricao(curso.getDescricao()).build();
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
    public CursoDTO alterar(Integer id, Curso novoCurso){
        Curso curso = consultarPorId(id);
        this.atualizaCurso(curso, novoCurso);
        return this.salvar(curso);
    }

    private void atualizaCurso(Curso curso, Curso novoCurso) {
        curso.setNome(novoCurso.getNome());
        curso.setDescricao(novoCurso.getDescricao());
    }

}
