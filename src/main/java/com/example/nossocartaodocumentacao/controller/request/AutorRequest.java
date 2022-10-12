package com.example.nossocartaodocumentacao.controller.request;

import com.example.nossocartaodocumentacao.model.Autor;
import com.example.nossocartaodocumentacao.model.Categoria;
import com.example.nossocartaodocumentacao.util.Unique;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


public record AutorRequest(

        @NotBlank
        String nome,

        @NotBlank
        @Email
        @Unique(domainClass = Autor.class, fieldName = "email")
        String email,

        @NotBlank
        @Length(max = 400)
        String descricao

) {

        public Autor toModel() {
                return new Autor(this.nome, this.email, this.descricao);
        }

}
