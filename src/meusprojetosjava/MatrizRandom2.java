package meusprojetosjava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatrizRandom2 {// Eclipse -> Github @guilhermeNetogit 22/02/2026 12:04:42

    public static void main(String[] args) {

        int[][] numerosAleatorios = new int[10][10];
        
        // Criando lista de 0 a 99
        List<Integer> lista = new ArrayList<>();
        
        for (int i = 1; i <= 100; i++) {
            lista.add(i);
        }
        
        // Embaralha a lista
        Collections.shuffle(lista);
        
        int indice = 0;
        for (int i = 0; i < numerosAleatorios.length; i++) {
            for (int j = 0; j < numerosAleatorios[i].length; j++) {
                numerosAleatorios[i][j] = lista.get(indice++);
            }
        }
        
        for (int i=0; i<numerosAleatorios.length; i++){
            for (int j=0; j<numerosAleatorios[i].length; j++){
                System.out.print(numerosAleatorios[i][j] + " ");
            }
            System.out.println();
        }
        
        int maiorL5 = 0;
        int menorL5 = 101;
        int linha5 = 5;
        for (int i=0; i<numerosAleatorios[linha5].length; i++){
            if (numerosAleatorios[linha5][i] > maiorL5){
                maiorL5 = numerosAleatorios[linha5][i];
            }
            if (numerosAleatorios[linha5][i] < menorL5){
                menorL5 = numerosAleatorios[linha5][i];
            }
        }
        System.out.println();
        System.out.println("Maior valor da linha 5 = " + maiorL5);
        System.out.println("Menor valor da linha 5 = " + menorL5);
        
        int maiorC7 = 0;
        int menorC7 = 101;
        int col7 = 7;
        for (int i=0; i<numerosAleatorios.length; i++){
            if (numerosAleatorios[i][col7] > maiorC7){
                maiorC7 = numerosAleatorios[i][col7]; 
            }
            if (numerosAleatorios[i][col7] < menorC7){
                menorC7 = numerosAleatorios[i][col7]; 
            }
        }
        System.out.println();
        System.out.println("Maior valor da coluna 7 = " + maiorC7);
        System.out.println("Menor valor da coluna 7 = " + menorC7);
    }

}