package br.com.fiap.t28scj.fundamentos.jbv28scjbot.entity;

import java.util.Arrays;

import org.junit.Test;

public class TesteDiversos {

	@Test
	public void testaTipoConta(){
		
//		TipoConta tipo = TipoConta.valueOf("CORRENTE");
		Arrays.asList(TipoConta.values()).forEach((t)->System.out.println("Item : " + t));
		TipoConta tipo = Arrays.asList(TipoConta.values()).stream()
			.filter(t->t.getId().equals(3))
			.findFirst()
			.get();
		System.out.println(tipo);
	}
}
