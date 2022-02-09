package com.udemy.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.cursomc.domain.Produto;
import com.udemy.cursomc.dto.ProdutoDTO;
import com.udemy.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;
	
	/**
	 * Busca Produtos pelo ID
	 * @return ResponseEntity (Classe que define as respostas para requisições HTTP)
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		Produto produto = service.find(id);
		return ResponseEntity.ok().body(produto);
	}
	
	
	@GetMapping()
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value = "nome", defaultValue = "") Integer nome, 
			@RequestParam(value = "categorias", defaultValue = "") Integer categorias, 
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction
			) { 
		Page<Produto> listaProduto = service.search(???, ???, page, linesPerPage, orderBy, direction); 
		Page<ProdutoDTO> listaProdutoDTO = listaProduto.map(obj -> new ProdutoDTO(obj)); 
		return ResponseEntity.ok().body(listaProdutoDTO); 
	}
}
