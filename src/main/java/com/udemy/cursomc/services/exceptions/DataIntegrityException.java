package com.udemy.cursomc.services.exceptions;

public class DataIntegrityException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public DataIntegrityException(String mensagem) { //Envia somente a mensagem
		super(mensagem);
	}
	
	public DataIntegrityException(String mensagem, Throwable cause) { //Envia a mensagem e a causa da exception
		super(mensagem, cause);
	}
}
