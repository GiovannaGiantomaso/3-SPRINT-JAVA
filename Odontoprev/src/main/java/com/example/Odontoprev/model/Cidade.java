package com.example.Odontoprev.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CIDADE_PACIENTE")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CIDADE_PACIENTE")
    @SequenceGenerator(name = "SEQ_CIDADE_PACIENTE", sequenceName = "SEQ_CIDADE_PACIENTE", allocationSize = 1)
    @Column(name = "ID_CIDADE")
    private Long id;

    @Column(name = "NOME_CIDADE", nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "ID_ESTADO")
    private Estado estado;

    public Cidade() {}

    public Cidade(String nome, Estado estado) {
        this.nome = nome;
        this.estado = estado;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}

