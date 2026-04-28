package diversos.livraria.entidade;

/**
 * Classe abstrata que representa um produto da loja.
 * <p>
 * Define os atributos e comportamentos comuns a todos os produtos, como código,
 * preço e quantidade, além de métodos abstratos que devem ser implementados
 * pelas subclasses.
 * </p>
 *
 * @author GitHub guilhermeNetogit
 * @since 01/04/2026 15:35:19
 */

public abstract class Produto {

	/* Cód. produto */
	private String codigo;

	/* Valor unitário do produto. */
	private double preco;

	/* Quantidade comprada do produto. */
	private int qtdneg;

	/**
	 * Retorna o código do produto.
	 *
	 * @return código do produto
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Define o código do produto.
	 *
	 * @param codigo código a ser atribuído
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Retorna o preço do produto.
	 *
	 * @return preço unitário
	 */
	public double getPreco() {
		return preco;
	}

	/**
	 * Define o preço do produto.
	 *
	 * @param preco preço a ser atribuído
	 */
	public void setPreco(double preco) {
		this.preco = preco;
	}

	/**
	 * Retorna a quantidade comprada do produto.
	 *
	 * @return quantidade
	 */
	public int getQtdneg() {
		return qtdneg;
	}

	/**
	 * Define a quantidade comprada do produto.
	 *
	 * @param qtdneg quantidade a ser atribuída
	 */
	public void setQtdneg(int qtdneg) {
		this.qtdneg = qtdneg;
	}

	/**
	 * Retorna o preço do produto.
	 *
	 * @return preço unitário
	 */
	public abstract String getNome();

	/**
	 * Calcula o valor do frete do produto.
	 * <p>
	 * O cálculo pode variar de acordo com o tipo de produto.
	 * </p>
	 *
	 * @return valor do frete
	 */
	public abstract double calcularFrete();

}
