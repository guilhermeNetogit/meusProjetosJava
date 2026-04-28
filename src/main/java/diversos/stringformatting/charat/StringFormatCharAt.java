package diversos.stringformatting.charat;

import java.util.Arrays;

public class StringFormatCharAt {// Eclipse -> Github @guilhermeNetogit 27/03/2026 11:47:12
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		String seq = "123456789";
		String java = "Java";
		int n = 9;

		// Primeira Forma Triângulo Completo
		for (int i = 0; i < seq.length(); i++) {
			for (int j = i; j < seq.length(); j++) {
				System.out.print(seq.charAt(j) + " ");
			}
			System.out.println();
		}
		System.out.println();

		// Segunda Forma apenas primeira linha/coluna aparecem
		// Primeira linha: string completa
		for (int i = 0; i < seq.length(); i++) {
			System.out.print(seq.charAt(i) + " ");
		}
		System.out.println();

		// Demais linhas: um caractere por linha (a partir do índice 1)
		for (int i = 1; i < seq.length(); i++) {
			System.out.println(seq.charAt(i));
		}
		System.out.println();

		// Terceira Forma Triângulo Vazado
		// Primeira linha
		for (int i = 1; i <= n; i++) {
			System.out.print(i + " ");
		}
		System.out.println();

		// Demais linhas
		for (int i = 2; i <= n; i++) {

			// Imprime o número inicial
			System.out.print(i + " ");

			// Calcula espaços internos
			int espacos = (n - i) * 2 - 2;

			for (int j = 0; j < espacos; j++) {
				System.out.print(" ");
			}

			// Imprime o 9 no final

			if (i != n) {
				System.out.println(n);
			} else {
				System.out.println();
			}
		}
		System.out.println();

		char[] jav = new char[4];
		java.getChars(0, 4, jav, 0);
		System.err.println(jav);

		byte[] javBytes = new byte[4];
		java.getBytes(0, 4, javBytes, 0);
		System.out.println(Arrays.toString(javBytes));

	}
}
