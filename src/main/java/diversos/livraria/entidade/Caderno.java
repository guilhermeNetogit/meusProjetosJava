package diversos.livraria.entidade;

import diversos.livraria.entidade.constantes.Tipo;

/**
 * Classe que representa um caderno no sistema.
 * <p>
 * Especialização da classe {@link Produto}, contendo informações específicas
 * como o tipo do caderno e regras de cálculo de frete.
 * </p>
 *
 * @author GitHub guilhermeNetogit
 * @since 01/04/2026 14:08:08
 */

public class Caderno extends Produto {

	/* Tipo do caderno. */
	private Tipo tipo;

	/**
	 * Construtor padrão.
	 */
	public Caderno() {
	}

	/**
	 * Retorna o tipo do caderno.
	 *
	 * @return tipo do caderno
	 */
	public Tipo getTipo() {
		return tipo;
	}

	/**
	 * Define o tipo do caderno.
	 *
	 * @param tipo tipo a ser atribuído ao caderno
	 */
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	/**
	 * Retorna o nome do produto.
	 * <p>
	 * O nome é composto pelo tipo do caderno.
	 * </p>
	 *
	 * @return nome do caderno
	 */
	@Override
	public String getNome() {
		return "Caderno " + tipo;
	}

	/**
	 * Calcula o valor do frete do caderno.
	 * <p>
	 * O cálculo considera o preço, a quantidade e o fator associado ao tipo.
	 * </p>
	 *
	 * @return valor do frete
	 */
	@Override
	public double calcularFrete() {
		return (getPreco() * getQtdneg()) * (1 + tipo.getFator());
	}

	/**
	 * Retorna uma representação textual do objeto.
	 *
	 * @return dados do caderno em formato String
	 */
	@Override
	public String toString() {
		return "Caderno {código=" + getCodigo() + ", tipo=" + (tipo != null ? tipo : "SEM_TIPO") + ", preço=" + getPreco() + "}";
	}

}
