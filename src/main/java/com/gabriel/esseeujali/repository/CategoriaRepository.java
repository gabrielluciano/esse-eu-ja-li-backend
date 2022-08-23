package com.gabriel.esseeujali.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.esseeujali.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
  
}
