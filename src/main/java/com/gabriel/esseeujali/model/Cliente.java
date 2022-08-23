package com.gabriel.esseeujali.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "NOME", length = 50, nullable = false)
  private String nome;

  @Column(name = "USUARIO", length = 20, nullable = false)
  private String usuario;

  @Column(name = "PASSWORD", length = 20, nullable = false)
  private String senha;

  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinTable(name = "livros_clientes")
  private List<Livro> livros = new ArrayList<>();

  public Cliente() {

  }

  public Cliente(Long id, String nome, String usuario, String senha) {
    this.id = id;
    this.nome = nome;
    this.usuario = usuario;
    this.senha = senha;
  }

  public Cliente(String nome, String usuario, String senha) {
    this.nome = nome;
    this.usuario = usuario;
    this.senha = senha;
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

  public String getUsuario() {
    return usuario;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public List<Livro> getLivros() {
    return livros;
  }

  public void setLivros(List<Livro> livros) {
    this.livros = livros;
  }

  public void addLivro(Livro livro) {
    this.livros.add(livro);
  }

  public boolean removeLivro(Livro livro) {
    return this.livros.remove(livro);
  }

}
