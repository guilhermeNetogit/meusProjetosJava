package meusprojetosjava.lampada.lampada1;

import java.util.Scanner;

public class AcaoLampada {// Eclipse -> Github @guilhermeNetogit 24/02/2026 16:54:29
    public static void main(String[] args){
        
    	Scanner scanner = new Scanner(System.in);
        Lampada lampada = new Lampada();
        
        // Liga a lâmpada inicialmente
        lampada.ligar();
        
        // Mostra o estado inicial
        lampada.mostrarEstado();
        
        lampada.desligar();
        
        lampada.mostrarEstado();
        
        lampada.mudarEstado();
        
        lampada.mostrarEstado();
        
        while (true) {
            System.out.print("\nDeseja alterar o estado da lâmpada? (s/n): ");
            String resposta = scanner.nextLine();
            
            if (resposta.equalsIgnoreCase("s")) {

                System.out.println();
            	/*lampada.mudarEstado();
                lampada.mostrarEstado();
                lampada.mudarEstado();
                lampada.mostrarEstado();
                lampada.mudarEstado();
                lampada.mostrarEstado();*/
                // Muda 3 vezes seguidas
                for (int i = 0; i < 3; i++) {
                    lampada.mudarEstado();
                    lampada.mostrarEstado();
                }

            } 
            else if (resposta.equalsIgnoreCase("n")) {
                System.out.println("\nPrograma finalizado.");
                break;
            } 
            else {
                System.out.println("Resposta inválida. Digite 's' ou 'n'.");
            }
        }
        
        scanner.close();
    }
}
