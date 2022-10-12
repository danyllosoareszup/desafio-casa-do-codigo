package com.example.nossocartaodocumentacao.model;

import org.hibernate.validator.constraints.ISBN;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;

@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UK_LIVRO_TITULO", columnNames = { "titulo" }),
        @UniqueConstraint(name = "UK_LIVRO_ISBN", columnNames = { "isbn" })
})
@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, length = 500)
    private String resumo;

    private String sumario;

    @Column(nullable = false)
    @Min(20)
    private BigDecimal preco;

    @Column(nullable = false)
    @Min(100)
    private Integer numeroDePaginas;

    @ISBN
    private String isbn;

    @Future
    private LocalDate dataPublicacao;

    @ManyToOne
    private Categoria categoria;

    @ManyToOne
    private Autor autor;

    public Livro(String titulo, String resumo, String sumario, BigDecimal preco, int numeroDePaginas,
                 String isbn, LocalDate dataPublicacao, Categoria categoria, Autor autor) {

        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroDePaginas = numeroDePaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.categoria = categoria;
        this.autor = autor;
    }

    public Livro() {
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public @Min(100) Integer getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Autor getAutor() {
        return autor;
    }
}
