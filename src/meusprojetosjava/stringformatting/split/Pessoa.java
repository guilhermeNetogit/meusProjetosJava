package meusprojetosjava.stringformatting.split;

public class Pessoa {// Eclipse -> Github @guilhermeNetogit 26/03/2026 23:26:17
	private int cod;
	private String nome;
	private String cpf;
	private int idade;
	private String estadoCivil;
	private int dependentes;

	public Pessoa() {
		super();
	}

	public Pessoa(int cod, String nome, String cpf, int idade, String estadoCivil, int dependentes) {
		super();
		this.cod = cod;
		this.nome = nome;
		this.cpf = cpf;
		this.idade = idade;
		this.estadoCivil = estadoCivil;
		this.dependentes = dependentes;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public int getDependentes() {
		return dependentes;
	}

	public void setDependentes(int dependentes) {
		this.dependentes = dependentes;
	}

	@Override
	public String toString() {
		return "Pessoa [cod: " + cod + ", nome: " + nome + ", cpf: " + cpf + ", idade: " + idade + ", estadoCivil: " + estadoCivil + ", dependentes: " + dependentes + "]";
	}

}