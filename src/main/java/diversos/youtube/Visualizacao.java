package diversos.youtube;

public class Visualizacao {

	private Aluno espectador;
	private Video filme;

	public Visualizacao(Aluno espectador, Video filme) {
		super();
		this.espectador = espectador;
		this.filme = filme;
		this.espectador.setTotAssistido(this.espectador.getTotAssistido() + 1);
		this.filme.setViews(this.filme.getViews() + 1);
	}

	public void avaliar() {
		this.filme.setAvaliacao(5);
	}

	public void avaliar(int nota) {
		this.filme.setAvaliacao(nota);
	}

	public void avaliar(double perCent) {
		double tot = 0;
		if (perCent <= 20) {
			tot = 3;
		} else if (perCent <= 50) {
			tot = 5;
		} else if (perCent <= 90) {
			tot = 8;
		} else {
			tot = 10;

		}
		this.filme.setAvaliacao(tot);
	}

	public Aluno getEspectador() {
		return espectador;
	}

	public void setEspectador(Aluno espectador) {
		this.espectador = espectador;
	}

	public Video getFilme() {
		return filme;
	}

	public void setFilme(Video filme) {
		this.filme = filme;
	}

	@Override
	public String toString() {
		return "Visualizacao \n[espectador=" + espectador + ", filme=" + filme + "]\n";
	}
		
}
