package com.springbootionic.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springbootionic.cursomc.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	// o framework ja entendo com essa anotação e colocando "findBy..." que você quer buscar algo no banco
	@Transactional(readOnly = true)
	Cliente findByEmail(String email);

}
