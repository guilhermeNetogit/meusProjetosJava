package diversos.exoodio.utilidade;

import java.util.Optional;
import java.util.Scanner;

import diversos.exoodio.basedados.DataBase;
import diversos.exoodio.entidade.Caderno;
import diversos.exoodio.entidade.Cupom;
import diversos.exoodio.entidade.Livro;
import diversos.exoodio.entidade.Pedido;
import diversos.exoodio.entidade.Produto;
import diversos.exoodio.entidade.constantes.Genero;
import diversos.exoodio.entidade.constantes.Tipo;
import diversos.exoodio.negocio.ProdutoNegocio;

/**
 * Classe utilitária responsável pela leitura de dados via console.
 * <p>
 * Centraliza a entrada de informações do usuário para criação de entidades
 * como {@link Livro}, {@link Caderno}, {@link Pedido} e {@link Cupom}.
 * </p>
 *
 * @author GitHub guilhermeNetogit
 * @since 01/04/2026 15:51:13
 */

public final class LeitoraDados {

	/** Classe do Java utilizada para leitura de dados via teclado */
	private static Scanner scanner;

	static {
		scanner = new Scanner(System.in);
	}

	/**
     * Lê um dado informado pelo usuário.
     *
     * @return texto digitado
     */
	public static String lerDado() {
		return scanner.nextLine();
	}

	/**
     * Lê os dados necessários para criação de um livro.
     *
     * @return objeto {@link Livro} preenchido
     */
	public static Livro lerLivro() {

		System.out.println("Cadastrando livro...");
		Livro livro = new Livro();

		System.out.println("Digite o nome");
		livro.setNome(lerDado());

		System.out.println("Digite o gênero: DRAMA, SUSPENSE, ROMANCE");
		String genero = lerDado();

		try {
			livro.setGenero(Genero.valueOf(genero.toUpperCase()));
		} catch (IllegalArgumentException e) {
			System.out.println("Gênero inválido!");
		}

		System.out.println("Digite o preço(padrão 0.0)");
		try {
		    livro.setPreco(Double.parseDouble(lerDado()));
		} catch (NumberFormatException e) {
		    System.out.println("Valor inválido! Usando 0.0");
		    livro.setPreco(0.0);
		}

		return livro;
	}

	/**
     * Lê os dados necessários para criação de um caderno.
     *
     * @return objeto {@link Caderno} preenchido
     */
	public static Caderno lerCaderno() {

		System.out.println("Cadastrando caderno...");
		Caderno caderno = new Caderno();

		System.out.println("Digite a quantidade de matérias: M2, M5, M10");
		String tipo = lerDado();

		try {
			caderno.setTipo(Tipo.valueOf(tipo.toUpperCase()));
		} catch (IllegalArgumentException e) {
			System.out.println("Quantidade de matérias inválida!");
		}

		System.out.println("Digite o preço(padrão 0.0)");
		String preco = lerDado();
		caderno.setPreco(Double.parseDouble(preco));

		return caderno;
	}

	/**
     * Lê os dados necessários para criação de um caderno.
     *
     * @return objeto {@link Caderno} preenchido
     */
	public static Pedido lerPedido(DataBase banco) {

		ProdutoNegocio produtoNegocio = new ProdutoNegocio(banco);

		System.out.println("Cadastrando pedido...");
		Pedido pedido = new Pedido();

		String opcao = "s";

		do {

			System.out.println("Digite o código do produto(livro/Caderno)");
			String codigo = lerDado();

			Optional<Produto> resultado = produtoNegocio.consultar(codigo);
			if (resultado.isPresent()) {

				Produto produto = resultado.get();

				System.out.println("Digite a quantidade");
				String quantidade = lerDado();

				produto.setQtdneg(Integer.parseInt(quantidade));

				pedido.getProdutos().add(produto);
			} else {
				System.out.println("Produto inexistente. Escolha um produto válido");
			}

			System.out.println("Deseja selecionar mais um produto? s/n");
			opcao = lerDado();
		} while ("s".equalsIgnoreCase(opcao));

		return pedido;
	}

	/**
     * Lê um cupom informado pelo usuário.
     *
     * @param banco base de dados contendo cupons disponíveis
     * @return {@link Optional} contendo o cupom, caso encontrado
     */
	public static Optional<Cupom> lerCupom(DataBase banco) {

		System.out.println(
				"Caso queira utilizar algum cupom escolha entre: CUPOM5, CUPOM10, CUPOM15. Se não desejar, deixe em branco.");

		String codigo = lerDado();

		for (Cupom cupom : banco.getCupons()) {
			if (cupom.getCodigo().equalsIgnoreCase(codigo)) {
				return Optional.of(cupom);
			}
		}

		return Optional.empty();
	}

	/**
     * Fecha o scanner utilizado para leitura de dados.
     */
	public static void fecharScanner() {
		scanner.close();
	}

}