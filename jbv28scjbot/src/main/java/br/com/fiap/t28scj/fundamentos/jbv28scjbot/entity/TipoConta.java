package br.com.fiap.t28scj.fundamentos.jbv28scjbot.entity;

import java.util.Arrays;

/**
 * 
 * @author Andrey
 * @since 15/11/2016
 */
public enum TipoConta {

	CORRENTE(1,"Corrente"),
	POUPANCA(2,"Poupança");
	
	private Integer id;
	private String descricao;
	
	TipoConta (Integer id, String descricao){
		this.id = id;
		this.descricao=descricao;
	}

	public String toString(){
		return this.descricao;
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoConta getById(String id){
		TipoConta tipo = Arrays.asList(TipoConta.values()).stream()
				.filter(t->t.getId().equals(Integer.parseInt(id)))
				.findFirst()
				.get();
		return tipo;
	}

}
