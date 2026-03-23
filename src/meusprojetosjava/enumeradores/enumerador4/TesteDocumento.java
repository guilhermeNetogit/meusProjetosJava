package meusprojetosjava.enumeradores.enumerador4;

public class TesteDocumento {// Eclipse -> Github @guilhermeNetogit 23/03/2026 16:53:59

	public static void main(String[] args) {

		for (TipoDocumento doc : TipoDocumento.values()) {
			String s = String.format("%-4s - %s", doc, doc.geraNumeroTeste());
			System.out.println(s);

		}

		System.out.println();

		Pessoa pf = new Pessoa();
		pf.setTipoDocumento(Enum.valueOf(TipoDocumento.class, "CPF"));
		pf.setNumeroDocumento(pf.getTipoDocumento().geraNumeroTeste());
		System.out.println(pf);

		Pessoa pj = new Pessoa();
		pj.setTipoDocumento(Enum.valueOf(TipoDocumento.class, "CNPJ"));
		pj.setNumeroDocumento(pj.getTipoDocumento().geraNumeroTeste());
		System.out.println(pj);

	}

}