package diversos.threads.thread1;

public class TesteThread {// Eclipse -> Github @guilhermeNetogit 25/03/2026 11:02:09

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		MinhaThread thread = new MinhaThread("Thread #1", 500);
		// thread.start();

		MinhaThread thread2 = new MinhaThread("Thread #2", 1000);

		MinhaThread thread3 = new MinhaThread("Thread #3", 2000);
	}

}