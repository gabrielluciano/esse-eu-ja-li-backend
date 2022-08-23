package com.gabriel.esseeujali.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.esseeujali.model.Livro;
import com.gabriel.esseeujali.service.LivroService;

@RestController
@RequestMapping(path = "/api/livros")
public class LivroController {
  
  private LivroService livroService;

  public LivroController(LivroService livroService) {
    this.livroService = livroService;
  }

  @GetMapping
  public List<Livro> getAll() {
    return livroService.listar();
  }

  @GetMapping(path = "/{id}")
  public Livro getOne(@PathVariable("id") Long id) {
    try {
      return livroService.pesquisar(id);
    } catch (Exception exception) {
      return null;
    }
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public Livro create(@RequestBody Livro livro) {
    return livroService.salvar(livro);
  }

}
