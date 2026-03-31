package diversos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Arrays {

	public static void main(String[] args) {// Eclipse -> Github @guilhermeNetogit 24/03/2026 12:18:04

		double[] temperaturas = new double[12];

		temperaturas[0] = 23.9; // Janeiro
		temperaturas[1] = 24; // Fevereiro
		temperaturas[2] = 24; // Março
		temperaturas[3] = 22; // Abril
		temperaturas[4] = 20; // Maio
		temperaturas[5] = 19; // Junho
		temperaturas[6] = 19; // Julho
		temperaturas[7] = 22; // Agosto
		temperaturas[8] = 25; // Setembro
		temperaturas[9] = 26; // Outubro
		temperaturas[10] = 25; // Novembro
		temperaturas[11] = 24; // Dezembro

		double soma = 0;
		int mesesPreenchidos = 0;

		// Primeiro percorre para contar e somar
		for (int i = 0; i < temperaturas.length; i++) {
			if (temperaturas[i] != 0.0) {
				mesesPreenchidos++;
				soma += temperaturas[i];
			}
		}

		System.out.println("Sua lista tem " + temperaturas.length + " meses.");
		System.out.println("Encontrado " + mesesPreenchidos + " preenchidos.\n");
		
		LocalDate hoje = LocalDate.now();
		int mesAtual = hoje.getMonthValue();
		int indiceArray = mesAtual - 1;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataFormatada = hoje.format(formatter);
		
		String[] nomesMeses = { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho"
				, "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" };

		System.out.println("Hoje é: " + dataFormatada);
		System.out.println("A temperatura no mês " + (indiceArray + 1) + " (" + nomesMeses[indiceArray] + ") é : " + temperaturas[indiceArray] + "°C\n");
		System.out.println("O endereço na memória desta informação é " + temperaturas + "\n");

		// Agora imprime apenas os meses preenchidos
		for (int i = 0; i < temperaturas.length; i++) {
			if (temperaturas[i] != 0.0) { // só mostra se for diferente de zero
				System.out.println("A temperatura no mês " + (i + 1) + " é " + temperaturas[i] + "°C");

			}
		}

		// Calcula e imprime média
		if (mesesPreenchidos > 0) {
			double media = soma / mesesPreenchidos;
			System.out.printf("\nTemperatura média: %.2f°C%n", media);
		} else {
			System.out.println("Nenhuma temperatura válida foi informada.");
		}
	}
}
