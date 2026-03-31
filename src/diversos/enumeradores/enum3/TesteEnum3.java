package diversos.enumeradores.enum3;

import diversos.enumeradores.enum2.DiaSemana2;

public class TesteEnum3 {// Eclipse -> Github @guilhermeNetogit 23/03/2026 10:47:09

	public static void main(String[] args) {

		DiaSemana2[] dias = DiaSemana2.values();

		for (int i = 0; i < dias.length; i++) {
			String s = String.format("%-7s - %s", dias[i], dias[i].getValor());

			System.out.println(s);
		}

		System.out.println("-------------");

		for (DiaSemana2 dia : DiaSemana2.values()) {
			String s = String.format("%-7s - %s", dia, dia.getValor());

			System.out.println(s);
		}

		System.out.println("-------------");

		System.out.println("Exemplo 1: Enum.valueOf -> " + Enum.valueOf(DiaSemana2.class, "DOMINGO"));

		DiaSemana2 dia = Enum.valueOf(DiaSemana2.class, "DOMINGO");

		System.out.println("Exemplo 2: Enum.valueOf -> " + dia);
	}

}
