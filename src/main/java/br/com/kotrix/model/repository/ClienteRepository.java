package br.com.kotrix.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kotrix.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
