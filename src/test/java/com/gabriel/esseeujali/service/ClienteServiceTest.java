package com.gabriel.esseeujali.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gabriel.esseeujali.exceptions.ClienteNaoEncontradoException;
import com.gabriel.esseeujali.model.Cliente;
import com.gabriel.esseeujali.model.Livro;
import com.gabriel.esseeujali.repository.ClienteRepository;

@SpringBootTest
public class ClienteServiceTest {

  @Autowired
  private ClienteService clienteService;

  @Autowired
  private LivroService livroService;

  @Autowired
  private ClienteRepository clienteRepository;

  @BeforeEach
  public void beforeEach() {
    clienteRepository.deleteAll();
  }

  @Test
  public void testeSalvarEObterCliente() throws Exception {
    Cliente cliente = new Cliente("José", "jose123", "12345");
    cliente = clienteService.salvar(cliente);

    Cliente clienteDB = clienteService.pesquisar(cliente.getId());
    assertEquals("José", clienteDB.getNome());
    assertEquals("jose123", clienteDB.getUsuario());
    assertEquals("12345", clienteDB.getSenha());
  }

  @Test
  public void testeObterClienteInvalido() {
    assertThrows(ClienteNaoEncontradoException.class, () -> {
      clienteService.pesquisar(1000L);
    });
  }

  @Test
  public void testeObterListaVaziaDeClientes() throws Exception {
    List<Cliente> clientes = clienteService.listar();
    assertEquals(0, clientes.size());
  }

  @Test
  public void testaObterListaComDoisClientes() {
    Cliente cliente1 = new Cliente("José", "jose123", "12345");
    Cliente cliente2 = new Cliente("Maria", "maria15", "12345");
    clienteService.salvar(cliente1);
    clienteService.salvar(cliente2);

    List<Cliente> clientes = clienteService.listar();
    assertEquals(2, clientes.size());
  }

  @Test
  public void testeAutenticarCliente() {
    Cliente clienteDB = new Cliente("José", "jose123", "12345");
    clienteDB = clienteService.salvar(clienteDB);

    Long id = clienteService.autenticar(clienteDB);
    assertNotNull(id);
  }

  @Test
  public void testeAutenticarClienteNaoExistente() {
    Cliente clienteDB = new Cliente("José", "jose123", "12345");
    clienteDB = clienteService.salvar(clienteDB);
    
    Cliente clienteNaoExistente = new Cliente("Maria", "maria15", "12345");
    assertNull(clienteService.autenticar(clienteNaoExistente));
  }

  @Test
  public void testeAdicionarLivroCliente() throws Exception {
    Cliente cliente = new Cliente("Renato", "renn", "123");
    cliente = clienteService.salvar(cliente);

    Livro livro = new Livro("Livro JKL", "JOsé", 10);
    livro = livroService.salvar(livro);

    clienteService.adicionarLivro(cliente.getId(), livro.getId());
    Cliente clienteDB = clienteService.pesquisar(cliente.getId());

    assertEquals(livro.getId(), clienteDB.getLivros().get(0).getId());
  }
}
