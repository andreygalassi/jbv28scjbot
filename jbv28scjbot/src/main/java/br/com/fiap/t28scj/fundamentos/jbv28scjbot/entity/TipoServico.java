package br.com.fiap.t28scj.fundamentos.jbv28scjbot.entity;

import java.math.BigDecimal;

/**
 * Tipo da movimentação de uma transação. Podendo ser saque ou deposito
 * 
 * @author Andrey
 * @since 03/12/2016
 */
public enum TipoServico {

	SAQUE(1,"Saque", "0.00"),
	DEPOSITO(2,"Deposito","0.00"),
	TARIFA_SAQUE(1,"Tarifa Saque", "2.50"),
	EXTRATO(3,"Tarifa Extrato","1.00"),
	EMPRESTIMO(4,"Tarifa Emprestimo","15.00");
	
	private Integer id;
	private String descricao;
	private BigDecimal custo;
	
	TipoServico (Integer id, String descricao, String custo){
		this.id = id;
		this.descricao=descricao;
		this.custo=new BigDecimal(custo);
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

	public BigDecimal getCusto() {
		return custo;
	}
}
