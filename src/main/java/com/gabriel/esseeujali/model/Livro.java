package com.gabriel.esseeujali.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "livros")
public class Livro {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "NOME", length = 50, nullable = false)
  private String nome;

  @Column(name = "AUTOR", length = 50, nullable = false)
  private String autor;

  @Column(name = "PAG", nullable = false)
  private Integer paginas;

  @ManyToMany(mappedBy = "livros", fetch = FetchType.EAGER)
  @JsonIgnore
  private List<Cliente> clientes = new ArrayList<>();

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "CATEGORIA_ID")
  private Categoria categoria;

  public Livro() {

  }

  public Livro(String nome, String autor, Integer paginas) {
    this.nome = nome;
    this.autor = autor;
    this.paginas = paginas;
  }

  public Livro(Long id, String nome, String autor, Integer paginas) {
    this.id = id;
    this.nome = nome;
    this.autor = autor;
    this.paginas = paginas;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getAutor() {
    return autor;
  }

  public void setAutor(String autor) {
    this.autor = autor;
  }

  public Integer getPaginas() {
    return paginas;
  }

  public void setPaginas(Integer paginas) {
    this.paginas = paginas;
  }

  public List<Cliente> getClientes() {
    return clientes;
  }

  public void setClientes(List<Cliente> clientes) {
    this.clientes = clientes;
  }

  public void addCliente(Cliente cliente) {
    this.clientes.add(cliente);
  }

  public boolean removeCliente(Cliente cliente) {
    return this.clientes.remove(cliente);
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

}
