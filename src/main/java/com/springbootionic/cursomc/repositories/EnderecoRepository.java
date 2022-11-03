package com.springbootionic.cursomc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springbootionic.cursomc.domain.Endereco;
import com.springbootionic.cursomc.domain.Estado;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
