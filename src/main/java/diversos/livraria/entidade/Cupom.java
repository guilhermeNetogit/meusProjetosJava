package diversos.livraria.entidade;

/**
 * Classe que representa um cupom de desconto no sistema.
 * <p>
 * O cupom pode ser utilizado no momento do fechamento do pedido para aplicar um
 * desconto percentual sobre o valor total.
 * </p>
 *
 * @author GitHub guilhermeNetogit
 * @since 01/04/2026 15:40:49
 */

public class Cupom {

	/** Código do cupom. */
	private String codigo;

	/** Percentual de desconto aplicado ao pedido. */
	private double desconto;

	/**
	 * Construtor do cupom
	 * 
	 * @param codigo   código do cupom
	 * @param desconto percentual de desconto (ex: 10 para 10%)
	 */
	public Cupom(String codigo, double desconto) {
		this.codigo = codigo;
		this.desconto = desconto;
	}

	/**
	 * Retorna o código do cupom.
	 *
	 * @return código do cupom
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Define o código do cupom.
	 *
	 * @param codigo código a ser atribuído
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Retorna o percentual de desconto.
	 *
	 * @return valor do desconto em porcentagem
	 */
	public double getDesconto() {
		return desconto;
	}

	/**
	 * Define o percentual de desconto.
	 *
	 * @param desconto valor a ser atribuído
	 */
	public void setDesconto(double desconto) {
		if (desconto < 0 || desconto > 100) {
			throw new IllegalArgumentException("Desconto deve estar entre 0 e 100");
		}
		this.desconto = desconto;
	}

}
