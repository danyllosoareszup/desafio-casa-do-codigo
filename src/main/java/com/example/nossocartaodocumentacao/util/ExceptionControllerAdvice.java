package com.example.nossocartaodocumentacao.util;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensagensDeErro> methodArgumentNotValid(MethodArgumentNotValidException ex) {
        MensagensDeErro erros = new MensagensDeErro();

        BindingResult bindingResult = ex.getBindingResult();

        bindingResult.getFieldErrors()
                .forEach(erros::adicionarErro);

        return ResponseEntity.badRequest().body(erros);
    }
}

