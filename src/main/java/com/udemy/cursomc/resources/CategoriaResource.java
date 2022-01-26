package com.udemy.cursomc.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.udemy.cursomc.domain.Categoria;
import com.udemy.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	/**
	 * Busca Categorias pelo ID
	 * @return ResponseEntity (Classe que define as respostas para requisições HTTP)
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) { //Definindo que será buscado o ID via parametro
		Categoria categoria = service.buscar(id);
		return ResponseEntity.ok().body(categoria);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Categoria categoria){ //Define que os dados da categoria vão vir no corpo da requisição e não como parametro
		categoria = service.insert(categoria);
		URI uri = ServletUriComponentsBuilder //Criando uma URI completa utilisando a classe ServletUriComponentsBuilder
					.fromCurrentRequest() //Chamando o método fromCurrentRequest que busca o corpo da URL
					.path("/{id}") //Adicionando um path /{id} no final da URI
					.buildAndExpand(categoria.getId()) //Atribuíndo o valor do path /{id} substituindo-o pelo id do objeto
					.toUri(); //Convertendo para URI
		return ResponseEntity.created(uri).build(); //Retornando o código 201 com a URI completa e o build para compilar.
	}
}
