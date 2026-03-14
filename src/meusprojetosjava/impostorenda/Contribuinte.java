package meusprojetosjava.impostorenda;

public abstract class Contribuinte {// Eclipse -> Github @guilhermeNetogit 14/03/2026 19:07:06

	String nome;
	double rendaBruta;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getRendaBruta() {
		return rendaBruta;
	}

	public void setRendaBruta(double rendaBruta) {
		this.rendaBruta = rendaBruta;
	}

	public abstract double calcularIR();

	public abstract String gerarRelatorio();

	@Override
	public String toString() {
		return String.format("Nome: %s", nome);

	}

}
