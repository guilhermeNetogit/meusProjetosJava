package diversos.trycatch.testethrows;

import java.util.Scanner;

public class TesteThrows {// Eclipse -> Github @guilhermeNetogit 19/03/2026 14:55:06

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		System.out.println("Entre com um número decimal");

		try {
			double num = leNumero(scan);
			System.out.println("Você digitou " + num);
		} catch (Exception e) {
			System.out.println("Entrada inválida");
			e.printStackTrace();
		} finally {
			scan.close();
		}
	}

	public static double leNumero(Scanner scan) throws Exception {

		return scan.nextDouble();

	}
}