package meusprojetosjava.exercicio46;

public abstract class FiguraGeometrica {

	private String nome;
	private String cor;

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}

	public String gerarInfo() {

		return String.format("%-8s %s\n", "Nome:", getNome()) 
				+ String.format("%-8s %s\n", "Cor:", getCor());
	}
}
