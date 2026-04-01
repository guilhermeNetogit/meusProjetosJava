package diversos.exoodio.console;

import java.util.Optional;

import diversos.exoodio.basedados.DataBase;
import diversos.exoodio.entidade.Caderno;
import diversos.exoodio.entidade.Cliente;
import diversos.exoodio.entidade.Cupom;
import diversos.exoodio.entidade.Livro;
import diversos.exoodio.entidade.Pedido;
import diversos.exoodio.negocio.ClienteNegocio;
import diversos.exoodio.negocio.PedidoNegocio;
import diversos.exoodio.negocio.ProdutoNegocio;
import diversos.exoodio.utilidade.LeitoraDados;

/**
 * Classe responsável por controlar a execução da aplicação via console.
 * <p>
 * Gerencia o fluxo principal do sistema, incluindo autenticação do cliente,
 * exibição de menus e delegação de operações para as camadas de negócio.
 * <p>
 * 
 * @author GitHub guilhermeNetogit
 * @since 01/04/2026 13:49:55
 */

public class Start {

	/** Cliente atualmente logado no sistema */
	private static Cliente clienteLogado = null;
	
	/** Instância simulando o banco de dados em memória */
	private static DataBase banco = new DataBase();

	/** Camada de negócio responsável por operações de cliente */
	private static ClienteNegocio clienteNegocio = new ClienteNegocio(banco);
	
	/** Camada de negócio responsável por operações de pedidos */
	private static PedidoNegocio pedidoNegocio = new PedidoNegocio(banco);
	
	/** Camada de negócio responsável por operações de produtos */
	private static ProdutoNegocio produtoNegocio = new ProdutoNegocio(banco);

	/**
	 * Método main utilizado para inicializar a aplicação.
	 * 
	 * @param args Parâmetros que podem ser passados para auxiliar na execução.
	 */

	/**
     * Método principal responsável por iniciar a aplicação.
     * <p>
     * Controla o fluxo de execução, incluindo autenticação do usuário,
     * exibição de menu e processamento das opções selecionadas.
     * </p>
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
	public static void main(String[] args) {

		System.out.println("Bem vindo ao e-Compras");

		String opcao = "";

		boolean executando = true;
		while (executando) {

			// Processo de login
			if (clienteLogado == null) {
				String cpf = "";

				while (clienteLogado == null) {
					System.out.print("\nDigite o cpf: ");
					cpf = LeitoraDados.lerDado().trim();

					// Validação básica de CPF (11 dígitos e só números)
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
				produtoNegocio.excluirLivro();
				break;
			case "3":
				Caderno caderno = LeitoraDados.lerCaderno();
				produtoNegocio.salvar(caderno);
				break;
			case "4":
				produtoNegocio.excluirCaderno();
				break;
			case "5":
				Pedido pedido = LeitoraDados.lerPedido(banco);
				Optional<Cupom> cupom = LeitoraDados.lerCupom(banco);

				if (cupom.isPresent()) {
					pedidoNegocio.salvar(pedido, cupom.get(), clienteLogado);
				} else {
					pedidoNegocio.salvar(pedido, clienteLogado);
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

	/**
     * Exibe o menu principal da aplicação no console.
     */
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
     * Realiza a identificação do usuário com base no CPF informado.
     * <p>
     * Consulta a base de dados e, caso o cliente exista, realiza o login
     * no sistema.
     * </p>
     *
     * @param cpf CPF do usuário que deseja acessar o sistema
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
