package meusprojetosjava.herancapolimorfismo;

import java.time.LocalDate;

public class ContaPoupanca extends ContaBancaria {

	private int diaRendimento;
	private LocalDate ultimoRendimento;

	public int getDiaRendimento() {
		return diaRendimento;
	}

	public void setDiaRendimento(int diaRendimento) {
		this.diaRendimento = diaRendimento;
	}

	@Override
	public String toString() {
		String cabecalho = super.toString()
				+ "\n Dia Rendimento: " + diaRendimento
				+ "\n-------------------------------------";
		return cabecalho;
	}

	public boolean calcularNovoSaldo(LocalDate hoje) {

		double taxaRendimento = 0.005; // ou pode receber como parâmetro, se preferir

		// Verifica se hoje é dia de rendimento E se o último rendimento não foi hoje
		if (hoje.getDayOfMonth() == this.diaRendimento && !hoje.equals(ultimoRendimento)) {
			double rendimento = this.getSaldo() * taxaRendimento;
			this.depositar(rendimento);
			this.ultimoRendimento = hoje; // marca que aplicou hoje
			return true;
		}
		return false;
	}

	public boolean hojeEDiaDeRendimento(LocalDate hoje) {
		return hoje.getDayOfMonth() == this.diaRendimento;
	}

	public boolean rendimentoJaPagoHoje(LocalDate hoje) {
		return hoje.equals(ultimoRendimento);
	}

	@Override
	public void consultarSaldo() {
		
		LocalDate hoje = LocalDate.now(); // chamada única
		super.consultarSaldo(); // exibe o saldo antes de qualquer rendimento

		if (calcularNovoSaldo(hoje)) {
			System.out.println(
					"Rendimento aplicado! Novo saldo disponível => " + String.format("R$ %,.2f", this.getSaldo()));
		} else if (hojeEDiaDeRendimento(hoje) && rendimentoJaPagoHoje(hoje)) {
			System.out.println("Rendimento já creditado anteriormente. Saldo permanece inalterado.");
		} else {
			System.out.println("Hoje não é dia de rendimento. Saldo permanece inalterado.");
		}
	}
}
