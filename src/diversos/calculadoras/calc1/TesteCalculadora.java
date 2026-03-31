package diversos.calculadoras.calc1;

public class TesteCalculadora {
    public static void main(String[] args) {
    	
        Calculadora calc = new Calculadora(); 

        calc.soma(5,3);
        calc.soma(5.0,3.0);
        calc.soma(5,3,2);
        int[] numeros = {1,2,3,4,5,6,7,8,9,10};
        calc.soma(numeros);

        System.out.println("Soma: " + calc.soma(5, 3));

        System.out.println("Soma: " + calc.soma(5.0, 3.0));

        System.out.println("Soma: " + calc.soma(5, 3, 2));

        System.out.println("Soma: [" + calc.soma(numeros) + "]");

    }
    
}
