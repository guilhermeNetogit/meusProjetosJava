package meusprojetosjava.veiculos.veiculos1;

import java.util.Scanner;

public class TesteVeiculos {

	public static void main(String[] args) {// Eclipse -> Github @guilhermeNetogit 22/03/2026 10:42:11

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

		Veiculos saveiro = new Veiculos();
		saveiro.marca = "Volkswagen";
		saveiro.modelo = "Saveiro Surf 1.8";
		saveiro.ano = 2008;
		saveiro.numPassageiros = 2;
		saveiro.tipoComb = new String[] { Veiculos.TIPO_COMB[2] };
		saveiro.capTanqComb = 53;
		saveiro.consumoCombustivelCid = 12;
		saveiro.consumoCombustivelEst = 14.2;
		saveiro.valor = 43226;

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

		// Array com todos os veículos
		Veiculos[] veiculos = { onix, fusca, uno, saveiro, opala, landau };

		System.out.print("Quantos km você pretende rodar? ");
		double kmDesejado = scanner.nextDouble();

		System.out.print("Qual o valor atual da gasolina (em R$)? ");
		double valorGasolina = scanner.nextDouble();

		System.out.println();

		int opcao;

		do {
			// Menu
			System.out.println("=== MENU DE VEÍCULOS ===");
			for (int i = 0; i < veiculos.length; i++) {
				System.out.printf("[%d] %s\n", i + 1, veiculos[i].modelo);
			}
			System.out.printf("[%d] Ver todos\n", veiculos.length + 1);
			System.out.printf("[%d] Alterar km e valor da gasolina\n", veiculos.length + 2);
			System.out.println("[0] Sair");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();

			if (opcao >= 1 && opcao <= veiculos.length) {
				exibirDetalhesVeiculo(veiculos[opcao - 1], kmDesejado, valorGasolina);
			} else if (opcao == veiculos.length + 1) {
				for (Veiculos v : veiculos) {
					exibirDetalhesVeiculo(v, kmDesejado, valorGasolina);
					System.out.println();
				}
			} else if (opcao == veiculos.length + 2) {
				// Opção para alterar os valores de km e preço da gasolina
				System.out.print("Digite a nova quilometragem desejada: ");
				kmDesejado = scanner.nextDouble();
				System.out.print("Digite o novo valor da gasolina (R$): ");
				valorGasolina = scanner.nextDouble();
				System.out.println("\nValores atualizados com sucesso!");
			} else if (opcao != 0) {
				System.out.println("Opção inválida! Tente novamente.");
			}
		} while (opcao != 0);

		scanner.close();
		System.out.println("Programa encerrado.");
	}

	private static void exibirDetalhesVeiculo(Veiculos v, double kmDesejado, double valorGasolina) {
		System.out.println(v);

		// Cidade
		v.exibirAutonomia("cidade", v.consumoCombustivelCid);
		double autonomiaCid = v.obterAutonomia(v.consumoCombustivelCid);
		System.out.printf("A autonomia na cidade (via return) é: %.2f km\n", autonomiaCid);

		double qtdCombCid = v.calcularCombustivel(kmDesejado, v.consumoCombustivelCid);
		double tanquesCid = v.calcularQtdTanques(qtdCombCid);
		double custoCid = v.calcularCustoCombustivel(qtdCombCid, valorGasolina);

		System.out.printf("Para rodar %.2f km na cidade com o %s, precisa abastecer %.2f lts ou %.2f tanque.\n",
				kmDesejado, v.modelo, qtdCombCid, tanquesCid);
		System.out.printf("O custo será de R$ %,.2f\n", custoCid);

		// Estrada
		v.exibirAutonomia("estrada", v.consumoCombustivelEst);
		double autonomiaEst = v.obterAutonomia(v.consumoCombustivelEst);
		System.out.printf("A autonomia na estrada (via return) é: %.2f km\n", autonomiaEst);

		double qtdCombEst = v.calcularCombustivel(kmDesejado, v.consumoCombustivelEst);
		double tanquesEst = v.calcularQtdTanques(qtdCombEst);
		double custoEst = v.calcularCustoCombustivel(qtdCombEst, valorGasolina);

		System.out.printf("Para rodar %.2f km na estrada com o %s, precisa abastecer %.2f lts ou %.2f tanque.\n",
				kmDesejado, v.modelo, qtdCombEst, tanquesEst);
		System.out.printf("O custo será de R$ %,.2f\n\n", custoEst);
	}
}
