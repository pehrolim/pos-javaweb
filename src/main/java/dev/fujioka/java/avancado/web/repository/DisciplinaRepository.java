package dev.fujioka.java.avancado.web.repository;


import dev.fujioka.java.avancado.web.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {
}
