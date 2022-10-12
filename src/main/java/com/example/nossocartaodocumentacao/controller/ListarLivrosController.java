package com.example.nossocartaodocumentacao.controller;
import com.example.nossocartaodocumentacao.controller.response.LivroResponse;
import com.example.nossocartaodocumentacao.model.Autor;
import com.example.nossocartaodocumentacao.model.Livro;
import com.example.nossocartaodocumentacao.repository.AutorRepository;
import com.example.nossocartaodocumentacao.repository.LivroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class ListarLivrosController {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public ListarLivrosController(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    @GetMapping("/autor/{id}")
    public ResponseEntity<?> listar(@PathVariable Long id) {

        Autor autor = autorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor inexistente"));

        List<Livro> livros =  livroRepository.findAllByAutorId(id);

        List<LivroResponse> livroResponses = LivroResponse.toResponseList(livros);

        return ResponseEntity.ok(livroResponses);
    }
}
