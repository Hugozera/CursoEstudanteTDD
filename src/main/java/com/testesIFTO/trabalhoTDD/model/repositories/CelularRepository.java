package com.testesIFTO.trabalhoTDD.model.repositories;

import com.testesIFTO.trabalhoTDD.model.entity.Celular;
import com.testesIFTO.trabalhoTDD.model.entity.Estudante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CelularRepository extends JpaRepository<Celular, Long> {
    List<Celular> findByEstudante(Estudante estudante);
}
