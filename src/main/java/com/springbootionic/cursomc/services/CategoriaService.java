// classe que faz contato com a repo, aqui eu faço tratamento de exceções
package com.springbootionic.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.springbootionic.cursomc.domain.Categoria;
import com.springbootionic.cursomc.repositories.CategoriaRepository;
import com.springbootionic.cursomc.services.exceptions.DataIntegrityException;
import com.springbootionic.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		// DataIntegrityViolationException é um erro (exceção) que é retornado quando 
		// é tentado excluir uma categoria que possui algum produto vinculado
		// para isso é criado uma classe para tratamento dessa exceção como eu quiser
		try {
			repo.deleteById(id);			
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos!");
		}
	}
}
