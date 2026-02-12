package meusprojetosjava.SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {// Eclipse -> Github @guilhermeNetogit 12/02/2026 19:34:48
	private static final long serialVersionUID = 1L;
    
    // Configurações do jogo
    private static final int BOARD_WIDTH = 600;
    private static final int BOARD_HEIGHT = 600;
    private static final int UNIT_SIZE = 25;
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
    private char direction = 'R'; // R=Direita, L=Esquerda, U=Cima, D=Baixo
    private boolean running = false;
    
    // Timer e Random
    private Timer timer;
    private Random random;
    
    // Cores personalizáveis
    private Color snakeHeadColor = new Color(0, 150, 0);
    private Color snakeBodyColor = new Color(45, 180, 0);
    private Color appleColor = Color.RED;
    private Color backgroundColor = Color.BLACK;
    
    public GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        this.setBackground(backgroundColor);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        
        startGame();
    }
    
    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    
    public void draw(Graphics g) {
        if (running) {
            // Desenhar linhas do grid (opcional - comentar para remover)
            g.setColor(new Color(30, 30, 30));
            for (int i = 0; i < BOARD_HEIGHT / UNIT_SIZE; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, BOARD_HEIGHT);
                g.drawLine(0, i * UNIT_SIZE, BOARD_WIDTH, i * UNIT_SIZE);
            }
            
            // Desenhar a maçã
            g.setColor(appleColor);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
            
            // Desenhar a cobra
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    // Cabeça da cobra
                    g.setColor(snakeHeadColor);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                    
                    // Olhos da cobra (detalhe)
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
                    // Corpo da cobra
                    g.setColor(snakeBodyColor);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                    
                    // Detalhe das escamas
                    g.setColor(new Color(30, 100, 0));
                    g.drawRect(x[i] + 2, y[i] + 2, UNIT_SIZE - 5, UNIT_SIZE - 5);
                }
            }
            
            // Placar
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Pontuação: " + applesEaten, 
                (BOARD_WIDTH - metrics.stringWidth("Pontuação: " + applesEaten)) / 2, 
                g.getFont().getSize());
        } else {
            gameOver(g);
        }
    }
    
    public void newApple() {
        // Gerar nova maçã em posição aleatória
        appleX = random.nextInt((int)(BOARD_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int)(BOARD_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
        
        // Verificar se a maçã não está em cima da cobra
        for (int i = 0; i < bodyParts; i++) {
            if (x[i] == appleX && y[i] == appleY) {
                newApple(); // Tentar novamente
                return;
            }
        }
    }
    
    public void move() {
        // Mover o corpo (cada parte segue a anterior)
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        
        // Mover a cabeça baseado na direção
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
        // Verificar se a cabeça da cobra colidiu com a maçã
        if (x[0] == appleX && y[0] == appleY) {
            bodyParts++;
            applesEaten++;
            newApple();
            
            // Aumentar velocidade a cada 5 maçãs
            if (applesEaten % 5 == 0 && DELAY > 50) {
                timer.setDelay(DELAY - 5);
            }
        }
    }
    
    public void checkCollisions() {
        // Verificar colisão com o próprio corpo
        for (int i = bodyParts; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
            }
        }
        
        // Verificar colisão com as bordas
        if (x[0] < 0 || x[0] >= BOARD_WIDTH || y[0] < 0 || y[0] >= BOARD_HEIGHT) {
            running = false;
        }
        
        if (!running) {
            timer.stop();
        }
    }
    
    public void gameOver(Graphics g) {
        // Pontuação final
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Pontuação: " + applesEaten, 
            (BOARD_WIDTH - metrics1.stringWidth("Pontuação: " + applesEaten)) / 2, 
            g.getFont().getSize());
        
        // Game Over
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", 
            (BOARD_WIDTH - metrics2.stringWidth("Game Over")) / 2, 
            BOARD_HEIGHT / 2);
        
        // Instrução para reiniciar
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        FontMetrics metrics3 = getFontMetrics(g.getFont());
        g.drawString("Pressione ESPAÇO para reiniciar", 
            (BOARD_WIDTH - metrics3.stringWidth("Pressione ESPAÇO para reiniciar")) / 2, 
            BOARD_HEIGHT / 2 + 50);
    }
    
    public void restartGame() {
        // Resetar todas as variáveis
        bodyParts = 3;
        applesEaten = 0;
        direction = 'R';
        
        // Limpar posições da cobra
        for (int i = 0; i < bodyParts; i++) {
            x[i] = 0;
            y[i] = 0;
        }
        
        // Reiniciar timer
        timer.setDelay(DELAY);
        
        // Iniciar novo jogo
        startGame();
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
                    
                case KeyEvent.VK_SPACE:
                    if (!running) {
                        restartGame();
                    }
                    break;
                    
                case KeyEvent.VK_ESCAPE:
                    System.exit(0);
                    break;
            }
        }
    }
}