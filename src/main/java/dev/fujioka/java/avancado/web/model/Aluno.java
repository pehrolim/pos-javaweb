package dev.fujioka.java.avancado.web.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Aluno implements Serializable {

    private static final long  serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue
    private Integer id;

    private String nome;

    private String matricula;

    private String curso;

}
