package br.com.fiap.t28scj.fundamentos.jbv28scjbot.entity;

/**
 * Tipo da movimentação de uma transação. Podendo ser saque ou deposito
 * 
 * @author Andrey
 * @since 15/11/2016
 */
public enum TipoTransacao {

	SAQUE(1,"Saque"),
	DEPOSITO(2,"Deposito");
	
	private Integer id;
	private String descricao;
	
	TipoTransacao (Integer id, String descricao){
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
