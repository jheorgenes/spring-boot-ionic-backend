package com.udemy.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.udemy.cursomc.domain.Cliente;
import com.udemy.cursomc.dto.ClienteDTO;
import com.udemy.cursomc.repositories.ClienteRepository;
import com.udemy.cursomc.resources.exception.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE); //Pega os atributos que foram passados na URI da requisição e guarda em uma lista
		Integer uriId = Integer.parseInt(map.get("id")); //Separa o parametro id, faz a conversão para Integer e guarda na variável uriId
		
		
		List<FieldMessage> list = new ArrayList<>();
		
		Cliente cliente = clienteRepository.findByEmail(objDto.getEmail()); //Instancia um cliente consultando no banco de dados um cliente que tenha o email que foi inserido via requisição.
		if(cliente != null && !cliente.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já existente!"));
		}

		// inclua os testes aqui, inserindo erros na lista

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty(); //Se retornar uma lista de mensagem vazia, significa que passou na validação.
	}
}
