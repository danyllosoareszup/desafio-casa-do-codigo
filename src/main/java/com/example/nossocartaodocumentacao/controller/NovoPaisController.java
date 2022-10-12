package com.example.nossocartaodocumentacao.controller;

import com.example.nossocartaodocumentacao.controller.request.PaisRequest;
import com.example.nossocartaodocumentacao.model.Pais;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/pais")
public class NovoPaisController {

    private final EntityManager entityManager;


    public NovoPaisController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PaisRequest paisRequest, UriComponentsBuilder uriComponentsBuilder) {

        Pais novoPais = paisRequest.toModel();

        entityManager.persist(novoPais);

        URI location = uriComponentsBuilder.path("/pais/{id}").buildAndExpand(novoPais.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<?> handleDatabaseConstraintErrors(PersistenceException ex, WebRequest request) {

        Map<String, Object> body = Map.of(
                "status", 422,
                "error", "Unprocessable Entity",
                "path", request.getDescription(false).replace("uri=", ""),
                "timestamp", LocalDateTime.now(),
                "message", "Já existe um país ocm este nome (unique constraint)"
        );

        return ResponseEntity
                .unprocessableEntity().body(body); // HTTP 422
    }
}
