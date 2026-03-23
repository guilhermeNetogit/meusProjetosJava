package meusprojetosjava.enumeradores.enumerador2;

public class Formulario {// Eclipse -> Github @guilhermeNetogit 23/03/2026 10:46:31

	enum Genero {
		FEMININO('F'), MASCULINO('M');

		@SuppressWarnings("unused")
		private char valor;

		Genero(char valor) {
			this.valor = valor;
		}
	}

	@SuppressWarnings("unused")
	private String nome;
	@SuppressWarnings("unused")
	private Genero genero;
}