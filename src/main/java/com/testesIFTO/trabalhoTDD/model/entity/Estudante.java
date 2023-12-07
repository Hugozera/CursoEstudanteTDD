package com.testesIFTO.trabalhoTDD.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity

public class Estudante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idade")
    private Integer idade;


    private String nome;

    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "estudante", cascade = CascadeType.ALL)
    private List<Celular> celulares;

    @ManyToOne
    @JoinColumn(name = "turma_curso_id")
    private TurmaCurso turmaCurso;

    public Estudante() {
    }

    @PrePersist
    @PreUpdate
    public void calcularIdade() {
        if (dataNascimento != null) {
            idade = Period.between(dataNascimento, LocalDate.now()).getYears();
        }
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Celular> getCelulares() {
        return celulares;
    }

    public void setCelulares(List<Celular> celulares) {
        this.celulares = celulares;
    }

    public TurmaCurso getTurmaCurso() {
        return turmaCurso;
    }

    public Estudante(int i, int i1, String hugo, String number, String informatica) {
    }

    public Estudante(Long id, Integer idade, String nome,  TurmaCurso turmaCurso) {
        this.id = id;
        this.idade = idade;
        this.nome = nome;
        this.turmaCurso = turmaCurso;
    }

    public void setTurmaCurso(TurmaCurso turmaCurso) {
        this.turmaCurso = turmaCurso;
    }
}
