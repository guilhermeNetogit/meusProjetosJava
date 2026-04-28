package diversos.threads.thread5;

public class ThreadTicTac implements Runnable {// Eclipse -> Github @guilhermeNetogit 25/03/2026 19:01:39

	TicTac tt;
	Thread t;

	final int NUM = 5;

	public ThreadTicTac(String nome, TicTac tt) {
		this.tt = tt;
		t = new Thread(this, nome);
		t.start();
	}

	@Override
	public void run() {

		if (t.getName().equalsIgnoreCase("Tic")) {
			for (int i = 0; i < NUM; i++) {
				tt.tic(true);
			}
			tt.tic(false);
		} else {
			for (int i = 0; i < NUM; i++) {
				tt.taque(true);
			}
			tt.taque(false);
		}

	}
}
