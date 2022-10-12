package com.example.nossocartaodocumentacao.controller.response;

import com.example.nossocartaodocumentacao.model.Livro;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record LivroResponse(

        Long id,

        String titulo,

        String resumo,

        String sumario,

        BigDecimal preco,

        Integer numeroDePaginas,

        String isbn,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataPublicacao,

        String categoria,

        String autor,

        String autorDescricao
) {
        public static LivroResponse toResponse(Livro livro) {
                return new LivroResponse(livro.getId(),
                        livro.getTitulo(),
                        livro.getResumo(),
                        livro.getSumario(),
                        livro.getPreco(),
                        livro.getNumeroDePaginas(),
                        livro.getIsbn(),
                        livro.getDataPublicacao(),
                        livro.getCategoria().getNome(),
                        livro.getAutor().getNome(),
                        livro.getAutor().getDescricao());
        }

        public static List<LivroResponse> toResponseList(List<Livro> livrosList) {
                return livrosList.stream().map(livro -> new LivroResponse(
                        livro.getId(),
                        livro.getTitulo(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null)).collect(Collectors.toList());
        }
}
