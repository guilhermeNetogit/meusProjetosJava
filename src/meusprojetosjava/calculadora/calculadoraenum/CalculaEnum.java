package meusprojetosjava.calculadora.calculadoraenum;

import java.util.Scanner;

public class CalculaEnum {// Eclipse -> Github @guilhermeNetogit 23/03/2026 17:59:33

	enum Operacao {
		SOMAR('+') {
			@Override
			public double executarOperacao(double num1, double num2) {
				return num1 + num2;
			}
		},
		SUBTRAIR('-') {
			@Override
			public double executarOperacao(double num1, double num2) {
				return num1 - num2;
			}
		},
		MULTIPLICAR('*') {
			@Override
			public double executarOperacao(double num1, double num2) {
				return num1 * num2;
			}
		},
		DIVIDIR('/') {
			@Override
			public double executarOperacao(double num1, double num2) {
				return num1 / num2;
			}
		};

		private final char simbolo;

		Operacao(char simbolo) {
			this.simbolo = simbolo;
		}

		public abstract double executarOperacao(double num1, double num2);

		@Override
		public String toString() {
			return String.valueOf(this.simbolo);
		}
	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.print("Informe o valor do primeiro número: ");
		double num1 = scan.nextDouble();

		System.out.print("Informe o valor do segundo número:  ");
		double num2 = scan.nextDouble();

		System.out.println();
		
		for (Operacao op : Operacao.values()) {
			System.out.print(num1 + " ");
			System.out.print(op.toString() + " ");
			System.out.print(num2 + " = ");
			System.out.println(op.executarOperacao(num1, num2));
		}
		scan.close();
	}
}
