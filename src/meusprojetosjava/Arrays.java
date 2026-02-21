package meusprojetosjava;

public class Arrays {

	public static void main(String[] args) {// Eclipse -> Github @guilhermeNetogit 21/02/2026 12:26:33
		
		double [] temperaturas = new double[365];
		
		temperaturas [0] = 23.9;
		temperaturas [1] = 23.9;
		temperaturas [2] = 23.9;
		temperaturas [3] = 23.9;
		temperaturas [4] = 23.9;
		temperaturas [5] = 23.9;
		temperaturas [6] = 23.9;
		temperaturas [7] = 24.0;
		
		double soma = 0;
		int diasPreenchidos = 0;
		
        // Primeiro percorre para contar e somar
        for (int i = 0; i < temperaturas.length; i++) {
            if (temperaturas[i] != 0.0) {
                diasPreenchidos++;
                soma += temperaturas[i];
            }
        }
        
		System.out.println("Sua lista tem " + temperaturas.length + " dias.");
		System.out.println("Mas apenas " + diasPreenchidos + " estão preenchidos.\n");
		
		System.out.println("O valor da temperatura no dia 3 é : " + temperaturas[2] + "°C\n" );
		System.out.println("O endereço na memória desta informação é " + temperaturas + "\n");
		
		
		// Agora imprime apenas os dias preenchidos
		for(int i = 0; i < temperaturas.length; i++) {			
			if (temperaturas[i] != 0.0) {  // só mostra se for diferente de zero
				System.out.println("O valor da temperatura no dia " + (i + 1) + " é " + temperaturas[i] + "°C");

			}
		}
			
        // Calcula e imprime média
        if (diasPreenchidos > 0) {
            double media = soma / diasPreenchidos;
            System.out.printf("\nTemperatura média: %.2f°C%n", media);
        } else {
            System.out.println("Nenhuma temperatura válida foi informada.");
        }
    }
}
