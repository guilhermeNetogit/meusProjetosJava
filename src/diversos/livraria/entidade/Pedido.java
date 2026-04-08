package diversos.livraria.entidade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe que representa um pedido de compra no sistema.
 * <p>
 * Um pedido está associado a um {@link Cliente} e contém uma lista de
 * {@link Produto}s adquiridos, além do valor total da compra.
 * </p>
 *
 * @author GitHub guilhermeNetogit
 * @since 01/04/2026 15:15:22
 * @version 02/04/2026 21:00
 */

public class Pedido {

	/** Código identificador do pedido */
    private String codigo;

    /** Cliente responsável pelo pedido */
    private Cliente cliente;

	/** Lista de produtos incluídos no pedido */
	private List<Produto> produtos;

	/** Valor total do pedido */
	private double total;
	
	/**
     * Construtor padrão.
     * Inicializa a lista de produtos.
     */
	public Pedido() {
		this.produtos = new ArrayList<>();
	}

	/**
	 * Retorna o cliente do pedido.
	 *
	 * @return cliente associado ao pedido
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Define o cliente do pedido.
	 *
	 * @param cliente cliente a ser associado
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Retorna o valor total do pedido.
	 *
	 * @return valor total
	 */
	public double getTotal() {
		return total;
	}

	/**
     * Define o valor total do pedido.
     *
     * @param total valor total a ser atribuído
     */
	public void setTotal(double total) {
		this.total = total;
	}

	/**
     * Retorna o código do pedido.
     *
     * @return código identificador
     */
	public String getCodigo() {
		return codigo;
	}

	/**
     * Define o código do pedido.
     *
     * @param codigo código identificador
     */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
     * Retorna a lista de produtos do pedido.
     *
     * @return lista de produtos
     */
	public List<Produto> getProdutos() {
		return Collections.unmodifiableList(produtos);
	}
	
	/**
     * Define a lista de produtos do pedido.
     *
     * @param produtos lista de produtos
     */
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	/**
     * Adiciona um novo produto à lista de itens do pedido.
     * <p>
     * Este método permite a inclusão controlada de produtos, mantendo o 
     * encapsulamento da lista interna de produtos do pedido.
     * </p>
     * 
     * @param produto O objeto {@link Produto} que será incluído no pedido.
     *                Não deve ser nulo.
     */
	public void adicionarProduto(Produto produto) {
		if (this.produtos == null) {
			this.produtos = new ArrayList<>();
		}
		this.produtos.add(produto);
	}

	/**
     * Retorna uma representação formatada dos produtos comprados.
     *
     * @return string com os produtos e suas quantidades
     */
	public String getProdutosComprados() {

		StringBuilder produtosStr = new StringBuilder();
		produtosStr.append("\n[");
		for (Produto produto : getProdutos()) {
			produtosStr.append(produto.toString());
			produtosStr.append(" | Qtd: ");
			produtosStr.append(produto.getQtdneg());
			produtosStr.append("\n ");
		}
		produtosStr.append("]");
		return produtosStr.toString();
	}
	
	/**
     * Retorna uma representação textual do pedido.
     *
     * @return dados do pedido formatados
     */
	@Override
	public String toString() {
	    return "\nPedido de Compra {" 
	        + "\n  codigo  = " + codigo
	        + "\n  cliente = " + cliente
	        + "\n  total   = " + String.format("R$ %.2f", total)
	        + "\n  produtos= " + getProdutosComprados()
	        + "\n}";
	}
	
}