package com.example.nossocartaodocumentacao.controller.request;

import com.example.nossocartaodocumentacao.model.Categoria;
import com.example.nossocartaodocumentacao.util.Unique;

import javax.validation.constraints.NotBlank;

public record CategoriaRequest(

        @NotBlank
        @Unique(domainClass = Categoria.class, fieldName = "nome")
        String nome
) {

    public Categoria toModel() {
        return new Categoria(this.nome);
    }

}
