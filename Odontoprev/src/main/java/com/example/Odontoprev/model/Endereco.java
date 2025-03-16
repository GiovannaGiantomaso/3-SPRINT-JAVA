package com.example.Odontoprev.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ENDERECO_PACIENTE")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ENDERECO")
    @SequenceGenerator(name = "SEQ_ENDERECO", sequenceName = "SEQ_ENDERECO", allocationSize = 1)
    @Column(name = "ID_ENDERECO")
    private Long id;

    @Column(name = "CEP", nullable = false, length = 10)
    private String cep;

    @Column(name = "NUMERO", nullable = false, length = 10)
    private String numero;

    @ManyToOne
    @JoinColumn(name = "ID_BAIRRO", referencedColumnName = "ID_BAIRRO", nullable = false)
    private Bairro bairro;

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
}


