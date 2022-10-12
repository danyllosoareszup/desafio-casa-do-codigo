package com.example.nossocartaodocumentacao.controller.request;

import com.example.nossocartaodocumentacao.model.Cliente;
import com.example.nossocartaodocumentacao.model.Estado;
import com.example.nossocartaodocumentacao.model.Pais;
import com.example.nossocartaodocumentacao.util.CpfOrCnpj;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;

public record ClienteRequest(

        @NotBlank
        String nome,

        @NotBlank
        String sobrenome,

        @NotNull
        @Email
        String email,

        @NotBlank
        @CpfOrCnpj
        String cpfOuCnpj,

        @NotBlank
        String endereco,

        @NotBlank
        String complemento,

        @Pattern(regexp = "^\\+[1-9][0-9]\\d{1,14}")
        @NotBlank
        String telefone,

        @NotBlank
        String cep,

        @NotBlank
        String cidade,


        @NotNull
        Long paisId,

        Long estadoId
) {

    public Cliente toModel(Estado estado, Pais pais) {

        return new Cliente(this.nome, this.sobrenome, this.email, this.cpfOuCnpj, this.endereco,
                            this.complemento, this.telefone, this.cep, this.cidade, estado, pais);
    }

}
