package meusprojetosjava.contacorrente.contacorrente2;

public class ContaCorrente2 {// Eclipse -> Github @guilhermeNetogit 05/03/2026 15:23:10

	private String numero;
	private String agencia;
	private boolean especial;
	private double limiteEspecial;
	private double valorEspecialUsado;
	private double saldo;

	public ContaCorrente2() {

	}

	public ContaCorrente2(String numero, String agencia, boolean especial, double limiteEspecial,
			double valorEspecialUsado, double saldo) {

		this.numero = numero;
		this.agencia = agencia;
		this.especial = especial;
		this.limiteEspecial = limiteEspecial;
		this.valorEspecialUsado = valorEspecialUsado;
		this.saldo = saldo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public boolean isEspecial() {
		return especial;
	}

	public void setEspecial(boolean especial) {
		this.especial = especial;
	}

	public double getLimiteEspecial() {
		return limiteEspecial;
	}

	public void setLimiteEspecial(double limiteEspecial) {
		this.limiteEspecial = limiteEspecial;
	}

	public double getValorEspecialUsado() {
		return valorEspecialUsado;
	}

	public void setValorEspecialUsado(double valorEspecialUsado) {
		this.valorEspecialUsado = valorEspecialUsado;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public boolean realizarSaque(double valorSaque) {

		if (saldo >= valorSaque) {
			saldo -= valorSaque;
			return true;

		} else { // não tem saldo na conta
			if (especial) {
				// verifica se o limite especial tem saldo
				double limite = limiteEspecial + saldo;
				if (limite >= valorSaque) {
					saldo -= valorSaque;
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}

	public void depositar(double valorDepositado) {

		saldo += valorDepositado;
	}

	public void consultarSaldo() {
		System.out.println();
		System.out.println("Saldo atual da conta = " + saldo);
	}

	boolean verificarUsoChequeEspecial() {
		return saldo < 0;
	}
}