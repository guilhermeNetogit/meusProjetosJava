package meusprojetosjava.veiculos.veiculos1;

import java.util.Scanner;

public class TesteVeiculos {

	public static void main(String[] args) {// Eclipse -> Github @guilhermeNetogit 23/03/2026 21:25:07

		Scanner scanner = new Scanner(System.in);

		// ========== Cadastros dos Veículos ==========
		Veiculos onix = criarVeiculo("Chevrolet", "Onix Joy 1.0", 2019, 5, new String[] { Veiculos.TIPO_COMB[2] }, 54,
				12.9, 15, 8.9, 10.8, 50000);

		Veiculos fusca = criarVeiculo("Volkswagen", "Fusca 1300", 1970, 5, new String[] { Veiculos.TIPO_COMB[0] }, 41,
				8, 11.5, 0, 0, 32000);

		Veiculos uno = criarVeiculo("Fiat", "Uno Eletronic 1.0", 1994, 5, new String[] { Veiculos.TIPO_COMB[0] }, 50,
				9.8, 13.2, 0, 0, 8000);

		Veiculos saveiro = criarVeiculo("Volkswagen", "Saveiro Surf 1.8", 2008, 2,
				new String[] { Veiculos.TIPO_COMB[2] }, 53, 10, 13, 7, 9, 43226);

		Veiculos civic = criarVeiculo("Honda", "Civic EXL 2.0", 2021, 5, new String[] { Veiculos.TIPO_COMB[2] }, 56,
				10.2, 12.8, 7.2, 8.9, 115000);

		Veiculos corolla = criarVeiculo("Toyota", "Corolla XEi 2.0", 2023, 5, new String[] { Veiculos.TIPO_COMB[2] },
				50, 11.9, 14.2, 8.3, 9.8, 140000);

		Veiculos fox = criarVeiculo("Volkswagen", "Fox Connect 1.6", 2021, 5, new String[] { Veiculos.TIPO_COMB[2] },
				50, 10.6, 12.1, 7.4, 8.2, 58000);

		Veiculos opala = criarVeiculo("Chevrolet", "Opala Comodoro", 1975, 5, new String[] { Veiculos.TIPO_COMB[0] },
				60, 5, 7, 0, 0, 58000);

		Veiculos landau = criarVeiculo("Ford", "Landau/Galaxie V8", 1978, 5, new String[] { Veiculos.TIPO_COMB[0] },
				107, 3.5, 5.5, 0, 0, 120000);

		// Array com todos os veículos
		Veiculos[] veiculos = { onix, fusca, uno, saveiro, civic, corolla, fox, opala, landau };

		System.out.print("Quantos km você pretende rodar? ");
		double kmDesejado = scanner.nextDouble();

		System.out.print("Qual o valor atual da gasolina (R$)? ");
		double valorGasolina = scanner.nextDouble();

		System.out.print("Qual o valor atual do álcool (R$)? ");
		double valorAlcool = scanner.nextDouble();

		System.out.println();

		int opcao;

		// Menu
		do {
			System.out.println("╔══════════════════════════════════╗");
			System.out.println("║         Menu de Veículos         ║");
			System.out.println("╠══════════════════════════════════╣");
			for (int i = 0; i < veiculos.length; i++) {
				System.out.printf("║ [%d] %-28s ║\n", i + 1, veiculos[i].modelo);
			}
			System.out.printf("║ [%d] %-27s ║\n", veiculos.length + 1, "Ver todos");
			System.out.printf("║ [%d] %-27s ║\n", veiculos.length + 2, "Alterar valores");
            System.out.println("║ [0] Sair                         ║");
            System.out.println("╚══════════════════════════════════╝");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();

			if (opcao >= 1 && opcao <= veiculos.length) {
				exibirDetalhesVeiculo(veiculos[opcao - 1], kmDesejado, valorGasolina, valorAlcool);
			} else if (opcao == veiculos.length + 1) {
				for (Veiculos v : veiculos) {
					exibirDetalhesVeiculo(v, kmDesejado, valorGasolina, valorAlcool);
					System.out.println("\nPressione ENTER para continuar...");
					scanner.nextLine();
					scanner.nextLine();
				}
			} else if (opcao == veiculos.length + 2) {
				// Opção para alterar os valores de km e preço da gasolina
				System.out.print("Digite a nova quilometragem desejada: ");
				kmDesejado = scanner.nextDouble();
				System.out.print("Digite o novo valor da gasolina (R$): ");
				valorGasolina = scanner.nextDouble();
				System.out.print("Digite o novo valor da álcool (R$): ");
				valorAlcool = scanner.nextDouble();
				System.out.println("\n✅ Valores atualizados com sucesso!");
			} else if (opcao != 0) {
				System.out.println("❌ Opção inválida! Tente novamente.");
			}
		} while (opcao != 0);

		scanner.close();
		System.out.println("\n🏁 Programa encerrado.");
	}

	// ========== Métodos Auxiliares ==========

	private static Veiculos criarVeiculo(String marca, String modelo, int ano, int numPassageiros, String[] tipoComb,
			double capTanq, double gasCid, double gasEst, double alcCid, double alcEst, double valor) {
		Veiculos v = new Veiculos();
		v.marca = marca;
		v.modelo = modelo;
		v.ano = ano;
		v.numPassageiros = numPassageiros;
		v.tipoComb = tipoComb;
		v.capTanqComb = capTanq;
		v.valor = valor;
		v.consumoGasolinaCid = gasCid;
		v.consumoGasolinaEst = gasEst;
		v.consumoAlcoolCid = alcCid;
		v.consumoAlcoolEst = alcEst;
		return v;
	}

	private static void exibirDetalhesVeiculo(Veiculos v, double km, double valorGasolina, double valorAlcool) {
		System.out.println(v);

		boolean usaGasolina = v.consumoGasolinaCid > 0;
		boolean usaAlcool = v.consumoAlcoolCid > 0;
		boolean isFlex = usaGasolina && usaAlcool;

		// ========== Cidade ==========
		System.out.println("\n>>> 🏙️  CÁLCULOS PARA CIDADE");

		if (usaGasolina) {
			v.exibirAutonomia("cidade (gasolina)", v.consumoGasolinaCid);
			double litros = v.calcularCombustivelGasolina(km, v.consumoGasolinaCid);
			double tanques = v.calcularQtdTanques(litros);
			double custo = v.calcularCustoGasolina(litros, valorGasolina);
			System.out.printf("⛽ Para %.1f km: %.2f litros (%.2f tanques) = R$ %,.2f\n", km, litros, tanques, custo);
		}

		if (usaAlcool) {
			v.exibirAutonomia("cidade (álcool)", v.consumoAlcoolCid);
			double litros = v.calcularCombustivelAlcool(km, v.consumoAlcoolCid);
			double tanques = v.calcularQtdTanques(litros);
			double custo = v.calcularCustoAlcool(litros, valorAlcool);
			System.out.printf("🌽 Para %.1f km: %.2f litros (%.2f tanques) = R$ %,.2f\n", km, litros, tanques, custo);
		}

		// Comparação cidade (se for flex)
		if (isFlex) {
			System.out.println(v.compararCustos(km, valorGasolina, valorAlcool, "CIDADE", v.consumoGasolinaCid,
					v.consumoAlcoolCid));
		}

		// ========== Estrada ==========

		System.out.println("\n>>> 🛣️  CÁLCULOS PARA ESTRADA");

		if (usaGasolina) {
			v.exibirAutonomia("estrada (gasolina)", v.consumoGasolinaEst);
			double litros = v.calcularCombustivelGasolina(km, v.consumoGasolinaEst);
			double tanques = v.calcularQtdTanques(litros);
			double custo = v.calcularCustoGasolina(litros, valorGasolina);
			System.out.printf("⛽ Para %.1f km: %.2f litros (%.2f tanques) = R$ %,.2f\n", km, litros, tanques, custo);
		}

		if (usaAlcool) {
			v.exibirAutonomia("estrada (álcool)", v.consumoAlcoolEst);
			double litros = v.calcularCombustivelAlcool(km, v.consumoAlcoolEst);
			double tanques = v.calcularQtdTanques(litros);
			double custo = v.calcularCustoAlcool(litros, valorAlcool);
			System.out.printf("🌽 Para %.1f km: %.2f litros (%.2f tanques) = R$ %,.2f\n", km, litros, tanques, custo);
		}

		// Comparação estrada (se for flex)
		if (isFlex) {
			System.out.println(v.compararCustos(km, valorGasolina, valorAlcool, "ESTRADA", v.consumoGasolinaEst,
					v.consumoAlcoolEst));
		}

		System.out.println("\n" + "═".repeat(50));

	}
}
