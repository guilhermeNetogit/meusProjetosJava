package meusprojetosjava.jogodavelha.jogodavelha2;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Interface Gráfica do Jogo da Velha V2.0 - Swing
 * 
 * Características: - Design moderno com sombras e cantos arredondados -
 * Animações suaves nos botões - Cores temáticas (X = azul, O =
 * vermelho/laranja) - Placar em tempo real - Mensagens de status animadas -
 * DESTAQUE DA LINHA VENCEDORA com bordas coloridas
 */
public class JogoDaVelha2Swing extends JFrame {// Eclipse -> Github @guilhermeNetogit 24/03/2026 23:38:20

	private static final long serialVersionUID = 8208707909256758069L;

	// Cores do tema
	private static final Color COR_FUNDO = new Color(245, 247, 250);
	private static final Color COR_X = new Color(59, 130, 246); // Azul
	private static final Color COR_O = new Color(239, 68, 68); // Vermelho
	private static final Color COR_VAZIO = new Color(255, 255, 255);
	private static final Color COR_BORDA = new Color(203, 213, 225);
	private static final Color COR_TEXTO = new Color(51, 65, 85);
	private static final Color COR_DESTAQUE = new Color(99, 102, 241);
	private static final Color COR_VITORIA_X = new Color(37, 99, 235); // Azul escuro
	private static final Color COR_VITORIA_O = new Color(220, 38, 38); // Vermelho escuro

	private JogoDaVelha2 jogo;
	private CelulaTabuleiro[][] celulasTabuleiro;
	private JLabel labelStatus;
	private JLabel labelPlacarHumano;
	private JLabel labelPlacarComputador;
	private JLabel labelPlacarEmpates;
	private JLabel labelVez;
	private JPanel painelPlacar;
	private Timer timerComputador;
	private Timer timerAnimacaoVitoria;

	public JogoDaVelha2Swing() {
		jogo = new JogoDaVelha2();

		// Configuração da janela
		setTitle("🎮 Jogo da Velha V2.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 650);
		setLocationRelativeTo(null);
		setResizable(false);

		// Layout principal
		setLayout(new BorderLayout(20, 20));
		getContentPane().setBackground(COR_FUNDO);

		// Painel superior com título e placar
		add(criarPainelSuperior(), BorderLayout.NORTH);

		// Painel central com tabuleiro
		add(criarPainelTabuleiro(), BorderLayout.CENTER);

		// Painel inferior com controles
		add(criarPainelInferior(), BorderLayout.SOUTH);

		// Timer para jogada do computador
		timerComputador = new Timer(800, e -> {
			fazerJogadaComputador();
		});
		timerComputador.setRepeats(false);

		// Timer para animação de vitória (piscar bordas)
		timerAnimacaoVitoria = new Timer(500, e -> {
			animarBordasVitoria();
		});

		// Iniciar primeira partida
		iniciarNovaPartida();
	}

	private JPanel criarPainelSuperior() {
		JPanel painel = new JPanel(new BorderLayout(10, 10));
		painel.setBackground(COR_FUNDO);
		painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

		// Título
		JLabel titulo = new JLabel("⭕ Jogo da Velha ❌", SwingConstants.CENTER);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
		titulo.setForeground(COR_TEXTO);
		painel.add(titulo, BorderLayout.NORTH);

		// Subtítulo
		JLabel subtitulo = new JLabel("Você (X) vs Computador (O)", SwingConstants.CENTER);
		subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		subtitulo.setForeground(new Color(100, 116, 139));
		painel.add(subtitulo, BorderLayout.CENTER);

		// Painel de placar
		painelPlacar = criarPainelPlacar();
		painel.add(painelPlacar, BorderLayout.SOUTH);

		return painel;
	}

	private JPanel criarPainelPlacar() {
		JPanel painel = new JPanel(new GridLayout(1, 3, 15, 0));
		painel.setBackground(COR_FUNDO);
		painel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

		// Card Vitórias Humano
		JPanel cardHumano = criarCardPlacar("🎮 Você", "0", COR_X);
		labelPlacarHumano = (JLabel) cardHumano.getComponent(1);
		painel.add(cardHumano);

		// Card Empates
		JPanel cardEmpates = criarCardPlacar("🤝 Empates", "0", new Color(107, 114, 128));
		labelPlacarEmpates = (JLabel) cardEmpates.getComponent(1);
		painel.add(cardEmpates);

		// Card Vitórias Computador
		JPanel cardComputador = criarCardPlacar("💻 PC", "0", COR_O);
		labelPlacarComputador = (JLabel) cardComputador.getComponent(1);
		painel.add(cardComputador);

		return painel;
	}

	private JPanel criarCardPlacar(String titulo, String valor, Color cor) {
		JPanel card = new JPanel(new GridLayout(2, 1, 5, 5));
		card.setBackground(Color.WHITE);
		card.setBorder(BorderFactory.createCompoundBorder(new LineBorder(COR_BORDA, 1, true),
				BorderFactory.createEmptyBorder(10, 15, 10, 15)));

		JLabel labelTitulo = new JLabel(titulo, SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		labelTitulo.setForeground(new Color(100, 116, 139));

		JLabel labelValor = new JLabel(valor, SwingConstants.CENTER);
		labelValor.setFont(new Font("Segoe UI", Font.BOLD, 24));
		labelValor.setForeground(cor);

		card.add(labelTitulo);
		card.add(labelValor);

		return card;
	}

	private JPanel criarPainelTabuleiro() {
		JPanel painel = new JPanel(new GridLayout(3, 3, 8, 8));
		painel.setBackground(COR_BORDA);
		painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		celulasTabuleiro = new CelulaTabuleiro[3][3];

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				CelulaTabuleiro celula = new CelulaTabuleiro(i, j);
				celulasTabuleiro[i][j] = celula;
				painel.add(celula);
			}
		}

		return painel;
	}

	private JPanel criarPainelInferior() {
		JPanel painel = new JPanel(new BorderLayout(10, 10));
		painel.setBackground(COR_FUNDO);
		painel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

		// Label de status/vez
		labelVez = new JLabel("🎯 Clique em 'Nova Partida' para começar", SwingConstants.CENTER);
		labelVez.setFont(new Font("Segoe UI", Font.BOLD, 14));
		labelVez.setForeground(COR_DESTAQUE);
		painel.add(labelVez, BorderLayout.NORTH);

		// Botão Nova Partida
		JButton btnNovaPartida = new JButton("🔄 Nova Partida");
		btnNovaPartida.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnNovaPartida.setBackground(COR_DESTAQUE);
		btnNovaPartida.setForeground(Color.WHITE);
		btnNovaPartida.setFocusPainted(false);
		btnNovaPartida.setBorder(BorderFactory.createEmptyBorder(12, 30, 12, 30));
		btnNovaPartida.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// Efeito hover no botão
		btnNovaPartida.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNovaPartida.setBackground(new Color(79, 82, 211));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnNovaPartida.setBackground(COR_DESTAQUE);
			}
		});

		btnNovaPartida.addActionListener(e -> iniciarNovaPartida());

		JPanel painelBotao = new JPanel();
		painelBotao.setBackground(COR_FUNDO);
		painelBotao.add(btnNovaPartida);
		painel.add(painelBotao, BorderLayout.CENTER);

		// Label de status do jogo
		labelStatus = new JLabel("Pronto para jogar!", SwingConstants.CENTER);
		labelStatus.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		labelStatus.setForeground(new Color(100, 116, 139));
		painel.add(labelStatus, BorderLayout.SOUTH);

		return painel;
	}

	private void iniciarNovaPartida() {
		// Para animação anterior se existir
		timerAnimacaoVitoria.stop();

		jogo.iniciarPartida();
		limparDestaqueVitoria();
		atualizarTabuleiro();
		atualizarPlacar();

		if (jogo.getJogadorAtual() == jogo.getJogadorComputador()) {
			labelVez.setText("💻 O computador começa!");
			labelVez.setForeground(COR_O);
			timerComputador.start();
		} else {
			labelVez.setText("🎮 Sua vez de jogar!");
			labelVez.setForeground(COR_X);
		}

		labelStatus.setText("Partida iniciada - Boa sorte!");
		habilitarBotoes(true);
	}

	private void fazerJogadaHumana(int linha, int coluna) {
		if (jogo.getJogadorAtual() != jogo.getJogadorHumano()) {
			return;
		}

		if (jogo.fazerJogadaHumana(linha, coluna)) {
			atualizarTabuleiro();

			if (verificarFimDeJogo()) {
				return;
			}

			labelVez.setText("💻 Vez do computador...");
			labelVez.setForeground(COR_O);
			habilitarBotoes(false);

			timerComputador.start();
		} else {
			// Animação de erro sutil
			celulasTabuleiro[linha][coluna].setBackgroundTemporario(new Color(254, 202, 202));
		}
	}

	private void fazerJogadaComputador() {
		jogo.fazerJogadaComputador();
		atualizarTabuleiro();

		if (!verificarFimDeJogo()) {
			labelVez.setText("🎮 Sua vez de jogar!");
			labelVez.setForeground(COR_X);
			habilitarBotoes(true);
		}
	}

	private boolean verificarFimDeJogo() {
		char vencedor = jogo.verificarVencedor();

		if (vencedor != ' ') {
			habilitarBotoes(false);

			// Detecta e destaca a linha vencedora
			int[][] linhaVencedora = detectarLinhaVencedora(vencedor);
			if (linhaVencedora != null) {
				destacarLinhaVitoria(linhaVencedora, vencedor);
			}

			if (vencedor == jogo.getJogadorHumano()) {
				jogo.incrementarVitoriasHumano();
				labelVez.setText("🎉 Você venceu! Parabéns!");
				labelVez.setForeground(COR_VITORIA_X);
			} else {
				jogo.incrementarVitoriasComputador();
				labelVez.setText("💻 O computador venceu!");
				labelVez.setForeground(COR_VITORIA_O);
			}
			atualizarPlacar();
			return true;
		} else if (jogo.tabuleiroCheio()) {
			jogo.incrementarEmpates();
			labelVez.setText("🤝 Empate!");
			labelVez.setForeground(new Color(107, 114, 128));
			atualizarPlacar();
			return true;
		}

		return false;
	}

	/**
	 * Detecta qual linha, coluna ou diagonal formou a sequência vencedora Retorna
	 * array com as coordenadas [linha][coluna] das 3 células vencedoras
	 */
	private int[][] detectarLinhaVencedora(char vencedor) {
		char[][] tabuleiro = jogo.getTabuleiro();

		// Verifica linhas
		for (int i = 0; i < 3; i++) {
			if (tabuleiro[i][0] == vencedor && tabuleiro[i][1] == vencedor && tabuleiro[i][2] == vencedor) {
				return new int[][] { { i, 0 }, { i, 1 }, { i, 2 } };
			}
		}

		// Verifica colunas
		for (int j = 0; j < 3; j++) {
			if (tabuleiro[0][j] == vencedor && tabuleiro[1][j] == vencedor && tabuleiro[2][j] == vencedor) {
				return new int[][] { { 0, j }, { 1, j }, { 2, j } };
			}
		}

		// Verifica diagonal principal
		if (tabuleiro[0][0] == vencedor && tabuleiro[1][1] == vencedor && tabuleiro[2][2] == vencedor) {
			return new int[][] { { 0, 0 }, { 1, 1 }, { 2, 2 } };
		}

		// Verifica diagonal secundária
		if (tabuleiro[0][2] == vencedor && tabuleiro[1][1] == vencedor && tabuleiro[2][0] == vencedor) {
			return new int[][] { { 0, 2 }, { 1, 1 }, { 2, 0 } };
		}

		return null;
	}

	/**
	 * Destaca as células vencedoras com bordas coloridas e animação
	 */
	private void destacarLinhaVitoria(int[][] coordenadas, char vencedor) {
		Color corVitoria = (vencedor == 'X') ? COR_VITORIA_X : COR_VITORIA_O;

		for (int[] coord : coordenadas) {
			int linha = coord[0];
			int coluna = coord[1];
			celulasTabuleiro[linha][coluna].setVencedora(true, corVitoria);
		}

		// Inicia animação de pulsação nas bordas
		timerAnimacaoVitoria.start();
	}

	private void animarBordasVitoria() {
		boolean estado = timerAnimacaoVitoria.getDelay() == 500;
		// Alterna entre borda grossa e fina para criar efeito de pulsação
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (celulasTabuleiro[i][j].isVencedora()) {
					celulasTabuleiro[i][j].toggleBordaPulsante();
				}
			}
		}
		timerAnimacaoVitoria.setDelay(estado ? 600 : 500);
	}

	private void limparDestaqueVitoria() {
		timerAnimacaoVitoria.stop();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				celulasTabuleiro[i][j].setVencedora(false, null);
			}
		}
	}

	private void atualizarTabuleiro() {
		char[][] tabuleiro = jogo.getTabuleiro();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				String texto = tabuleiro[i][j] == ' ' ? "" : String.valueOf(tabuleiro[i][j]);
				celulasTabuleiro[i][j].setTexto(texto);
			}
		}
	}

	private void atualizarPlacar() {
		labelPlacarHumano.setText(String.valueOf(jogo.getVitoriasHumano()));
		labelPlacarComputador.setText(String.valueOf(jogo.getVitoriasComputador()));
		labelPlacarEmpates.setText(String.valueOf(jogo.getEmpates()));

		// Destaque visual no placar do líder
		if (jogo.getVitoriasHumano() > jogo.getVitoriasComputador()) {
			labelPlacarHumano.setFont(new Font("Segoe UI", Font.BOLD, 28));
			labelPlacarComputador.setFont(new Font("Segoe UI", Font.BOLD, 24));
		} else if (jogo.getVitoriasComputador() > jogo.getVitoriasHumano()) {
			labelPlacarHumano.setFont(new Font("Segoe UI", Font.BOLD, 24));
			labelPlacarComputador.setFont(new Font("Segoe UI", Font.BOLD, 28));
		} else {
			labelPlacarHumano.setFont(new Font("Segoe UI", Font.BOLD, 24));
			labelPlacarComputador.setFont(new Font("Segoe UI", Font.BOLD, 24));
		}
	}

	private void habilitarBotoes(boolean habilitar) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				celulasTabuleiro[i][j].setEnabled(habilitar && celulasTabuleiro[i][j].getTexto().isEmpty());
			}
		}
	}

	/**
	 * Classe interna que representa uma célula do tabuleiro com suporte a destaque
	 * de vitória
	 */
	private class CelulaTabuleiro extends JButton {

		private static final long serialVersionUID = 3774464898860829168L;

		private boolean isVencedora = false;
		private Color corVitoria;
		private boolean bordaGrossa = true;
		private String texto = "";

		public CelulaTabuleiro(int linha, int coluna) {
			setFont(new Font("Segoe UI", Font.BOLD, 48));
			setBackground(COR_VAZIO);
			setFocusPainted(false);
			setBorderPainted(false);
			setContentAreaFilled(false);
			setCursor(new Cursor(Cursor.HAND_CURSOR));
			setPreferredSize(new Dimension(100, 100));

			// Efeito hover
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					if (texto.isEmpty() && jogo.getJogadorAtual() == jogo.getJogadorHumano() && isEnabled()) {
						setBackground(new Color(241, 245, 249));
						repaint();
					}
				}

				@Override
				public void mouseExited(MouseEvent e) {
					if (!isVencedora) {
						setBackground(COR_VAZIO);
					}
					repaint();
				}
			});

			// Ação de clique
			addActionListener(e -> fazerJogadaHumana(linha, coluna));
		}

		public void setTexto(String texto) {
			this.texto = texto;
			repaint();
		}

		public String getTexto() {
			return texto;
		}

		public void setVencedora(boolean vencedora, Color cor) {
			this.isVencedora = vencedora;
			this.corVitoria = cor;
			if (vencedora && cor != null) {
				// Fundo sutil da cor do vencedor
				setBackground(new Color(cor.getRed(), cor.getGreen(), cor.getBlue(), 20));
			} else {
				setBackground(COR_VAZIO);
			}
			repaint();
		}

		public boolean isVencedora() {
			return isVencedora;
		}

		public void toggleBordaPulsante() {
			bordaGrossa = !bordaGrossa;
			repaint();
		}

		public void setBackgroundTemporario(Color cor) {
			setBackground(cor);
			repaint();
			Timer timer = new Timer(300, e -> {
				if (!isVencedora) {
					setBackground(COR_VAZIO);
					repaint();
				}
			});
			timer.setRepeats(false);
			timer.start();
		}

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

			int width = getWidth();
			int height = getHeight();
			int arc = 15;

			// Fundo arredondado
			g2.setColor(getBackground());
			g2.fillRoundRect(2, 2, width - 4, height - 4, arc, arc);

			// Borda
			if (isVencedora && corVitoria != null) {
				// Borda grossa e colorida para células vencedoras
				g2.setColor(corVitoria);
				int espessura = bordaGrossa ? 5 : 3;
				g2.setStroke(new BasicStroke(espessura, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
				g2.drawRoundRect(espessura / 2, espessura / 2, width - espessura, height - espessura, arc, arc);

				// Sombra externa sutil
				g2.setColor(new Color(corVitoria.getRed(), corVitoria.getGreen(), corVitoria.getBlue(), 50));
				g2.setStroke(new BasicStroke(2));
				g2.drawRoundRect(1, 1, width - 2, height - 2, arc, arc);
			} else {
				// Borda normal
				g2.setColor(COR_BORDA);
				g2.setStroke(new BasicStroke(1));
				g2.drawRoundRect(1, 1, width - 2, height - 2, arc, arc);
			}

			// Texto (X ou O)
			if (!texto.isEmpty()) {
				g2.setFont(getFont());
				FontMetrics fm = g2.getFontMetrics();

				if (texto.equals("X")) {
					g2.setColor(COR_X);
				} else {
					g2.setColor(COR_O);
				}

				// Sombra no texto para efeito 3D
				g2.setColor(new Color(0, 0, 0, 30));
				int x = (width - fm.stringWidth(texto)) / 2 + 2;
				int y = ((height - fm.getHeight()) / 2) + fm.getAscent() + 2;
				g2.drawString(texto, x, y);

				// Texto principal
				if (texto.equals("X")) {
					g2.setColor(isVencedora ? COR_VITORIA_X : COR_X);
				} else {
					g2.setColor(isVencedora ? COR_VITORIA_O : COR_O);
				}
				x = (width - fm.stringWidth(texto)) / 2;
				y = ((height - fm.getHeight()) / 2) + fm.getAscent();
				g2.drawString(texto, x, y);
			}

			g2.dispose();
		}
	}

	public static void main(String[] args) {
		// Look and Feel moderno
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(() -> {
			new JogoDaVelha2Swing().setVisible(true);
		});
	}
}