package meusprojetosjava.veiculos.veiculos1;

import java.util.Scanner;

public class TesteVeiculos {

	public static void main(String[] args) {// Eclipse -> Github @guilhermeNetogit 11/03/2026 13:38:06

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
		fusca.modelo = "Fusca 1300";
		fusca.ano = 1970;
		fusca.numPassageiros = 5;
		fusca.tipoComb = new String[] { Veiculos.TIPO_COMB[0] };
		fusca.capTanqComb = 41;
		fusca.consumoCombustivelCid = 8;
		fusca.consumoCombustivelEst = 11.5;
		fusca.valor = 32000;

		Veiculos uno = new Veiculos();

		uno.marca = "Fiat";
		uno.modelo = "Uno Eletronic 1.0";
		uno.ano = 1994;
		uno.numPassageiros = 5;
		uno.tipoComb = new String[] { Veiculos.TIPO_COMB[0] };
		uno.capTanqComb = 50;
		uno.consumoCombustivelCid = 9.8;
		uno.consumoCombustivelEst = 13.2;
		uno.valor = 8000;

		Veiculos opala = new Veiculos();

		opala.marca = "Chevrolet";
		opala.modelo = "Opala Comodoro";
		opala.ano = 1975;
		opala.numPassageiros = 5;
		opala.tipoComb = new String[] { Veiculos.TIPO_COMB[0] };
		opala.capTanqComb = 60;
		opala.consumoCombustivelCid = 5;
		opala.consumoCombustivelEst = 7;
		opala.valor = 58000;

		Veiculos landau = new Veiculos();

		landau.marca = "Ford";
		landau.modelo = "Landau/Galaxie V8";
		landau.ano = 1978;
		landau.numPassageiros = 5;
		landau.tipoComb = new String[] { Veiculos.TIPO_COMB[0] };
		landau.capTanqComb = 107;
		landau.consumoCombustivelCid = 3.5;
		landau.consumoCombustivelEst = 5.5;
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
		System.out.printf("A autonomia na cidade (via return) é: %.2f km\n", autonomiaOnixCid);
		System.out.printf("A autonomia na estrada (via return) é: %.2f km\n", autonomiaOnixEst);
		System.out.println();

		double qtdCombOnixCid = onix.calcularCombustivel(kmDesejado, onix.consumoCombustivelCid);
		double qtdCombOnixEst = onix.calcularCombustivel(kmDesejado, onix.consumoCombustivelEst);
		double tanquesOnixCid = onix.calcularQtdTanques(qtdCombOnixCid);
		double tanquesOnixEst = onix.calcularQtdTanques(qtdCombOnixEst);		
		double custoOnixCid = onix.calcularCustoCombustivel(qtdCombOnixCid, valorGasolina);
		double custoOnixEst = onix.calcularCustoCombustivel(qtdCombOnixEst, valorGasolina);

		System.out.println("Para rodar " + kmDesejado + " km na cidade com o " + onix.modelo + ", precisa abastecer "
				+ String.format("%.2f", qtdCombOnixCid) + " lts ou "
				+ String.format("%.2f", tanquesOnixCid) + " tanque. \nO custo será de "
				+ String.format("R$ %,.2f", custoOnixCid)
				);
		System.out.println(
				"E para rodar a mesma distância na estrada, precisa abastecer " + String.format("%.2f", qtdCombOnixEst)
						+ " lts ou " + String.format("%.2f", tanquesOnixEst)
						+ " tanque. \nO custo será de " + String.format("R$ %,.2f", custoOnixEst)
						);
		System.out.println();

		// ===================== FUSCA =====================
		System.out.println(fusca);
		fusca.exibirAutonomia("cidade", fusca.consumoCombustivelCid);
		fusca.exibirAutonomia("estrada", fusca.consumoCombustivelEst);
		double autonomiaFuscaCid = fusca.obterAutonomia(fusca.consumoCombustivelCid);
		double autonomiaFuscaEst = fusca.obterAutonomia(fusca.consumoCombustivelEst);
		
		System.out.printf("A autonomia na cidade (via return) é: %.2f km\n", autonomiaFuscaCid);
		System.out.printf("A autonomia na estrada (via return) é: %.2f km\n", autonomiaFuscaEst);
		System.out.println();
		
		double qtdCombFuscaCid = fusca.calcularCombustivel(kmDesejado, fusca.consumoCombustivelCid);
		double qtdCombFuscaEst = fusca.calcularCombustivel(kmDesejado, fusca.consumoCombustivelEst);
		double tanquesFuscaCid = fusca.calcularQtdTanques(qtdCombFuscaCid);
		double tanquesFuscaEst = fusca.calcularQtdTanques(qtdCombFuscaEst);
		double custoFuscaCid = fusca.calcularCustoCombustivel(qtdCombFuscaCid, valorGasolina);
		double custoFuscaEst = fusca.calcularCustoCombustivel(qtdCombFuscaEst, valorGasolina);
		
		System.out.println( "Para rodar " + kmDesejado + " km na cidade com o " + fusca.modelo + ", precisa abastecer "
				+ String.format("%.2f", qtdCombFuscaCid) + " lts ou "
				+ String.format("%.2f", tanquesFuscaCid) + " tanque. \nO custo será de "
				+ String.format("R$ %,.2f", custoFuscaCid)
				);
		System.out.println(
				"E para rodar a mesma distância na estrada, precisa abastecer " + String.format("%.2f", qtdCombFuscaEst)
						+ " lts ou " + String.format("%.2f", tanquesFuscaEst)
						+ " tanque. \nO custo será de " + String.format("R$ %,.2f", custoFuscaEst)
						);
		System.out.println();
		
		// ===================== UNO =====================
		System.out.println(uno);
		uno.exibirAutonomia("cidade", uno.consumoCombustivelCid);
		uno.exibirAutonomia("estrada", uno.consumoCombustivelEst);
		double autonomiaUnoCid = uno.obterAutonomia(uno.consumoCombustivelCid);
		double autonomiaUnoEst = uno.obterAutonomia(uno.consumoCombustivelEst);

		System.out.printf("A autonomia na cidade (via return) é: %.2f km\n", autonomiaUnoCid);
		System.out.printf("A autonomia na estrada (via return) é: %.2f km\n", autonomiaUnoEst);

		System.out.println();

		double qtdCombUnoCid = uno.calcularCombustivel(kmDesejado, uno.consumoCombustivelCid);
		double qtdCombUnoEst = uno.calcularCombustivel(kmDesejado, uno.consumoCombustivelEst);
		double tanquesUnoCid = uno.calcularQtdTanques(qtdCombUnoCid);
		double tanquesUnoEst = uno.calcularQtdTanques(qtdCombUnoEst);
		double custoUnoCid = uno.calcularCustoCombustivel(qtdCombUnoCid, valorGasolina);
		double custoUnoEst = uno.calcularCustoCombustivel(qtdCombUnoEst, valorGasolina);
		
		System.out.println("Para rodar " + kmDesejado + "km na cidade com o " + uno.modelo + ", precisa abastecer "
				+ String.format("%.2f", qtdCombUnoCid) + " lts. ou " 
				+ String.format("%.2f", tanquesUnoCid) + " tanque. \nO custo será de "
				+ String.format("R$ %,.2f", custoUnoCid)
				);
		System.out.println("E para rodar a mesma distância na estrada, precisa abastecer "
				+ String.format("%.2f", qtdCombUnoEst) + " lts ou " + String.format("%.2f", tanquesUnoEst)
				+ " tanque. \nO custo será de " + String.format("R$ %,.2f", custoUnoEst));
		System.out.println();
		
		// ===================== OPALA =====================
		System.out.println(opala);
		opala.exibirAutonomia("cidade", opala.consumoCombustivelCid);
		opala.exibirAutonomia("estrada", opala.consumoCombustivelEst);
		double autonomiaOpalaCid = opala.obterAutonomia(opala.consumoCombustivelCid);
		double autonomiaOpalaEst = opala.obterAutonomia(opala.consumoCombustivelEst);

		System.out.printf("A autonomia na cidade (via return) é: %.2f km\n", autonomiaOpalaCid);
		System.out.printf("A autonomia na estrada (via return) é: %.2f km\n", autonomiaOpalaEst);

		System.out.println();

		double qtdCombOpalaCid = opala.calcularCombustivel(kmDesejado, opala.consumoCombustivelCid);
		double qtdCombOpalaEst = opala.calcularCombustivel(kmDesejado, opala.consumoCombustivelEst);
		double tanquesOpalaCid = opala.calcularQtdTanques(qtdCombOpalaCid);
		double tanquesOpalaEst = opala.calcularQtdTanques(qtdCombOpalaEst);
		double custoOpalaCid = opala.calcularCustoCombustivel(qtdCombOpalaCid, valorGasolina);
		double custoOpalaEst = opala.calcularCustoCombustivel(qtdCombOpalaEst, valorGasolina);
		
		System.out.println("Para rodar " + kmDesejado + "km na cidade com o " + opala.modelo + ", precisa abastecer "
				+ String.format("%.2f", qtdCombOpalaCid) + " lts. ou " 
				+ String.format("%.2f", tanquesOpalaCid) + " tanque. \nO custo será de "
				+ String.format("R$ %,.2f", custoOpalaCid)
				);
		System.out.println("E para rodar a mesma distância na estrada, precisa abastecer "
				+ String.format("%.2f", qtdCombOpalaEst) + " lts ou " + String.format("%.2f", tanquesOpalaEst)
				+ " tanque. \nO custo será de " + String.format("R$ %,.2f", custoOpalaEst));
		System.out.println();
		
		 
		// ===================== LANDAU =====================
		System.out.println(landau);
		landau.exibirAutonomia("cidade", landau.consumoCombustivelCid);
		landau.exibirAutonomia("estrada", landau.consumoCombustivelEst);
		double autonomiaLandauCid = landau.obterAutonomia(landau.consumoCombustivelCid);
		double autonomiaLandauEst = landau.obterAutonomia(landau.consumoCombustivelEst);

		System.out.printf("A autonomia na cidade (via return) é: %.2f km\n", autonomiaLandauCid);
		System.out.printf("A autonomia na estrada (via return) é: %.2f km\n", autonomiaLandauEst);

		System.out.println();

		double qtdCombLandauCid = landau.calcularCombustivel(kmDesejado, landau.consumoCombustivelCid);
		double qtdCombLandauEst = landau.calcularCombustivel(kmDesejado, landau.consumoCombustivelEst);
		double tanquesLandauCid = landau.calcularQtdTanques(qtdCombLandauCid);
		double tanquesLandauEst = landau.calcularQtdTanques(qtdCombLandauEst);
		double custoLandauCid = landau.calcularCustoCombustivel(qtdCombLandauCid, valorGasolina);
		double custoLandauEst = landau.calcularCustoCombustivel(qtdCombLandauEst, valorGasolina);
		
		System.out.println("Para rodar " + kmDesejado + "km na cidade com o " + landau.modelo + ", precisa abastecer "
				+ String.format("%.2f", qtdCombLandauCid) + " lts. ou " 
				+ String.format("%.2f", tanquesLandauCid) + " tanque. \nO custo será de "
				+ String.format("R$ %,.2f", custoLandauCid)
				);
		System.out.println("E para rodar a mesma distância na estrada, precisa abastecer "
				+ String.format("%.2f", qtdCombLandauEst) + " lts ou " + String.format("%.2f", tanquesLandauEst)
				+ " tanque. \nO custo será de " + String.format("R$ %,.2f", custoLandauEst));
		System.out.println();
		 
		scanner.close();
	}

}
