package dev.fujioka.java.avancado.web.resource;



import dev.fujioka.java.avancado.web.model.Disciplina;
import dev.fujioka.java.avancado.web.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaResource {

    @Autowired
    DisciplinaService disciplinaService;

    @PostMapping
    public ResponseEntity<Disciplina> salvar(@RequestBody Disciplina disciplina){
        return ResponseEntity.ok(disciplinaService.salvar(disciplina));
    }

    @GetMapping
    public ResponseEntity<List<Disciplina>> getDisciplinas(){
        return ResponseEntity.ok(disciplinaService.listarDisciplinas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> consultaPorId(@PathVariable int id){
        return ResponseEntity.ok(disciplinaService.consultarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Disciplina> deletePorId(@PathVariable int id){
        disciplinaService.excluir(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Disciplina> alterar(@RequestBody Disciplina disciplina){
        return ResponseEntity.ok(disciplinaService.alterar(disciplina));
    }

}
