package meusprojetosjava.calculadora.calculadora2;

public class TesteCalculadora2 {
	
	static int resultado1;
	static int resultado3;
	static int resultado4;
	static double resultado2;
	
    public static void main(String[] args) {
    	
        //Calculadora2 calc = new Calculadora2(); 

        //Acalc.soma(5,3);
        //Acalc.soma(5.0,3.0);
        //Acalc.soma(5,3,2);
        int[] numeros = {1,2,3,4,5,6,7,8,9,10};
        //calc.soma(numeros);
    	
    	resultado1 = Calculadora2.soma(15,25);
    	resultado2 = Calculadora2.soma(16.7,3.3);
    	resultado3 = Calculadora2.soma(22, 33, 45);
    	resultado4 = Calculadora2.soma(numeros);
    	//Calculadora2.soma(numeros);

        System.out.println("Soma: " + resultado1);

        System.out.println("Soma: " + resultado2);

        System.out.println("Soma: " + resultado3);

        System.out.println("Soma: [" + resultado4 + "]");

    }
    
}
