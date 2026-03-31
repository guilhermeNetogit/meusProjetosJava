package diversos.exoodio.entidade.constantes;

public enum Genero {

	/**
	 * Gêneros dos livros vendidos.
	 * 
	 * @author thiago leite https://github.com/tlcdio/LabOOJava/blob/master/src/one/digitalinovation/laboojava/entidade/constantes
	 */
	
	DRAMA(15),
    SUSPENSE(10),
    ROMANCE(5);

    private double fator;

	
    // @param fator Valor *por tipo que influencia no cálculo do frete.
    Genero(double fator) {
        this.fator = fator / 100;
    }
    
	public double getFator() {
		return fator;
	}

}
