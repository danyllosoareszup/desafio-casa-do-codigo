package com.example.nossocartaodocumentacao.controller.request;
import com.example.nossocartaodocumentacao.model.Autor;
import com.example.nossocartaodocumentacao.model.Categoria;
import com.example.nossocartaodocumentacao.model.Livro;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.Length;
import javax.persistence.Column;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public record LivroRequest(

        @NotBlank
        String titulo,

        @NotBlank
        @Length(max = 500)
        String resumo,

        String sumario,

        @NotNull
        @Min(20)
        BigDecimal preco,

        @Column(nullable = false)
        @Min(100)
        int numeroDePaginas,

        @ISBN
        String isbn,

        @Future
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataPublicacao,

        @NotNull
        Long categoriaId,

        @NotNull
        Long autorId
) {

    public Livro toModel(Categoria categoria, Autor autor) {
        return new Livro(this.titulo, this.resumo, this.sumario, this.preco, this.numeroDePaginas, this.isbn, this.dataPublicacao, categoria, autor);
    }
}
