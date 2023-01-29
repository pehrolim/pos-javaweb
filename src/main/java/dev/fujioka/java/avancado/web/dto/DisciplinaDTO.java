package dev.fujioka.java.avancado.web.dto;

import dev.fujioka.java.avancado.web.model.StatusDisciplina;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class DisciplinaDTO {

    private String nome;

    private String cargaHoraria;

    private String professor;

    private StatusDisciplina statusDisciplina;

    private String observacao;

}
