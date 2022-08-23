package com.gabriel.esseeujali.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.esseeujali.exceptions.LivroNaoEncontradoException;
import com.gabriel.esseeujali.model.Livro;
import com.gabriel.esseeujali.repository.LivroRepository;

@Service
public class LivroService {

  @Autowired
  private LivroRepository livroRepository;

  public Livro salvar(Livro livro) {
    return livroRepository.save(livro);
  }

  public Livro pesquisar(Long id) throws LivroNaoEncontradoException {
    Optional<Livro> livroDB = livroRepository.findById(id);
    if (livroDB.isEmpty())
      throw new LivroNaoEncontradoException("Livro não encontrado");
    return livroDB.get();
  }

  public List<Livro> listar() {
    return livroRepository.findAll();
  }
}
