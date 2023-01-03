package dev.fujioka.java.avancado.web.resource;


import dev.fujioka.java.avancado.web.exception.EntidadeNaoEncontradaException;
import dev.fujioka.java.avancado.web.model.Curso;
import dev.fujioka.java.avancado.web.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoResource {
    @Autowired
    private CursoService cursoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Curso> salvar(@RequestBody Curso curso){

        return ResponseEntity.ok(cursoService.salvar(curso));

    }

    @GetMapping
    public ResponseEntity<List<Curso>> getCursos(){

        return ResponseEntity.ok(cursoService.listarCursos());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> consultaPorId(@PathVariable Integer id){

        try{
            return ResponseEntity.ok(cursoService.consultarPorId(id));
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Curso> deletePorId(@PathVariable int id){

        try {
            cursoService.excluir(id);
            return ResponseEntity.ok().build();
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> alterar(@PathVariable Integer id, @RequestBody Curso curso){

        try{
            return ResponseEntity.ok(cursoService.alterar(id, curso));
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }

    }


}
