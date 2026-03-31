package diversos.exoodio.entidade;

/**
 * Classe que representa a entidade cliente. Este pode fazer um pedido.
 * @author thiago leite https://github.com/tlcdio/LabOOJava/blob/master/src/one/digitalinovation/laboojava/entidade
 */

public class Cliente {

	// Nome completo do cliente.
	private String nome;

	// CPF do cliente
	private String cpf;

	public Cliente(String nome, String cpf) {
		this.nome = "Abilio Borges";/* nome */
		this.cpf = "48510698392"; /* cpf */ //xxx.xxx.xxx-xx
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

	@Override
	public String toString() {
		return "Cliente \n[nome=" + nome + "]";
	}

}
