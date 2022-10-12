package com.example.nossocartaodocumentacao.controller.request;

import com.example.nossocartaodocumentacao.model.Estado;
import com.example.nossocartaodocumentacao.model.Pais;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record EstadoRequest(
        @NotBlank
        String nome

) {

    public Estado toModel(Pais pais) {
        return new Estado(this.nome, pais);
    }

}
