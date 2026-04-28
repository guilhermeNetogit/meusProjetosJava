package diversos.trycatch.multiploscatch;

public class MultiplosCatch {// Eclipse -> Github @guilhermeNetogit 19/03/2026 14:20:46

	public static void main(String[] args) {

		int numeros[] = { 1, 2, 4, 8, 16, 32, 64, 128, 256, 512 };
		int denomina[] = { 2, 0, 1, 2, 0, 4, 8, 16 };

		for (int i = 0; i < numeros.length; i++) {
			try {
				if (denomina[i] == 0) {
					throw new ArithmeticException();
				}
				double result = (double) numeros[i] / denomina[i];
				System.out.println(numeros[i] + "/" + denomina[i] + " = " + result);

			} catch (ArithmeticException exc) {
				System.out.println("Erro ao executar divisão por zero.");
			} catch (ArrayIndexOutOfBoundsException exc) {
				System.out.println("\nSeu array numeros tem " + numeros.length + " posições, ");
				System.out.println("Seu array denomina tem " + denomina.length + " posições.");
				System.out.print("Não será possível realizar " + (numeros.length - denomina.length) + " operaç");
				System.out.println(String.format((numeros.length - denomina.length) > 1 ? "ões." : "ão."));

				break;
			}

		}
		System.out.println("\nJava version " + System.getProperty("java.version"));
	}
}