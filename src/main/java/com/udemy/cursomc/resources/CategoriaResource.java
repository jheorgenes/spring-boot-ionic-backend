package com.udemy.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.udemy.cursomc.domain.Categoria;
import com.udemy.cursomc.dto.CategoriaDTO;
import com.udemy.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@GetMapping()
	public ResponseEntity<List<CategoriaDTO>> findAll() { //Definindo que será buscado o ID via parametro
		List<Categoria> listaCategoria = service.findAll(); //Buscando categoria no banco de dados utilizando o método findALL que busca tudo
		List<CategoriaDTO> listaCategoriaDTO = listaCategoria.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList()); //Convertendo uma lista de categoria em uma lista de categoria DTO utilizando o Stream
		return ResponseEntity.ok().body(listaCategoriaDTO); //Retornando a lista de categoria no corpo da requisição
	}
	
	/* Endpoint para buscar todas as categorias por paginação */
	@GetMapping(value = "/page")
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction
			) { 
		Page<Categoria> listaCategoria = service.findPage(page, linesPerPage, orderBy, direction); 
		Page<CategoriaDTO> listaCategoriaDTO = listaCategoria.map(obj -> new CategoriaDTO(obj)); 
		return ResponseEntity.ok().body(listaCategoriaDTO); 
	}
	
	/**
	 * Busca Categorias pelo ID
	 * @return ResponseEntity (Classe que define as respostas para requisições HTTP)
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> find(@PathVariable Integer id) { //Definindo que será buscado o ID via parametro
		Categoria categoria = service.find(id); //Buscando categoria no banco de dados
		return ResponseEntity.ok().body(categoria);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO categoriaDTO){ //Define que os dados da categoria vão vir no corpo da requisição e não como parametro
		Categoria categoria = service.fromDTO(categoriaDTO); //Convertenado categoriaDTO para categoria antes de inserir no banco
		categoria = service.insert(categoria); //Inserindo no banco de dados.
		URI uri = ServletUriComponentsBuilder //Criando uma URI completa utilisando a classe ServletUriComponentsBuilder
					.fromCurrentRequest() //Chamando o método fromCurrentRequest que busca o corpo da URL
					.path("/{id}") //Adicionando um path /{id} no final da URI
					.buildAndExpand(categoria.getId()) //Atribuíndo o valor do path /{id} substituindo-o pelo id do objeto
					.toUri(); //Convertendo para URI
		return ResponseEntity.created(uri).build(); //Retornando o código 201 com a URI completa e o build para compilar.
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO categoriaDTO, @PathVariable Integer id){
		Categoria categoria = service.fromDTO(categoriaDTO);
		categoria.setId(id); //Garantindo que a categoria passada no ID será a mesma a ser atualizada
		categoria = service.update(categoria); //Atualizando a categoria no banco de dados
		return ResponseEntity.noContent().build(); //Retornando uma resposta com o objeto vazio e status code 204
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Categoria> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
