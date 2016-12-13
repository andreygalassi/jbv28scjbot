package br.com.fiap.t28scj.fundamentos.jbv28scjbot.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Andrey
 * @since 15/11/2016
 */
public class Conta {

	private Pessoa titular;
	private Set<Pessoa> dependentes;
	private LocalDate dtRegistro;
	private BigDecimal saldo;
	private List<Movimentacao> movimentacoes;
	private TipoConta tipo;
	private List<Emprestimo> emprestimos;

	public Conta(Pessoa titular, TipoConta tipo) {
		super();
		this.titular = titular;
		this.tipo = tipo;
		this.dtRegistro = LocalDate.now();
		this.movimentacoes = new ArrayList<>();
		this.emprestimos = new ArrayList<>();
		this.dependentes = new HashSet<>();
		this.saldo = BigDecimal.ZERO;
		this.dependentes = new HashSet<>();
	}

	public List<Emprestimo> getEmprestimos() {
		return emprestimos;
	}

	public Pessoa getTitular() {
		return titular;
	}

	public Set<Pessoa> getDependentes() {
		if (dependentes == null)
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

	public Movimentacao novoEmprestimo(BigDecimal valor, Integer prazo) {
		Movimentacao m = new Movimentacao(this, TipoServico.EMPRESTIMO.getCusto(),
				"Tarifa emprestimo no valor de R$" + valor.toString(), TipoTransacao.SAQUE,
				TipoServico.TARIFA_EMPRESTIMO);
		movimentacoes.add(m);
		m = new Movimentacao(this, valor, "Emprestimo no valor de R$" + valor.toString(), TipoTransacao.DEPOSITO,
				TipoServico.EMPRESTIMO);
		movimentacoes.add(m);
		Emprestimo emprestimo = new Emprestimo(emprestimos.size() + 1, this, valor, prazo);
		emprestimos.add(emprestimo);
		saldo = saldo.add(valor);
		saldo = saldo.subtract(TipoServico.EMPRESTIMO.getCusto());
		return m;
	}

	public void depositar(BigDecimal valor) {
		Movimentacao m = new Movimentacao(this, valor, "Deposito em conta no valor de R$" + valor.toString(),
				TipoTransacao.DEPOSITO, TipoServico.DEPOSITO);
		movimentacoes.add(m);
		saldo = saldo.add(valor);
	}

	public void extrato() throws Exception {
		if (saldo.subtract(new BigDecimal("1.00")).signum() < 0) {
			throw new Exception("Conta sem saldo para tarifa extrato. Lembre-se, o extrato tem custo de R$1,00");
		}
		Movimentacao m = new Movimentacao(this, new BigDecimal("1.00"), "Tarifa Extrato", TipoTransacao.SAQUE,
				TipoServico.EXTRATO);
		movimentacoes.add(m);
		saldo = saldo.subtract(new BigDecimal("1.00"));
	}

	public void sacar(BigDecimal valor) throws Exception {
		if (saldo.subtract(valor.add(new BigDecimal("2.5"))).signum() < 0) {
			// Movimentacao m = new Movimentacao(this, valor, "Saque não
			// realizado por falta de saldo", TipoTransacao.SAQUE,
			// TipoServico.SAQUE);
			// movimentacoes.add(m);
			throw new Exception("Conta sem saldo para saque. Lembre-se, o saque tem custo de R$2,50");
		}
		Movimentacao m = new Movimentacao(this, valor, "Saque em conta no valor de R$" + valor.toString(),
				TipoTransacao.SAQUE, TipoServico.SAQUE);
		movimentacoes.add(m);
		m = new Movimentacao(this, valor, "Custo de R$2,50 para saque de R$" + valor.toString(), TipoTransacao.SAQUE,
				TipoServico.TARIFA_SAQUE);
		movimentacoes.add(m);
		saldo = saldo.subtract(valor).subtract(new BigDecimal("2.5"));
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
		return String.format("Conta [titular=%s, qtDependentes=%s, dtRegistro=%s, saldo=%s, tipo=%s]", titular,
				dependentes.size(), dtRegistro, saldo, tipo);
	}

}
