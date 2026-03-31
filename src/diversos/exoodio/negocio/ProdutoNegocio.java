package diversos.exoodio.negocio;

import java.util.Optional;

import diversos.exoodio.basedados.DataBase;
import diversos.exoodio.entidade.Produto;

/**
 * Classe para manipular a entidade {@link Produto}.
 * 
 * @author thiago leite
 */
public class ProdutoNegocio {

	/**
	 * {@inheritDoc}.
	 */
	private DataBase bancoDados;

	/**
	 * Construtor.
	 * 
	 * @param banco Banco de dados para ter armazenar e ter acesso os produtos
	 */
	public ProdutoNegocio(DataBase banco) {
		this.bancoDados = banco;
	}

	/**
	 * Salva um novo produto(livro ou caderno) na loja.
	 * 
	 * @param novoProduto Livro ou caderno que pode ser vendido
	 */
	public void salvar(Produto novoProduto) {

		String codigo = String.format("PR%04d", bancoDados.getProxCodProd());
		novoProduto.setCodigo(codigo);

		boolean produtoRepetido = false;
		for (Produto produto : bancoDados.getProdutos()) {
			if (produto.getCodigo().equals(novoProduto.getCodigo())) {
				produtoRepetido = true;
				System.out.println("Produto já cadastrado.");
				break;
			}
		}

		if (!produtoRepetido) {
			this.bancoDados.adicionarProduto(novoProduto);
			System.out.println("Produto cadastrado com sucesso!");
		}
	}

	/**
	 * Exclui um produto pelo código de cadastro.
	 * 
	 * @param codigo Código de cadastro do produto
	 */
	public void excluir() {

		if (bancoDados.getProdutos().isEmpty()) {
			System.out.println("Não existem produtos cadastrados.");
			return;
		}

		System.out.println("Digite a opção que deseja excluir:\n");

		// Exibir lista numerada
		for (int i = 0; i < bancoDados.getProdutos().size(); i++) {
			Produto produto = bancoDados.getProdutos().get(i);
			System.out.printf("[%d] %s - %s%n", i + 1, produto.getCodigo(), produto.getNome());
		}

		try {
			System.out.print("\nOpção: ");
			int opcao = Integer.parseInt(diversos.exoodio.utilidade.LeitoraDados.lerDado());

			int indice = opcao - 1;

			if (indice >= 0 && indice < bancoDados.getProdutos().size()) {

				Produto produto = bancoDados.getProdutos().get(indice);

				// CONFIRMAÇÃO
				System.out.println("\nDeseja realmente excluir o seguinte item?");
				System.out.printf("Produto: %s (%s)%n", produto.getNome(), produto.getCodigo());
				System.out.print("(s/n): ");

				String confirmacao = diversos.exoodio.utilidade.LeitoraDados.lerDado();

				if (confirmacao.equalsIgnoreCase("s")) {
					bancoDados.removerProduto(indice);
					System.out.println("Produto excluído com sucesso!");
				} else {
					System.out.println("Operação cancelada.");
				}

			} else {
				System.out.println("Opção inválida.");
			}

		} catch (NumberFormatException e) {
			System.out.println("Entrada inválida. Digite um número.");
		}
	}

	/**
	 * Obtem um produto a partir de seu código de cadastro.
	 * 
	 * @param codigo Código de cadastro do produto
	 * @return Optional indicando a existência ou não do Produto
	 */
	public Optional<Produto> consultar(String codigo) {

		for (Produto produto : bancoDados.getProdutos()) {

			if (produto.getCodigo().equalsIgnoreCase(codigo)) {
				return Optional.of(produto);
			}
		}

		return Optional.empty();
	}

	/**
	 * Lista todos os produtos cadastrados.
	 */
	public void listarTodos() {

		if (bancoDados.getProdutos().isEmpty()) {
			System.out.println("Não existem produtos cadastrados");
		} else {

			for (Produto produto : bancoDados.getProdutos()) {
				System.out.println(produto.toString());
			}
		}
	}
}