# 🐍 Jogo da Cobrinha (Snake) em Java

![Java](https://img.shields.io/badge/Java-17%2B-007396?logo=java&logoColor=white)
![Swing](https://img.shields.io/badge/GUI-Swing-4D4D4D?logo=swing&logoColor=white)
![Graphics2D](https://img.shields.io/badge/Render-Graphics2D-6A0DAD?logo=opengl&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-blue)
![Status](https://img.shields.io/badge/Status-Complete-success)

<p align="center"> <img src="https://media.giphy.com/media/26gR2qGRnxxXAvhBu/giphy.gif" alt="Snake Game Demo" width="400"/> </p>

## ✨ Sobre o Projeto
Uma implementação clássica e totalmente funcional do Jogo da Cobrinha (Snake) desenvolvida em Java com Swing. O jogo apresenta física suave, controles responsivos, sistema de pontuação e efeitos visuais detalhados como olhos direcionais e escamas na cobra.

## 🚀 Funcionalidades
- **Cobra animada** com olhos que seguem a direção do movimento;
- **Sistema de pontuação** em tempo real;
- **Velocidade progressiva**, o jogo fica mais rápido a cada 5 maçãs
- **Maçãs vermelhas** geradas aleatoriamente
- **Detecção de colisão** com paredes e com o próprio corpo
- **Reinício rápido** com a barra de espaço
- **Controles alternativos** (Setas ou WASD)
- **Grid opcional** para visualização das unidades

## 🎮 Como Executar

### Pré-requisitos
- Java JDK 11 ou superior
- Git (opcional)

### Compilação e Execução
```
bash
# 1. Clone o repositório
git clone https://github.com/seu-usuario/snake-game-java.git
cd snake-game-java

# 2. Compile o programa
javac SnakeGame.java GamePanel.java

# 3. Execute a aplicação
java SnakeGame
```
### Executando no VS Code / Eclipse / IntelliJ
```
text
# 1. Importe o projeto como "Java Project"
# 2. Compile as classes SnakeGame.java e GamePanel.java
# 3. Execute a classe SnakeGame.java
# 4. Pressione qualquer seta para começar!
```
## 🎮 Como Usar
Controles do Teclado:
Tecla	Ação
← → ↑ ↓	Move a cobra
A / W / S / D	Movimento alternativo
Espaço	Reinicia o jogo
ESC	Fecha o jogo

Regras:
🍎 Coma a maçã vermelha para crescer e ganhar 1 ponto

💥 Não bata na parede ou no próprio corpo

⚡ A cada 5 maçãs, a velocidade aumenta

## 🖥️ Interface Gráfica
```text
┌─────────────────────────────────────────────────┐
│  Pontuação: 42                                  │
│                                                 │
│                    🟩                          │
│                    🟩🍎                        │
│                    🟩🟩🟩                     │
│                                                 │
│  ┌─────────────────────────────────────────┐    │
│  │                                         │    │
│  │         🐍 COMENDO 🍎                  │    │
│  │                                         │    │
│  └─────────────────────────────────────────┘    │
└─────────────────────────────────────────────────┘
```
## 🏗️ Estrutura do Código
```text
SnakeGame/
├── SnakeGame.java          # Classe principal (JFrame)
├── GamePanel.java          # Lógica do jogo e renderização
│   ├── initUI()           # Configuração da janela
│   ├── startGame()        # Inicializa o timer
│   ├── draw()             # Renderização gráfica
│   ├── move()             # Movimentação da cobra
│   ├── checkApple()       # Colisão com maçã
│   ├── checkCollisions()  # Colisões com paredes/corpo
│   ├── gameOver()         # Tela de fim de jogo
│   └── MyKeyAdapter()     # Controles do teclado
└── README.md              # Documentação
```
## 🔧 Personalização
Você pode facilmente personalizar o jogo alterando algumas constantes no GamePanel.java:

### Modificando Velocidade e Tamanho
```java
java
// Altere a velocidade base (milissegundos)
private static final int DELAY = 100;  // Quanto menor, mais rápido

// Altere o tamanho da cobra e do grid
private static final int UNIT_SIZE = 25;  // Tamanho de cada quadrado
private static final int BOARD_WIDTH = 600;  // Largura do tabuleiro
private static final int BOARD_HEIGHT = 600; // Altura do tabuleiro

java
// Alterando Cores
// Cores da cobra
private Color snakeHeadColor = new Color(0, 150, 0);  // Cabeça verde
private Color snakeBodyColor = new Color(45, 180, 0); // Corpo verde claro

// Cor da maçã
private Color appleColor = Color.RED;  // Pode ser Color.YELLOW, Color.BLUE, etc.

// Cor do fundo
private Color backgroundColor = Color.BLACK;  // Ou Color.DARK_GRAY
Modos Alternativos (Descomente e teste!)

java
// 1. MODO PAREDE INVISÍVEL - Atravessa as bordas
if (x[0] < 0) x[0] = BOARD_WIDTH - UNIT_SIZE;
if (x[0] >= BOARD_WIDTH) x[0] = 0;
if (y[0] < 0) y[0] = BOARD_HEIGHT - UNIT_SIZE;
if (y[0] >= BOARD_HEIGHT) y[0] = 0;

// 2. MAÇÃ ESPECIAL - Maçã dourada a cada 10 pontos
if (applesEaten % 10 == 0 && applesEaten > 0) {
    g.setColor(Color.YELLOW);  // Vale 2 pontos
}

// 3. COBRA ARCO-ÍRIS - Gradiente de cores
for (int i = 0; i < bodyParts; i++) {
    float hue = (float)i / bodyParts;
    g.setColor(Color.getHSBColor(hue, 1.0f, 0.8f));
}
```
## 🚀 Recursos Futuros (To-Do)
- [ ] Modo multiplayer (2 cobras no mesmo tabuleiro)
- [ ] Paredes internas (obstáculos fixos)
- [ ] Maçãs especiais que valem mais pontos
- [ ] Modo infinito (atravessa paredes)
- [ ] High scores persistentes com arquivo
- [ ] Efeitos sonoros (comer, colidir, vencer)
- [ ] Animações de explosão ao morrer
- [ ] Temas de cores (claro/escuro/neon)

## 🤝 Contribuindo
### Contribuições são muito bem-vindas! Para contribuir:

Fork o projeto

1. Crie uma branch para sua feature (git checkout -b feature/MinhaFeature)

2. Commit suas mudanças (git commit -m 'Adiciona MinhaFeature')

3. Push para a branch (git push origin feature/MinhaFeature)

4. Abra um Pull Request

## 📝 Licença
Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

## 👨‍💻 Autor
Guilherme Neto

![GitHUb](https://img.shields.io/badge/GitHub-100000?logo=github&logoColor=white&link=https%3A%2F%2Fgithub.com%2FguilhermeNetogit)
![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?logo=linkedin&logoColor=white)

## 📞 Suporte
Encontrou um bug ou tem uma sugestão?
📌 Abra uma issue no GitHub.

## <p align="center"> ⭐ Se este projeto te ajudou ou inspirou, dá uma estrelinha no repositório! ⭐ </p><p align="center"> <sub>Desenvolvido com ☕ e 🐍 durante uma tarde de código</sub> </p>
