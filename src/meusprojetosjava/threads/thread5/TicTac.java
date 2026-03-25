package meusprojetosjava.threads.thread5;

public class TicTac {// Eclipse -> Github @guilhermeNetogit 25/03/2026 19:01:46

	boolean tic;

	synchronized void tic(boolean estaExecutando) {

		if (!estaExecutando) {
			tic = true;
			notify();
			return;
		}

		System.out.print("Tic...");

		tic = true;

		notify();

		try {
			while (tic) {
				wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	synchronized void taque(boolean estaExecutando) {

		if (!estaExecutando) {
			tic = false;
			notify();
			return;
		}

		System.out.println("Tac!");

		tic = false;

		notify();

		try {
			while (!tic) {
				wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}