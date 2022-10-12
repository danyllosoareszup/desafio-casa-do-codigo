package com.example.nossocartaodocumentacao.util;

import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.Collection;

public class MensagensDeErro {

    private final Collection<String> mensagens= new ArrayList<>();

    public void adicionarErro(String campo, String mensagem){

        String mensagemDeErro=String.format("Campo %s %s.",campo,mensagem);

        mensagens.add(mensagemDeErro);
    }

    public void adicionarErro(FieldError fieldError){

        String mensagemDeErro = String.format("Campo %s %s.",fieldError.getField(),fieldError.getDefaultMessage());

        mensagens.add(mensagemDeErro);
    }

    public Collection<String> getMensagens() {
        return mensagens;
    }
}
