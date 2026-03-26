package meusprojetosjava.threads.thread6;

public class ThreadSuspend implements Runnable {// Eclipse -> Github @guilhermeNetogit 26/03/2026 10:42:45

	private String nome;
	private boolean estaSuspensa;
	private boolean foiTerminada;

	public ThreadSuspend(String nome) {
		this.nome = nome;
		this.estaSuspensa = false;
		new Thread(this, nome).start();
		// Thread t = new Thread(this, nome);
		// t.start();
	}

	@Override
	public void run() {

		System.out.println("Executando " + this.nome);

		try {
			for (int i = 0; i < 10; i++) {

				System.out.println(nome + ", " + i);

				Thread.sleep(300);
				synchronized (this) {
					while (estaSuspensa) {
						wait();
					}
					if (this.foiTerminada) {
						break;
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();

		}

		System.out.println(this.nome + " terminada.");

	}

	void suspend() {
		this.estaSuspensa = true;
	}

	synchronized void resume() {
		this.estaSuspensa = false;
		notify();
	}

	synchronized void stop() {
		this.foiTerminada = true;
		notify();
	}
}
