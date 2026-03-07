package meusprojetosjava.calculadora.fatorial;

// import java.math.BigInteger; // apenas se for trabalhar com BigInteger na variavel;

public class Fatorial {// Eclipse -> Github @guilhermeNetogit 07/03/2026 16:28:23

	public static int fatorialNaoRecursivo(int num) {

		// Fatorial Não Recursivo
		if (num == 0) {
			return 1;
		}
		int total = 1;
		for (int i = num; i > 1; i--) {
			total *= i;
		}
		return total;
	}

	public static int fatorialRecursivo(int num) {

		// Fatorial Recursivo
		if (num < 0)
			throw new IllegalArgumentException();
		if (num == 0 || num == 1) {
			return 1; // return BigInteger.ONE; // se for trabalhar com BigInteger;
		}
		return num * fatorialRecursivo(num - 1); // BigInteger.valueOf(num).multiply(fatorialRecursivo(num - 1)); // se
													// for trabalhar com BigInteger;
	}
}
