package meusprojetosjava.SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener { // Eclipse -> Github @guilhermeNetogit 19/02/2026 20:34:44
	private boolean awaitingResetConfirmation = false;
	private static final long serialVersionUID = 1L;
    
    // Configurações do jogo
    private static final int BOARD_WIDTH = 800;  // Era 600
    private static final int BOARD_HEIGHT = 800; // Era 600
    private static final int UNIT_SIZE = 25;     // Mantido 25 (bom tamanho)
    private static final int GAME_UNITS = (BOARD_WIDTH * BOARD_HEIGHT) / UNIT_SIZE;
    private static final int DELAY = 100;
    
    // Controle da cobra
    private final int[] x = new int[GAME_UNITS];
    private final int[] y = new int[GAME_UNITS];
    private int bodyParts = 3;
    private int applesEaten = 0;
    
    // Posição da maçã
    private int appleX;
    private int appleY;
    
    // Direção
    private char direction = 'R';
    private boolean running = false;
    private boolean showingMenu = true;
    private boolean showingRanking = false;
    
    // Timer e Random
    private Timer timer;
    private Random random;
    
    // Cores personalizáveis
    private Color snakeHeadColor = new Color(0, 150, 0);
    private Color snakeBodyColor = new Color(45, 180, 0);
    private Color appleColor = Color.RED;
    private Color backgroundColor = Color.BLACK;
    
    // Sistema de ranking
    private ScoreManager scoreManager;
    private String playerName = "";
    private boolean enteringName = true;
    
    public GamePanel() {
        random = new Random();
        scoreManager = new ScoreManager();
        this.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        this.setBackground(backgroundColor);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
    }
    
    public void startGame() {
        showingMenu = false;
        showingRanking = false;
        enteringName = false;
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (showingMenu) {
            drawMenu(g);
        } else if (showingRanking) {
            drawRanking(g);
        } else if (running) {
            draw(g);
        } else {
            gameOver(g);
        }
    }
    
    private void drawMenu(Graphics g) {
        // Título principal - MAIOR
        g.setColor(new Color(50, 200, 50));
        g.setFont(new Font("Arial", Font.BOLD, 60)); // Era 50
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("JOGO DA COBRINHA", 
            (BOARD_WIDTH - metrics1.stringWidth("JOGO DA COBRINHA")) / 2, 
            180); // Ajustado para baixo
        
        // Instruções - MAIORES e melhor espaçadas
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 22)); // Era 18
        
        int y = 280; // Começa mais abaixo
        String[] instructions = {
            "Use as setas ou WASD para mover",
            "Coma as maçãs para crescer e ganhar pontos",
            "Não bata nas paredes ou no próprio corpo"
        };
        
        for (String instruction : instructions) {
            FontMetrics fm = getFontMetrics(g.getFont());
            g.drawString(instruction, 
                (BOARD_WIDTH - fm.stringWidth(instruction)) / 2, 
                y);
            y += 40; // Espaçamento maior
        }
        
        // Entrada do nome - MAIOR
        if (enteringName) {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 30)); // Era 24
            
            String namePrompt = "Digite seu nome: " + playerName + 			(System.currentTimeMillis() % 1000 < 500 ? "_" : "");
            FontMetrics fm = getFontMetrics(g.getFont());
            g.drawString(namePrompt, 
                (BOARD_WIDTH - fm.stringWidth(namePrompt)) / 2, 
                450); // Posição ajustada
            
            g.setColor(Color.GRAY);
            g.setFont(new Font("Arial", Font.PLAIN, 20)); // Era 16
            String enterPrompt = "Pressione ENTER para começar";
            fm = getFontMetrics(g.getFont());
            g.drawString(enterPrompt, 
                (BOARD_WIDTH - fm.stringWidth(enterPrompt)) / 2, 
                500);
        }
        
        // Opções do menu - MAIORES
        g.setColor(Color.CYAN);
        g.setFont(new Font("Arial", Font.BOLD, 24)); // Era 20
        
        String[] options = {
            "Pressione R para ver o Ranking",
            "Pressione ESC para sair"
        };
        
        y = 580; // Posição inicial das opções
        for (String option : options) {
            FontMetrics fm = getFontMetrics(g.getFont());
            g.drawString(option, 
                (BOARD_WIDTH - fm.stringWidth(option)) / 2, 
                y);
            y += 35;
        }
    }
    
    private void drawRanking(Graphics g) {
        // Título - MAIOR
        g.setColor(new Color(255, 215, 0));
        g.setFont(new Font("Arial", Font.BOLD, 48)); // Era 40
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("TOP 10 PONTUAÇÕES", 
            (BOARD_WIDTH - metrics.stringWidth("TOP 10 PONTUAÇÕES")) / 2, 
            100); // Ajustado
        
        // Cabeçalho - MAIOR
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 22)); // Era 18
        g.drawString("Pos.", 70, 160); // Ajustado
        g.drawString("Jogador", 200, 160); // Ajustado
        g.drawString("Pontos", 450, 160); // Ajustado
        g.drawString("Data", 600, 160); // Ajustado
        
        // Linha separadora - ajustada
        g.drawLine(50, 170, 750, 170);
        
        // Lista de pontuações
        List<ScoreManager.Score> scores = scoreManager.getTopScores();
        g.setFont(new Font("Arial", Font.PLAIN, 20)); // Era 16
        
        if (scores.isEmpty()) {
            g.setColor(Color.GRAY);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            String msg = "Nenhuma pontuação registrada ainda!";
            FontMetrics fm = getFontMetrics(g.getFont());
            g.drawString(msg, 
                (BOARD_WIDTH - fm.stringWidth(msg)) / 2, 
                400);
        } else {
            for (int i = 0; i < scores.size(); i++) {
                ScoreManager.Score score = scores.get(i);
                int y = 210 + i * 35; // Espaçamento maior
                
                // Cores para as primeiras posições
                if (i == 0) g.setColor(new Color(255, 215, 0));
                else if (i == 1) g.setColor(new Color(192, 192, 192));
                else if (i == 2) g.setColor(new Color(205, 127, 50));
                else g.setColor(Color.WHITE);
                
                g.drawString((i + 1) + "º", 70, y);
                g.drawString(score.getPlayerName(), 200, y);
                g.drawString(String.valueOf(score.getScore()), 450, y);
                g.drawString(score.getDate(), 600, y);
            }
        }
        
        // Estatísticas do ranking
        if (scoreManager.hasScores()) {
            g.setColor(new Color(100, 100, 200));
            g.setFont(new Font("Arial", Font.ITALIC, 18));
            String stats = "Total de jogadores no ranking: " + scoreManager.getTotalPlayers();
            FontMetrics fm = getFontMetrics(g.getFont());
            g.drawString(stats, 
                (BOARD_WIDTH - fm.stringWidth(stats)) / 2, 
                650);
        }
        
        // Instruções - MAIORES
        g.setColor(Color.CYAN);
        g.setFont(new Font("Arial", Font.BOLD, 22));
        
        if (scoreManager.hasScores()) {
            String resetMsg = "Pressione R para resetar o ranking";
            FontMetrics fm = getFontMetrics(g.getFont());
            g.drawString(resetMsg, 
                (BOARD_WIDTH - fm.stringWidth(resetMsg)) / 2, 
                700);
        }
        
        String backMsg = "Pressione M para voltar ao menu";
        FontMetrics fm = getFontMetrics(g.getFont());
        g.drawString(backMsg, 
            (BOARD_WIDTH - fm.stringWidth(backMsg)) / 2, 
            750);
        
        // Confirmação de reset (se ativa)
        if (awaitingResetConfirmation) {
            drawResetConfirmation(g);
        }
    }

    private void drawResetConfirmation(Graphics g) {
        // Fundo semi-transparente
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
        
        // Caixa de confirmação - MAIOR
        g.setColor(new Color(50, 50, 50));
        g.fillRect(200, 250, 400, 250); // Era 150,200,300,200
        g.setColor(Color.RED);
        g.drawRect(200, 250, 400, 250);
        
        // Texto de confirmação - MAIOR
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24)); // Era 20
        String msg1 = "RESETAR RANKING?";
        FontMetrics fm1 = getFontMetrics(g.getFont());
        g.drawString(msg1, 
            400 - fm1.stringWidth(msg1) / 2, 320); // Ajustado
        
        g.setFont(new Font("Arial", Font.PLAIN, 20)); // Era 16
        String msg2 = "Todas as pontuações serão";
        String msg3 = "permanentemente apagadas!";
        FontMetrics fm2 = getFontMetrics(g.getFont());
        g.drawString(msg2, 
            400 - fm2.stringWidth(msg2) / 2, 370); // Ajustado
        g.drawString(msg3, 
            400 - fm2.stringWidth(msg3) / 2, 400); // Ajustado
        
        // Opções - MAIORES
        g.setColor(new Color(100, 255, 100));
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("ENTER - Confirmar", 250, 460); // Ajustado
        g.setColor(new Color(255, 100, 100));
        g.drawString("ESC - Cancelar", 450, 460); // Ajustado
    }
    
    public void draw(Graphics g) {
        // Grid - ajustado para o novo tamanho
        g.setColor(new Color(30, 30, 30));
        for (int i = 0; i < BOARD_HEIGHT / UNIT_SIZE; i++) {
            g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, BOARD_HEIGHT);
            g.drawLine(0, i * UNIT_SIZE, BOARD_WIDTH, i * UNIT_SIZE);
        }
        
        // Desenhar a maçã
        g.setColor(appleColor);
        g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
        
        // Desenhar a cobra (código existente, sem mudanças)
        for (int i = 0; i < bodyParts; i++) {
            if (i == 0) {
                g.setColor(snakeHeadColor);
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                
                g.setColor(Color.WHITE);
                if (direction == 'R') {
                    g.fillOval(x[i] + UNIT_SIZE - 6, y[i] + 5, 4, 4);
                    g.fillOval(x[i] + UNIT_SIZE - 6, y[i] + UNIT_SIZE - 9, 4, 4);
                } else if (direction == 'L') {
                    g.fillOval(x[i] + 2, y[i] + 5, 4, 4);
                    g.fillOval(x[i] + 2, y[i] + UNIT_SIZE - 9, 4, 4);
                } else if (direction == 'U') {
                    g.fillOval(x[i] + 5, y[i] + 2, 4, 4);
                    g.fillOval(x[i] + UNIT_SIZE - 9, y[i] + 2, 4, 4);
                } else if (direction == 'D') {
                    g.fillOval(x[i] + 5, y[i] + UNIT_SIZE - 6, 4, 4);
                    g.fillOval(x[i] + UNIT_SIZE - 9, y[i] + UNIT_SIZE - 6, 4, 4);
                }
            } else {
                g.setColor(snakeBodyColor);
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                
                g.setColor(new Color(30, 100, 0));
                g.drawRect(x[i] + 2, y[i] + 2, UNIT_SIZE - 5, UNIT_SIZE - 5);
            }
        }
        
        // Placar - MAIOR e melhor posicionado
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24)); // Era 20
        String scoreText = "Pontuação: " + applesEaten;
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString(scoreText, 
            (BOARD_WIDTH - metrics.stringWidth(scoreText)) / 2, 
            40); // Ajustado para o topo
        
        // Mostrar recorde a ser batido
        int minScore = scoreManager.getMinimumScoreToEnter();
        if (minScore > 0) {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.drawString("Mínimo para o Top 10: " + minScore, 20, 30);
        }
    }
    
    public void gameOver(Graphics g) {
        // Pontuação final - MAIOR
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 36)); // Era 30
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Pontuação: " + applesEaten, 
            (BOARD_WIDTH - metrics1.stringWidth("Pontuação: " + applesEaten)) / 2, 
            250); // Ajustado
        
        // Verificar recordes
        boolean isNewRecord = scoreManager.isNewRecord(playerName, applesEaten);
        
        if (isNewRecord && applesEaten > 0) {
            if (scoreManager.getPlayerBestScore(playerName) == 0) {
                g.setColor(new Color(0, 255, 255));
            } else {
                g.setColor(new Color(255, 215, 0));
            }
            
            g.setFont(new Font("Arial", Font.BOLD, 28)); // Era 25
            String recordMessage = scoreManager.getRecordMessage(playerName, applesEaten);
            FontMetrics metrics4 = getFontMetrics(g.getFont());
            g.drawString(recordMessage, 
                (BOARD_WIDTH - metrics4.stringWidth(recordMessage)) / 2, 
                300); // Ajustado
            
            int previousBest = scoreManager.getPlayerBestScore(playerName);
            if (previousBest > 0 && applesEaten > previousBest) {
                g.setColor(Color.LIGHT_GRAY);
                g.setFont(new Font("Arial", Font.PLAIN, 22)); // Era 18
                String prevMsg = "Recorde anterior: " + previousBest;
                FontMetrics metrics5 = getFontMetrics(g.getFont());
                g.drawString(prevMsg, 
                    (BOARD_WIDTH - metrics5.stringWidth(prevMsg)) / 2, 
                    340); // Ajustado
            }
            
            scoreManager.addScore(playerName, applesEaten);
        } else if (applesEaten > 0) {
            int bestScore = scoreManager.getPlayerBestScore(playerName);
            if (bestScore > 0) {
                g.setColor(Color.YELLOW);
                g.setFont(new Font("Arial", Font.PLAIN, 22));
                String neededMsg = "Seu recorde é " + bestScore + ". Faltam " + 
                                  (bestScore - applesEaten + 1) + " ponto(s) para superá-lo!";
                FontMetrics metrics4 = getFontMetrics(g.getFont());
                g.drawString(neededMsg, 
                    (BOARD_WIDTH - metrics4.stringWidth(neededMsg)) / 2, 
                    300);
            }
        }
        
        // Game Over - MAIOR
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 60)); // Era 50
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", 
            (BOARD_WIDTH - metrics2.stringWidth("Game Over")) / 2, 
            BOARD_HEIGHT / 2);
        
        // Instruções - MAIORES
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24)); // Era 20
        String[] gameOverOptions = {
            "Pressione M para voltar ao menu",
            "Pressione R para ver o ranking"
        };
        
        int y = BOARD_HEIGHT / 2 + 70;
        for (String option : gameOverOptions) {
            FontMetrics fm = getFontMetrics(g.getFont());
            g.drawString(option, 
                (BOARD_WIDTH - fm.stringWidth(option)) / 2, 
                y);
            y += 40;
        }
    }
    
    public void newApple() {
        appleX = random.nextInt((int)(BOARD_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int)(BOARD_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
        
        for (int i = 0; i < bodyParts; i++) {
            if (x[i] == appleX && y[i] == appleY) {
                newApple();
                return;
            }
        }
    }
    
    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        
        switch (direction) {
            case 'R':
                x[0] += UNIT_SIZE;
                break;
            case 'L':
                x[0] -= UNIT_SIZE;
                break;
            case 'U':
                y[0] -= UNIT_SIZE;
                break;
            case 'D':
                y[0] += UNIT_SIZE;
                break;
        }
    }
    
    public void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            bodyParts++;
            applesEaten++;
            newApple();
            
            if (applesEaten % 5 == 0 && DELAY > 50) {
                timer.setDelay(DELAY - 5);
            }
        }
    }
    
    public void checkCollisions() {
        for (int i = bodyParts; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
            }
        }
        
        if (x[0] < 0 || x[0] >= BOARD_WIDTH || y[0] < 0 || y[0] >= BOARD_HEIGHT) {
            running = false;
        }
        
        if (!running) {
            timer.stop();
        }
    }
    
    public void restartGame() {
        bodyParts = 3;
        applesEaten = 0;
        direction = 'R';
        
        for (int i = 0; i < bodyParts; i++) {
            x[i] = 0;
            y[i] = 0;
        }
        
        timer.setDelay(DELAY);
        showingMenu = true;
        enteringName = true;
        playerName = "";
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }
    
    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (showingMenu) {
                if (enteringName) {
                    // Processar entrada do nome (mesmo código anterior)
                    if (e.getKeyCode() == KeyEvent.VK_ENTER && !playerName.trim().isEmpty()) {
                        startGame();
                    } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && playerName.length() > 0) {
                        playerName = playerName.substring(0, playerName.length() - 1);
                    } else if (e.getKeyCode() == KeyEvent.VK_R) {
                        showingMenu = false;
                        showingRanking = true;
                        enteringName = false;
                    } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        System.exit(0);
                    } else {
                        char c = e.getKeyChar();
                        if (Character.isLetterOrDigit(c) || c == ' ' && playerName.length() < 15) {
                            playerName += c;
                        }
                    }
                } else {
                    // Menu sem entrada de nome
                    if (e.getKeyCode() == KeyEvent.VK_R) {
                        showingMenu = false;
                        showingRanking = true;
                    } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        System.exit(0);
                    }
                }
            } else if (showingRanking) {
                if (awaitingResetConfirmation) {
                    // Processar confirmação de reset
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        // Confirmou reset
                        if (scoreManager.resetRanking()) {
                            JOptionPane.showMessageDialog(GamePanel.this, 
                                "Ranking resetado com sucesso!", 
                                "Reset Completo", 
                                JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(GamePanel.this, 
                                "Erro ao resetar ranking!", 
                                "Erro", 
                                JOptionPane.ERROR_MESSAGE);
                        }
                        awaitingResetConfirmation = false;
                    } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        // Cancelou reset
                        awaitingResetConfirmation = false;
                    }
                } else {
                    if (e.getKeyCode() == KeyEvent.VK_M) {
                        showingRanking = false;
                        showingMenu = true;
                        enteringName = true;
                        playerName = "";
                    } else if (e.getKeyCode() == KeyEvent.VK_R && scoreManager.hasScores()) {
                        // Ativar modo de confirmação de reset
                        awaitingResetConfirmation = true;
                    }
                }
            } else if (!running) {
                // Tela de game over
                if (e.getKeyCode() == KeyEvent.VK_M) {
                    restartGame();
                } else if (e.getKeyCode() == KeyEvent.VK_R) {
                    showingRanking = true;
                    showingMenu = false;
                }
            } else {
                // Controles do jogo (mesmo código anterior)
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_A:
                        if (direction != 'R') {
                            direction = 'L';
                        }
                        break;
                        
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D:
                        if (direction != 'L') {
                            direction = 'R';
                        }
                        break;
                        
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_W:
                        if (direction != 'D') {
                            direction = 'U';
                        }
                        break;
                        
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S:
                        if (direction != 'U') {
                            direction = 'D';
                        }
                        break;
                        
                    case KeyEvent.VK_ESCAPE:
                        running = false;
                        timer.stop();
                        restartGame();
                        break;
                }
            }
            repaint();
        }
    }
}