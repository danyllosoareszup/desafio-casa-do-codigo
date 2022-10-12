package com.example.nossocartaodocumentacao.controller.response;

import javax.validation.constraints.NotBlank;

public record CategoriaResponse(
        @NotBlank
        String nome
) {
}
