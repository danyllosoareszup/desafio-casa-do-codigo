package com.example.nossocartaodocumentacao.controller;

import com.example.nossocartaodocumentacao.controller.response.LivroResponse;
import com.example.nossocartaodocumentacao.model.Livro;
import com.example.nossocartaodocumentacao.repository.LivroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/livros")
public class DetalharLivroController {


    private final LivroRepository livroRepository;

    public DetalharLivroController(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id){

        Livro livro = livroRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro inexistente"));

        LivroResponse response = LivroResponse.toResponse(livro);

        return ResponseEntity.ok(response);
    }
}
