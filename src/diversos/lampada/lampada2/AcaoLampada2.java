package diversos.lampada.lampada2;

import java.util.Scanner;

public class AcaoLampada2 {// VSCode -> Github @guilhermeNetogit 04/03/2026 19:58:36
    public static void main(String[] args){
        
    	Scanner scanner = new Scanner(System.in);
        Lampada2 lampada = new Lampada2();
        
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
