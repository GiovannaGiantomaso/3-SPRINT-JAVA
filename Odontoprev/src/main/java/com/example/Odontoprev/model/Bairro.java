package com.example.Odontoprev.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "BAIRRO_PACIENTE")
@SequenceGenerator(name = "SEQ_BAIRRO_PACIENTE", sequenceName = "SEQ_BAIRRO_PACIENTE", allocationSize = 1)
public class Bairro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BAIRRO_PACIENTE")
    @Column(name = "ID_BAIRRO")
    private Long id;

    @Column(name = "NOME_BAIRRO", nullable = false, length = 100)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "ID_CIDADE", nullable = false)
    private Cidade cidade;
}
