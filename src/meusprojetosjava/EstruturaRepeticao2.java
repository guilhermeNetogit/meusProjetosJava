package meusprojetosjava;

public class EstruturaRepeticao2 {// Eclipse -> Github @guilhermeNetogit 18/02/2026 21:04:13

	public static void main(String[] args) {
		// Laço for: inicialização ; condição ; incremento
		for (int cc = 0;	// cc recebe 0
				 cc < 5;	// executa enquanto cc for menor ou igual a 5 
				 cc++) {	// incrementa cc a cada loop
			System.out.println("Contador = " + cc);			
		}
		
		for (int cc = 5;	// cc recebe 5
				 cc >= 0;	// executa enquanto cc for maior ou igual a 0 
				 cc--) {	// decrementa cc a cada loop
			System.out.println("Contador = " + cc);
		} 
		
		System.out.println("");
		
		for (int cc1 = 0, cc2 = 10;	
				 cc1 < cc2;
				 cc1++, cc2--) {	
			System.out.println("Cont. 1 = " + cc1 + "; Cont. 2 = " + cc2);		
		} 
		
		System.out.println("");
		
		int soma = 0;
		for (int cc = 1; cc < 10; soma += cc++) { 
			System.out.println(soma);		
		} 	
			
		int soma2 = 0;
		for (int cc = 1; cc < 10; soma2 += cc++); { 
			System.out.println("A soma dos números acima é: " + soma2);		
		}
	}
}