package com.example.Odontoprev.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "BAIRRO_PACIENTE")
public class Bairro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BAIRRO_PACIENTE")
    @SequenceGenerator(name = "SEQ_BAIRRO_PACIENTE", sequenceName = "SEQ_BAIRRO_PACIENTE", allocationSize = 1)
    @Column(name = "ID_BAIRRO")
    private Long id;

    @Column(name = "NOME_BAIRRO", nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "ID_CIDADE")
    private Cidade cidade;

    public Bairro() {}

    public Bairro(String nome, Cidade cidade) {
        this.nome = nome;
        this.cidade = cidade;
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}

