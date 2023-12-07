package com.testesIFTO.trabalhoTDD.model.repositories;

import com.testesIFTO.trabalhoTDD.model.entity.Curso;
import com.testesIFTO.trabalhoTDD.model.entity.TurmaCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurmaCursoRepository extends JpaRepository<TurmaCurso, Long> {
    List<TurmaCurso> findByEstudantesIsNotNull();
    List<TurmaCurso> findByCurso(Curso curso);

}