package diversos.exoodio.entidade;

import diversos.exoodio.entidade.constantes.Genero;

/**
 * Classe que representa um livro, qual é uma especialização de um produto da loja.
 * @author thiago leite https://github.com/tlcdio/LabOOJava/blob/master/src/one/digitalinovation/laboojava/entidade
 */

public class Livro extends Produto {

	// Nome do livro.
	private String nome;
	
	// Genero do livro.
	private Genero genero;

	public Livro() {
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	// {@inheritDoc}.
	
	@Override
    public double calcularFrete() {
        return (getPreco() * getQtdneg()) * (1 + genero.getFator());
    }

	@Override
	public String toString() {
		return "Livro \n{nome=" + nome
				+ ", gênero=" + genero
				+ ", código()=" + getCodigo()
				+ ", preço()=" + getPreco()
				+ "}";
	}

	public void consultarLivro() {
		System.out.println("Lista de livros em construção...");
	}
}
