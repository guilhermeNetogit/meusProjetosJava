package meusprojetosjava.herancapolimorfismo;

public class ContaBancaria {

	private String nomeCliente;
	private String agencia;
	private String numConta;
	private String tipoConta;
	private double saldo;

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getNumConta() {
		return numConta;
	}

	public void setNumConta(String numConta) {
		this.numConta = numConta;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	@Override
	public String toString() {
		String cabecalho = "*********** Banco do Povo ***********" 
				+ "\nTipo Conta: " + tipoConta 
				+ "\nCliente: " + nomeCliente 
				+ "\nAgência: " + agencia 
				+ "\nConta: " + numConta;
		return cabecalho;
	}

	public void depositar(double valorDepositado) {
		saldo += valorDepositado;
	}

	public boolean realizarSaque(double valorSaque) {

		if (saldo >= valorSaque) {
			saldo -= valorSaque;
			return true;

		}
		return false;
	}

	public void consultarSaldo() {
		System.out.println();
		System.out.println("Saldo atual da conta => " + String.format("R$ %,.2f", saldo));
	}
}
