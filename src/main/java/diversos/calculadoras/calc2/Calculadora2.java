package diversos.calculadoras.calc2;

public class Calculadora2 {
    public static  int soma(int numA, int numB) {
        return numA + numB;
    }
    public static  double soma(double numA, double numB) {
        return numA + numB;
    }
    public static  int soma(int numA, int numB, int numC) {
        return numA + numB + numC;
    }
    public static  int soma(int[] numeros) {
        int resultado = 0;
        for (int i = 0 ; i < numeros.length ; i++) {
            resultado += numeros[i];
        }
        return resultado;
    }
}
