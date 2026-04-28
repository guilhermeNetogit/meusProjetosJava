package diversos.threads.thread4;

public class TesteThreadSync {// Eclipse -> Github @guilhermeNetogit 25/03/2026 18:30:32

	public static void main(String[] args) {

		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }; // 55
		ThreadSync thread1 = new ThreadSync("Thread #1", array);
		ThreadSync thread2 = new ThreadSync("Thread #2", array);

		Thread t1 = new Thread(thread1, "Thread #1");
		Thread t2 = new Thread(thread2, "Thread #2");

		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		t2.start();
		try {
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
