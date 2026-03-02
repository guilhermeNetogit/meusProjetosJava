package meusprojetosjava.Veiculos;

import java.util.Scanner;

public class TesteVeiculos {

	public static void main(String[] args) {// Eclipse -> Github @guilhermeNetogit 01/03/2026 22:09:23

		Scanner scanner = new Scanner(System.in);

		Veiculos onix = new Veiculos();

		onix.marca = "Chevrolet";
		onix.modelo = "Onix Joy";
		onix.ano = 2019;
		onix.numPassageiros = 5;
		onix.tipoComb = new String[] { Veiculos.Tipo_Comb[2] };
		onix.capTanqComb = 54;
		onix.consumoCombustivel = 13.1;
		onix.valor = 50000;

		Veiculos fusca = new Veiculos();

		fusca.marca = "Volkswagen";
		fusca.modelo = "Fusca";
		fusca.ano = 1970;
		fusca.numPassageiros = 5;
		fusca.tipoComb = new String[] { Veiculos.Tipo_Comb[0] };
		fusca.capTanqComb = 41;
		fusca.consumoCombustivel = 10.0;
		fusca.valor = 32000;

		Veiculos uno = new Veiculos();

		uno.marca = "Fiat";
		uno.modelo = "Uno Eletronic";
		uno.ano = 1994;
		uno.numPassageiros = 5;
		uno.tipoComb = new String[] { Veiculos.Tipo_Comb[0] };
		uno.capTanqComb = 50;
		uno.consumoCombustivel = 10.2;
		uno.valor = 7000;

		Veiculos opala = new Veiculos();

		opala.marca = "Chevrolet";
		opala.modelo = "Opala Comodoro";
		opala.ano = 1972;
		opala.numPassageiros = 5;
		opala.tipoComb = new String[] { Veiculos.Tipo_Comb[0] };
		opala.capTanqComb = 60;
		opala.consumoCombustivel = 5.5;
		opala.valor = 85000;

		Veiculos landau = new Veiculos();

		landau.marca = "Ford";
		landau.modelo = "Landau";
		landau.ano = 1978;
		landau.numPassageiros = 5;
		landau.tipoComb = new String[] { Veiculos.Tipo_Comb[0] };
		landau.capTanqComb = 107;
		landau.consumoCombustivel = 4.5;
		landau.valor = 120000;

		System.out.print("Quantos km você pretende rodar? \n");

		double kmDesejado = scanner.nextDouble();
		System.out.println();

		// ===================== ONIX =====================
		System.out.println(onix);
		onix.exibirAutonomia();
		double autonomiaOnix = onix.obterAutonomia();
		System.out.println("A autonomia do veículo (via return) é: " + autonomiaOnix + " km");
		System.out.println();

		double qtdCombOnix = onix.calcularCombustivel(kmDesejado);
		System.out.println(
				"Para rodar " + kmDesejado + " km no " + onix.modelo + ", precisa abastecer " + String.format("%.2f", qtdCombOnix)
						+ " lts ou " + String.format("%.2f", qtdCombOnix / onix.capTanqComb) + " tanque.");
		System.out.println();

		// ===================== FUSCA =====================
		System.out.println(fusca);
		fusca.exibirAutonomia();
		double autonomiaFusca = fusca.obterAutonomia();
		System.out.println("A autonomia do veículo (via return) é: " + autonomiaFusca + " km");

		System.out.println();

		double qtdCombFusca = fusca.calcularCombustivel(kmDesejado);
		System.out.println(
				"Para rodar " + kmDesejado + " km no " + fusca.modelo + ", precisa abastecer " + String.format("%.2f", qtdCombFusca)
						+ " lts ou " + String.format("%.2f", qtdCombFusca / fusca.capTanqComb) + " tanque.");
		System.out.println();

		// ===================== UNO =====================
		System.out.println(uno);
		uno.exibirAutonomia();
		double autonomiaUno = uno.obterAutonomia();
		System.out.println("A autonomia do veículo (via return) é: " + autonomiaUno + " km");

		System.out.println();

		double qtdCombUno = uno.calcularCombustivel(kmDesejado);
		System.out.println(
				"Para rodar " + kmDesejado + " km no " + uno.modelo + ", precisa abastecer " + String.format("%.2f", qtdCombUno)
						+ " lts. ou " + String.format("%.2f", qtdCombUno / uno.capTanqComb) + " tanque.");
		System.out.println();

		// ===================== OPALA =====================
		System.out.println(opala);
		opala.exibirAutonomia();
		double autonomiaOpala = opala.obterAutonomia();
		System.out.println("A autonomia do veículo (via return) é: " + autonomiaOpala + " km");

		System.out.println();

		double qtdCombOpala = opala.calcularCombustivel(kmDesejado);
		System.out.println(
				"Para rodar " + kmDesejado + " km no " + opala.modelo + ", precisa abastecer " + String.format("%.2f", qtdCombOpala)
						+ " lts. ou " + String.format("%.2f", qtdCombOpala / opala.capTanqComb) + " tanque.");
		System.out.println();

		// ===================== LANDAU =====================
		System.out.println(landau);
		landau.exibirAutonomia();
		double autonomiaLandau = landau.obterAutonomia();
		System.out.println("A autonomia do veículo (via return) é: " + autonomiaLandau + " km");

		System.out.println();

		double qtdCombLandau = landau.calcularCombustivel(kmDesejado);
		System.out.println(
				"Para rodar " + kmDesejado + " km no " + landau.modelo + ", precisa abastecer " + String.format("%.2f", qtdCombLandau)
						+ " lts. ou " + String.format("%.2f", qtdCombLandau / landau.capTanqComb) + " tanque.");
		System.out.println();

		scanner.close();
	}

}
