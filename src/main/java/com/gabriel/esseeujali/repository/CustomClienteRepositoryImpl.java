package com.gabriel.esseeujali.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.gabriel.esseeujali.model.Cliente;

public class CustomClienteRepositoryImpl implements CustomClienteRepository {

  @PersistenceContext
  private EntityManager em;

  @Override
  public Cliente findByUsarioEPassword(String usuario, String password) throws NoResultException {
    String query = "SELECT c From Cliente c WHERE c.usuario = :usuario AND c.senha = :password";
    
    return em.createQuery(query, Cliente.class)
      .setParameter("usuario", usuario)
      .setParameter("password", password)
      .getSingleResult();
  }

}
