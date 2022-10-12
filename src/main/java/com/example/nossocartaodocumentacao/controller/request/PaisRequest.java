package com.example.nossocartaodocumentacao.controller.request;

import com.example.nossocartaodocumentacao.model.Pais;

import javax.validation.constraints.NotBlank;

public record PaisRequest(
        @NotBlank
        String nome
) {

    public Pais toModel() {
        return new Pais(this.nome);
    }
}
