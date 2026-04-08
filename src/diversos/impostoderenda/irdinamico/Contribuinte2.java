package diversos.impostoderenda.irdinamico;

public abstract class Contribuinte2 {// Eclipse -> Github @guilhermeNetogit 15/03/2026 22:30:45

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
		return String.format(nome);
	}

}
