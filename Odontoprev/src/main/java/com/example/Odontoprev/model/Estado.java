package com.example.Odontoprev.model;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ESTADO_PACIENTE")
public class Estado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ESTADO_PACIENTE")
    @SequenceGenerator(name = "SEQ_ESTADO_PACIENTE", sequenceName = "SEQ_ESTADO_PACIENTE", allocationSize = 1)
    @Column(name = "ID_ESTADO")
    private Long id;

    @Column(name = "NOME_ESTADO", nullable = false, length = 100)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "ID_PAIS", nullable = false)
    private Pais pais;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Pais getPais() { return pais; }
    public void setPais(Pais pais) { this.pais = pais; }
}
