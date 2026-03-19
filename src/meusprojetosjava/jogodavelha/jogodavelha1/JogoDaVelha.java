package meusprojetosjava.jogodavelha.jogodavelha1;

import java.util.*;

/**
 * Classe principal que implementa um jogo da velha completo em modo console.
 * 
 * <p>
 * <b>Características principais:</b>
 * </p>
 * <ul>
 * <li>Modo jogador vs computador</li>
 * <li>Utiliza busca em arvore (tree search) com uma IA baseada no algoritmo
 * Minimax sendo impossível vence-la.</li>
 * <li>Controle de placar (vitórias e empates)</li>
 * <li>Loop para múltiplas partidas</li>
 * </ul>
 * 
 * <p>
 * <b>Inteligência Artificial:</b>
 * </p>
 * Simula todas as jogadas possíveis do jogo, considera o que o oponente pode
 * fazer, o que ele mesmo pode fazer depois e escolhe a jogada com o melhor
 * resultado garantido. Nunca perde. Sempre que possível ganha, caso contrário,
 * força o empate.
 * </p>
 */

public class JogoDaVelha {// Eclipse -> Github @guilhermeNetogit 23/02/2026 20:35:42
	private static char[][] tabuleiro = new char[3][3];
	private static char jogadorHumano = 'X';
	private static char jogadorComputador = 'O';
	private static char jogadorAtual = 'X';
	private static Scanner scanner = new Scanner(System.in);
	private static Random random = new Random();
	private static int vitoriasHumano = 0;
	private static int vitoriasComputador = 0;
	private static int empates = 0;

	public static void main(String[] args) {

		System.out.println("╔════════════════════════════════╗");
		System.out.println("║      JOGO DA VELHA - Início    ║");
		System.out.println("╚════════════════════════════════╝");
		System.out.println("\nVocê é o jogador X");
		System.out.println("Computador é o jogador O");

		boolean jogarNovamente = true;

		while (jogarNovamente) {
			jogarPartida();

			System.out.println("\n========= PLACAR =========");
			System.out.println("  Você:        " + vitoriasHumano + " vitórias");
			System.out.println("  Computador:  " + vitoriasComputador + " vitórias");
			System.out.println("  Empates:     " + empates);

			System.out.print("\nDeseja jogar novamente? (S/N): ");
			String resposta = scanner.next().toUpperCase();

			while (!resposta.equals("S") && !resposta.equals("N")) {
				System.out.print("Resposta inválida! Digite S para Sim ou N para Não: ");
				resposta = scanner.next().toUpperCase();
			}

			jogarNovamente = resposta.equals("S");
		}

		System.out.println("\n╔════════════════════════════════╗");
		System.out.println("║      JOGO DA VELHA - Fim       ║");
		System.out.println("╚════════════════════════════════╝");

		System.out.println("\n════════ PLACAR FINAL ════════");
		System.out.println("  Você:        " + vitoriasHumano + " vitória(s)");
		System.out.println("  Computador:  " + vitoriasComputador + " vitória(s)");
		System.out.println("  Empates:     " + empates);
		System.out.println("══════════════════════════════\n");

		System.out.println("Obrigado por jogar! 👋");

		scanner.close();
	}

	private static void jogarPartida() {
		inicializarTabuleiro();
		exibirTabuleiro();

		// Decide aleatoriamente quem começa (opcional)
		jogadorAtual = random.nextBoolean() ? jogadorHumano : jogadorComputador;
		if (jogadorAtual == jogadorComputador) {
			System.out.println("O computador começa!");
		} else {
			System.out.println("Você começa!");
		}

		// Loop principal do jogo
		while (true) {
			if (jogadorAtual == jogadorHumano) {
				jogadaHumano();
			} else {
				jogadaComputador();
			}

			exibirTabuleiro();

			// Verificar se há um vencedor
			char vencedor = verificarVencedor();
			if (vencedor != ' ') {
				if (vencedor == jogadorHumano) {
					System.out.println("Parabéns! Você venceu!");
					vitoriasHumano++;
				} else if (vencedor == jogadorComputador) {
					System.out.println("O computador venceu!");
					vitoriasComputador++;
				}
				break;
			}

			// Verificar se o jogo empatou
			if (tabuleiroCheio()) {
				System.out.println("O jogo empatou!");
				empates++;
				break;
			}

			// Alternar jogador
			jogadorAtual = (jogadorAtual == jogadorHumano) ? jogadorComputador : jogadorHumano;
		}
	}

	private static void inicializarTabuleiro() {
		for (int i = 0; i < tabuleiro.length/* 3 */; i++) {
			for (int j = 0; j < tabuleiro.length /* 3 */; j++) {
				tabuleiro[i][j] = ' ';
			}
		}
	}

	private static void exibirTabuleiro() {
		System.out.println("\n  0   1   2");
		for (int i = 0; i < tabuleiro.length /* 3 */; i++) {
			System.out.print(i + " ");
			for (int j = 0; j < tabuleiro.length /* 3 */; j++) {
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

	private static void jogadaHumano() {
		int linha, coluna;

		while (true) {
			System.out.print("Sua vez! Digite linha (0-2) e coluna (0-2) separados por espaço: ");

			try {
				linha = scanner.nextInt();
				coluna = scanner.nextInt();

				if (linha < 0 || linha > 2 || coluna < 0 || coluna > 2) {
					System.out.println("Posição inválida! Use valores entre 0 e 2.");
				} else if (tabuleiro[linha][coluna] != ' ') {
					System.out.println("Posição já ocupada! Escolha outra.");
				} else {
					tabuleiro[linha][coluna] = jogadorHumano;
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida! Use apenas números.");
				scanner.nextLine(); // Limpar buffer
			}
		}
	}

	private static void jogadaComputador() {
		System.out.println("Vez do computador...");

		// Pequena pausa para parecer que o computador está "pensando"
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Estratégia da IA:
		int melhorPontuacao = Integer.MIN_VALUE;
		int[] melhorJogada = null;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (tabuleiro[i][j] == ' ') {
					tabuleiro[i][j] = jogadorComputador;
					int pontuacao = minimax(false, 0);
					tabuleiro[i][j] = ' '; // desfaz jogada

					if (pontuacao > melhorPontuacao) {
						melhorPontuacao = pontuacao;
						melhorJogada = new int[] { i, j };
					}
				}
			}
		}

		if (melhorJogada != null) {
			tabuleiro[melhorJogada[0]][melhorJogada[1]] = jogadorComputador;
		}
	}

	/**
	 * Algoritmo Minimax.
	 *
	 * @param maximizando  indica se está maximizando (computador) ou minimizando
	 *                     (humano)
	 * @param profundidade nível da árvore de decisão
	 * @return pontuação da jogada
	 */
	private static int minimax(boolean maximizando, int profundidade) {
		char vencedor = verificarVencedor();

		// Pontuação com penalidade por profundidade — IA prefere vencer rápido
		if (vencedor == jogadorComputador)
			return 10 - profundidade;
		if (vencedor == jogadorHumano)
			return profundidade - 10;
		if (tabuleiroCheio())
			return 0;

		if (maximizando) {
			int melhor = Integer.MIN_VALUE;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (tabuleiro[i][j] == ' ') {
						tabuleiro[i][j] = jogadorComputador;
						melhor = Math.max(melhor, minimax(false, profundidade + 1));
						tabuleiro[i][j] = ' ';
					}
				}
			}
			return melhor;
		} else {
			int melhor = Integer.MAX_VALUE;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (tabuleiro[i][j] == ' ') {
						tabuleiro[i][j] = jogadorHumano;
						melhor = Math.min(melhor, minimax(true, profundidade + 1));
						tabuleiro[i][j] = ' ';
					}
				}
			}
			return melhor;
		}
	}

	private static char verificarVencedor() {
		// Verificar linhas
		for (int i = 0; i < tabuleiro.length /* 3 */; i++) {
			if (tabuleiro[i][0] != ' ' && tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2]) {
				return tabuleiro[i][0];
			}
		}

		// Verificar colunas
		for (int j = 0; j < tabuleiro.length /* 3 */; j++) {
			if (tabuleiro[0][j] != ' ' && tabuleiro[0][j] == tabuleiro[1][j] && tabuleiro[1][j] == tabuleiro[2][j]) {
				return tabuleiro[0][j];
			}
		}

		// Verificar diagonais
		if (tabuleiro[0][0] != ' ' && tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2]) {
			return tabuleiro[0][0];
		}

		if (tabuleiro[0][2] != ' ' && tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0]) {
			return tabuleiro[0][2];
		}

		return ' '; // Nenhum vencedor ainda
	}

	private static boolean tabuleiroCheio() {
		for (int i = 0; i < tabuleiro.length /* 3 */; i++) {
			for (int j = 0; j < tabuleiro.length /* 3 */; j++) {
				if (tabuleiro[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;
	}
}