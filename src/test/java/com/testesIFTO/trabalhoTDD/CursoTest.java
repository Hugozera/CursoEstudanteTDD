package com.testesIFTO.trabalhoTDD;


import com.testesIFTO.trabalhoTDD.model.entity.Curso;
import com.testesIFTO.trabalhoTDD.model.entity.TurmaCurso;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CursoTest {

    @Test
    void testeDeCadastroDeCurso() {

        Curso curso = new Curso();
        curso.setId(1L);
        curso.setNome("Curso de Teste");

        TurmaCurso turmaCurso = new TurmaCurso();
        turmaCurso.setId(1L);
        turmaCurso.setCurso(curso);

        curso.setTurmas(Collections.singletonList(turmaCurso));

        assertEquals(1L, curso.getId());
        assertEquals("Curso de Teste", curso.getNome());
        assertEquals(Collections.singletonList(turmaCurso), curso.getTurmas());

        Curso novoCurso = new Curso();
        novoCurso.setId(2L);
        novoCurso.setNome("Novo Curso de Teste");
        novoCurso.setTurmas(Collections.emptyList());

        assertEquals(2L, novoCurso.getId());
        assertEquals("Novo Curso de Teste", novoCurso.getNome());
        assertEquals(Collections.emptyList(), novoCurso.getTurmas());
    }
}
