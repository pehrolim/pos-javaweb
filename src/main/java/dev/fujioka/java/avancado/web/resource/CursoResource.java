package dev.fujioka.java.avancado.web.resource;


import dev.fujioka.java.avancado.web.model.Curso;
import dev.fujioka.java.avancado.web.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curso")
public class CursoResource {
    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<Curso> salvar(@RequestBody Curso curso){
        return ResponseEntity.ok(cursoService.salvar(curso));
    }

    @GetMapping
    public ResponseEntity<List<Curso>> getCursos(){
        return ResponseEntity.ok(cursoService.listarCursos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> consultaPorId(@PathVariable int id){
        return ResponseEntity.ok(cursoService.consultarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Curso> deletePorId(@PathVariable int id){
        cursoService.excluir(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Curso> alterar(@RequestBody Curso curso){
        return ResponseEntity.ok(cursoService.alterar(curso));
    }


}
