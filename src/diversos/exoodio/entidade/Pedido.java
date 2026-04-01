package diversos.exoodio.entidade;

import java.util.ArrayList;

/**
 * Classe que representa a entidade pedido, qual é a compra dos produtos por um cliente.
 * @author thiago leite https://github.com/tlcdio/LabOOJava/blob/master/src/one/digitalinovation/laboojava/entidade
 */

import java.util.List;

public class Pedido {

	private String codigo;
	private Cliente cliente;
	private List<Produto> produtos;
	private double total;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Pedido() {
		this.produtos = new ArrayList<>();
	}

	public String getProdutosComprados () {
		
		StringBuilder produtos = new StringBuilder();
		produtos.append("[");
		for (Produto produto : getProdutos()) {
			produtos.append(produto.toString());
			produtos.append("\nQtd: ");
			produtos.append(produto.getQtdneg());
			produtos.append(" ");
		}
		produtos.append("]");
		return produtos.toString();
	}
	
	@Override
	public String toString() {
		return "\nPedido de Compra\n{codigo=" + codigo
				+ "\n cliente=" + cliente 
				+ "\n produtos=" + getProdutosComprados()
				+ "\n total=" + String.format("R$ %.2f", total)
				+ "}";
	}
	
}