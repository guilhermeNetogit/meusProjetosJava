package meusprojetosjava.impostorenda;

public class TesteImpostoRenda {// Eclipse -> Github @guilhermeNetogit 14/03/2026 19:06:41

	public static void main(String[] args) {

		PessoaJuridica p1 = new PessoaJuridica();
		p1.setNome("Empresa Pessoa Jurídica 1");
		p1.setCnpj("44.379.462/0001-37");
		p1.setRendaBruta(15350);

		PessoaJuridica p2 = new PessoaJuridica();
		p2.setNome("Empresa Pessoa Jurídica 2");
		p2.setCnpj("17.686.485/0001-11");
		p2.setRendaBruta(20000.99);

		PessoaJuridica p3 = new PessoaJuridica();
		p3.setNome("Empresa Pessoa Jurídica 3");
		p3.setCnpj("66.905.259/0001-90");
		p3.setRendaBruta(50000);

		PessoaFisica p4 = new PessoaFisica();
		p4.setNome("Pessoa Física 1");
		p4.setCpf("307.961.650-25");
		p4.setRendaBruta(2428.80);// 2428.95

		PessoaFisica p5 = new PessoaFisica();
		p5.setNome("Pessoa Física 2");
		p5.setCpf("681.816.970-81");
		p5.setRendaBruta(3585.51);

		PessoaFisica p6 = new PessoaFisica();
		p6.setNome("Pessoa Física 3");
		p6.setCpf("321.861.300-09");
		p6.setRendaBruta(6592.9);

		Contribuinte[] contribuinte = new Contribuinte[6];

		contribuinte[0] = p1;
		contribuinte[1] = p2;
		contribuinte[2] = p3;
		contribuinte[3] = p4;
		contribuinte[4] = p5;
		contribuinte[5] = p6;

		for (Contribuinte c : contribuinte) {
			System.out.println(c.gerarRelatorio());
			System.out.println("--------------------------------------------------------------------------------");
		}
	}

}
