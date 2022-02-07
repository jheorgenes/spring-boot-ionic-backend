package com.udemy.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.udemy.cursomc.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	@Transactional(readOnly = true) //Não é envolvida como uma transação no banco de dados (Diminui o looking no gerenciamento de transações do banco)
	Cliente findByEmail(String email);
}
