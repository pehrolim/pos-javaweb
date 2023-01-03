package dev.fujioka.java.avancado.web.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Aluno {

    public Aluno(){

    }
    public Aluno(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue
    private Integer id;

    private String nome;

    private String matricula;

    private String curso;

}
