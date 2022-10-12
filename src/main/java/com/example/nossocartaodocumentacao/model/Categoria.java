package com.example.nossocartaodocumentacao.model;

import javax.persistence.*;

@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UK_CATEGORIA_NOME", columnNames = { "nome" })
})
@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    public Categoria(String nome) {
        this.nome = nome;
    }

    @Deprecated
    public Categoria() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
