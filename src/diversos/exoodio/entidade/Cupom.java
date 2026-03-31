package diversos.exoodio.entidade;

/**
 * Classe que representa a entidade cupom. Este pode ser utilizado no ato do fechamento do pedido
 * para obter um desconto, caso desejado.
 * @author thiago leite https://github.com/tlcdio/LabOOJava/blob/master/src/one/digitalinovation/laboojava/entidade
 */

public class Cupom {
	
	// Código do cupom.
	private String codigo;
	
	// Valor em porcentagem do desconto.
	private double desconto;
	
	 /**
     * Construtor do cupom
     * @param codigo Código do cupom
     * @param desconto Porcentagem de desconto
     */
	public Cupom(String codigo, double desconto) {
		this.codigo = codigo;
		this.desconto = desconto;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
	
	
}
