package meusprojetosjava.Calculadora;

public class Calculadora {
    public int soma(int numA, int numB) {
        return numA + numB;
    }
    public double soma(double numA, double numB) {
        return numA + numB;
    }
    public int soma(int numA, int numB, int numC) {
        return numA + numB + numC;
    }
    public int soma(int[] numeros) {
        int resultado = 0;
        for (int i = 0 ; i < numeros.length ; i++) {
            resultado += numeros[i];
        }
        return resultado;
    }
}
