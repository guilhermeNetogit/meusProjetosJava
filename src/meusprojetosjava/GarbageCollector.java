package meusprojetosjava;

import meusprojetosjava.contatos.contatos2.Contatos2;
import java.util.Arrays;

public class GarbageCollector {// Eclipse -> Github @guilhermeNetogit 24/03/2026 20:33:26

	public static double obterMemoriaUsada() {

		final double MB = 1024 * 1024.0;

		Runtime runtime = Runtime.getRuntime();
		long usadoByte = (runtime.totalMemory() - runtime.freeMemory());
		double usadoMB = usadoByte / MB;

		System.out.printf("%.6f MB%n", usadoMB);
		System.out.printf("%,d Bytes%n", usadoByte);
		return usadoMB;
	}

	public static void main(String[] args) {

		Contatos2[] contatos = new Contatos2[100000];

		for (int i = 0; i < contatos.length; i++) {
			contatos[i] = new Contatos2(
					new String[] { Contatos2.TIPO_CONTATO[2] }, 
					"Contato" + i, 
					"Rua" + i,
					new String[] { "123-4567" + i }, 
					"contato" + i + "@email.com");
		}

		long criados = Arrays.stream(contatos).filter(c -> c != null).count();
		System.out.println("Contatos criados -> " + criados);
		
		double antes = obterMemoriaUsada();

		contatos = null;

		Runtime.getRuntime().gc();

		System.out.println("\nContatos removidos -> ");
		double depois = obterMemoriaUsada();

		System.out.printf("\nTotal liberado: %.2f MB%n", antes - depois);

	}

}
