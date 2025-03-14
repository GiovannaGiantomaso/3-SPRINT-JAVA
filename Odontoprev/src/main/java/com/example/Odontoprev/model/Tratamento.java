package com.example.Odontoprev.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TRATAMENTO")
@SequenceGenerator(name = "SEQ_TRATAMENTO", sequenceName = "SEQ_TRATAMENTO", allocationSize = 1)
public class Tratamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TRATAMENTO")
    @Column(name = "ID_TRATAMENTO")
    private Long id;

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    @Column(name = "TIPO", nullable = false)
    private String tipo;

    @Column(name = "CUSTO", nullable = false)
    private Double custo;

    public Tratamento() {}

    public Tratamento(String descricao, String tipo, Double custo) {
        this.descricao = descricao;
        this.tipo = tipo;
        this.custo = custo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Double getCusto() { return custo; }
    public void setCusto(Double custo) { this.custo = custo; }
}
