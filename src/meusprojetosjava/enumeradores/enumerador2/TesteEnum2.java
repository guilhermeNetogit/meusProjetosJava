package meusprojetosjava.enumeradores.enumerador2;

public class TesteEnum2 {// Eclipse -> Github @guilhermeNetogit 23/03/2026 10:46:24

	public static void main(String[] args) {

		DiaSemana2 dia = DiaSemana2.SEGUNDA;

		System.out.println(dia.toString() + " - " + dia.getValor());

		@SuppressWarnings("unused")
		Data data = new Data(23, 3, 2026, DiaSemana2.SEGUNDA);
	}

}