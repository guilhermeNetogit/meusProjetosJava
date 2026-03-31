package diversos.calculadoras.fibonacci;

public class Fibonacci {// Eclipse -> Github @guilhermeNetogit 07/03/2026 21:43:38
	
	final static int Tam_Memoria = 50;
	static long[] memoria = new long[Tam_Memoria];
	
	public static long fibonacci(int num) {
		
		if (num < 2) {
			return memoria[num];
		}
		
		// verifica se o número já foi calculado e salvo na memória
		// caso contrário, faz o cálcula e salva
		
		if (memoria[num]  == 0){
			memoria[num] = fibonacci(num-1) + fibonacci(num-2);
		}

		return memoria[num];
	}
	
		/*public static void main(String[] args) {

			memoria[0]= 0;
			memoria[1]= 1;
			
			for (int i=1; i<Tam_Memoria; i++){
				System.out.println("Fibonacci de " + i + ": " + fibonacci(i));
			}
		
	}*/

}
