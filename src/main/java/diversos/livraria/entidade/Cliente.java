package diversos.livraria.entidade;

/**
 * Classe que representa um cliente do sistema.
 * <p>
 * O cliente pode realizar pedidos e é identificado por seu nome e CPF.
 * </p>
 *
 * @author GitHub guilhermeNetogit
 * @date 01/04/2026 14:18:48
 */

public class Cliente {

	/* Nome completo do cliente. */
	private String nome;

	/* CPF do cliente */
	private String cpf;

	/**
	 * Construtor da classe Cliente.
     *
	 * @param nome nome do cliente
	 * @param cpf CPF do cliente (apenas números)
	 */
	public Cliente(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf; 
	}

	/**
     * Retorna o nome do cliente.
     *
     * @return nome do cliente
     */
	public String getNome() {
		return nome;
	}

	/**
     * Define o nome do cliente.
     *
     * @param nome nome a ser atribuído
     */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
     * Retorna o CPF do cliente.
     *
     * @return CPF do cliente
     */
	public String getCpf() {
		return cpf;
	}

	/**
     * Define o CPF do cliente.
     *
     * @param cpf CPF a ser atribuído
     */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
     * Retorna uma representação textual do cliente.
     *
     * @return dados do cliente em formato String
     */
	@Override
	public String toString() {
		return "Cliente [nome=" + nome + "]";
	}

}
