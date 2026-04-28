package diversos.contatos.contatos2;

import java.util.Arrays;

public class Contatos2 {// Eclipse -> Github @guilhermeNetogit 24/03/2026 20:33:15

	private static int count = 0;

	private int id;
	String[] tipoContato;
	private String nome;
	private String endereco;
	private String[] telefones;
	private String email;

	public Contatos2() {
		count++;
		id = count;
	}

	public int getId() {
		return id;
	}

	public String[] getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(String[] tipoContato) {
		this.tipoContato = tipoContato;
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

	public String[] getTelefones() {
		return telefones;
	}

	public void setTelefones(String[] telefones) {
		this.telefones = telefones;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// Array fixo (tipo de contato)
	public static final String[] TIPO_CONTATO = { "Cliente", // [0]
			"Fornecedor", 	 // [1]
			"Funcionário",	 // [2]
			"Usuário",		 // [3]
			"Motorista",	 // [4]
			"Transportadora" // [5]
	};

	public Contatos2(String[] tipoContato, String nome, String endereco, String[] telefones, String email) {

		this.tipoContato = tipoContato;
		this.nome = nome;
		this.endereco = endereco;
		this.telefones = telefones;
		this.email = email;
	}

	@Override
	public String toString() {
		String s = String.format("Id: %s \nTipo de Contato: %s \nNome: %s \nEndereço: %s \nTelefones: %s \nEmail: %s\n",
				id, Arrays.toString(tipoContato), nome, endereco, Arrays.toString(telefones), email);

		return s;
	}

}
