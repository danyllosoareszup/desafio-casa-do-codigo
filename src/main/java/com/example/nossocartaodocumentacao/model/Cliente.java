package com.example.nossocartaodocumentacao.model;

import com.example.nossocartaodocumentacao.util.CpfOrCnpj;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UK_CLIENTE_EMAIL", columnNames = { "email" }),
        @UniqueConstraint(name = "UK_CLIENTE_DOCUMENTO", columnNames = { "cpf_cnpj" })
})
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sobrenome;

    @Column(nullable = false)
    private String email;

    @NotBlank
    @CpfOrCnpj
    @Column(name = "cpf_cnpj")
    private String cpfOuCnpj;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String complemento;

    @Pattern(regexp = "^\\+[1-9][0-9]\\d{1,14}")
    @NotNull
    private String telefone;

    @Column(nullable = false)
    private String cep;

    @NotBlank
    private String cidade;

    @ManyToOne
    private Pais pais;

    @ManyToOne(optional = true)
    private Estado estado;


    public Cliente(String nome, String sobrenome, String email, String cpfOuCnpj, String endereco,
                   String complemento, String telefone, String cep, String cidade, Estado estado, Pais pais)
    {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.endereco = endereco;
        this.complemento = complemento;
        this.telefone = telefone;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
    }

    public Cliente() {
    }

    public Long getId() {
        return id;
    }
}
