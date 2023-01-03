package dev.fujioka.java.avancado.web.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Disciplina {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue
    private Integer id;

    private String nome;

    @Column(name = "carga_horaria")
    private String cargaHoraria;

    private String professor;

    @Enumerated(EnumType.STRING)
    private StatusDisciplina statusDisciplina;

    private String observacao;


}
