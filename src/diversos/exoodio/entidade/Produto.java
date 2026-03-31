package diversos.exoodio.entidade;

/**
 * Classe que representa a abstração dos produtos que podem ser vendidos pela loja.
 * @author thiago leite https://github.com/tlcdio/LabOOJava/blob/master/src/one/digitalinovation/laboojava/entidade
 */

public abstract class Produto {

	// Cód. produto
	private String codigo;
	
	// Valor unitário do produto.
	private double preco;
	
	// Quantidade comprada do produto.
	private int qtdneg;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQtdneg() {
		return qtdneg;
	}

	public void setQtdneg(int qtdneg) {
		this.qtdneg = qtdneg;
	}
	
	public abstract String getNome();
	
	/**
     * Calcula o preço do frete para os produtos comprados. Este cálculo pode
     * variar de acordo com o produto
	 * @return 
     * @return valor do frete para o determinado produto
     */
	
	 //TODO Método de cálculo de frete
	public abstract double calcularFrete();
	   
}
