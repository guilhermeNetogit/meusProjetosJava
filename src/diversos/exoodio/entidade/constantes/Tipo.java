package diversos.exoodio.entidade.constantes;

public enum Tipo {

	/**
	 * Tipo dos cadernos vendidos.
	 * 
	 * @author guilhermeNetogit
	 */

	M2(2), M5(5), M10(10);

	private double fator;

	Tipo(double fator) {
		this.fator = fator;
	}

	public double getFator() {
		return fator;
	}

}
