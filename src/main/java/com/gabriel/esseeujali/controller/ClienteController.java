package com.gabriel.esseeujali.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.esseeujali.model.Cliente;
import com.gabriel.esseeujali.service.ClienteService;

@RestController
@RequestMapping(path = "/api/clientes")
public class ClienteController {

  private ClienteService clienteService;

  public ClienteController(ClienteService clienteService) {
   this.clienteService = clienteService;
  }

  @GetMapping
  public List<Cliente> getAll() {
    return clienteService.listar();
  }

  @GetMapping(path = "/{id}")
  public Cliente getOne(@PathVariable("id") Long id) {
    try {
      return clienteService.pesquisar(id);
    } catch (Exception exception) {
      return null;
    }
  }
  
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public Cliente create(@RequestBody Cliente cliente) {
    return clienteService.salvar(cliente);
  }

  @PostMapping(path = "/auth")
  public Long authenticate(@RequestBody Cliente cliente) {
    return clienteService.autenticar(cliente);
  }

  @PostMapping(path = "/addlivro")
  public boolean addLivro(@RequestBody  Map<String, Long> json) {
    return clienteService.adicionarLivro(json.get("idCliente"), json.get("idLivro"));
  }
}
