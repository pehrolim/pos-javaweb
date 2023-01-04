package dev.fujioka.java.avancado.web.service;

import dev.fujioka.java.avancado.web.exception.EntidadeNaoEncontradaException;
import dev.fujioka.java.avancado.web.model.Aluno;
import dev.fujioka.java.avancado.web.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Transactional
    public Aluno salvar(Aluno aluno){
        return alunoRepository.save(aluno);
    }


    public List<Aluno> listarAlunos(){
        return alunoRepository.findAll();
    }


    public Aluno consultarPorId(int id){
        return alunoRepository.findById(id).orElseThrow(() -> new  EntidadeNaoEncontradaException(String.format("Não existe aluno com o id: %d", id)));
    }

    @Transactional
    public void excluir(int id){

        try {
            this.consultarPorId(id);
            alunoRepository.deleteById(id);
        }catch (EntidadeNaoEncontradaException e){
            throw new  EntidadeNaoEncontradaException(String.format("Não existe aluno com o id: %d", id));
        }
    }

    @Transactional
    public Aluno alterar(Integer id, Aluno novoAluno){
        Aluno aluno = consultarPorId(id);
        this.atualizaAluno(aluno, novoAluno);
        return this.salvar(aluno);
    }

    public List<Aluno> buscarAlunoLike(String nome){
        return alunoRepository.buscarAlunoPorNomeLike(nome);
    }

    private void atualizaAluno(Aluno aluno, Aluno novoAluno){
        aluno.setNome(novoAluno.getNome());
        aluno.setMatricula(novoAluno.getMatricula());
        aluno.setCurso(novoAluno.getCurso());
    }

}
