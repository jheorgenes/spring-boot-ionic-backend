package com.udemy.cursomc.domain.enums;

public enum TipoCliente {
	
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int cod;
	private String descricao;
	
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	/* Faz a conversão de Integer para o Enum TipoCliente */
	public static TipoCliente toEnum(Integer cod) {
		if(cod == null) { //Se for nulo, retorna nulo
			return null;
		}
		
		for(TipoCliente x : TipoCliente.values()) { //Percorre os tipos de enums
			if(cod.equals(x.getCod())) //Se o código for existente, retorna o código
				return x;
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod); //Se o código do tipo enum não for um código existente, lança a exception
	}
	
}
