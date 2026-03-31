package diversos.calculadoras.fibonacci;

public class TesteFibonacci {// Eclipse -> Github @guilhermeNetogit 07/03/2026 21:43:29

	/*public static void main(String[] args) {
		
		for (int i=0; i<=12; i++) {
			System.out.print(Fibonacci.fibonacci(i) + " ");
		}
		System.out.println();
		System.out.println(Fibonacci.fibonacci(12));

	}*/
	
	public static void main(String[] args) {

		Fibonacci.memoria[0]= 0;
		Fibonacci.memoria[1]= 1;
		
		int num = 12;
		
		for (int i=0; i<=num; i++){
			System.out.println("Fibonacci de " + i + ": " + Fibonacci.fibonacci(i));
		}
	}

}
