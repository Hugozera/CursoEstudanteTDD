package com.testesIFTO.trabalhoTDD.control.services;

import com.testesIFTO.trabalhoTDD.model.entity.Curso;
import com.testesIFTO.trabalhoTDD.model.entity.Estudante;
import com.testesIFTO.trabalhoTDD.model.entity.TurmaCurso;
import com.testesIFTO.trabalhoTDD.model.repositories.EstudanteRepository;
import com.testesIFTO.trabalhoTDD.model.repositories.TurmaCursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.JdbcAggregateOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaCursoService {

    @Autowired
    private TurmaCursoRepository turmaCursoRepository;

    @Autowired
    EstudanteRepository estudanteRepository;
    @Autowired
    private CursoService cursoService;

    // Outras dependências, se houver

    public List<TurmaCurso> listarTurmas() {
        return turmaCursoRepository.findAll();
    }

    public TurmaCurso obterTurma(Long id) {
        return turmaCursoRepository.findById(id).orElse(null);
    }

    public TurmaCurso cadastrarTurma(TurmaCurso turmaCurso) {
        return turmaCursoRepository.save(turmaCurso);
    }

    public List<Curso> listarCursos() {
        return cursoService.listarCursos();
    }

    public Curso obterCurso(Long id) {
        return cursoService.obterCurso(id);
    }

    public List<TurmaCurso> listarTurmasPorCurso(Long cursoId) {

        return null;
    }

    public void adicionarEstudantes(Long turmaCursoId, List<Long> estudantesId) {
        TurmaCurso turmaCurso = turmaCursoRepository.findById(turmaCursoId)
                .orElseThrow(() -> new IllegalArgumentException("TurmaCurso não encontrada"));

        EstudanteRepository estudanteRepository = null;
        List<Estudante> estudantes = estudanteRepository.findAllById(estudantesId);
        turmaCurso.getEstudantes().addAll(estudantes);
        turmaCursoRepository.save(turmaCurso);
    }
    public void associarCursoATurma(Long turmaId, Long cursoId) {
        TurmaCurso turma = obterTurma(turmaId);
        Curso curso = cursoService.obterCurso(cursoId);

        if (turma != null && curso != null) {
            turma.setCurso(curso);
            turmaCursoRepository.save(turma);
        } else {
            throw new IllegalArgumentException("Turma ou curso não encontrados");
        }
    }
    public TurmaCurso findById(Long id) {
        return turmaCursoRepository.findById(id).orElse(null);
    }

    public List<TurmaCurso> findAll() {
        return turmaCursoRepository.findAll();
    }

    public void adicionarEstudante(Long turmaId, Long estudanteId) {
        TurmaCurso turmaCurso = findById(turmaId);
        if (turmaCurso != null) {
            EstudanteService estudanteService = null;
            Estudante estudante = estudanteService.findById(estudanteId);
            if (estudante != null) {
                turmaCurso.getEstudantes().add(estudante);
                turmaCursoRepository.save(turmaCurso);
            } else {
                // Lógica de tratamento se o estudante não for encontrado
            }
        } else {
            // Lógica de tratamento se a turma não for encontrada
        }
    }
    public TurmaCurso obterTurmaPorId(Long turmaId) {
        // Lógica para recuperar a turma pelo ID do banco de dados
        return turmaCursoRepository.findById(turmaId).orElse(null);
    }

    public Estudante obterEstudantePorId(Long estudanteId) {
        // Lógica para recuperar o estudante pelo ID do banco de dados
        return estudanteRepository.findById(estudanteId).orElse(null);
    }

    public void salvarRelacaoTurmaEstudante(TurmaCurso turma, Estudante estudante) {
        if (turma != null && estudante != null) {
            turma.adicionarEstudante(estudante);
            turmaCursoRepository.save(turma);
        }
    }
}
