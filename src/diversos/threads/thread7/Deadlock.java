package diversos.threads.thread7;

public class Deadlock {// Eclipse -> Github @guilhermeNetogit 26/03/2026 11:46:14

	public static void main(String[] args) {

		final String RECURSO1 = "Recurso #1";
		final String RECURSO2 = "Recurso #2";

		Thread t1 = new Thread() {
			public void run() {
				synchronized (RECURSO1) {
					System.out.println("Thread #1: bloqueou recurso 1");

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					System.out.println("Thread #1: tentando acessar recurso 2");

					synchronized (RECURSO2) {
						System.out.println("Thread #1: bloqueou recurso 2");
					}
					System.out.println("Thread #1: liberou recurso 2");
				}
				System.out.println("Thread #1: liberou recurso 1");
			}
		};

		Thread t2 = new Thread() {
			public void run() {
				synchronized (RECURSO1) {
					System.out.println("Thread #2: bloqueou recurso 1");

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					System.out.println("Thread #2: tentando acessar recurso 2");

					synchronized (RECURSO2) {
						System.out.println("Thread #2: bloqueou recurso 2");
					}
					System.out.println("Thread #2: liberou recurso 2");
				}
				System.out.println("Thread #2: liberou recurso 1");
			}
		};

		t1.start();
		t2.start();
	}

}
