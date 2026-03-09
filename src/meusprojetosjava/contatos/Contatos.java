package meusprojetosjava.contatos;

public class Contatos {// Eclipse -> Github @guilhermeNetogit 08/03/2026 22:20:58
	
	private String nome;
	private Endereco endereco;
	private Telefone[] telefones;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public Telefone[] getTelefones() {
		return telefones;
	}
	public void setTelefones(Telefone[] telefones) {
		this.telefones = telefones;
	}
}
