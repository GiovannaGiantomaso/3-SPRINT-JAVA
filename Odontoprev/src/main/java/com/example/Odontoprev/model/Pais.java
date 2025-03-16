package com.example.Odontoprev.model;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PAIS_PACIENTE")
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PAIS_PACIENTE")
    @SequenceGenerator(name = "SEQ_PAIS_PACIENTE", sequenceName = "SEQ_PAIS_PACIENTE", allocationSize = 1)
    @Column(name = "ID_PAIS")
    private Long id;

    @Column(name = "NOME_PAIS", nullable = false)
    private String nome;

    // 🔹 Construtor sem argumentos (necessário para o JPA)
    public Pais() {}

    // 🔹 Construtor correto para a criação no código
    public Pais(String nome) {
        this.nome = nome;
    }

    // 🔹 Getters e Setters
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
}

