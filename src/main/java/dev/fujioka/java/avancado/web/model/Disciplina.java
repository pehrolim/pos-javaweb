package dev.fujioka.java.avancado.web.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Disciplina implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue
    private Integer id;

    private String nome;

    @Column(name = "carga_horaria")
    private String cargaHoraria;

    private String professor;

    @Column(name = "status")
    private StatusDisciplina statusDisciplina;

    private String observacao;


}
