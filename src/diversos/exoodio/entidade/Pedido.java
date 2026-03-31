package diversos.exoodio.entidade;

/**
 * Classe que representa a entidade pedido, qual é a compra dos produtos por um cliente.
 * @author thiago leite https://github.com/tlcdio/LabOOJava/blob/master/src/one/digitalinovation/laboojava/entidade
 */

import java.util.List;

public class Pedido {
	
	private String codigo;
	private List<Produto> produtos;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	// TODO Preencher esta classe

	// codigo
	// cliente
	// produtos
	// total
}