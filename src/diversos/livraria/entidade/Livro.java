package diversos.livraria.entidade;

import diversos.livraria.entidade.constantes.Genero;

/**
 * Classe que representa um livro no sistema.
 * <p>
 * Especialização da classe {@link Produto}, contendo informações específicas
 * como nome e gênero, além de regras próprias de cálculo de frete.
 * </p>
 *
 * @author GitHub guilhermeNetogit
 * @since 01/04/2026 15:45:21
 */

public class Livro extends Produto {

	/** Nome do livro */
	private String nome;
	
	/** Genero do livro */
	private Genero genero;

	/**
     * Construtor padrão.
     */
	public Livro() {
	}
	
	/**
     * Retorna o nome do livro.
     *
     * @return nome do livro
     */
	@Override
	public String getNome() {
		return nome;
	}
	
	/**
     * Define o nome do livro.
     *
     * @param nome nome a ser atribuído
     */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
     * Retorna o gênero do livro.
     *
     * @return gênero do livro
     */
	public Genero getGenero() {
		return genero;
	}

	/**
     * Define o gênero do livro.
     *
     * @param genero gênero a ser atribuído
     */
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	/**
     * Calcula o valor do frete do livro.
     * <p>
     * O cálculo considera o preço, a quantidade e o fator do gênero.
     * </p>
     *
     * @return valor do frete
     */
	@Override
    public double calcularFrete() {
        return (getPreco() * getQtdneg()) * (1 + (genero != null ? genero.getFator() : 0));
    }

	/**
     * Retorna uma representação textual do livro.
     *
     * @return dados do livro em formato String
     */
	@Override
	public String toString() {
		return "Livro {código()=" + getCodigo()
				+ ", nome=" + nome
				+ ", gênero=" + genero
				+ ", preço()=" + getPreco()
				+ "}";
	}

	/**
     * Exibe uma mensagem informando que a listagem de livros está em construção.
     * 
     * TODO
     */
	public void consultarLivro() {
		System.out.println("Lista de livros em construção...");
	}
}
