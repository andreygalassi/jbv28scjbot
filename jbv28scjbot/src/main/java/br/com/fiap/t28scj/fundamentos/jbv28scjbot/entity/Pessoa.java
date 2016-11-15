package br.com.fiap.t28scj.fundamentos.jbv28scjbot.entity;

import java.time.LocalDate;

/**
 * Destinada as informações dos clientes
 * 
 * @author usuario
 * @since 15/11/2016
 */
public class Pessoa {
	
	private Long id;
	private String nome;
	private String endereco;
	private String cpf;
	private LocalDate dtNascimento;
	private final LocalDate dtRegistro;
	
	public Pessoa(String nome, String endereco, String cpf, LocalDate dtNascimento) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.cpf = cpf;
		this.dtNascimento = dtNascimento;
		this.dtRegistro = LocalDate.now();
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public LocalDate getDtNascimento() {
		return dtNascimento;
	}
	public void setDtNascimento(LocalDate dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	public LocalDate getDtRegistro() {
		return dtRegistro;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Pessoa [id=%s, nome=%s, cpf=%s, dtNascimento=%s]", id, nome,
				cpf, dtNascimento);
	}
	
}
