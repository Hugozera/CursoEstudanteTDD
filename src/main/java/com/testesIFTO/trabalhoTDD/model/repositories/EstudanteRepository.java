package com.testesIFTO.trabalhoTDD.model.repositories;

import com.testesIFTO.trabalhoTDD.model.entity.Estudante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudanteRepository extends JpaRepository<Estudante, Long> {
    Estudante findByNome(String nome);

    @Query("SELECT e FROM Estudante e WHERE FUNCTION('DATEDIFF', YEAR, e.dataNascimento, CURRENT_DATE) >= :idade")
    List<Estudante> findByIdadeGreaterThanEqual(@Param("idade") int idade);

    List<Estudante> findByCelularesIsNotNull();
}
