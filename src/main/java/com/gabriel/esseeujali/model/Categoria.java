package com.gabriel.esseeujali.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "categorias")
public class Categoria {
    
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "NOME", length = 50, nullable = false)
  private String nome;

  @OneToMany(mappedBy = "categoria", cascade = CascadeType.PERSIST)
  @JsonIgnore
  private List<Livro> livros = new ArrayList<>();

  public Categoria() {

  }

  public Categoria(String nome) {
    this.nome = nome;
  }

  public Categoria(Long id, String nome) {
    this.id = id;
    this.nome = nome;
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
