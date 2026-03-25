package meusprojetosjava.threads.thread4;

public class ThreadSync implements Runnable {// Eclipse -> Github @guilhermeNetogit 25/03/2026 18:30:38

	private String nome;
	private int[] nums;
	private static Calculadora calc = new Calculadora();

	public ThreadSync(String nome, int[] nums) {
		this.nome = nome;
		this.nums = nums;
		// new Thread(this, nome).start();
		// Thread t = new Thread(this, nome);
		// t.start();
	}

	@Override
	public void run() {

		System.out.println(this.nome + " iniciada");

		int soma = calc.somaArray(this.nums);

		System.out.println("Resultado da soma para thread " + this.nome + " é: " + soma);

		System.out.println(this.nome + " terminada\n");

	}

}