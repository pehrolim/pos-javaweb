package dev.fujioka.java.avancado.web.repository;

import dev.fujioka.java.avancado.web.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {



}
