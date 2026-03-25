package meusprojetosjava.threads.thread3;

import meusprojetosjava.threads.thread2.ThreadRunnable;

public class TesteThread3 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		ThreadRunnable thread1 = new ThreadRunnable("Thread #1", 500);
		ThreadRunnable thread2 = new ThreadRunnable("Thread #2", 500);
		ThreadRunnable thread3 = new ThreadRunnable("Thread #3", 500);

		for (int i = 0; i < 16; i++) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Programa finalizado -> i < 16.");
	}
}
