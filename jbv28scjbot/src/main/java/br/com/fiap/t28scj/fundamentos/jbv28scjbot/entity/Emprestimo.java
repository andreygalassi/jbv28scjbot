package br.com.fiap.t28scj.fundamentos.jbv28scjbot.entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Emprestimo {
	
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private Integer id;
	private Conta conta;
	private Date dtContratacao;
	private BigDecimal valorPresente;
	private BigDecimal valorJuros;
	private BigDecimal valorFuturo;
	private BigDecimal valorTaxa;
	private Integer prazo;
	private Date dtPagamento;
	
	public Emprestimo(Integer id, Conta conta, BigDecimal valorPresente, Integer prazo) {
		super();
		this.id = id;
		this.conta = conta;
		this.valorPresente = valorPresente;
		this.prazo = prazo;
		LocalDate hoje = LocalDate.now();
		Instant instant = hoje.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		this.dtContratacao = Date.from(instant);
		this.dtPagamento = Date.from(hoje.plusMonths(prazo).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		this.valorTaxa = new BigDecimal("0.05");
		this.valorJuros = valorPresente.multiply(valorTaxa).multiply(new BigDecimal(prazo));
		this.valorFuturo = valorPresente.add(valorJuros);
	}

	@Override
	public String toString() {
		return String.format("%s - %s - %s - %s\n",id, valorPresente, sdf.format(dtPagamento), valorFuturo );
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Date getDtContratacao() {
		return dtContratacao;
	}

	public void setDtContratacao(Date dtContratacao) {
		this.dtContratacao = dtContratacao;
	}

	public BigDecimal getValorPresente() {
		return valorPresente;
	}

	public void setValorPresente(BigDecimal valorPresente) {
		this.valorPresente = valorPresente;
	}

	public BigDecimal getValorJuros() {
		return valorJuros;
	}

	public void setValorJuros(BigDecimal valorJuros) {
		this.valorJuros = valorJuros;
	}

	public BigDecimal getValorFuturo() {
		return valorFuturo;
	}

	public void setValorFuturo(BigDecimal valorFuturo) {
		this.valorFuturo = valorFuturo;
	}

	public BigDecimal getValorTaxa() {
		return valorTaxa;
	}

	public void setValorTaxa(BigDecimal valorTaxa) {
		this.valorTaxa = valorTaxa;
	}

	public Integer getPrazo() {
		return prazo;
	}

	public void setPrazo(Integer prazo) {
		this.prazo = prazo;
	}

	public Date getDtPagamento() {
		return dtPagamento;
	}

	public void setDtPagamento(Date dtPagamento) {
		this.dtPagamento = dtPagamento;
	}
	
	
	
}
