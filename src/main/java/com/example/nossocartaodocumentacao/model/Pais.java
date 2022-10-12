package com.example.nossocartaodocumentacao.model;

import javax.persistence.*;

@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UK_PAIS_NOME", columnNames = { "nome" })
})
@Entity
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    public Pais(String nome) {
        this.nome = nome;
    }

    @Deprecated
    public Pais() {
    }

    public Long getId() {
        return id;
    }
}
