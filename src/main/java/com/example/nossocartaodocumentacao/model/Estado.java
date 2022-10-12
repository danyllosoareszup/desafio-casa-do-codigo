package com.example.nossocartaodocumentacao.model;

import javax.persistence.*;

@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UK_ESTATO_NOME_ID", columnNames = { "nome", "pais_id" })
})
@Entity
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    private Pais pais;

    public Estado(String nome, Pais pais) {
        this.nome = nome;
        this.pais = pais;
    }

    @Deprecated
    public Estado() {
    }

    public Long getId() {
        return id;
    }
}
