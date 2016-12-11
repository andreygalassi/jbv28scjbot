package br.com.fiap.t28scj.fundamentos.jbv28scjbot.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Emprestimo {


	private Long id;
	private Conta conta;
	private Date dtContratacao;
	private BigDecimal valor;
	private BigDecimal saldoDevedor;
	private List<Movimentacao> movimentacoes;
	private Integer prazo;
	
}
