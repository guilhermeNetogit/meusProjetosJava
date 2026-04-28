package diversos.castinginstanceof;

import java.util.Date;

public class Pessoa {

	private String nome;
	private Date dtnasc;
	private String endereco;
	private String telefone;
	private String cpf;

	protected String nomeVisivel;

	public Pessoa(String nome, Date dtnasc, String endereco, String telefone) {
		super();
		this.nome = nome;
		this.dtnasc = dtnasc;
		this.endereco = endereco;
		this.telefone = telefone;
	}

	public Pessoa() {
	}

	public Date getDtnasc() {
		return dtnasc;
	}

	public void setDtnasc(Date dtnasc) {
		this.dtnasc = dtnasc;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String obterEtiquetaEndereco() {
		String s = "Endereço da Pessoa: ";
		s += this.getEndereco();

		return s;
	}
}
