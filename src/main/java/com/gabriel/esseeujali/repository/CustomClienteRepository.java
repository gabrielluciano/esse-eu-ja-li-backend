package com.gabriel.esseeujali.repository;

import javax.persistence.NoResultException;

import com.gabriel.esseeujali.model.Cliente;

public interface CustomClienteRepository {
  
  Cliente findByUsarioEPassword(String usuario, String password) throws NoResultException;

}
