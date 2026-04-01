package diversos.exoodio.entidade.constantes;

/**
 * Enumeração que representa os tipos de caderno disponíveis no sistema.
 * <p>
 * Cada tipo possui um fator percentual que influencia no cálculo do valor
 * ou frete do produto.
 * </p>
 *
 * @author GitHub guilhermeNetogit
 * @since 01/04/2026 14:03:33
 */

public enum Tipo {

	M2(2), M5(5), M10(10);

	/** Fator percentual aplicado ao tipo */
	private double fator;

	/**
     * Construtor do enum.
     * <p>
     * Converte o valor percentual informado para formato decimal.
     * </p>
     *
     * @param fator valor percentual que influencia no cálculo
     */
	Tipo(double fator) {
		this.fator = fator / 100.0;
	}

	/**
     * Retorna o fator aplicado ao tipo.
     *
     * @return fator em formato decimal
     */
	public double getFator() {
		return fator;
	}

}
