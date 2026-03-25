package meusprojetosjava.threads.thread3;

import meusprojetosjava.threads.thread2.ThreadRunnable;

public class Teste2Thread3 {

	public static void main(String[] args) {

		ThreadRunnable thread1 = new ThreadRunnable("Thread #1", 500);
		ThreadRunnable thread2 = new ThreadRunnable("Thread #2", 500);
		ThreadRunnable thread3 = new ThreadRunnable("Thread #3", 500);

		thread1.start();
		thread2.start();
		thread3.start();

		while (thread1.isAlive() || thread2.isAlive() || thread3.isAlive()) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Programa finalizado");
	}
}