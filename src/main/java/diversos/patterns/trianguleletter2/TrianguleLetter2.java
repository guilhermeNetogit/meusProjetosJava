package diversos.patterns.trianguleletter2;

public class TrianguleLetter2 {// Eclipse -> Github @guilhermeNetogit 18/03/2026 15:03:17
    public static void main(String[] args) {
    	int letterA = 65; // 65 é o código ASCII da letra 'A'
    	int letterZ = 90; // 90 é o código ASCII da letra 'Z'
        int totalRows = 13; // Quantidade de linhas para ir de A até M e Z até N
        
        for (int i = 0; i < totalRows; i++) {
            // Parte da Esquerda (Crescente: A até M)
            char leftChar = (char) (letterA + i);
            for (int j = 0; j <= i; j++) {
                System.out.print(leftChar + " ");
            }

            // Espaçamento central dinâmico
            // Ajuste o multiplicador (ex: 4 ou 3) conforme o tamanho do seu console
            int spaces = (totalRows - i - 1) * 4; 
            for (int s = 0; s < spaces; s++) {
                System.out.print(" ");
            }

            // Parte da Direita (Decrescente: Z até N)
            char rightChar = (char) (letterZ - i);
            for (int j = 0; j <= i; j++) {
                System.out.print(rightChar + " ");
            }

            System.out.println();
        }
    }
}