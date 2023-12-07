package com.testesIFTO.trabalhoTDD.control.services;

import com.testesIFTO.trabalhoTDD.model.entity.Estudante;
import com.testesIFTO.trabalhoTDD.model.repositories.EstudanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class EstudanteService {

    @Autowired
    private EstudanteRepository estudanteRepository;

    public List<Estudante> encontrarPorIdade(int idade) {
        return estudanteRepository.findByIdadeGreaterThanEqual(idade);
    }

    public Estudante findById(Long id) {
        return estudanteRepository.findById(id).orElse(null);
    }

    public Estudante salvarEstudante(Estudante estudante) {
        // Calcula a idade a partir da data de nascimento
        LocalDate dataNascimento = estudante.getDataNascimento();
        if (dataNascimento != null) {
            int idade = Period.between(dataNascimento, LocalDate.now()).getYears();
            estudante.setIdade(idade);
        }

        // Salva o estudante
        return estudanteRepository.save(estudante);
    }

    public List<Estudante> listarEstudantes() {
        return estudanteRepository.findAll();
    }

    public Estudante obterEstudante(Long id) {
        return estudanteRepository.findById(id).orElse(null);
    }

    public Estudante cadastrarEstudante(Estudante estudante) {
        return estudanteRepository.save(estudante);
    }
}
