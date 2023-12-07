package com.testesIFTO.trabalhoTDD.model.repositories;

import com.testesIFTO.trabalhoTDD.model.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    Curso findByNome(String nome);
}
