package meusprojetosjava.threads.thread8;

public enum CorSemaforo {// Eclipse -> Github @guilhermeNetogit 26/03/2026 14:10:53

	VERDE(5000), AMARELO(1000), VERMELHO(5000);

	private int tempoEspera;

	CorSemaforo(int tempoEspera) {
		this.tempoEspera = tempoEspera;
	}

	public int getTempoEspera() {
		return tempoEspera;
	}

}
