package com.gabriel.esseeujali;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gabriel.esseeujali.model.Categoria;
import com.gabriel.esseeujali.model.Cliente;
import com.gabriel.esseeujali.model.Livro;
import com.gabriel.esseeujali.repository.CategoriaRepository;
import com.gabriel.esseeujali.repository.ClienteRepository;
import com.gabriel.esseeujali.repository.LivroRepository;

@Configuration
public class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(ClienteRepository clienteRepository, LivroRepository livroRepository,
      CategoriaRepository categoriaRepository) {
    return args -> {
      log.info("Precarregando " + clienteRepository.save(new Cliente("José", "josee", "12345")));
      log.info("Precarregando " + clienteRepository.save(new Cliente("Maria", "mariaa", "12345")));
      log.info("Precarregando " + clienteRepository.save(new Cliente("Bruno", "brunoo", "12345")));
      log.info("Precarregando " + clienteRepository.save(new Cliente("Marcos", "marcoss", "12345")));

      Categoria categoria1 = new Categoria("Ficção");
      Categoria categoria2 = new Categoria("Terror");
      
      Livro livro1 = new Livro("Livro ABC", "Autor ABC", 120);
      Livro livro2 = new Livro("Livro DEF", "Autor DEF", 80);
      Livro livro3 = new Livro("Livro GHI", "Autor GHI", 250);
      Livro livro4 = new Livro("Livro JKL", "Autor JKL", 400);

      livro1.setCategoria(categoria1);
      livro2.setCategoria(categoria1);
      livro3.setCategoria(categoria2);
      livro4.setCategoria(categoria2);
      categoria1.addLivro(livro1);
      categoria1.addLivro(livro2);
      categoria2.addLivro(livro3);
      categoria2.addLivro(livro4);

      categoria1 = categoriaRepository.save(categoria1);
      categoria1 = categoriaRepository.save(categoria2);
    };
  }

}
