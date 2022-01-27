package com.udemy.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.udemy.cursomc.domain.Categoria;
import com.udemy.cursomc.dto.CategoriaDTO;
import com.udemy.cursomc.repositories.CategoriaRepository;
import com.udemy.cursomc.services.exceptions.DataIntegrityException;
import com.udemy.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	public Categoria find(Integer id) {
		Optional<Categoria> categoria = repository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName())); //Verifica se tem categoria ou se tiver nulo ou inválido, retorna a exception criada manualmente
	}

	public Categoria insert(Categoria categoria) {
		categoria.setId(null); //Ao verificar que essa categoria tem o ID nulo, significa que não há categoria cadastrada
		return repository.save(categoria);
	}

	public Categoria update(Categoria categoria) {
		Categoria newCategoria = find(categoria.getId()); 
		updateData(newCategoria, categoria);
		return repository.save(newCategoria);
	}

	public void delete(Integer id) {
		find(id); //testando se o ID existe no banco
		try {
			repository.deleteById(id); //Deletando a categoria cujo o ID que foi recebido
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos"); //Classe de exception criada manualmente
		}
	}

	public List<Categoria> findAll() {
		return repository.findAll();
	}
	
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy); //Instanciando paginação e convertendo no parametro o Direction.
		return repository.findAll(pageRequest);
	}
	
	
	public Categoria fromDTO(CategoriaDTO categoriaDto) {
		return new Categoria(categoriaDto.getId(), categoriaDto.getNome());
	}

	private void updateData(Categoria newCategoria, Categoria categoria) {
		newCategoria.setNome(categoria.getNome());
	}
}
