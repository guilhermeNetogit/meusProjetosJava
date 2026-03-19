package meusprojetosjava.jogodavelha.jogodavelha2;

import java.util.Random;

/**
 * Classe que representa a lógica de um jogo da velha desacoplado da interface.
 *
 * <p>
 * <b>Características:</b>
 * </p>
 * <ul>
 * <li>Separação entre lógica e interface (boa prática OO)</li>
 * <li>IA baseada em regras simples (busca heurística)</li>
 * <li>Controle de estado do jogo</li>
 * </ul>
 *
 * <p>
 * <b>Inteligência Artificial:</b>
 * </p>
 * <ul>
 * <li>Tenta vencer</li>
 * <li>Tenta bloquear o adversário</li>
 * <li>Caso contrário, joga aleatoriamente</li>
 * </ul>
 */

public class JogoDaVelha2 {

	private char[][] tabuleiro;
	private char jogadorHumano;
	private char jogadorComputador;
	private char jogadorAtual;
	private Random random;
	private int vitoriasHumano;
	private int vitoriasComputador;
	private int empates;

	public JogoDaVelha2() {
		tabuleiro = new char[3][3];
		jogadorHumano = 'X';
		jogadorComputador = 'O';
		random = new Random();
		vitoriasHumano = 0;
		vitoriasComputador = 0;
		empates = 0;
		resetTabuleiro();
	}

	// Inicializa o tabuleiro com espaços vazios
	public void resetTabuleiro() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				tabuleiro[i][j] = ' ';
			}
		}
	}

	// Prepara uma nova partida: limpa o tabuleiro e sorteia quem começa
	public void iniciarPartida() {
		resetTabuleiro();
		jogadorAtual = random.nextBoolean() ? jogadorHumano : jogadorComputador;
	}

	// Tenta realizar uma jogada do humano. Retorna true se a jogada for válida.
	public boolean fazerJogadaHumana(int linha, int coluna) {
		if (linha < 0 || linha > 2 || coluna < 0 || coluna > 2) {
			return false; // posição fora do tabuleiro
		}
		if (tabuleiro[linha][coluna] != ' ') {
			return false; // posição já ocupada
		}
		if (jogadorAtual != jogadorHumano) {
			return false; // não é a vez do humano
		}
		tabuleiro[linha][coluna] = jogadorHumano;
		jogadorAtual = jogadorComputador; // passa a vez para o computador
		return true;
	}

	/**
	 * Executa jogada do computador. Utiliza heurística simples.
	 */
	// Executa a jogada do computador (automática, seguindo a lógica original)
	public void fazerJogadaComputador() {
		if (jogadorAtual != jogadorComputador) {
			return;
		}

		int[] move;

		// 1. Tenta vencer
		move = encontrarJogadaVencedora(jogadorComputador);
		// 2. Se não tiver jogada vencedora, tenta bloquear o humano
		if (move == null) {
			move = encontrarJogadaVencedora(jogadorHumano);
		}
		// 3. Caso contrário, joga aleatoriamente
		if (move == null) {
			do {
				move = new int[] { random.nextInt(3), random.nextInt(3) };
			} while (tabuleiro[move[0]][move[1]] != ' ');
		}

		tabuleiro[move[0]][move[1]] = jogadorComputador;
		jogadorAtual = jogadorHumano; // passa a vez para o humano
	}

	// Verifica se há um vencedor. Retorna 'X', 'O' ou ' ' (nenhum)
	public char verificarVencedor() {
		// Linhas
		for (int i = 0; i < 3; i++) {
			if (tabuleiro[i][0] != ' ' && tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2]) {
				return tabuleiro[i][0];
			}
		}

		// Colunas
		for (int j = 0; j < 3; j++) {
			if (tabuleiro[0][j] != ' ' && tabuleiro[0][j] == tabuleiro[1][j] && tabuleiro[1][j] == tabuleiro[2][j]) {
				return tabuleiro[0][j];
			}
		}

		// Diagonal principal
		if (tabuleiro[0][0] != ' ' && tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2]) {
			return tabuleiro[0][0];
		}

		// Diagonal secundária
		if (tabuleiro[0][2] != ' ' && tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0]) {
			return tabuleiro[0][2];
		}

		return ' ';
	}

	// Verifica se o tabuleiro está completamente preenchido
	public boolean tabuleiroCheio() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (tabuleiro[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;
	}

	// Retorna a posição (linha, coluna) de uma jogada que completa uma linha para o
	// jogador informado.
	// Usado pelo computador para vencer ou bloquear.
	private int[] encontrarJogadaVencedora(char jogador) {
		// Linhas
		for (int i = 0; i < 3; i++) {
			int count = 0;
			int posVazia = -1;
			for (int j = 0; j < 3; j++) {
				if (tabuleiro[i][j] == jogador) {
					count++;
				} else if (tabuleiro[i][j] == ' ') {
					posVazia = j;
				}
			}
			if (count == 2 && posVazia != -1) {
				return new int[] { i, posVazia };
			}
		}

		// Colunas
		for (int j = 0; j < 3; j++) {
			int count = 0;
			int posVazia = -1;
			for (int i = 0; i < 3; i++) {
				if (tabuleiro[i][j] == jogador) {
					count++;
				} else if (tabuleiro[i][j] == ' ') {
					posVazia = i;
				}
			}
			if (count == 2 && posVazia != -1) {
				return new int[] { posVazia, j };
			}
		}

		// Diagonal principal
		int count = 0;
		int posVazia = -1;
		for (int i = 0; i < 3; i++) {
			if (tabuleiro[i][i] == jogador) {
				count++;
			} else if (tabuleiro[i][i] == ' ') {
				posVazia = i;
			}
		}
		if (count == 2 && posVazia != -1) {
			return new int[] { posVazia, posVazia };
		}

		// Diagonal secundária
		count = 0;
		posVazia = -1;
		for (int i = 0; i < 3; i++) {
			if (tabuleiro[i][2 - i] == jogador) {
				count++;
			} else if (tabuleiro[i][2 - i] == ' ') {
				posVazia = i;
			}
		}
		if (count == 2 && posVazia != -1) {
			return new int[] { posVazia, 2 - posVazia };
		}

		return null;
	}

	// Getters para acesso controlado aos dados do jogo
	public char getJogadorAtual() {
		return jogadorAtual;
	}

	public char getJogadorHumano() {
		return jogadorHumano;
	}

	public char getJogadorComputador() {
		return jogadorComputador;
	}

	public int getVitoriasHumano() {
		return vitoriasHumano;
	}

	public int getVitoriasComputador() {
		return vitoriasComputador;
	}

	public int getEmpates() {
		return empates;
	}

	// Incrementa as estatísticas (chamado pelo frontend quando a partida termina)
	public void incrementarVitoriasHumano() {
		vitoriasHumano++;
	}

	public void incrementarVitoriasComputador() {
		vitoriasComputador++;
	}

	public void incrementarEmpates() {
		empates++;
	}

	// Retorna uma cópia do tabuleiro para que o frontend possa exibi-lo sem
	// modificá-lo
	public char[][] getTabuleiro() {
		char[][] copia = new char[3][3];
		for (int i = 0; i < 3; i++) {
			System.arraycopy(tabuleiro[i], 0, copia[i], 0, 3);
		}
		return copia;
	}
}