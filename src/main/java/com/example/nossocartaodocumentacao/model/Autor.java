package com.example.nossocartaodocumentacao.model;

import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;

@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UK_AUTOR_EMAIL", columnNames = { "email" })
})
@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 400)
    private String descricao;

    @CreationTimestamp
    private LocalDateTime registradoEm;

    public Autor(String nome, String email, String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    @Deprecated
    public Autor() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
