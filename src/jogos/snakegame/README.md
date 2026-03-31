# 🐍 Jogo da Cobrinha (Snake) em Java

![Java](https://img.shields.io/badge/Java-17%2B-007396?logo=java&logoColor=white)
![Swing](https://img.shields.io/badge/GUI-Swing-4D4D4D?logo=swing&logoColor=white)
![Graphics2D](https://img.shields.io/badge/Render-Graphics2D-6A0DAD?logo=opengl&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-blue)
![Status](https://img.shields.io/badge/Status-Complete-success)

<p align="center"> <img src="https://media.giphy.com/media/26gR2qGRnxxXAvhBu/giphy.gif" alt="Snake Game Demo" width="400"/> </p>

## ✨ Sobre o Projeto
Uma implementação clássica e totalmente funcional do Jogo da Cobrinha (Snake) desenvolvida em Java com Swing. O jogo apresenta física suave, controles responsivos, sistema de pontuação e efeitos visuais detalhados como olhos direcionais e escamas na cobra, além de um completo sistema de ranking com recordes pessoais!

## 🚀 Funcionalidades
- **Cobra animada** com olhos que seguem a direção do movimento;
- **Sistema de pontuação** em tempo real;
- **Velocidade progressiva**, o jogo fica mais rápido a cada 5 maçãs;
- **Maçãs vermelhas** geradas aleatoriamente;
- **Detecção de colisão** com paredes e com o próprio corpo;
- **Menu principal interativo** com entrada do nome do jogador;
- **Sistema de Ranking TOP 10** com persistência em arquivo;
- **Recorde pessoal** - cada jogador aparece apenas uma vez no ranking com sua melhor pontuação;
- **Tela de ranking** com cores especiais (ouro, prata, bronze);
- **Opção de resetar o ranking** com confirmação em duas etapas;
- **Controles alternativos** (Setas ou WASD);
- **Tela de Game Over** com feedback sobre recordes;
- **Grid opcional** para visualização das unidades;
- **Tela em (800x800)** com textos grandes e melhor legibilidade.

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
javac SnakeGame.java GamePanel.java ScoreManager.java

# 3. Execute a aplicação
java SnakeGame
```
### Executando no VS Code / Eclipse / IntelliJ
```
text
# 1. Importe o projeto como "Java Project"
# 2. Compile as classes SnakeGame.java, GamePanel.java e ScoreManager.java
# 3. Execute a classe SnakeGame.java
# 4. Digite seu nome e pressione ENTER para começar!
```
## 🎮 Como Usar
Menu Principal
| **Tecla**      | **Ação**                              |
|:---------------|:--------------------------------------|
| Letras/Números | Digitar nome do jogador               |
| ENTER          | Começar o jogo (após digitar o nome)  |
| R              | Ver o ranking                         |
| ESC            | Sair do jogo                          |

Controles do Jogo
| **Tecla**      | **Ação**                              |
|:---------------|:--------------------------------------|
| ← → ↑ ↓        | Move a cobra                          |
| A / W / S / D  | Movimento alternativo                 |
| ESC            | Voltar ao menu principal              |

Tela de Ranking
| **Tecla**      | **Ação**                              |
|:---------------|:--------------------------------------|
| R              | Resetar o ranking (com confirmação)   |
| M              | Voltar ao menu principal              |
| ENTER          | Confirmar reset do ranking            |
| ESC            | Cancelar reset do ranking             |

### Regras:
🍎 Coma a maçã vermelha para crescer e ganhar 1 ponto

💥 Não bata na parede ou no próprio corpo

⚡ A cada 5 maçãs, a velocidade aumenta

🏆 Apenas sua melhor pontuação é registrada no ranking

📊 Para entrar no TOP 10, você precisa superar a 10ª colocação

## 🖥️ Interface Gráfica
Menu Principal

```text
┌─────────────────────────────────────────────────┐
│                                                 │
│            JOGO DA COBRINHA                     │
│                                                 │
│    Use as setas ou WASD para mover              │
│    Coma as maçãs para crescer e ganhar pontos   │
│    Não bata nas paredes ou no próprio corpo     │
│                                                 │
│         Digite seu nome: Neto_                  │
│         Pressione ENTER para começar            │
│                                                 │
│    Pressione R para ver o Ranking               │
│    Pressione ESC para sair                      │
└─────────────────────────────────────────────────┘
```
Tela de Ranking

```text
┌─────────────────────────────────────────────────┐
│                                                 │
│              TOP 10 PONTUAÇÕES                  │
│                                                 │
│  Pos.  Jogador     Pontos  Data                 │
│  ─────────────────────────────────────────────  │
│  1°    Neto        42      19/02/2026 20:38     │
│  2°    Ana         35      19/02/2026 19:22     │
│  3°    João        28      19/02/2026 18:15     │
│  4°    Maria       22      19/02/2026 17:30     │
│                                                 │
│  Total de jogadores no ranking: 4               │
│                                                 │
│  Pressione R para resetar o ranking             │
│  Pressione M para voltar ao menu                │
└─────────────────────────────────────────────────┘
```
Durante o Jogo

```text
┌─────────────────────────────────────────────────┐
│  Pontuação: 42              Mínimo Top 10: 35   │
│                                                 │
│                    🟩                          │
│                    🟩🍎                        │
│                    🟩🟩🟩                     │
│                                                 │
│              🐍 COMENDO 🍎                     │
│                                                 │
└─────────────────────────────────────────────────┘
```
Game Over

```text
┌─────────────────────────────────────────────────┐
│                                                 │
│              Pontuação: 42                      │
│                                                 │
│         🏆 NOVO RECORDE PESSOAL! 🏆            │
│         Recorde anterior: 35                    │
│                                                 │
│                 GAME OVER                       │
│                                                 │
│    Pressione M para voltar ao menu              │
│    Pressione R para ver o ranking               │
└─────────────────────────────────────────────────┘
```
## 🏗️ Estrutura do Código
```text
SnakeGame/
├── SnakeGame.java         # Classe principal (JFrame)
├── GamePanel.java         # Lógica do jogo e renderização
│   ├── initUI()           # Configuração da janela
│   ├── startGame()        # Inicializa o timer
│   ├── draw()             # Renderização gráfica
│   ├── drawMenu()         # Menu principal
│   ├── drawRanking()      # Tela de ranking
│   ├── gameOver()         # Tela de fim de jogo
│   ├── move()             # Movimentação da cobra
│   ├── checkApple()       # Colisão com maçã
│   ├── checkCollisions()  # Colisões com paredes/corpo
│   └── MyKeyAdapter()     # Controles do teclado
├── ScoreManager.java      # Gerenciamento do ranking
│   ├── addScore()         # Adiciona/atualiza pontuação
│   ├── resetRanking()     # Reseta o ranking
│   ├── isNewRecord()      # Verifica se é novo recorde
│   └── Score (inner class)# Representa uma pontuação
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
private static final int UNIT_SIZE = 25;     // Tamanho de cada quadrado
private static final int BOARD_WIDTH = 800;  // Largura do tabuleiro
private static final int BOARD_HEIGHT = 800; // Altura do tabuleiro

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
- [x] High scores persistentes com arquivo
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

[![GitHUb](https://img.shields.io/badge/GitHub-100000?logo=github&logoColor=white)](https://www.github.com/guilhermeNetogit/)
![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?logo=linkedin&logoColor=white)

## 📞 Suporte
Encontrou um bug ou tem uma sugestão?
📌 Abra uma issue no GitHub.

## <p align="center"> ⭐ Se este projeto te ajudou ou inspirou, dá uma estrelinha no repositório! ⭐ </p><p align="center"> <sub>Desenvolvido com ☕ e 🐍 durante uma tarde de código</sub> </p>
