package diversos.exoodio.basedados;

import java.util.ArrayList;
import java.util.List;

import diversos.exoodio.entidade.Cliente;
import diversos.exoodio.entidade.Cupom;
import diversos.exoodio.entidade.Pedido;
import diversos.exoodio.entidade.Produto;

/**
 * Classe responsável por simular um banco de dados. Esta faz as inserções e exclusões da
 * aplicação. Atualizações não são permitidas para facilitar o funcionamento da aplicação.
 * @author thiago leite https://github.com/tlcdio/LabOOJava/blob/master/src/one/digitalinovation/laboojava/basedados
 */

public class DataBase {
	
	private int contadorCod = 1;
	
	// Lista que armazena os produtos(livros e cadernos) cadastrados.
	private List<Produto> produtos;
	
	// Lista que armazena os pedidos cadastrados.
	private List<Pedido> pedidos;

	// Lista que armazena os cupons disponíveis.
	private List<Cupom> cupons;
	
	// Cliente cadastrado.
    private Cliente cliente;
    
    public DataBase() {

        this.produtos = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.cliente = new Cliente("Abilio Borges", "48510698392");

        this.cupons = new ArrayList<>();
        cupons.add(new Cupom("CUPOM5", 5));
        cupons.add(new Cupom("CUPOM10", 10));
        cupons.add(new Cupom("CUPOM15", 15));
          
    }

	public int getProxCodProd() {
		return contadorCod++;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public List<Cupom> getCupons() {
		return cupons;
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public void removerProduto(int posicao) {
        produtos.remove(posicao);
    }

    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void removerPedido(int posicao) {
        pedidos.remove(posicao);
    }
}
