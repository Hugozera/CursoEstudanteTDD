package com.testesIFTO.trabalhoTDD;

import com.testesIFTO.trabalhoTDD.model.entity.Estudante;
import com.testesIFTO.trabalhoTDD.model.entity.TurmaCurso;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TurmaCursoTest {

    @Test
    void testeAdicionaCursoEalunoACurso() {
        TurmaCurso turmaCurso = new TurmaCurso();
        turmaCurso.setInicioMatriculas("2023-01-01");

        assertEquals(LocalDate.of(2023, 1, 1), turmaCurso.getInicioMatriculasAsDate());
    }

    @Test
    void adicionarERemoverEstudanteTest() {
        TurmaCurso turmaCurso = new TurmaCurso();

        Estudante estudante1 = new Estudante(1, 22, "hugo", "2001", "informatica");
        estudante1.setId(1L);
        estudante1.setNome("João");

        Estudante estudante2 = new Estudante(1, 22, "hugo", "2001", "informatica");
        estudante2.setId(2L);
        estudante2.setNome("Maria");

        turmaCurso.adicionarEstudante(estudante1);
        turmaCurso.adicionarEstudante(estudante2);

        assertNotNull(turmaCurso.getEstudantes());
        assertEquals(2, turmaCurso.getEstudantes().size());
        assertTrue(turmaCurso.getEstudantes().contains(estudante1));
        assertTrue(turmaCurso.getEstudantes().contains(estudante2));

        turmaCurso.removerEstudante(estudante1);

        assertEquals(1, turmaCurso.getEstudantes().size());
        assertTrue(turmaCurso.getEstudantes().contains(estudante2));
        assertFalse(turmaCurso.getEstudantes().contains(estudante1));
    }
}
