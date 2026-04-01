package diversos.exoodio.console;

import java.util.Optional;

import diversos.exoodio.basedados.DataBase;
import diversos.exoodio.entidade.Cliente;
import diversos.exoodio.entidade.Cupom;
import diversos.exoodio.entidade.Livro;
import diversos.exoodio.entidade.Pedido;
import diversos.exoodio.negocio.ClienteNegocio;
import diversos.exoodio.negocio.PedidoNegocio;
import diversos.exoodio.negocio.ProdutoNegocio;
import diversos.exoodio.utilidade.LeitoraDados;

/**
 * Classe responsável por controlar a execução da aplicação.
 * 
 * @author thiago leite
 *         https://github.com/tlcdio/LabOOJava/tree/master/src/one/digitalinovation/laboojava/console
 */

public class Start {

	private static Cliente clienteLogado = null;

	private static DataBase banco = new DataBase();

	private static ClienteNegocio clienteNegocio = new ClienteNegocio(banco);
	private static PedidoNegocio pedidoNegocio = new PedidoNegocio(banco);
	private static ProdutoNegocio produtoNegocio = new ProdutoNegocio(banco);

	/**
	 * Método utilitário para inicializar a aplicação.
	 * 
	 * @param args Parâmetros que podem ser passados para auxiliar na execução.
	 */

	public static void main(String[] args) {

		System.out.println("Bem vindo ao e-Compras");

		String opcao = "";

		boolean executando = true;
		while (executando) {

			if (clienteLogado == null) {
				String cpf = "";

				while (clienteLogado == null) {
					System.out.print("\nDigite o cpf: ");
					cpf = LeitoraDados.lerDado().trim();

					// Valida se tem 11 dígitos e só números
					if (!cpf.matches("\\d{11}")) {
						System.out.println("CPF inválido! Digite apenas os 11 números sem pontos ou traços.");
						continue; // volta pro início do while
					}

					// CPF tem formato válido, tenta identificar
					identificarUsuario(cpf);

					if (clienteLogado == null) {
						System.out.println("CPF não cadastrado. Tente novamente...");
					}
				}
			}
			exibirMenu();

			opcao = LeitoraDados.lerDado();

			switch (opcao) {
			case "1":
				Livro livro = LeitoraDados.lerLivro();
				produtoNegocio.salvar(livro);
				break;
			case "2":
				System.out.println("Digite o código do livro");
				produtoNegocio.excluir();
				break;
			case "3":
				// TODO Cadastrar Caderno
				break;
			case "4":
				// TODO Excluir Caderno
				break;
			case "5":
				Pedido pedido = LeitoraDados.lerPedido(banco);
				Optional<Cupom> cupom = LeitoraDados.lerCupom(banco);

				if (cupom.isPresent()) {
					pedidoNegocio.salvar(pedido, cupom.get());
				} else {
					pedidoNegocio.salvar(pedido);
				}
				break;
			case "6":
				System.out.println("Digite o código do pedido");
				String codigoPedido = LeitoraDados.lerDado();
				pedidoNegocio.excluir(codigoPedido);
				break;
			case "7":
				produtoNegocio.listarTodos();
				break;
			case "8":
				pedidoNegocio.listarTodos();
				break;
			case "9":
				System.out.println(String.format("Volte sempre %s!", clienteLogado.getNome()));
				clienteLogado = null;
				break;
			case "0":
				System.out.println("Aplicação encerrada.");

				executando = false;
				break;
			default:
				System.out.println("Opção inválida.");
				break;
			}
		}
		LeitoraDados.fecharScanner();
	}

	public static void exibirMenu() {
		System.out.println("\nSelecione uma opção:");
		System.out.println("[1] - Cadastrar Livro");
		System.out.println("[2] - Excluir Livro");
		// TODO Desafio: Consultar Livro(nome)
		System.out.println("[3] - Cadastrar Caderno");
		System.out.println("[4] - Excluir Caderno");
		// TODO Desafio: Consultar Caderno(matéria)
		System.out.println("[5] - Fazer pedido");
		System.out.println("[6] - Excluir pedido");
		// TODO Desafio: Consultar Pedido(código)
		System.out.println("[7] - Listar produtos");
		System.out.println("[8] - Listar pedidos");
		System.out.println("[9] - Deslogar");
		System.out.println("[0] - Sair");
	}

	/**
	 * Procura o usuário na base de dados.
	 * 
	 * @param cpf CPF do usuário que deseja logar na aplicação
	 */
	private static void identificarUsuario(String cpf) {

		Optional<Cliente> resultado = clienteNegocio.consultar(cpf);

		if (resultado.isPresent()) {

			Cliente cliente = resultado.get();
			System.out.println(String.format("\nOlá %s! Você está logado.", cliente.getNome()));
			clienteLogado = cliente;
		} else {
			System.out.println("Usuário não cadastrado.");

		}
	}

}
