package meusprojetosjava;

public class Arrays {

	public static void main(String[] args) {// Eclipse -> Github @guilhermeNetogit 21/02/2026 14:53:45
		
		double [] temperaturas = new double[12];
		
		temperaturas [0] = 23.9;
		temperaturas [1] = 23.9;
		temperaturas [2] = 23.5;
		temperaturas [3] = 22.9;
		temperaturas [4] = 20.8;
		temperaturas [5] = 20.3;
		temperaturas [6] = 20.6;
		temperaturas [7] = 22.5;
		temperaturas [8]= 24.5;
		temperaturas [9] = 25.1;
		temperaturas [10] = 23.7;
		temperaturas [11] = 23.7;
		
		double soma = 0;
		int mesesPreenchidos = 0;
		
        // Primeiro percorre para contar e somar
        for (int i = 0; i < temperaturas.length; i++) {
            if (temperaturas[i] != 0.0) {
                mesesPreenchidos++;
                soma += temperaturas[i];
            }
        }
        
		System.out.println("Sua lista tem " + temperaturas.length + " meses.");
		System.out.println("Encontrado " + mesesPreenchidos + " preenchidos.\n");
		
		System.out.println("A temperatura no mês 3 é : " + temperaturas[2] + "°C\n" );
		System.out.println("O endereço na memória desta informação é " + temperaturas + "\n");
		
		
		// Agora imprime apenas os meses preenchidos
		for(int i = 0; i < temperaturas.length; i++) {			
			if (temperaturas[i] != 0.0) {  // só mostra se for diferente de zero
				System.out.println("A temperatura no mês " + (i + 1) + " é " + temperaturas[i] + "°C");

			}
		}
			
        // Calcula e imprime média
        if (mesesPreenchidos > 0) {
            double media = soma / mesesPreenchidos;
            System.out.printf("\nTemperatura média: %.2f°C%n", media);
        } else {
            System.out.println("Nenhuma temperatura válida foi informada.");
        }
    }
}
