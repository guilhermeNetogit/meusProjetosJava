package diversos.livraria.basedados;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import diversos.livraria.entidade.Cliente;
import diversos.livraria.entidade.Cupom;
import diversos.livraria.entidade.Pedido;
import diversos.livraria.entidade.Produto;

/**
 * Classe responsável por simular um banco de dados em memória (apenas em runtime).
 * <p>
 * Realiza operações de inserção e exclusão da aplicação. 
 * Atualizações não são permitidas para facilitar o fluxo da aplicação.
 * </p>
 * 
 * @author GitHub guilhermeNetogit
 * @since 01/04/2026 13:23:10
 */

public class DataBase {

	/** Contador utilizado para gerar códigos únicos de produtos */
	private int contadorCod = 1;

	/* Lista de produtos cadastrados (livros e cadernos) */
	private List<Produto> produtos;

	/* Lista de pedidos cadastrados */
	private List<Pedido> pedidos;

	/* Lista de cupons disponíveis */
	private List<Cupom> cupons;
	
	/* Lista de clientes disponíveis */
	private List<Cliente>  clientes;

	/** Cliente cadastrado no sistema */
	//private Cliente cliente;

	/**
	 * Construtor padrão da classe.
	 * Inicializa listas e dados padrão do sistema.
	 */
	public DataBase() {

		this.produtos = new ArrayList<>();
		this.pedidos = new ArrayList<>();
		this.clientes = new ArrayList<>();
		this.cupons = new ArrayList<>();

		cupons.add(new Cupom("CUPOM5", 5));
		cupons.add(new Cupom("CUPOM10", 10));
		cupons.add(new Cupom("CUPOM15", 15));
		clientes.add(new Cliente("Abilio Borges", "12345678901"));
	}

	/**
	 * Retorna o próximo código sequencial para um produto.
	 * 
	 * @return código inteiro único do produto
	 */
	public int getProxCodProd() {
		return contadorCod++;
	}

	/**
	 * Retorna a lista de produtos cadastrados.
	 * 
	 * @return lista imutável de produtos
	 */
	public List<Produto> getProdutos() {
		return Collections.unmodifiableList(produtos);
	}

	/**
	 * Retorna a lista de pedidos cadastrados.
	 * 
	 * @return lista imutável de pedidos
	 */
	public List<Pedido> getPedidos() {
		return Collections.unmodifiableList(pedidos);
	}

	 /**
     * Retorna a lista de cupons disponíveis.
     *
     * @return lista imutável de cupons
     */
	public List<Cupom> getCupons() {
		return Collections.unmodifiableList(cupons);
	}

	/**
	 * Retorna o cliente cadastrado.
	 * 
	 * @return cliente do sistema
	 */
	public List<Cliente> getClientes() {
		return Collections.unmodifiableList(clientes);
	} 
	
	/**
	 * Adiciona um novo produto à lista.
	 * @param produto produto a ser adicionado
	 */
	public void adicionarProduto(Produto produto) {
		produtos.add(produto);
	}

	/**
	 * Remove um produto com base na posição informada.
	 * @param posicao índice do produto na lista
	 */
	public void removerProduto(int posicao) {
		produtos.remove(posicao);
	}

	/**
	 * Adiciona um novo pedido à lista.
	 * 
	 * @param pedido pedido a ser adicionado
	 */
	public void adicionarPedido(Pedido pedido) {
		pedidos.add(pedido);
	}

	/**
     * Remove um pedido com base na posição informada.
     *
     * @param posicao índice do pedido na lista
     */
	public void removerPedido(int posicao) {
		pedidos.remove(posicao);
	}
}
