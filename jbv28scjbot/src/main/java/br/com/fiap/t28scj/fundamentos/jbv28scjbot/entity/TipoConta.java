package br.com.fiap.t28scj.fundamentos.jbv28scjbot.entity;

/**
 * 
 * @author usuario
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
}
