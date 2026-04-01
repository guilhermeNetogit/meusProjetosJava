package diversos.exoodio.negocio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import diversos.exoodio.basedados.DataBase;
import diversos.exoodio.entidade.Caderno;
import diversos.exoodio.entidade.Livro;
import diversos.exoodio.entidade.Produto;
import diversos.exoodio.utilidade.LeitoraDados;

/**
 * Classe responsável pelas regras de negócio relacionadas aos produtos.
 * <p>
 * Permite cadastrar, consultar, listar e excluir produtos do sistema,
 * incluindo especializações como {@link Livro} e {@link Caderno}.
 * </p>
 *
 * @author GitHub guilhermeNetogit
 * @since 01/04/2026 16:10:07
 */

public class ProdutoNegocio {

	/** Instância do banco de dados em memória */
	private DataBase bancoDados;

	/**
	 * Construtor.
	 * 
	 * @param banco banco de dados contendo os produtos
	 */
	public ProdutoNegocio(DataBase banco) {
		this.bancoDados = banco;
	}

	/**
     * Salva um novo produto no sistema.
     * <p>
     * Gera automaticamente um código único para o produto.
     * </p>
     *
     * @param novoProduto produto a ser cadastrado
     */
	public void salvar(Produto novoProduto) {

        String codigo = String.format("PR%04d", bancoDados.getProxCodProd());
        novoProduto.setCodigo(codigo);

        bancoDados.adicionarProduto(novoProduto);
        System.out.println("Produto cadastrado com sucesso!");
    }

	/**
     * Exclui apenas produtos do tipo {@link Livro}.
     */
	public void excluirLivro() {
		List<Produto> livros = bancoDados.getProdutos().stream().filter(p -> p instanceof Livro)
				.collect(Collectors.toList());

		if (livros.isEmpty()) {
			System.out.println("Não existem livros cadastrados.");
			return;
		}
		excluir(livros);
	}

	/**
     * Exclui apenas produtos do tipo {@link Caderno}.
     */
	public void excluirCaderno() {
    List<Produto> cadernos = bancoDados.getProdutos().stream()
        .filter(p -> p instanceof Caderno)
        .collect(Collectors.toList());

    if (cadernos.isEmpty()) {
        System.out.println("Não existem cadernos cadastrados.");
        return;
    }
    excluir(cadernos);
}
	
	/**
     * Exibe uma lista de produtos e permite ao usuário selecionar
     * qual deseja excluir.
     *
     * @param lista lista de produtos filtrados
     */
	private void excluir(List<Produto> lista) {

		System.out.println("Digite a opção que deseja excluir:\n");

		// Exibir lista numerada
		for (int i = 0; i < lista.size(); i++) {
			Produto produto = lista.get(i);
			System.out.printf("[%d] %s - %s%n", i + 1, produto.getCodigo(), produto.getNome());
		}

		try {
			System.out.print("\nOpção: ");
			int opcao = Integer.parseInt(LeitoraDados.lerDado());

			int indice = opcao - 1;

			if (indice >= 0 && indice < lista.size()) {

				Produto produto = lista.get(indice);

				// CONFIRMAÇÃO
				System.out.println("\nDeseja realmente excluir o seguinte item?");
				System.out.printf("Produto: %s (%s)%n", produto.getNome(), produto.getCodigo());
				System.out.print("(s/n): ");

				String confirmacao = LeitoraDados.lerDado();

				if (confirmacao.equalsIgnoreCase("s")) {
					bancoDados.removerProduto(bancoDados.getProdutos().indexOf(produto));
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
     * Consulta um produto pelo código.
     *
     * @param codigo código do produto
     * @return {@link Optional} contendo o produto, se encontrado
     */
	public Optional<Produto> consultar(String codigo) {

        return bancoDados.getProdutos().stream()
                .filter(p -> p.getCodigo().equalsIgnoreCase(codigo))
                .findFirst();
    }

	/**
	 * Lista todos os produtos cadastrados no sistema.
	 */
	public void listarTodos() {

		if (bancoDados.getProdutos().isEmpty()) {
			System.out.println("Não existem produtos cadastrados");
			return;
		}
		for (Produto produto : bancoDados.getProdutos()) {
			System.out.println(produto.toString());
		}
	}
}
