package meusprojetosjava.SnakeGame;

import javax.swing.*;

public class SnakeGame extends JFrame {// Eclipse -> Github @guilhermeNetogit 12/02/2026 19:34:57
	private static final long serialVersionUID = 1L;
    
    public SnakeGame() {
        initUI();
    }
    
    private void initUI() {
        add(new GamePanel());
        
        setTitle("Jogo da Cobrinha 🐍");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new SnakeGame();
            frame.setVisible(true);
        });
    }
}