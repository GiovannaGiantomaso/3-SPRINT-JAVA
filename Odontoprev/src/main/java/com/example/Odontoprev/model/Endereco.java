package com.example.Odontoprev.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ENDERECO_PACIENTE")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ENDERECO_PACIENTE")
    @SequenceGenerator(name = "SEQ_ENDERECO_PACIENTE", sequenceName = "SEQ_ENDERECO_PACIENTE", allocationSize = 1)
    @Column(name = "ID_ENDERECO")
    private Long id;

    @Column(name = "CEP", nullable = false, length = 10)
    private String cep;

    @Column(name = "NUMERO", nullable = false, length = 10)
    private String numero;

    @ManyToOne
    @JoinColumn(name = "ID_BAIRRO", referencedColumnName = "ID_BAIRRO", nullable = false)
    private Bairro bairro;

    @ManyToOne
    @JoinColumn(name = "ID_CIDADE", referencedColumnName = "ID_CIDADE", nullable = false)
    private Cidade cidade;

    @ManyToOne
    @JoinColumn(name = "ID_ESTADO", referencedColumnName = "ID_ESTADO", nullable = false)
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "ID_PAIS", referencedColumnName = "ID_PAIS", nullable = false)
    private Pais pais;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
}
