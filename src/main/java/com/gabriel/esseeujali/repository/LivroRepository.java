package com.gabriel.esseeujali.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.esseeujali.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
  
}
