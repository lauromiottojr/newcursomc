package com.springbootionic.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.springbootionic.cursomc.domain.Cliente;
import com.springbootionic.cursomc.dto.ClienteDTO;
import com.springbootionic.cursomc.repositories.ClienteRepository;
import com.springbootionic.cursomc.services.exceptions.DataIntegrityException;
import com.springbootionic.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		// DataIntegrityViolationException é um erro (exceção) que é retornado quando 
		// é tentado excluir uma categoria que possui algum produto vinculado
		// para isso é criado uma classe para tratamento dessa exceção como eu quiser
		try {
			repo.deleteById(id);			
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um cliente que possui pedidos!");
		}
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	/* função para paginação de categorias */	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
}
