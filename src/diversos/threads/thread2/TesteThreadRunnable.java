package diversos.threads.thread2;

public class TesteThreadRunnable {// Eclipse -> Github @guilhermeNetogit 25/03/2026 11:37:41
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		ThreadRunnable thread1 = new ThreadRunnable("Thread #1", 600);
		// Thread t1 = new Thread(thread1); // Está instanciado no construtor
		// t1.start();

		ThreadRunnable thread2 = new ThreadRunnable("Thread #2", 1800);

		ThreadRunnable thread3 = new ThreadRunnable("Thread #3", 2400);

	}

}