package br.com.fiap.t28scj.fundamentos.jbv28scjbot.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.fiap.t28scj.fundamentos.jbv28scjbot.entity.Pessoa;

public class PessoaUtils {

	private Pessoa pessoa;

	public Pessoa criarPessoa(String nome, String endereco, String cpf, String dataNascimento) {
		pessoa = new Pessoa(nome, endereco, cpf,
				LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		return pessoa;
	}

}
