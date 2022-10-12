package com.example.nossocartaodocumentacao.controller;

import com.example.nossocartaodocumentacao.controller.request.ClienteRequest;
import com.example.nossocartaodocumentacao.model.Cliente;
import com.example.nossocartaodocumentacao.model.Estado;
import com.example.nossocartaodocumentacao.model.Pais;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class NovoClienteController {

    private final EntityManager entityManager;

    public NovoClienteController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ClienteRequest clienteRequest) {

        Pais pais = Optional.ofNullable(entityManager.find(Pais.class, clienteRequest.paisId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pais inexistente"));

        Estado estado = null;

        if(clienteRequest.estadoId() != null) {
            estado = Optional.ofNullable(entityManager.find(Estado.class, clienteRequest.estadoId()))
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado inexistente"));
        }

        Cliente novoCliente = clienteRequest.toModel(estado, pais);

        entityManager.persist(novoCliente);

        return ResponseEntity.ok(novoCliente.getId());
    }

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<?> handleDatabaseConstraintErrors(PersistenceException ex, WebRequest request) {

        Map<String, Object> body = Map.of(
                "status", 422,
                "error", "Unprocessable Entity",
                "path", request.getDescription(false).replace("uri=", ""),
                "timestamp", LocalDateTime.now(),
                "message", "Já existe cliente com este email ou documento cadastrado (unique constraint)"
        );

        return ResponseEntity
                .unprocessableEntity().body(body); // HTTP 422
    }
}
