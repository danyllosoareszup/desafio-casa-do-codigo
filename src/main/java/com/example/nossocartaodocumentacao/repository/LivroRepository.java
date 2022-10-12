package com.example.nossocartaodocumentacao.repository;

import com.example.nossocartaodocumentacao.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {


    List<Livro> findAllByAutorId(Long id);
}
