package meusprojetosjava.patterns.trianguleletter;

public class TrianguleLetter {// Eclipse -> Github @guilhermeNetogit 18/03/2026 15:03:02

	public static void main(String[] args) {

		int letter = 65; // O número 65 é o código ASCII da letra 'A'. 
						 // Em Java, (char) 65 vira 'A', 66 vira 'B', e assim por diante.

		for (int i = 0; i <= 12; i++) { // O laço externo (i) controla as linhas — vai de 0 a 12, ou seja, 13 linhas no total.
			for (int j = 0; j <= i; j++) {
				System.out.print((char) letter + " ");
			}
			letter++;
			System.out.println();
		}

	}
}