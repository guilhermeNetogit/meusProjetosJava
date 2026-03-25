package meusprojetosjava.threads.thread2;

public class TesteThreadRunnable {// Eclipse -> Github @guilhermeNetogit 25/03/2026 11:37:41
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		MinhaThreadRunnable thread1 = new MinhaThreadRunnable("Thread #1", 600);
		// Thread t1 = new Thread(thread1); // Está instanciado no construtor
		// t1.start();

		MinhaThreadRunnable thread2 = new MinhaThreadRunnable("Thread #2", 1800);

		MinhaThreadRunnable thread3 = new MinhaThreadRunnable("Thread #3", 2400);

	}

}