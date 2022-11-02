// interface que faz contato com o banco de dados
package com.springbootionic.cursomc.repositories;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springbootionic.cursomc.domain.Cliente;
import com.springbootionic.cursomc.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	// o framework ja entendo com essa anotação e colocando "findBy..." que você
	// quer buscar algo no banco
	@Transactional(readOnly = true)
	Page<Pedido> findByCliente(Cliente cliente, PageRequest pageRequest);

}
