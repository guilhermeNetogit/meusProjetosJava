package diversos.exoodio.negocio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import diversos.exoodio.basedados.DataBase;
import diversos.exoodio.entidade.Cliente;
import diversos.exoodio.entidade.Cupom;
import diversos.exoodio.entidade.Pedido;
import diversos.exoodio.entidade.Produto;

/**
 * Classe responsável pelas regras de negócio relacionadas aos pedidos.
 * <p>
 * Realiza operações como cadastro, exclusão, listagem e cálculo do valor total
 * dos pedidos, aplicando regras como descontos e frete.
 * </p>
 *
 * @author GitHub guilhermeNetogit
 * @since 01/04/2026 15:27:15
 */

public class PedidoNegocio {

	/** Instância do banco de dados em memória */
    private DataBase bancoDados;

    /**
     * Construtor.
     * 
     * @param banco banco de dados utilizado para persistência dos pedidos
     */
    public PedidoNegocio(DataBase banco) {
        this.bancoDados = banco;
    }

    /**
     * Calcula o valor total de um pedido.
     * <p>
     * O cálculo considera o frete de cada produto e aplica desconto
     * caso um cupom seja informado.
     * </p>
     *
     * @param produtos lista de produtos do pedido
     * @param cupom cupom de desconto (opcional)
     * @return valor total calculado
     */
    private double calcularTotal(List<Produto> produtos, Cupom cupom) {

        double total = 0.0;
        for (Produto produto: produtos) {
            total += produto.calcularFrete();
        }

        if (cupom != null) {
            return  total * (1 - cupom.getDesconto()/100.0);
        } else {
            return  total;
        }

    }

    /**
     * Salva um novo pedido sem cupom de desconto.
     *
     * @param novoPedido pedido a ser armazenado
     * @param cliente cliente responsável pelo pedido
     */
    public void salvar(Pedido novoPedido, Cliente cliente) {
        salvar(novoPedido, null, cliente);
    }

    /**
     * Salva um novo pedido com cupom de desconto.
     *
     * @param novoPedido pedido a ser armazenado
     * @param cupom cupom de desconto aplicado
     * @param cliente cliente responsável pelo pedido
     */
    public void salvar(Pedido novoPedido, Cupom cupom, Cliente cliente) {
    	
        LocalDate hoje = LocalDate.now();
        String codigo = String.format("PED%02d%02d%s%04d"
        		, hoje.getMonthValue()
        		, hoje.getDayOfMonth()
        		, hoje.format(DateTimeFormatter.ofPattern("yy"))
        		, bancoDados.getPedidos().size() + 1);
    	
        novoPedido.setCodigo(codigo);
        novoPedido.setCliente(cliente);
        novoPedido.setTotal(calcularTotal(novoPedido.getProdutos(), cupom));
        bancoDados.adicionarPedido(novoPedido);
        System.out.println("Pedido cadastrado com sucesso!");
    
    }

    /**
     * Exclui um pedido com base no código informado.
     *
     * @param codigo código identificador do pedido
     */
	public void excluir(String codigo) {

		int indiceRemocao = -1;
		for (int i = 0; i < bancoDados.getPedidos().size(); i++) {

			Pedido pedido = bancoDados.getPedidos().get(i);

			if (pedido.getCodigo().equals(codigo)) {
				indiceRemocao = i;
				break;
			}
		}

		if (indiceRemocao != -1) {
			bancoDados.removerPedido(indiceRemocao);
			System.out.println("Pedido excluído com sucesso.");
		} else {
			System.out.println("Pedido inexistente.");
		}
	}

	/**
	 * Lista todos os pedidos confirmados no sistema.
	 */
	public void listarTodos() {

		if (bancoDados.getPedidos().isEmpty()) {
			System.out.println("Não existem pedidos confirmados");
			return;
		}

		for (Pedido pedido : bancoDados.getPedidos()) {
			System.out.println(pedido.toString());
		}
	}

}