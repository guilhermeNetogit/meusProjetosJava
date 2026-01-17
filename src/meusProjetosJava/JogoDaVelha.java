package meusProjetosJava;

import java.util.*;

public class JogoDaVelha {// Eclipse -> Github @guilhermeNetogit passou aqui em 17/01/2026 19:52:01
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
        System.out.println("=== JOGO DA VELHA ===");
        System.out.println("Você é o jogador X");
        System.out.println("Computador é o jogador O");
        
        boolean jogarNovamente = true;
        
        while (jogarNovamente) {
            jogarPartida();
            
            System.out.println("\n=== PLACAR ===");
            System.out.println("Você: " + vitoriasHumano + " vitórias");
            System.out.println("Computador: " + vitoriasComputador + " vitórias");
            System.out.println("Empates: " + empates);
            
            System.out.print("\nDeseja jogar novamente? (S/N): ");
            String resposta = scanner.next().toUpperCase();
            
            while (!resposta.equals("S") && !resposta.equals("N")) {
                System.out.print("Resposta inválida! Digite S para Sim ou N para Não: ");
                resposta = scanner.next().toUpperCase();
            }
            
            jogarNovamente = resposta.equals("S");
        }
        
        System.out.println("\nObrigado por jogar!");
        System.out.println("Placar final:");
        System.out.println("Você: " + vitoriasHumano + " vitórias");
        System.out.println("Computador: " + vitoriasComputador + " vitórias");
        System.out.println("Empates: " + empates);
        
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
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = ' ';
            }
        }
    }
    
    private static void exibirTabuleiro() {
        System.out.println("\n  0   1   2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("  ---------");
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
        // 1. Tentar vencer na próxima jogada
        int[] jogadaVencedora = encontrarJogadaVencedora(jogadorComputador);
        if (jogadaVencedora != null) {
            tabuleiro[jogadaVencedora[0]][jogadaVencedora[1]] = jogadorComputador;
            return;
        }
        
        // 2. Bloquear jogada vencedora do humano
        int[] jogadaBloqueio = encontrarJogadaVencedora(jogadorHumano);
        if (jogadaBloqueio != null) {
            tabuleiro[jogadaBloqueio[0]][jogadaBloqueio[1]] = jogadorComputador;
            return;
        }
        
        // 3. Se o centro estiver livre, jogar nele
        if (tabuleiro[1][1] == ' ') {
            tabuleiro[1][1] = jogadorComputador;
            return;
        }
        
        // 4. Jogar em um canto vazio
        int[][] cantos = {{0, 0}, {0, 2}, {2, 0}, {2, 2}};
        List<int[]> cantosVazios = new ArrayList<>();
        
        for (int[] canto : cantos) {
            if (tabuleiro[canto[0]][canto[1]] == ' ') {
                cantosVazios.add(canto);
            }
        }
        
        if (!cantosVazios.isEmpty()) {
            int[] cantoEscolhido = cantosVazios.get(random.nextInt(cantosVazios.size()));
            tabuleiro[cantoEscolhido[0]][cantoEscolhido[1]] = jogadorComputador;
            return;
        }
        
        // 5. Jogar em qualquer posição vazia
        List<int[]> posicoesVazias = new ArrayList<>();
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') {
                    posicoesVazias.add(new int[]{i, j});
                }
            }
        }
        
        if (!posicoesVazias.isEmpty()) {
            int[] posicaoEscolhida = posicoesVazias.get(random.nextInt(posicoesVazias.size()));
            tabuleiro[posicaoEscolhida[0]][posicaoEscolhida[1]] = jogadorComputador;
        }
    }
    
    private static int[] encontrarJogadaVencedora(char jogador) {
        // Verificar linhas
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
                return new int[]{i, posVazia};
            }
        }
        
        // Verificar colunas
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
                return new int[]{posVazia, j};
            }
        }
        
        // Verificar diagonal principal
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
            return new int[]{posVazia, posVazia};
        }
        
        // Verificar diagonal secundária
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
            return new int[]{posVazia, 2 - posVazia};
        }
        
        return null;
    }
    
    private static char verificarVencedor() {
        // Verificar linhas
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] != ' ' && 
                tabuleiro[i][0] == tabuleiro[i][1] && 
                tabuleiro[i][1] == tabuleiro[i][2]) {
                return tabuleiro[i][0];
            }
        }
        
        // Verificar colunas
        for (int j = 0; j < 3; j++) {
            if (tabuleiro[0][j] != ' ' && 
                tabuleiro[0][j] == tabuleiro[1][j] && 
                tabuleiro[1][j] == tabuleiro[2][j]) {
                return tabuleiro[0][j];
            }
        }
        
        // Verificar diagonais
        if (tabuleiro[0][0] != ' ' && 
            tabuleiro[0][0] == tabuleiro[1][1] && 
            tabuleiro[1][1] == tabuleiro[2][2]) {
            return tabuleiro[0][0];
        }
        
        if (tabuleiro[0][2] != ' ' && 
            tabuleiro[0][2] == tabuleiro[1][1] && 
            tabuleiro[1][1] == tabuleiro[2][0]) {
            return tabuleiro[0][2];
        }
        
        return ' '; // Nenhum vencedor ainda
    }
    
    private static boolean tabuleiroCheio() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}