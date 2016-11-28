package br.com.fiap.t28scj.fundamentos.jbv28scjbot.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author Andrey
 * @since 15/11/2016
 */
public class Conta {

	private Long id;
	private Pessoa titular;
	private Set<Pessoa> dependentes;
	private LocalDate dtRegistro;
	private BigDecimal saldo;
	private List<Movimentacao> movimentacoes;
	private TipoConta tipo;

	public Conta(Pessoa titular, TipoConta tipo) {
		super();
		this.titular = titular;
		this.tipo = tipo;
		this.dtRegistro = LocalDate.now();
		this.movimentacoes = new ArrayList<>();
		this.dependentes = new HashSet<>();
		this.saldo = BigDecimal.ZERO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getTitular() {
		return titular;
	}

	public Set<Pessoa> getDependentes() {
		if(dependentes == null)
			dependentes = new HashSet<>();
		return dependentes;
	}

	public void setDependentes(Set<Pessoa> dependentes) {
		this.dependentes = dependentes;
	}

	public LocalDate getDtRegistro() {
		return dtRegistro;
	}

	public void setDtRegistro(LocalDate dtRegistro) {
		this.dtRegistro = dtRegistro;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}
	
	public void depositar(BigDecimal valor)
	{
		saldo = saldo.add(valor);
	}
	
	public void sacar(BigDecimal valor)
	{
		saldo = saldo.subtract(valor).subtract(new BigDecimal(2.5));
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

	public TipoConta getTipo() {
		return tipo;
	}

	public void setTipo(TipoConta tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return String.format("Conta [id=%s, titular=%s, qtDependentes=%s, dtRegistro=%s, saldo=%s, tipo=%s]", id,
				titular, dependentes.size(), dtRegistro, saldo, tipo);
	}

}
