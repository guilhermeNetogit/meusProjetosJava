package diversos.patterns.pascaltriangule;

import java.util.Scanner;

public class PascalTriangule {// Eclipse -> Github @guilhermeNetogit 18/03/2026 15:11:40

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Quantas linhas (máx. 34)? ");
        int linhas = sc.nextInt();
        System.out.println();

        int[][] triangulo = new int[linhas][];

        for (int i = 0; i < linhas; i++) {
            triangulo[i] = new int[i + 1];
            triangulo[i][0] = 1;
            triangulo[i][i] = 1;

            for (int j = 1; j < i; j++) {
                triangulo[i][j] = triangulo[i - 1][j - 1] + triangulo[i - 1][j];
            }
        }

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < linhas - i; j++) {
                System.out.print("  ");
            }
            for (int j = 0; j <= i; j++) {
                System.out.printf("%4d", triangulo[i][j]);
            }
            System.out.println();
        }

        sc.close();
    }
}
// CURIOSIDADE
// Pi codificado no Triângulo de Pasca -> π = 3 + 2/3*(1/4 - 1/20 + 1/56 - 1/120 + 1/220- ...)