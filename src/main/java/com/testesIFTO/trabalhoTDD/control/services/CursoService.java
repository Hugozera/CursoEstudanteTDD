package com.testesIFTO.trabalhoTDD.control.services;

import com.testesIFTO.trabalhoTDD.model.entity.Curso;
import com.testesIFTO.trabalhoTDD.model.entity.TurmaCurso;
import com.testesIFTO.trabalhoTDD.model.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Component

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;


    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    public Curso obterCurso(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    public Curso cadastrarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public List<TurmaCurso> listarTurmasDoCurso(Long idCurso) {
        Curso curso = obterCurso(idCurso);
        if (curso != null) {
            return curso.getTurmas();
        }
        return null;
    }

    // Outros métodos conforme necessário
}
