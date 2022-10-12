package com.example.nossocartaodocumentacao.controller;

import com.example.nossocartaodocumentacao.controller.request.AutorRequest;
import com.example.nossocartaodocumentacao.controller.request.CategoriaRequest;
import com.example.nossocartaodocumentacao.controller.response.CategoriaResponse;
import com.example.nossocartaodocumentacao.model.Autor;
import com.example.nossocartaodocumentacao.model.Categoria;
import com.example.nossocartaodocumentacao.repository.CategoriaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/categorias")
public class NovaCategoriaController {

    private final CategoriaRepository categoriaRepository;

    public NovaCategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CategoriaRequest categoriaRequest) {

        Categoria novaCategoria = categoriaRequest.toModel();
        categoriaRepository.save(novaCategoria);

        return ResponseEntity.ok(new CategoriaResponse(novaCategoria.getNome()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDatabaseConstraintErrors(DataIntegrityViolationException ex, WebRequest request) {

        Map<String, Object> body = Map.of(
                "status", 422,
                "error", "Unprocessable Entity",
                "path", request.getDescription(false).replace("uri=", ""),
                "timestamp", LocalDateTime.now(),
                "message", "esta categoria já existe (unique constraint)"
        );

        return ResponseEntity
                .unprocessableEntity().body(body); // HTTP 422
    }
}
