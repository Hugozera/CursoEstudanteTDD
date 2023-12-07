package com.testesIFTO.trabalhoTDD.model.entity;

    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;

    import java.util.List;

@Entity
@Getter
@Setter
    public class Curso {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nome;


         @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
         private List<TurmaCurso> turmas;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<TurmaCurso> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<TurmaCurso> turmas) {
        this.turmas = turmas;
    }
}

