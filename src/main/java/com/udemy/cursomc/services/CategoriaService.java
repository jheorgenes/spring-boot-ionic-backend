package com.udemy.cursomc.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.udemy.cursomc.domain.Categoria;
import com.udemy.cursomc.repositories.CategoriaRepository;
import com.udemy.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> categoria = repository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName())); //Verifica se tem categoria ou se tiver nulo ou inválido, retorna a exception criada manualmente
	}

	public Categoria insert(Categoria categoria) {
		categoria.setId(null); //Ao verificar que essa categoria tem o ID nulo, significa que não há categoria cadastrada
		return repository.save(categoria);
	}
	

}
