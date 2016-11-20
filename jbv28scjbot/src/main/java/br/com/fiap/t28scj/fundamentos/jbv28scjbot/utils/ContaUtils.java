package br.com.fiap.t28scj.fundamentos.jbv28scjbot.utils;

import br.com.fiap.t28scj.fundamentos.jbv28scjbot.entity.Conta;
import br.com.fiap.t28scj.fundamentos.jbv28scjbot.entity.Pessoa;
import br.com.fiap.t28scj.fundamentos.jbv28scjbot.entity.TipoConta;

public class ContaUtils {

	private Conta conta;

	public void criarConta(Pessoa pessoa, TipoConta tipoConta) {
		conta = new Conta(pessoa, tipoConta);
	}

	public void modificarConta(String endereço) {
		conta.getTitular().setEndereco(endereço);
	}

	public void incluirDependentes(Pessoa dependente) {
		conta.getDependentes().add(dependente);
	}

	public String getDadosTitular() {
		return "Titular: " + conta.getTitular();
	}

	public String getDadosDependentes() {
		return "Dependentes: \n" + conta.getDependentes().toString();
	}

	public boolean contaJaFoiCriada() {
		return conta != null;
	}

}
