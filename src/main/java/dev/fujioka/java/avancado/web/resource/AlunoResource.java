package dev.fujioka.java.avancado.web.resource;

import dev.fujioka.java.avancado.web.exception.EntidadeNaoEncontradaException;
import dev.fujioka.java.avancado.web.model.Aluno;
import dev.fujioka.java.avancado.web.repository.AlunoRepository;
import dev.fujioka.java.avancado.web.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoResource {

    @Autowired
    private AlunoService alunoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Aluno> salvar(@RequestBody Aluno aluno){
        return ResponseEntity.ok(alunoService.salvar(aluno));
    }


   @GetMapping
   public ResponseEntity<List<Aluno>> getAlunos(){
        return ResponseEntity.ok(alunoService.listarAlunos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> consultaPorId(@PathVariable int id){
        try {
            return ResponseEntity.ok(alunoService.consultarPorId(id));
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Aluno> deletePorId(@PathVariable int id){
        try {
            alunoService.excluir(id);
            return ResponseEntity.ok().build();
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> alterar(@PathVariable Integer id, @RequestBody Aluno aluno){
        try {
            return ResponseEntity.ok(alunoService.alterar(id, aluno));
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/like/{nome}")
    public ResponseEntity<List<Aluno>> listarPorLike(@PathVariable String nome){
        return ResponseEntity.ok(alunoService.buscarAlunoLike(nome));
    }


}
