package meusprojetosjava.stringformatting.tokenizer;

import java.util.StringTokenizer;

public class StringFormatTokenizer {// Eclipse -> Github @guilhermeNetogit 27/03/2026 11:26:14

	public static void main(String[] args) {

		String doArquivo = "1;Antônio;046.164.496-78;30;casado;2;";

		StringTokenizer st = new StringTokenizer(doArquivo, ";");

		while (st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}

	}

}