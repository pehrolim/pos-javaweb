package dev.fujioka.java.avancado.web.resource;



import dev.fujioka.java.avancado.web.dto.DisciplinaDTO;
import dev.fujioka.java.avancado.web.exception.EntidadeNaoEncontradaException;
import dev.fujioka.java.avancado.web.model.Disciplina;
import dev.fujioka.java.avancado.web.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaResource {

    @Autowired
    DisciplinaService disciplinaService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public DisciplinaDTO salvar(@RequestBody Disciplina disciplina){
        return disciplinaService.salvar(disciplina);
    }

    @GetMapping
    public ResponseEntity<List<Disciplina>> getDisciplinas(){
        return ResponseEntity.ok(disciplinaService.listarDisciplinas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> consultaPorId(@PathVariable int id){
        try {
            return ResponseEntity.ok(disciplinaService.consultarPorId(id));
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Disciplina> deletePorId(@PathVariable int id){
        try {
            disciplinaService.excluir(id);
            return ResponseEntity.ok().build();
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplinaDTO> alterar(@PathVariable Integer id,@RequestBody Disciplina disciplina){
        try {
            return ResponseEntity.ok(disciplinaService.alterar(id, disciplina));
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }
    }

}
