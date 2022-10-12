package com.example.nossocartaodocumentacao.controller;

import com.example.nossocartaodocumentacao.controller.request.EstadoRequest;
import com.example.nossocartaodocumentacao.controller.request.PaisRequest;
import com.example.nossocartaodocumentacao.model.Estado;
import com.example.nossocartaodocumentacao.model.Pais;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/pais/{id}")
public class NovoEstadoController {


    private final EntityManager entityManager;

    public NovoEstadoController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @PostMapping("/estado")
    public ResponseEntity<?> cadastrar(@PathVariable Long id,@RequestBody @Valid EstadoRequest estadoRequest, UriComponentsBuilder uriComponentsBuilder) {

        Pais pais = Optional.ofNullable(entityManager.find(Pais.class, id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pais inexistente"));

        Estado novoEstado = estadoRequest.toModel(pais);

        entityManager.persist(novoEstado);

        URI location = uriComponentsBuilder.path("/pais/{id}/estado/{idEstado}").buildAndExpand(pais.getId(), novoEstado.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<?> handleDatabaseConstraintErrors(PersistenceException ex, WebRequest request) {

        Map<String, Object> body = Map.of(
                "status", 422,
                "error", "Unprocessable Entity",
                "path", request.getDescription(false).replace("uri=", ""),
                "timestamp", LocalDateTime.now(),
                "message", "Não é possível ter dois estados com o mesmo nome em um país"
        );

        return ResponseEntity
                .unprocessableEntity().body(body); // HTTP 422
    }
}
