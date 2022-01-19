package com.udemy.cursomc.services.exceptions;

public class ObjectNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String mensagem) { //Envia somente a mensagem
		super(mensagem);
	}
	
	public ObjectNotFoundException(String mensagem, Throwable cause) { //Envia a mensagem e a causa da exception
		super(mensagem, cause);
	}
}
