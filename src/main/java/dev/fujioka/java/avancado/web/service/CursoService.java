package dev.fujioka.java.avancado.web.service;


import dev.fujioka.java.avancado.web.model.Curso;
import dev.fujioka.java.avancado.web.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    public Curso salvar(Curso curso){
        return cursoRepository.save(curso);
    }

    public List<Curso> listarCursos(){
        return cursoRepository.findAll();
    }

    public Curso consultarPorId(int id){
        return cursoRepository.findById(id).orElseThrow();
    }

    public void excluir(int id){
        cursoRepository.deleteById(id);
    }

    public Curso alterar(Curso curso){
        if(Objects.isNull(curso.getId())){
            throw new RuntimeException("ID não preenchido");
        }
        return cursoRepository.save(curso);
    }

}
