package br.com.fiap.t28scj.fundamentos.jbv28scjbot.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Movimentação de saque ou deposito em cada conta.
 * 
 * @author Andrey
 * @since 15/11/2016
 */
public class Movimentacao implements Comparable<Movimentacao> {

	private Long id;
	private Conta conta;
	private BigDecimal valor;
	private String descricao;
	private TipoTransacao tipo;
	private TipoServico servico;
	private final LocalDate dtRegistro;
	
	public Movimentacao(Conta conta, BigDecimal valor, String descricao, TipoTransacao tipo, TipoServico servico) {
		super();
		this.conta = conta;
		this.valor = valor;
		this.descricao = descricao;
		this.tipo = tipo;
		this.servico = servico;
		this.dtRegistro = LocalDate.now();
	}
	
	public TipoServico getServico() {
		return servico;
	}

	public void setServico(TipoServico servico) {
		this.servico = servico;
	}

	public LocalDate getDtRegistro() {
		return dtRegistro;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public TipoTransacao getTipo() {
		return tipo;
	}
	public void setTipo(TipoTransacao tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return String.format("%s - %s - %s - %s - %s\n",dtRegistro, valor, tipo, servico, descricao );
	}

	@Override
	public int compareTo(Movimentacao o) {
		return this.dtRegistro.compareTo(o.getDtRegistro());
	}
	
	
}
