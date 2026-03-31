package diversos.threads.thread3;

public class Teste3Thread3 {// Eclipse -> Github @guilhermeNetogit 25/03/2026 14:18:14

	public static void main(String[] args) {

		ThreadRunnable3 thread1 = new ThreadRunnable3("Thread #1", 500);
		ThreadRunnable3 thread2 = new ThreadRunnable3("Thread #2", 700);
		ThreadRunnable3 thread3 = new ThreadRunnable3("Thread #3", 900);
		ThreadRunnable3 thread4 = new ThreadRunnable3("Thread #4", 50);

		Thread t1 = new Thread(thread1);
		Thread t2 = new Thread(thread2);
		Thread t3 = new Thread(thread3);
		Thread t4 = new Thread(thread4);

		t1.setPriority(8); // vai funcionar mais quando os tempos forem iguais ou próximos
		t2.setPriority(5);
		t3.setPriority(3);
		t4.setPriority(1);

		t1.start();

		try {
			t1.join(1500);
			System.out.println("Passou dos 1500ms ou t1 terminou");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		t2.start();

		try {
			t2.join(2100);
			System.out.println("Passou dos 2100ms ou t2 terminou");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		t3.start();

		try {
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		t4.start();

		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("\nPrograma finalizado");
	}
}