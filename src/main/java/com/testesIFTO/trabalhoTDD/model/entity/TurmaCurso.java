package com.testesIFTO.trabalhoTDD.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class TurmaCurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "inicio_matriculas")
    private String inicioMatriculas;

    @Column(name = "fim_matriculas")
    private String fimMatriculas;

    private int vagas;
    private int vagasDisponiveis;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @ManyToMany
    @JoinTable(name = "turma_estudante",
            joinColumns = @JoinColumn(name = "turma_id"),
            inverseJoinColumns = @JoinColumn(name = "estudante_id"))
    private List<Estudante> estudantes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getInicioMatriculasAsDate() {
        return LocalDate.parse(this.inicioMatriculas, DateTimeFormatter.ISO_DATE);
    }
    public void adicionarEstudante(Estudante estudante) {
        if (this.estudantes == null) {
            this.estudantes = new ArrayList<>();
        }
        this.estudantes.add(estudante);
    }

    public void removerEstudante(Estudante estudante) {
        if (this.estudantes != null) {
            this.estudantes.remove(estudante);
        }
    }
    public void setInicioMatriculas(String inicioMatriculas) {
        this.inicioMatriculas = inicioMatriculas;
    }

    public String getFimMatriculas() {
        return fimMatriculas;
    }

    public void setFimMatriculas(String fimMatriculas) {
        this.fimMatriculas = fimMatriculas;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    public int getVagasDisponiveis() {
        return vagasDisponiveis;
    }

    public void setVagasDisponiveis(int vagasDisponiveis) {
        this.vagasDisponiveis = vagasDisponiveis;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Estudante> getEstudantes() {
        return estudantes;
    }

    public void setEstudantes(List<Estudante> estudantes) {
        this.estudantes = estudantes;
    }
}
