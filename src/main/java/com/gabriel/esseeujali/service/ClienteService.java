package com.gabriel.esseeujali.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.esseeujali.exceptions.ClienteNaoEncontradoException;
import com.gabriel.esseeujali.model.Cliente;
import com.gabriel.esseeujali.model.Livro;
import com.gabriel.esseeujali.repository.ClienteRepository;
import com.gabriel.esseeujali.repository.LivroRepository;

@Service
public class ClienteService {

  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  private LivroRepository livroRepository;

  public Cliente salvar(Cliente cliente) {
    return clienteRepository.save(cliente);
  }

  public Cliente pesquisar(Long id) throws ClienteNaoEncontradoException {

    Optional<Cliente> clienteDB = clienteRepository.findById(id);

    if (clienteDB.isEmpty())
      throw new ClienteNaoEncontradoException("Cliente não encontrado");

    return clienteDB.get();
  }

  public List<Cliente> listar() {
    return clienteRepository.findAll();
  }

  public Long autenticar(Cliente cliente) {
    try {
      Cliente clienteDB = clienteRepository.findByUsarioEPassword(cliente.getUsuario(), cliente.getSenha());
      if (clienteDB != null) return clienteDB.getId();
    } catch(NoResultException exception) {
    }
    return null;
  }

  public boolean adicionarLivro(Long idCliente, Long idLivro) {
    Optional<Cliente> clienteOpt = clienteRepository.findById(idCliente);
    Optional<Livro> livroOpt = livroRepository.findById(idLivro);

    if (clienteOpt.isEmpty() || livroOpt.isEmpty())
      return false;

    Cliente cliente = clienteOpt.get();
    Livro livro = livroOpt.get();

    cliente.addLivro(livro);
    livro.addCliente(cliente);

    clienteRepository.save(cliente);
    return true;
  }

}
