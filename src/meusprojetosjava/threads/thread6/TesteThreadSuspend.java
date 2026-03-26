package meusprojetosjava.threads.thread6;

public class TesteThreadSuspend {// Eclipse -> Github @guilhermeNetogit 26/03/2026 10:42:53

	public static void main(String[] args) {

		ThreadSuspend t1 = new ThreadSuspend("Thread #1");
		ThreadSuspend t2 = new ThreadSuspend("Thread #2");

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) { //adicionada a pausa geral para dar tempo de executar antes da pausa #1
			e.printStackTrace();
		}
		
		System.out.println("Pausando a Thread #1");
		t1.suspend();

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Pausando a Thread #2");
		t2.suspend();

		System.out.println("Resumindo a Thread #1");
		t1.resume();

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Resumindo a Thread #2");
		t2.resume();

		System.out.println("Terminando a Thread #2");
		t2.stop();
	}

}
