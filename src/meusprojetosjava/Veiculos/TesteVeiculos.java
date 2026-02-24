package meusprojetosjava.Veiculos;

class TesteVeiculos {

	public static void main(String[] args) {// Eclipse -> Github @guilhermeNetogit 24/02/2026 11:59:23

		Veiculos onix = new Veiculos();

		onix.marca = "Chevrolet";
		onix.modelo = "Onix";
		onix.ano = 2019;
		onix.numPassageiros = 5;
		onix.tipoComb = new String[] {Veiculos.Tipo_Comb[2]};
		onix.capTanqComb = 54;
		onix.consumoCombustivel = 13.1;
		
		Veiculos fusca = new Veiculos();
		
		fusca.marca = "Volkswagen";
		fusca.modelo = "Fusca";
		fusca.ano = 1970;
		fusca.numPassageiros = 5;
		fusca.capTanqComb = 41;
		fusca.consumoCombustivel = 10.0;
		
		Veiculos uno = new Veiculos();
		
		uno.marca = "Fiat";
		uno.modelo = "Uno";
		uno.ano = 1994;
		uno.numPassageiros = 5;
		uno.tipoComb = new String[] {Veiculos.Tipo_Comb[0]}; 
		uno.capTanqComb = 50;
		uno.consumoCombustivel = 10.2;

		
		System.out.println(onix);
		System.out.println();
		System.out.println(fusca);
		System.out.println();
		System.out.println(uno);

	}

}
