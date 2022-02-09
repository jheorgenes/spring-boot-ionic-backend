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
import com.udemy.cursomc.domain.Cliente;
import com.udemy.cursomc.dto.ClienteDTO;
import com.udemy.cursomc.dto.ClienteNewDTO;
import com.udemy.cursomc.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	@GetMapping()
	public ResponseEntity<List<ClienteDTO>> findAll() { 
		List<Cliente> listaCliente = service.findAll(); 
		List<ClienteDTO> listaClienteDTO = listaCliente.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList()); 
		return ResponseEntity.ok().body(listaClienteDTO); 
	}

	@GetMapping(value = "/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Cliente> listaCliente = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listaClienteDTO = listaCliente.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listaClienteDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		Cliente cliente = service.find(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO clienteNewDTO){ //Recebe um novo DTO personalizado para incluir cliente, endereço e Telefone
		Cliente cliente = service.fromDTO(clienteNewDTO); //Cria uma instância de cliente com os campos que foram preenchidos
		cliente = service.insert(cliente); //Insere no banco de dados
		URI uri = ServletUriComponentsBuilder //Gera a URI
					.fromCurrentRequest() //Com os dados da requisição
					.path("/{id}") //criando um path com um id
					.buildAndExpand(cliente.getId()) //Trocando o /{id} pelo id do cliente gerado
					.toUri(); //Montando a URI completa
		return ResponseEntity.created(uri).build(); //retornando um status code 201 e compilando
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Integer id) {
		Cliente cliente = service.fromDTO(clienteDTO);
		cliente.setId(id);
		cliente = service.update(cliente);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Cliente> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
