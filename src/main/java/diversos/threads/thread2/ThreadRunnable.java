package diversos.threads.thread2;

public class ThreadRunnable implements Runnable {// Eclipse -> Github @guilhermeNetogit 25/03/2026 11:37:49

	private String nome;
	private int tempo;
	private Thread t;

	public ThreadRunnable(String nome, int tempo) {
		this.nome = nome;
		this.tempo = tempo;
		t = new Thread(this);
		t.start();
	}

	public ThreadRunnable(String nome, int tempo, boolean autoStart) {
		this.nome = nome;
		this.tempo = tempo;
		this.t = new Thread(this);
		if (autoStart) {
			t.start();
		}
	}

	public void start() {
		if (!t.isAlive()) {
			t.start();
		}
	}

	public boolean isAlive() {
		return t.isAlive();
	}

	@Override
	public void run() {

		try {
			for (int i = 0; i < 6; i++) {
				System.out.println(nome + " contador " + i);
				Thread.sleep(tempo);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(nome + " terminou a execução");
	}

}
