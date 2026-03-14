package meusprojetosjava.impostorenda;

public class PessoaFisica extends Contribuinte {// Eclipse -> Github @guilhermeNetogit 14/03/2026 19:07:01

	private String cpf;
	private String faixa;
	private double impostoCalculado;

	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getFaixa() {
		return faixa;
	}
	public void setFaixa(String faixa) {
		this.faixa = faixa;
	}

	@Override
	public double calcularIR() {

		double renda = this.getRendaBruta();

		if (renda <= 2428.80) {
			this.faixa = "Isento";
			this.impostoCalculado = 0;
		} else if (renda <= 2826.65) {
			this.faixa = "7,5%";
			this.impostoCalculado = (renda * 0.075) - 182.16;
		} else if (renda <= 3751.05) {
			this.faixa = "15%";
			this.impostoCalculado = (renda * 0.15) - 394.16;
		} else if (renda <= 4664.68) {
			this.faixa = "22,5%";
			this.impostoCalculado = (renda * 0.225) - 675.49;
		} else {
		// if (renda >= 4664.69) {
		this.faixa = "27,5%";
		this.impostoCalculado = (renda * 0.275) - 908.73;
		}
		
		return this.impostoCalculado;
	}

	public double getAliquotaEfetiva() {
		
		if (this.getRendaBruta() == 0) return 0;
		return (this.impostoCalculado / this.getRendaBruta()) * 100;
	}

	// Método especial para gerar a explicação do cálculo
	public String gerarRelatorio() {
		
		calcularIR();
		
		StringBuilder sb = new StringBuilder();
		sb.append("Pessoa Física\n\n");
		sb.append("Contribuinte\n");
		sb.append(super.toString()).append("\n");
		sb.append(String.format("CPF: %s\n", getCpf()));
		sb.append(String.format("Renda Bruta: R$ %,.2f%n\n", getRendaBruta()));
		sb.append(String.format("Imposto a pagar: R$ %.2f\n", calcularIR()));
		sb.append(String.format("Detalhamento: você se encaixa na faixa de %s, sua alíquota efetiva é de %.2f%%",
				faixa, getAliquotaEfetiva()));

		return sb.toString();
	}
}
