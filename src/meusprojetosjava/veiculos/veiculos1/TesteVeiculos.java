package meusprojetosjava.veiculos.veiculos1;

import java.util.Scanner;

public class TesteVeiculos {

	public static void main(String[] args) {// Eclipse -> Github @guilhermeNetogit 10/03/2026 23:18:04

		Scanner scanner = new Scanner(System.in);

		Veiculos onix = new Veiculos();

		onix.marca = "Chevrolet";
		onix.modelo = "Onix Joy";
		onix.ano = 2019;
		onix.numPassageiros = 5;
		onix.tipoComb = new String[] { Veiculos.TIPO_COMB[2] };
		onix.capTanqComb = 54;
		onix.consumoCombustivelCid = 12.9;
		onix.consumoCombustivelEst = 15;
		onix.valor = 50000;

		Veiculos fusca = new Veiculos();

		fusca.marca = "Volkswagen";
		fusca.modelo = "Fusca";
		fusca.ano = 1970;
		fusca.numPassageiros = 5;
		fusca.tipoComb = new String[] { Veiculos.TIPO_COMB[0] };
		fusca.capTanqComb = 41;
		fusca.consumoCombustivelCid = 10.0;
		fusca.valor = 32000;

		Veiculos uno = new Veiculos();

		uno.marca = "Fiat";
		uno.modelo = "Uno Eletronic";
		uno.ano = 1994;
		uno.numPassageiros = 5;
		uno.tipoComb = new String[] { Veiculos.TIPO_COMB[0] };
		uno.capTanqComb = 50;
		uno.consumoCombustivelCid = 10.2;
		uno.valor = 7000;

		Veiculos opala = new Veiculos();

		opala.marca = "Chevrolet";
		opala.modelo = "Opala Comodoro";
		opala.ano = 1972;
		opala.numPassageiros = 5;
		opala.tipoComb = new String[] { Veiculos.TIPO_COMB[0] };
		opala.capTanqComb = 60;
		opala.consumoCombustivelCid = 5.5;
		opala.valor = 85000;

		Veiculos landau = new Veiculos();

		landau.marca = "Ford";
		landau.modelo = "Landau";
		landau.ano = 1978;
		landau.numPassageiros = 5;
		landau.tipoComb = new String[] { Veiculos.TIPO_COMB[0] };
		landau.capTanqComb = 107;
		landau.consumoCombustivelCid = 4.5;
		landau.valor = 120000;

		System.out.print("Quantos km você pretende rodar? \n");
		double kmDesejado = scanner.nextDouble();

		System.out.print("Qual o valor atual da gasolina (em R$)? \n");
		double valorGasolina = scanner.nextDouble();

		System.out.println();

		// ===================== ONIX =====================
		System.out.println(onix);
		onix.exibirAutonomia("cidade", onix.consumoCombustivelCid);
		onix.exibirAutonomia("estrada", onix.consumoCombustivelEst);
		double autonomiaOnixCid = onix.obterAutonomia(onix.consumoCombustivelCid);
		double autonomiaOnixEst = onix.obterAutonomia(onix.consumoCombustivelEst);
		System.out.println("A autonomia na cidade (via return) é: " + autonomiaOnixCid + " km");
		System.out.println("A autonomia na estrada (via return) é: " + autonomiaOnixEst + " km");
		System.out.println();

		double qtdCombOnixCid = onix.calcularCombustivel(kmDesejado, onix.consumoCombustivelCid);
		double qtdCombOnixEst = onix.calcularCombustivel(kmDesejado, onix.consumoCombustivelEst);
		double tanquesCid = onix.calcularQtdTanques(qtdCombOnixCid);
		double tanquesEst = onix.calcularQtdTanques(qtdCombOnixEst);		
		double custoOnixCid = onix.calcularCustoCombustivel(qtdCombOnixCid, valorGasolina);
		double custoOnixEst = onix.calcularCustoCombustivel(qtdCombOnixEst, valorGasolina);

		System.out.println("Para rodar " + kmDesejado + " km na cidade com o " + onix.modelo + ", precisa abastecer "
				+ String.format("%.2f", qtdCombOnixCid) + " lts ou "
				+ String.format("%.2f", tanquesCid) + " tanque. \nO custo será de "
				+ String.format("R$ %,.2f", custoOnixCid)
				);
		System.out.println(
				"E para rodar a mesma distância na estrada, precisa abastecer " + String.format("%.2f", qtdCombOnixEst)
						+ " lts ou " + String.format("%.2f", tanquesEst)
						+ " tanque. \nO custo será de " + String.format("R$ %,.2f", custoOnixEst)
						);
		System.out.println();

		// ===================== FUSCA =====================
		/*
		 * System.out.println(fusca); fusca.exibirAutonomiaCid(); double autonomiaFusca
		 * = fusca.obterAutonomiaCid();
		 * System.out.println("A autonomia do veículo (via return) é: " + autonomiaFusca
		 * + " km");
		 * 
		 * System.out.println();
		 * 
		 * double qtdCombFusca = fusca.calcularCombustivelCid(kmDesejado);
		 * System.out.println( "Para rodar " + kmDesejado + " km no " + fusca.modelo +
		 * ", precisa abastecer " + String.format("%.2f", qtdCombFusca) + " lts ou " +
		 * String.format("%.2f", qtdCombFusca / fusca.capTanqComb) + " tanque.");
		 * System.out.println();
		 * 
		 * // ===================== UNO ===================== System.out.println(uno);
		 * uno.exibirAutonomiaCid(); double autonomiaUno = uno.obterAutonomiaCid();
		 * System.out.println("A autonomia do veículo (via return) é: " + autonomiaUno +
		 * " km");
		 * 
		 * System.out.println();
		 * 
		 * double qtdCombUno = uno.calcularCombustivelCid(kmDesejado);
		 * System.out.println( "Para rodar " + kmDesejado + " km no " + uno.modelo +
		 * ", precisa abastecer " + String.format("%.2f", qtdCombUno) + " lts. ou " +
		 * String.format("%.2f", qtdCombUno / uno.capTanqComb) + " tanque.");
		 * System.out.println();
		 * 
		 * // ===================== OPALA =====================
		 * System.out.println(opala); opala.exibirAutonomiaCid(); double autonomiaOpala
		 * = opala.obterAutonomiaCid();
		 * System.out.println("A autonomia do veículo (via return) é: " + autonomiaOpala
		 * + " km");
		 * 
		 * System.out.println();
		 * 
		 * double qtdCombOpala = opala.calcularCombustivelCid(kmDesejado);
		 * System.out.println( "Para rodar " + kmDesejado + " km no " + opala.modelo +
		 * ", precisa abastecer " + String.format("%.2f", qtdCombOpala) + " lts. ou " +
		 * String.format("%.2f", qtdCombOpala / opala.capTanqComb) + " tanque.");
		 * System.out.println();
		 * 
		 * // ===================== LANDAU =====================
		 * System.out.println(landau); landau.exibirAutonomiaCid(); double
		 * autonomiaLandau = landau.obterAutonomiaCid();
		 * System.out.println("A autonomia do veículo (via return) é: " +
		 * autonomiaLandau + " km");
		 * 
		 * System.out.println();
		 * 
		 * double qtdCombLandau = landau.calcularCombustivelCid(kmDesejado);
		 * System.out.println( "Para rodar " + kmDesejado + " km no " + landau.modelo +
		 * ", precisa abastecer " + String.format("%.2f", qtdCombLandau) + " lts. ou " +
		 * String.format("%.2f", qtdCombLandau / landau.capTanqComb) + " tanque.");
		 * System.out.println();
		 */

		scanner.close();
	}

}
