package dev.fujioka.java.avancado.web.model;


import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Professor {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue
    private Integer id;

    private String nome;

    private String email;

}
