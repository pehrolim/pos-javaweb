package dev.fujioka.java.avancado.web.resource;



import dev.fujioka.java.avancado.web.exception.EntidadeNaoEncontradaException;
import dev.fujioka.java.avancado.web.model.Professor;
import dev.fujioka.java.avancado.web.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorResource {

    @Autowired
    ProfessorService professorService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Professor> salvar(@RequestBody Professor professor){
        return ResponseEntity.ok(professorService.salvar(professor));
    }

    @GetMapping
    public ResponseEntity<List<Professor>> getProfessores(){
        return ResponseEntity.ok(professorService.listarProfessores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> consultaPorId(@PathVariable int id){
        try{
            return ResponseEntity.ok(professorService.consultarPorId(id));
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Professor> deletePorId(@PathVariable int id){
       try{
           professorService.excluir(id);
           return ResponseEntity.ok().build();
       }catch (EntidadeNaoEncontradaException e){
           return ResponseEntity.notFound().build();
       }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> alterar(@PathVariable Integer id, @RequestBody Professor professor){
        try {
            return ResponseEntity.ok(professorService.alterar(id, professor));
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }
    }
}
