package dev.fujioka.java.avancado.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProfessorDTO {

    private String nome;

    private String email;

}
