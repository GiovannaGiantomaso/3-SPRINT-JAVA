package com.example.Odontoprev.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ENDERECO_PACIENTE")
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ENDERECO_PACIENTE")
    @SequenceGenerator(name = "SEQ_ENDERECO_PACIENTE", sequenceName = "SEQ_ENDERECO_PACIENTE", allocationSize = 1)
    @Column(name = "ID_ENDERECO")
    private Long id;

    @Column(name = "CEP", length = 10, nullable = false)
    private String cep;

    @Column(name = "NUMERO", length = 10, nullable = false)
    private String numero;

    @ManyToOne
    @JoinColumn(name = "ID_BAIRRO", referencedColumnName = "ID_BAIRRO")
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
