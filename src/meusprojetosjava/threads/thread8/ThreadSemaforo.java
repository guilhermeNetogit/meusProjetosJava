package meusprojetosjava.threads.thread8;

public class ThreadSemaforo implements Runnable {// Eclipse -> Github @guilhermeNetogit 26/03/2026 14:10:47

	private CorSemaforo cor;
	private boolean parar;
	private boolean corMudou;
	private int ciclosRestantes;

	public ThreadSemaforo(int ciclos) {
		this.cor = CorSemaforo.VERMELHO;
		this.ciclosRestantes = ciclos * 3;
		new Thread(this).start();
		new Thread(this::executarContador).start();
	}

	@Override
	public void run() {

		while (!parar) {
			try {
				/*
				 * switch (this.cor) { case VERMELHO: Thread.sleep(5000); break; case AMARELO:
				 * Thread.sleep(300); break; case VERDE: Thread.sleep(5000); break; default:
				 * break; }
				 */
				Thread.sleep(this.cor.getTempoEspera() + 2000);
				this.mudarCor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private void executarContador() {
		while (!parar) {
			try {
				CorSemaforo corAtual = this.cor;
				int tempo = corAtual.getTempoEspera() / 1000; // converte ms para segundos

				System.out.print(corAtual.name() + "... ");
				for (int i = tempo; i >= 0; i--) {
					System.out.print(i + " ");
					System.out.flush();
					Thread.sleep(1000); // ← pausa de 500ms após o 0
				}
				System.out.println();
				Thread.sleep(1000); // ← 1 segundo extra após o 0 antes de mudar

				esperaCorMudar();

				ciclosRestantes--; // ← decrementa a cada cor
				if (ciclosRestantes <= 0) {
					desligarSemafaro(); // ← encerra sozinho
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private synchronized void mudarCor() {
		switch (this.cor) {
		case VERMELHO:
			this.cor = CorSemaforo.VERDE;
			break;
		case AMARELO:
			this.cor = CorSemaforo.VERMELHO;
			break;
		case VERDE:
			this.cor = CorSemaforo.AMARELO;
			break;
		default:
			break;
		}
		this.corMudou = true;
		notify();
	}

	public synchronized void esperaCorMudar() {
		while (!this.corMudou) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.corMudou = false;
	}

	public synchronized void desligarSemafaro() {
		this.parar = true;
	}

	public CorSemaforo getCor() {
		return cor;
	}
}
