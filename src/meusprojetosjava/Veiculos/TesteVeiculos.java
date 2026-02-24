package meusprojetosjava.Veiculos;

class TesteVeiculos {

	public static void main(String[] args) {// Eclipse -> Github @guilhermeNetogit 24/02/2026 13:20:58	

		Veiculos onix = new Veiculos();

		onix.marca = "Chevrolet";
		onix.modelo = "Onix Joy";
		onix.ano = 2019;
		onix.numPassageiros = 5;
		onix.tipoComb = new String[] {Veiculos.Tipo_Comb[2]};
		onix.capTanqComb = 54;
		onix.consumoCombustivel = 13.1;
		onix.valor = 50000;
		
		Veiculos fusca = new Veiculos();
		
		fusca.marca = "Volkswagen";
		fusca.modelo = "Fusca";
		fusca.ano = 1970;
		fusca.numPassageiros = 5;
		fusca.capTanqComb = 41;
		fusca.consumoCombustivel = 10.0;
		
		Veiculos uno = new Veiculos();
		
		uno.marca = "Fiat";
		uno.modelo = "Uno Eletronic";
		uno.ano = 1994;
		uno.numPassageiros = 5;
		uno.tipoComb = new String[] {Veiculos.Tipo_Comb[0]}; 
		uno.capTanqComb = 50;
		uno.consumoCombustivel = 10.2;
		uno.valor = 7000;

		
		System.out.println(onix);
		onix.exibirAutonomia();
		double autonomiaOnix = onix.obterAutonomia();
		System.out.println("A autonomia do veículo (via return) é: " + autonomiaOnix + " km");
		System.out.println();
		
		System.out.println(fusca);
		fusca.exibirAutonomia();
		double autonomiaFusca = fusca.obterAutonomia();
		System.out.println("A autonomia do veículo (via return) é: " + autonomiaFusca + " km");
		System.out.println();
		
		System.out.println(uno);
		uno.exibirAutonomia();
		double autonomiaUno = uno.obterAutonomia();
		System.out.println("A autonomia do veículo (via return) é: " + autonomiaUno + " km");

	}

}
