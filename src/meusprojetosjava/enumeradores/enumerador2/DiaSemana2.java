package meusprojetosjava.enumeradores.enumerador2;

public enum DiaSemana2 {// Eclipse -> Github @guilhermeNetogit 23/03/2026 10:46:38

	DOMINGO(1), SEGUNDA(2), TERCA(3), QUARTA(4), QUINTA(5), SEXTA(6), SABADO(7);

	private final int valor;

	DiaSemana2(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}
}
