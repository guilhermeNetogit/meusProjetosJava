package diversos.threads.thread5;

public class TesteTicTac {// Eclipse -> Github @guilhermeNetogit 25/03/2026 19:01:32

	public static void main(String[] args) {

		TicTac tt = new TicTac();
		ThreadTicTac tic = new ThreadTicTac("Tic", tt);
		ThreadTicTac tac = new ThreadTicTac("Tac", tt);

		try {
			tic.t.join();
			tac.t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}