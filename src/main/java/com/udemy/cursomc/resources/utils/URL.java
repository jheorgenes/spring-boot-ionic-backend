package com.udemy.cursomc.resources.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

	public static List<Integer> decodeIntList(String s){
//		String[] vet = s.split(",");
//		List<Integer> list = new ArrayList<>();
//		
//		for (String string : vet) {
//			list.add(Integer.parseInt(string));
//		}
//		
//		return list;
		
		return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList()); //Transforma uma string em uma lista de Strings, converte todos os itens para Inteiro e retorna a lista de inteiros.
	}
}
