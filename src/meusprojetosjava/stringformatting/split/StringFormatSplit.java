package meusprojetosjava.stringformatting.split;

public class StringFormatSplit {// Eclipse -> Github @guilhermeNetogit 26/03/2026 23:27:43

	public static void main(String[] args) {

		String doArquivo = "1;Antônio;046.164.496-78;30;casado;2;";
		String[] infos = doArquivo.split(";");
		String cpfLimpo = infos[2].replace(".", "").replace("-", "");
		for (String info : infos) {
			System.out.println(info);
		}
		System.out.println();
		Pessoa pessoa = new Pessoa(Integer.parseInt(infos[0]), infos[1]
				, cpfLimpo, Integer.parseInt(infos[3]), infos[4]
				, Integer.parseInt(infos[5]));
		System.out.println(pessoa);
	}

}