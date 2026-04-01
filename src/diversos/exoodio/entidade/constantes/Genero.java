package diversos.exoodio.entidade.constantes;

/**
 * Enumeração que representa os gêneros de livros disponíveis no sistema.
 * <p>
 * Cada gênero possui um fator percentual que influencia no cálculo do frete.
 * </p>
 *
 * @author GitHub guilhermeNetogit
 * @since 01/04/2026 13:59:03
 */

public enum Genero {

	DRAMA(15), SUSPENSE(10), ROMANCE(5);

	/* Fator percentual aplicado no cálculo de frete */
	private double fator;

	/**
	 * Construtor do enum.
	 * <p>
	 * Converte o valor percentual informado para formato decimal.
	 * </p>
	 * 
	 * @param fator
	 */
	Genero(double fator) {
		this.fator = fator / 100;
	}
	
	/**
	 * Retorna o fator aplicado ao gênero.
     *
	 * @return
	 */
	public double getFator() {
		return fator;
	}

}
