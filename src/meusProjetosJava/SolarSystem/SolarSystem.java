package meusProjetosJava.SolarSystem;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class SolarSystem extends JPanel {// Eclipse -> Github @guilhermeNetogit passou aqui em 18/01/2026 17:57:27
	private static final long serialVersionUID = 1L;
    private JPanel infoPanel;
    private List<CelestialBody> bodies = new ArrayList<>();
    private List<Point> stars = new ArrayList<>();
    private double speedMultiplier = 1.0;
    private Random random = new Random();
    private CelestialBody selectedPlanet = null;
    
    // Distâncias reais em milhões de km e velocidades da luz
    private static final double[] DISTANCES_SOL = {
        0,				// Sol
        57.909227,		// Mercúrio
        108.209475,		// Vênus
        149.597871,		// Terra
        227.936637,		// Marte
        778.547200,		// Júpiter
        1433.449370,	// Saturno
        2870.658186,	// Urano
        4498.396441		// Netuno
    };
    
    // Velocidade da luz: 299792.458 km/s
    private static final double LIGHT_SPEED_KM_S = 299792.458;
    
    class CelestialBody {
        String name;
        double x, y;
        double radius;
        Color color;
        double orbitRadius;
        double angle;
        double baseSpeed;
        List<CelestialBody> moons;
        List<Point> trail;
        private static final int MAX_TRAIL_LENGTH = 50;
        
        CelestialBody(String name, double orbitRadius, double radius, Color color, double baseSpeed) {
            this.name = name;
            this.orbitRadius = orbitRadius;
            this.radius = radius;
            this.color = color;
            this.baseSpeed = baseSpeed;
            this.moons = new ArrayList<>();
            this.trail = new ArrayList<>();
        }
        
        void update(double speedMultiplier) {
            // Salvar posição atual para a trilha
            trail.add(new Point((int)x, (int)y));
            if (trail.size() > MAX_TRAIL_LENGTH) {
                trail.remove(0);
            }
            
            angle += baseSpeed * speedMultiplier;
            x = Math.cos(angle) * orbitRadius;
            y = Math.sin(angle) * orbitRadius;
            
            for (CelestialBody moon : moons) {
                moon.update(speedMultiplier);
            }
        }
        
        void draw(Graphics2D g2d, int centerX, int centerY) {
            int screenX = centerX + (int)x;
            int screenY = centerY + (int)y;
            
            // Desenhar trilha (rastro do planeta)
            drawTrail(g2d, centerX, centerY);
            
            // Órbita
            g2d.setColor(new Color(50, 50, 50, 100));
            g2d.drawOval(centerX - (int)orbitRadius, centerY - (int)orbitRadius,
                        (int)orbitRadius * 2, (int)orbitRadius * 2);
            
            // Planeta
            GradientPaint gradient = new GradientPaint(
                screenX - (int)radius, screenY - (int)radius, color.brighter(),
                screenX + (int)radius, screenY + (int)radius, color.darker(),
                true
            );
            g2d.setPaint(gradient);
            g2d.fillOval(screenX - (int)radius, screenY - (int)radius,
                        (int)radius * 2, (int)radius * 2);
            
            // Borda do planeta
            g2d.setColor(color.brighter().brighter());
            g2d.setStroke(new BasicStroke(2));
            g2d.drawOval(screenX - (int)radius, screenY - (int)radius,
                        (int)radius * 2, (int)radius * 2);
            g2d.setStroke(new BasicStroke(1));
            
            // Nome (se não estiver muito pequeno)
            if (radius > 5) {
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Arial", Font.BOLD, 12));
                g2d.drawString(name, screenX + (int)radius + 5, screenY);
            }
            
            // Se for o planeta selecionado, desenhar um destaque
            if (this == selectedPlanet) {
                g2d.setColor(Color.YELLOW);
                g2d.setStroke(new BasicStroke(3));
                g2d.drawOval(screenX - (int)radius - 5, screenY - (int)radius - 5,
                            (int)radius * 2 + 10, (int)radius * 2 + 10);
                g2d.setStroke(new BasicStroke(1));
            }
            
            // Luas
            for (CelestialBody moon : moons) {
                moon.drawRelative(g2d, screenX, screenY);
            }
        }
        
        void drawTrail(Graphics2D g2d, int centerX, int centerY) {
            if (trail.size() > 1) {
                g2d.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 100));
                g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                
                for (int i = 1; i < trail.size(); i++) {
                    Point prev = trail.get(i - 1);
                    Point curr = trail.get(i);
                    g2d.drawLine(centerX + prev.x, centerY + prev.y, 
                                 centerX + curr.x, centerY + curr.y);
                }
                g2d.setStroke(new BasicStroke(1));
            }
        }
        
        void drawRelative(Graphics2D g2d, int parentX, int parentY) {
            int screenX = parentX + (int)x;
            int screenY = parentY + (int)y;
            
            g2d.setColor(color);
            g2d.fillOval(screenX - (int)radius, screenY - (int)radius,
                        (int)radius * 2, (int)radius * 2);
        }
    }
    
    public SolarSystem() {
        // Configurar estrelas de fundo
        initializeStars();
        
        // Configurar layout
        setLayout(new BorderLayout());
        
        // INICIALIZAR SISTEMA SOLAR PRIMEIRO
        initializeSolarSystem();
        
        // Painel principal para o sistema solar
        JPanel solarPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawSolarSystem(g);
            }
        };
        solarPanel.setOpaque(false);
        
        // Painel de botões à esquerda
        JPanel buttonPanel = createButtonPanel();
        
        // Painel de informações à direita (inicialmente invisível)
        infoPanel = createInfoPanel();
        
        // Adicionar componentes
        add(buttonPanel, BorderLayout.WEST);
        add(solarPanel, BorderLayout.CENTER);
        //add(infoPanel, BorderLayout.EAST);
        
        // Configurar teclado
        setupKeyboardControls(solarPanel);
        
        // Timer para animação
        Timer timer = new Timer(50, e -> {
            updateSolarSystem();
            solarPanel.repaint();
            infoPanel.repaint();
        });
        timer.start();
        
        setPreferredSize(new Dimension(1400, 800));
        setBackground(Color.BLACK);
    }
    
    private void toggleInfoPanel(boolean show) {
        if (show) {
            if (infoPanel.getParent() == null) {
                add(infoPanel, BorderLayout.EAST);
            }
        } else {
            remove(infoPanel);
        }
        revalidate();
        repaint();
    }
    
    private void initializeStars() {
        for (int i = 0; i < 300; i++) {
            stars.add(new Point(random.nextInt(1400), random.nextInt(800)));
        }
    }
    
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 5, 5));
        panel.setBackground(new Color(20, 20, 40));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Título
        JLabel title = new JLabel("PLANETAS", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(Color.CYAN);
        panel.add(title);
        
        // Botão para "Nenhum"
        JButton noneButton = createPlanetButton("[X] Nenhum", null);
        panel.add(noneButton);
        
        // Botões para cada planeta
        String[] planetNames = {"[1] Mercúrio", "[2] Vênus", "[3] Terra", "[4] Marte", 
                                "[5] Júpiter", "[6] Saturno", "[7] Urano", "[8] Netuno"};
        
        for (int i = 0; i < planetNames.length; i++) {
            final int index = i + 1; // +1 porque o Sol é index 0
            JButton button = createPlanetButton(planetNames[i], bodies.get(index));
            panel.add(button);
        }
        
        // Painel de controles de velocidade
        JPanel speedPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        speedPanel.setBackground(new Color(20, 20, 40));
        
        JButton speedUp = createControlButton("+", e -> {
            speedMultiplier *= 1.5;
            if (speedMultiplier > 20) speedMultiplier = 20;
        });
        
        JButton speedDown = createControlButton("-", e -> {
            speedMultiplier /= 1.5;
            if (speedMultiplier < 0.01) speedMultiplier = 0.01;
        });
        
        JButton pauseButton = createControlButton("Pausa", e -> speedMultiplier = 0);
        JButton resetButton = createControlButton("Reset", e -> speedMultiplier = 1.0);
        JButton doubleButton = createControlButton("2x", e -> speedMultiplier = 2.0);
        JButton halfButton = createControlButton("0.5x", e -> speedMultiplier = 0.5);
        
        speedPanel.add(speedUp);
        speedPanel.add(speedDown);
        speedPanel.add(pauseButton);
        speedPanel.add(resetButton);
        speedPanel.add(doubleButton);
        speedPanel.add(halfButton);
        
        panel.add(new JSeparator());
        panel.add(new JLabel("Velocidade:", SwingConstants.CENTER) {{
            setForeground(Color.YELLOW);
        }});
        panel.add(speedPanel);
        
        return panel;
    }
    
    private JButton createPlanetButton(String text, CelestialBody planet) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(40, 40, 80));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        button.addActionListener(e -> {
            selectedPlanet = planet;
            // Mostrar painel apenas se um planeta for selecionado
            toggleInfoPanel(planet != null);
            repaint();
        });
        
        // Efeito hover
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(60, 60, 120));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(40, 40, 80));
            }
        });
        
        return button;
    }
    
    private JButton createControlButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 12));
        button.setBackground(new Color(60, 60, 100));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.addActionListener(action);
        return button;
    }
    
    private JPanel createInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(20, 20, 40));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Título
        JLabel title = new JLabel("INFORMAÇÕES", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(Color.GREEN);
        panel.add(title, BorderLayout.NORTH);
        
        // Painel de informações dinâmicas
        JPanel infoContent = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawPlanetInfo(g);
            }
        };
        infoContent.setOpaque(false);
        infoContent.setPreferredSize(new Dimension(250, 0));
        
        panel.add(infoContent, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void drawPlanetInfo(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int y = 40;
        
        if (selectedPlanet == null) {
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.ITALIC, 14));
            g2d.drawString("Selecione um planeta", 20, y);
            g2d.drawString("para ver informações", 20, y + 25);
            return;
        }
        
        // Encontrar índice do planeta
        int planetIndex = -1;
        for (int i = 0; i < bodies.size(); i++) {
            if (bodies.get(i) == selectedPlanet) {
                planetIndex = i;
                break;
            }
        }
        
        if (planetIndex > 0) { // Não é o Sol
            double distance = DISTANCES_SOL[planetIndex];
            double lightTime = (distance * 1000000) / LIGHT_SPEED_KM_S; // em segundos
            
            g2d.setColor(selectedPlanet.color);
            g2d.setFont(new Font("Arial", Font.BOLD, 20));
            g2d.drawString(selectedPlanet.name, 20, y);
            
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.PLAIN, 14));
            y += 40;
            
            // Distância até o Sol
            g2d.drawString("🌞 Distância do Sol:", 20, y);
            g2d.setColor(Color.YELLOW);
            g2d.drawString(String.format("  %.1f milhões de km", distance), 20, y + 20);
            g2d.drawString(String.format("  %.2f UA", distance / 149.6), 20, y + 40);
            y += 70;
            
            // Tempo da luz
            g2d.setColor(Color.WHITE);
            g2d.drawString("⚡ Tempo da luz solar:", 20, y);
            g2d.setColor(new Color(255, 255, 150));
            
            if (lightTime < 60) {
                g2d.drawString(String.format("  %.2f segundos", lightTime), 20, y + 20);
            } else if (lightTime < 3600) {
                g2d.drawString(String.format("  %.2f minutos", lightTime / 60), 20, y + 20);
            } else {
                g2d.drawString(String.format("  %.2f horas", lightTime / 3600), 20, y + 20);
            }
            
            y += 60;
            
            // Velocidade orbital
            double circumference = 2 * Math.PI * distance;
            double orbitalPeriodDays = getOrbitalPeriod(planetIndex);
            double orbitalSpeed = circumference / (orbitalPeriodDays * 24 * 3600);
            
            g2d.setColor(Color.WHITE);
            g2d.drawString("🔄 Velocidade orbital:", 20, y);
            g2d.setColor(Color.CYAN);
            g2d.drawString(String.format("  %.1f km/s", orbitalSpeed), 20, y + 20);
            y += 50;
            
            // Período orbital
            g2d.setColor(Color.WHITE);
            g2d.drawString("📅 Período orbital:", 20, y);
            g2d.setColor(Color.PINK);
            
            if (orbitalPeriodDays < 365) {
                g2d.drawString(String.format("  %.1f dias terrestres", orbitalPeriodDays), 20, y + 20);
            } else {
                g2d.drawString(String.format("  %.1f anos terrestres", orbitalPeriodDays / 365), 20, y + 20);
            }
            y += 50;
            
            // Luas
            g2d.setColor(Color.WHITE);
            g2d.drawString("🌙 Luas naturais:", 20, y);
            g2d.setColor(Color.LIGHT_GRAY);
            if (selectedPlanet.moons.isEmpty()) {
                g2d.drawString("  Nenhuma", 20, y + 20);
            } else {
                g2d.drawString("  " + selectedPlanet.moons.size() + " luas", 20, y + 20);
                int moonY = y + 40;
                for (CelestialBody moon : selectedPlanet.moons) {
                    if (moonY < getHeight() - 30) {
                        g2d.drawString("  • " + moon.name, 30, moonY);
                        moonY += 20;
                    }
                }
            }
        }
    }
    
    private double getOrbitalPeriod(int planetIndex) {
        // Períodos orbitais aproximados em dias terrestres
        double[] periods = {0, 88, 225, 365, 687, 4333, 10759, 30687, 60190};
        return periods[planetIndex];
    }
    
    private void initializeSolarSystem() {
        // Sol (centro, não orbita)
        CelestialBody sun = new CelestialBody("Sol", 0, 35, Color.YELLOW, 0);
        
        // Planetas
        CelestialBody mercury = new CelestialBody("Mercúrio", 100, 7, new Color(169, 169, 169), 0.05);
        CelestialBody venus = new CelestialBody("Vênus", 150, 9, new Color(255, 165, 0), 0.03);
        CelestialBody earth = new CelestialBody("Terra", 200, 10, new Color(30, 144, 255), 0.02);
        CelestialBody mars = new CelestialBody("Marte", 260, 8, new Color(255, 69, 0), 0.015);
        CelestialBody jupiter = new CelestialBody("Júpiter", 350, 25, new Color(255, 215, 0), 0.008);
        CelestialBody saturn = new CelestialBody("Saturno", 450, 22, new Color(210, 180, 140), 0.006);
        CelestialBody uranus = new CelestialBody("Urano", 550, 18, new Color(173, 216, 230), 0.004);
        CelestialBody neptune = new CelestialBody("Netuno", 650, 17, new Color(65, 105, 225), 0.003);
        
        // Luas
        CelestialBody lua = new CelestialBody("Lua", 20, 3, Color.LIGHT_GRAY, 0.1);
        earth.moons.add(lua);
        
        // Luas de Marte
        CelestialBody phobos = new CelestialBody("Fobos", 15, 2, Color.GRAY, 0.15);
        CelestialBody deimos = new CelestialBody("Deimos", 25, 1, Color.DARK_GRAY, 0.12);
        mars.moons.add(phobos);
        mars.moons.add(deimos);
        
        // Luas de Júpiter (principais)
        CelestialBody io = new CelestialBody("Io", 40, 5, Color.YELLOW, 0.2);
        CelestialBody europa = new CelestialBody("Europa", 55, 4, Color.WHITE, 0.18);
        CelestialBody ganymede = new CelestialBody("Ganimedes", 70, 6, new Color(169, 169, 169), 0.16);
        CelestialBody callisto = new CelestialBody("Calisto", 85, 5, new Color(139, 69, 19), 0.14);
        jupiter.moons.add(io);
        jupiter.moons.add(europa);
        jupiter.moons.add(ganymede);
        jupiter.moons.add(callisto);
        
        bodies.add(sun);
        bodies.add(mercury);
        bodies.add(venus);
        bodies.add(earth);
        bodies.add(mars);
        bodies.add(jupiter);
        bodies.add(saturn);
        bodies.add(uranus);
        bodies.add(neptune);
    }
    
    private void setupKeyboardControls(JPanel panel) {
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP: speedMultiplier *= 1.5; break;
                    case KeyEvent.VK_DOWN: speedMultiplier /= 1.5; break;
                    case KeyEvent.VK_RIGHT: speedMultiplier += 0.5; break;
                    case KeyEvent.VK_LEFT: speedMultiplier -= 0.5; break;
                    case KeyEvent.VK_SPACE: speedMultiplier = speedMultiplier == 0 ? 1.0 : 0; break;
                    case KeyEvent.VK_R: speedMultiplier = 1.0; break;
                }
                if (speedMultiplier > 20) speedMultiplier = 20;
                if (speedMultiplier < 0.01) speedMultiplier = 0.01;
                repaint();
            }
        });
    }
    
    private void updateSolarSystem() {
        for (int i = 1; i < bodies.size(); i++) { // Começa em 1 para pular o Sol
            bodies.get(i).update(speedMultiplier);
        }
    }
    
    private void drawSolarSystem(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Fundo gradiente (espaço)
        GradientPaint spaceGradient = new GradientPaint(
            0, 0, new Color(0, 0, 20),
            getWidth(), getHeight(), new Color(0, 0, 40),
            true
        );
        g2d.setPaint(spaceGradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        
        // Desenhar estrelas
        drawStars(g2d);
        
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        
        // Desenhar anéis de Saturno primeiro (para ficar atrás)
        drawSaturnRings(g2d, centerX, centerY);
        
        // Desenhar corpos celestes
        for (CelestialBody body : bodies) {
            body.draw(g2d, centerX, centerY);
        }
        
        // Desenhar informações gerais
        drawGeneralInfo(g2d);
    }
    
    private void drawStars(Graphics2D g2d) {
        for (Point star : stars) {
            // Estrelas piscando aleatoriamente
            if (random.nextInt(100) < 70) {
                int size = 1 + random.nextInt(3);
                int alpha = 150 + random.nextInt(105);
                g2d.setColor(new Color(255, 255, 255, alpha));
                g2d.fillOval(star.x, star.y, size, size);
            }
        }
    }
    
    private void drawSaturnRings(Graphics2D g2d, int centerX, int centerY) {
        for (CelestialBody body : bodies) {
            if (body.name.equals("Saturno")) {
                int saturnX = centerX + (int)body.x;
                int saturnY = centerY + (int)body.y;
                
                // Anéis com gradiente
                for (int i = 0; i < 3; i++) {
                    int ringWidth = 25 + i * 15;
                    GradientPaint ringGradient = new GradientPaint(
                        saturnX - ringWidth, saturnY, new Color(210, 180, 140, 50),
                        saturnX + ringWidth, saturnY, new Color(210, 180, 140, 150),
                        false
                    );
                    g2d.setPaint(ringGradient);
                    g2d.setStroke(new BasicStroke(8 - i * 2));
                    g2d.drawOval(saturnX - ringWidth, saturnY - 10 + i * 3, 
                                 ringWidth * 2, 20 - i * 6);
                }
                g2d.setStroke(new BasicStroke(1));
                break;
            }
        }
    }
    
    private void drawGeneralInfo(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("SISTEMA SOLAR 3D", getWidth()/2 - 70, 30);
        
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        g2d.drawString("Velocidade: " + String.format("%.2f", speedMultiplier) + "x", getWidth() - 150, 30);
        g2d.drawString("Planetas: 8 | Luas: 10", getWidth() - 150, 50);
        
        if (speedMultiplier == 0) {
            g2d.setColor(Color.RED);
            g2d.drawString("PAUSADO", getWidth()/2 - 30, getHeight() - 20);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("🌌 Sistema Solar Avançado");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            SolarSystem solarSystem = new SolarSystem();
            frame.add(solarSystem);
            
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
            // Garantir que o painel receba foco para os controles de teclado
            solarSystem.requestFocusInWindow();
        });
    }
}