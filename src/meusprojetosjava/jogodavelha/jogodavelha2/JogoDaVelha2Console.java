package meusprojetosjava.jogodavelha.jogodavelha2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class JogoDaVelha2Console {// Eclipse -> Github @guilhermeNetogit 24/03/2026 23:38:32

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("╔═════════════════════════════════════╗");
		System.out.println("║     JOGO DA VELHA V2.0 - Início     ║");
		System.out.println("╚═════════════════════════════════════╝");
		System.out.println("\nVocê é o jogador X");
		System.out.println("Computador é o jogador O");

		JogoDaVelha2 jogo = new JogoDaVelha2();
		boolean jogarNovamente = true;

		while (jogarNovamente) {
			jogarPartida(jogo);

			System.out.println("\n========= PLACAR =========");
			System.out.println(" Você:       " + jogo.getVitoriasHumano() + " vitórias");
			System.out.println(" Computador: " + jogo.getVitoriasComputador() + " vitórias");
			System.out.println(" Empates:    " + jogo.getEmpates());

			System.out.print("\nDeseja jogar novamente? (S/N): ");
			String resposta = scanner.next().toUpperCase();

			while (!resposta.equals("S") && !resposta.equals("N")) {
				System.out.print("Resposta inválida! Digite S para Sim ou N para Não: ");
				resposta = scanner.next().toUpperCase();
			}

			jogarNovamente = resposta.equals("S");
		}

		int vitoriasUsuario = jogo.getVitoriasHumano();
		int vitoriasComputador = jogo.getVitoriasComputador();
		int empates = jogo.getEmpates();
		
		String marcadorUsuario = "";
		String marcadorComputador = "";
		String marcadorEmpate = "";
		
		if (vitoriasUsuario > vitoriasComputador) {
			marcadorUsuario = " -> Vencedor";
		} else if (vitoriasComputador > vitoriasUsuario) {
			marcadorComputador = " -> Vencedor";
		} else {
			marcadorEmpate = " -> Rodada empatada";
		}
		
		System.out.println("\n╔═════════════════════════════════════╗");
		System.out.println("║      JOGO DA VELHA V2.0 - Fim       ║");
		System.out.println("╚═════════════════════════════════════╝");

		System.out.println("\n════════════ PLACAR FINAL ════════════");
		System.out.println(" Você:       " + vitoriasUsuario + " vitória(s)" + marcadorUsuario);
		System.out.println(" Computador: " + vitoriasComputador + " vitória(s)" + marcadorComputador);
		System.out.println(" Empates:    " + empates + marcadorEmpate);
		System.out.println("══════════════════════════════════════\n");

		System.out.println("Obrigado por jogar! 👋");
		scanner.close();
	}

	// Controla uma partida completa
	private static void jogarPartida(JogoDaVelha2 jogo) {
		jogo.iniciarPartida();
		exibirTabuleiro(jogo.getTabuleiro());

		if (jogo.getJogadorAtual() == jogo.getJogadorComputador()) {
			System.out.println("O computador começa!");
		} else {
			System.out.println("Você começa!");
		}

		while (true) {
			if (jogo.getJogadorAtual() == jogo.getJogadorHumano()) {
				// Vez do humano
				boolean jogadaValida = false;
				while (!jogadaValida) {
					System.out.print("Sua vez! Digite linha (0-2) e coluna (0-2) separados por espaço: ");
					try {
						int linha = scanner.nextInt();
						int coluna = scanner.nextInt();
						jogadaValida = jogo.fazerJogadaHumana(linha, coluna);
						if (!jogadaValida) {
							System.out.println("Jogada inválida! Tente novamente.");
						}
					} catch (InputMismatchException e) {
						System.out.println("Entrada inválida! Use apenas números.");
						scanner.nextLine(); // limpa o buffer
					}
				}
			} else {
				// Vez do computador
				System.out.println("Vez do computador...");
				jogo.fazerJogadaComputador();
			}

			exibirTabuleiro(jogo.getTabuleiro());

			// Verifica se o jogo terminou
			char vencedor = jogo.verificarVencedor();
			if (vencedor != ' ') {
				if (vencedor == jogo.getJogadorHumano()) {
					System.out.println("🎉 Parabéns! Você venceu!");
					jogo.incrementarVitoriasHumano();
				} else {
					System.out.println("💻 O computador venceu!");
					jogo.incrementarVitoriasComputador();
				}
				break;
			} else if (jogo.tabuleiroCheio()) {
				System.out.println("🤝 Empate!");
				jogo.incrementarEmpates();
				break;
			}
		}
	}

	// Exibe o tabuleiro formatado (recebe uma cópia do tabuleiro do backend)
	private static void exibirTabuleiro(char[][] tabuleiro) {
		System.out.println("\n  0   1   2");
		for (int i = 0; i < 3; i++) {
			System.out.print(i + " ");
			for (int j = 0; j < 3; j++) {
				System.out.print(tabuleiro[i][j]);
				if (j < 2)
					System.out.print(" | ");
			}
			System.out.println();
			if (i < 2)
				System.out.println("  ---------");
		}
		System.out.println();
	}
}