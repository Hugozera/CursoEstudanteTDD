package com.testesIFTO.trabalhoTDD;

import com.testesIFTO.trabalhoTDD.model.entity.Celular;
import com.testesIFTO.trabalhoTDD.model.entity.Curso;
import com.testesIFTO.trabalhoTDD.model.entity.Estudante;
import com.testesIFTO.trabalhoTDD.model.entity.TurmaCurso;
import com.testesIFTO.trabalhoTDD.model.repositories.CursoRepository;
import com.testesIFTO.trabalhoTDD.model.repositories.EstudanteRepository;
import com.testesIFTO.trabalhoTDD.model.repositories.TurmaCursoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EstudanteTest {

    @Mock
    private TurmaCursoRepository turmaCursoRepository;

    @Mock
    private EstudanteRepository estudanteRepository;

    @Mock
    private CursoRepository cursoRepository;

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


    @Test
    void calcularIdade() {
        Estudante estudante = new Estudante(1, 22, "hugo", "2001", "informatica");
        estudante.setDataNascimento(LocalDate.now().minusYears(20));

        estudante.calcularIdade();

        assertEquals(20, estudante.getIdade());
    }

@Test
    void dadosObrigatorios() {

        Estudante estudante = new Estudante(1, 22, "hugo", "2001", "informatica");
        estudante.setId(1L);
        estudante.setNome("João");
        estudante.setIdade(20);
        estudante.setDataNascimento(LocalDate.of(2002, 1, 1));


        Celular celular = new Celular();
        celular.setId(1L);
        celular.setNumero("123456789");
        celular.setEstudante(estudante);

        estudante.setCelulares(Collections.singletonList(celular));


        TurmaCurso turmaCurso = new TurmaCurso();
        turmaCurso.setId(1L);
        turmaCurso.setInicioMatriculas("2023-01-01");
        turmaCurso.setFimMatriculas("2023-01-15");
        turmaCurso.setVagas(30);
        turmaCurso.setVagasDisponiveis(10);
        turmaCurso.setCurso(new Curso());

        estudante.setTurmaCurso(turmaCurso);


        assertEquals(1L, estudante.getId());
        assertEquals("João", estudante.getNome());
        assertEquals(20, estudante.getIdade());
        assertEquals(LocalDate.of(2002, 1, 1), estudante.getDataNascimento());
        assertEquals(Collections.singletonList(celular), estudante.getCelulares());
        assertEquals(turmaCurso, estudante.getTurmaCurso());


        Estudante novoEstudante = new Estudante(1, 22, "hugo", "2001", "informatica");
        novoEstudante.setId(2L);
        novoEstudante.setNome("Maria");
        novoEstudante.setIdade(22);
        novoEstudante.setDataNascimento(LocalDate.of(2000, 5, 10));

        Celular novoCelular = new Celular();
        novoCelular.setId(2L);
        novoCelular.setNumero("987654321");
        novoCelular.setEstudante(novoEstudante);

        novoEstudante.setCelulares(Collections.singletonList(novoCelular));

        TurmaCurso novaTurmaCurso = new TurmaCurso();
        novaTurmaCurso.setId(2L);
        novaTurmaCurso.setInicioMatriculas("2023-02-01");
        novaTurmaCurso.setFimMatriculas("2023-02-15");
        novaTurmaCurso.setVagas(25);
        novaTurmaCurso.setVagasDisponiveis(5);
        novaTurmaCurso.setCurso(new Curso());

        novoEstudante.setTurmaCurso(novaTurmaCurso);

        assertEquals(2L, novoEstudante.getId());
        assertEquals("Maria", novoEstudante.getNome());
        assertEquals(22, novoEstudante.getIdade());
        assertEquals(LocalDate.of(2000, 5, 10), novoEstudante.getDataNascimento());
        assertEquals(Collections.singletonList(novoCelular), novoEstudante.getCelulares());
        assertEquals(novaTurmaCurso, novoEstudante.getTurmaCurso());
    }

}