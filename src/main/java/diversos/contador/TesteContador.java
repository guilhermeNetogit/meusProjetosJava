package diversos.contador;

public class TesteContador {
	
	static void imprimirValor() {
		System.out.println(Contador.obterValor());
	}

	public static void main(String[] args) {
		
		imprimirValor();
		
		int i = 0;
		do {
			Contador.incrementar();
			i++;
		} while (i <= 7);
		
		imprimirValor();
		
		Contador.zerar();
		
		imprimirValor();
		
		new Contador();
		new Contador();
		new Contador();
		
		imprimirValor();
		
		new Contador();
		new Contador();
		
		imprimirValor();
		
		new Contador();
		
		imprimirValor();
	}

}
