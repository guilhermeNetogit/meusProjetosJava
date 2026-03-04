package meusprojetosjava.Veiculos2;

public class TesteVeiculos2 {// Eclipse -> Github @guilhermeNetogit 03/03/2026 22:24:59

	public static void main(String[] args) {

		// ===================== SAVEIRO =====================
		Veiculos2 saveiro = new Veiculos2();
		saveiro.setMarca("Volkswagen");
		saveiro.setModelo("Saveiro Surf 1.8");
		saveiro.setAno(2008);
		//saveiro.setNumPassageiros(2); // comentado para usar o parâmetro da instância;
		saveiro.tipoComb = new String[] {Veiculos2.Tipo_Comb[2]};
		saveiro.setCapTanqComb(53);
		saveiro.setConsumoCombustivel(9.5);
		saveiro.setValor(21924);

		System.out.println(saveiro.getNumPassageiros() + " - R$" + saveiro.getValor());
		System.out.println();
		
		System.out.println("Utilizando exemplo de Encapsulamento");
		System.out.println(saveiro);
		saveiro.exibirAutonomia();
		double autonomiaSaveiro = saveiro.obterAutonomia();
		System.out.println("A autonomia do veículo (via return) é: " + autonomiaSaveiro + " km");

		System.out.println();

		// ===================== ONIX =====================
		Veiculos2 onix = new Veiculos2("Chevrolet", "Onix Joy", 2019, 5, new String[] { Veiculos2.Tipo_Comb[2] }, 54,
				13.1, 50000);

		System.out.println(onix);
		onix.exibirAutonomia();
		double autonomiaOnix = onix.obterAutonomia();
		System.out.println("A autonomia do veículo (via return) é: " + autonomiaOnix + " km");

		System.out.println();

		// ===================== FUSCA =====================
		Veiculos2 fusca = new Veiculos2("Volkswagen", "Fusca", 1970, 5, new String[] { Veiculos2.Tipo_Comb[0] }, 41, 10,
				32000);

		System.out.println(fusca);
		fusca.exibirAutonomia();
		double autonomiaFusca = fusca.obterAutonomia();
		System.out.println("A autonomia do veículo (via return) é: " + autonomiaFusca + " km");

		System.out.println();

		// ===================== UNO =====================
		Veiculos2 uno = new Veiculos2("Fiat", "Uno Eletronic", 1994, 5, new String[] { Veiculos2.Tipo_Comb[0] }, 50,
				10.2, 7000);
		System.out.println(uno);
		uno.exibirAutonomia();
		double autonomiaUno = uno.obterAutonomia();
		System.out.println("A autonomia do veículo (via return) é: " + autonomiaUno + " km");

		System.out.println();
		
		// ===================== OPALA =====================
		Veiculos2 opala = new Veiculos2("Chevrolet", "Opala Comodoro", 1972, 5, new String[] { Veiculos2.Tipo_Comb[0] }, 60,
				5.5, 85000);
		System.out.println(opala);
		opala.exibirAutonomia();
		double autonomiaOpala = opala.obterAutonomia();
		System.out.println("A autonomia do veículo (via return) é: " + autonomiaOpala + " km");

		System.out.println();
		
		// ===================== LANDAU =====================
		Veiculos2 landau = new Veiculos2("Ford", "Landau", 1978, 5, new String[] {Veiculos2.Tipo_Comb[0]}, 107,
				4.5, 120000);
		System.out.println(landau);
		landau.exibirAutonomia();
		double autonomiaLandau = landau.obterAutonomia();
		System.out.println("A autonomia do veículo (via return) é: " + autonomiaLandau + " km");

		System.out.println();

	}

}
