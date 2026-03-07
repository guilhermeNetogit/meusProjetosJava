package meusprojetosjava.calculadora.fatorial;

// import java.math.BigInteger; // apenas se for trabalhar com BigInteger na variavel;

public class TesteFatorial {// Eclipse -> Github @guilhermeNetogit 07/03/2026 16:28:38

	public static void main(String[] args) {

		int fatorialNR = Fatorial.fatorialNaoRecursivo(5);
		System.out.println(fatorialNR);
		System.out.println();

		// Levando em conta apenas os numeros inteiros positivos (precisa usar o
		// toUnsigned):

		// byte 8 bits numMax = 127 maxFatorial = 5
		// short 16 bits numMax = 32.767 maxFatorial = 7
		// int 32 bits numMax = 2.147.483.647 maxFatorial = 12
		// long 64 bits numMax = 9.223.372.036.854.775.807 maxFatorial = 20
		// BigInteger arbitrario numMax = ilimitado (depende da memmória da JVM)
		// maxFatorial = 3248 (testado)

		int fatorialR = Fatorial.fatorialRecursivo(1);
		System.out.println(Integer.toUnsignedLong(fatorialR));

	}

}
