package com.udemy.cursomc.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.udemy.cursomc.domain.Pedido;
import com.udemy.cursomc.repositories.PedidoRepository;
import com.udemy.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;
	
	public Pedido find(Integer id) {
		Optional<Pedido> pedido = repository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName())); //Verifica se tem pedido. Se o pedido for inválido ou nulo, retorna a exception criada manualmente
	}
}
