package com.example.nossocartaodocumentacao.controller;

import com.example.nossocartaodocumentacao.controller.request.AutorRequest;
import com.example.nossocartaodocumentacao.model.Autor;
import com.example.nossocartaodocumentacao.repository.AutorRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/autores")
public class NovoAutorController {

    private final AutorRepository autorRepository;

    public NovoAutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid AutorRequest autorRequest) {

        Autor novoAutor = autorRequest.toModel();
        autorRepository.save(novoAutor);

        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDatabaseConstraintErrors(DataIntegrityViolationException ex, WebRequest request) {

        Map<String, Object> body = Map.of(
                "status", 422,
                "error", "Unprocessable Entity",
                "path", request.getDescription(false).replace("uri=", ""),
                "timestamp", LocalDateTime.now(),
                "message", "email já existente no sistema (unique constraint)"
        );

        return ResponseEntity
                .unprocessableEntity().body(body); // HTTP 422
    }
}
