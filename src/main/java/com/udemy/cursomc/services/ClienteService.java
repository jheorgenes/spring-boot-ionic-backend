package com.udemy.cursomc.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.udemy.cursomc.domain.Cliente;
import com.udemy.cursomc.repositories.ClienteRepository;
import com.udemy.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public Cliente find(Integer id) {
		Optional<Cliente> cliente = repository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())); //Verifica se tem cliente ou se tiver nulo ou inválido, retorna a exception criada manualmente
	}
}
