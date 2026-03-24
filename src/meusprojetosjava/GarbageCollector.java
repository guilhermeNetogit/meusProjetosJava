package meusprojetosjava;

import meusprojetosjava.contatos.contatos2.Contatos2;

public class GarbageCollector {// Eclipse -> Github @guilhermeNetogit 24/03/2026 19:43:08

	public static void obterMemoriaUsada() {

		final double MB = 1024 * 1024.0;

		Runtime runtime = Runtime.getRuntime();

		System.out.println((runtime.totalMemory() - runtime.freeMemory()) / MB + " MB");
		System.out.println((runtime.totalMemory() - runtime.freeMemory()) + " Byte");
	}

	@SuppressWarnings("removal")
	public static void main(String[] args) {

		Contatos2[] contatos = new Contatos2[100000];
		Contatos2 contato;

		for (int i = 0; i < contatos.length; i++) {
			contato = new Contatos2();
			contato.setTipoContato(new String[] { Contatos2.TIPO_CONTATO[2] });
			contato.setNome("Contato" + i);
			contato.setTelefones(new String[] { "123-4567" + i });
			contato.setEmail("contato" + i + "@email.com");
			contatos[i] = contato;
		}

		System.out.println("Contatos criados");

		obterMemoriaUsada();

		contatos = null;

		Runtime.getRuntime().runFinalization();

		Runtime.getRuntime().gc();

		System.out.println("Contatos removidos");

		obterMemoriaUsada();
	}

}
