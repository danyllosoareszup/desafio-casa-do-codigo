package com.example.nossocartaodocumentacao.controller;

import com.example.nossocartaodocumentacao.controller.request.LivroRequest;
import com.example.nossocartaodocumentacao.model.Autor;
import com.example.nossocartaodocumentacao.model.Categoria;
import com.example.nossocartaodocumentacao.model.Livro;
import com.example.nossocartaodocumentacao.repository.AutorRepository;
import com.example.nossocartaodocumentacao.repository.CategoriaRepository;
import com.example.nossocartaodocumentacao.repository.LivroRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/livros")
public class NovoLivroController {


    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private final CategoriaRepository categoriaRepository;

    public NovoLivroController(LivroRepository livroRepository, AutorRepository autorRepository, CategoriaRepository categoriaRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid LivroRequest livroRequest) {

        Categoria categoria = categoriaRepository
                .findById(livroRequest.categoriaId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "categoria inexistente"));

        Autor autor = autorRepository
                .findById(livroRequest.autorId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "autor inexistente"));

        Livro novoLivro = livroRequest.toModel(categoria, autor);
        livroRepository.save(novoLivro);

        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDatabaseConstraintErrors(DataIntegrityViolationException ex, WebRequest request) {

        Map<String, Object> body = Map.of(
                "status", 422,
                "error", "Unprocessable Entity",
                "path", request.getDescription(false).replace("uri=", ""),
                "timestamp", LocalDateTime.now(),
                "message", "Já existe livro com este ISBN ou TITULO cadastrado (unique constraint)"
        );

        return ResponseEntity
                .unprocessableEntity().body(body); // HTTP 422
    }
}
