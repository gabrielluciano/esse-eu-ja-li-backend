package com.gabriel.esseeujali.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gabriel.esseeujali.exceptions.LivroNaoEncontradoException;
import com.gabriel.esseeujali.model.Categoria;
import com.gabriel.esseeujali.model.Livro;
import com.gabriel.esseeujali.repository.LivroRepository;

@SpringBootTest
public class LivrosServiceTest {
  
  @Autowired
  private LivroService livroService;

  @Autowired
  private LivroRepository livroRepository;

  @BeforeEach
  public void beforeEach() {
    livroRepository.deleteAll();
  }

  @Test
  public void testeSalvarEObterLivro() throws Exception {
    Livro livro = new Livro("Livro ABC", "Bruno", 120);
    livro = livroService.salvar(livro);

    Livro livroDB = livroService.pesquisar(livro.getId());
    assertEquals("Livro ABC", livroDB.getNome());
    assertEquals("Bruno", livroDB.getAutor());
    assertEquals(120, livroDB.getPaginas());
  }

  @Test
  public void testePesquisarLivroNaoExistente() {
    assertThrows(LivroNaoEncontradoException.class, () -> {
      livroService.pesquisar(1L);
    });
  }

  @Test
  public void testeObterListaVaziaDeLivros() {
    List<Livro> livros = livroService.listar();

    assertEquals(0, livros.size());
  }

  @Test
  public void testeObterMultiplosLivros() {
    Livro livro1 = new Livro("Livro ABC", "Bruno", 120);
    Livro livro2 = new Livro("Livro DEF", "Marcos", 80);
    livroService.salvar(livro1);
    livroService.salvar(livro2);

    List<Livro> livros = livroService.listar();
    assertEquals(2, livros.size());
  }

  @Test 
  public void testeSalvarLivroComCategoria() {
    Livro livro = new Livro("Livro ABC", "Bruno", 120);
    Categoria categoria = new Categoria("alfabeto");
    livro.setCategoria(categoria);
    categoria.addLivro(livro);

    livro = livroService.salvar(livro);
    assertEquals("alfabeto", livro.getCategoria().getNome());
  }

}
