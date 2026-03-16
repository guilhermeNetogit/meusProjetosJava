package meusprojetosjava.impostoderenda.impostorenda2;

public class PessoaJuridica2 extends Contribuinte2 {// Eclipse -> Github @guilhermeNetogit 15/03/2026 22:31:19

	private String cnpj;
	private final double tetoMensal = 20000.00;

	// Atributos para cache (evita recalcular no relatório)
	private double impostoBase;
	private double impostoAdicional;
	private double impostoTotal;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public double calcularIR() {
		// 1. Cálculo da base (15%)
		double renda = this.getRendaBruta();
		impostoBase = renda * 0.15;

		// getImpostoBase() + getImpostoAdicional();

		// 2. Calcula os 10% do adicional sobre o que exceder R$ 20.000,00
		double excesso = renda - tetoMensal;
		impostoAdicional = (excesso > 0) ? excesso * 0.10 : 0;

		// 3. Total
		impostoTotal = impostoBase + impostoAdicional;

		return impostoTotal;
	}

	// Método especial para gerar a explicação do cálculo
	public String gerarRelatorio() {

		calcularIR();

		StringBuilder sb = new StringBuilder();
		sb.append("-----------------------------------------------------------------------------------\n");
		sb.append("Pessoa Juridica\n\n");
		sb.append("Contribuinte\n");
		sb.append(String.format("%-20s %s\n", "Nome:", super.toString()));
		sb.append(String.format("%-20s %s\n", "CNPJ:", getCnpj()));
		sb.append(String.format("%-20s R$ %,.2f\n", "Renda Bruta:", getRendaBruta()));
		sb.append(String.format("%-20s R$ %,.2f\n", "Teto Mensal:", tetoMensal));

		if (getRendaBruta() <= tetoMensal) {
			sb.append(String.format("%-20s R$ %,.2f (15%% sobre o total)", "Imposto a pagar:", impostoTotal));
		} else {
			double excessoValor = rendaBruta - tetoMensal;
			sb.append(String.format("%-20s R$ %,.2f\n", "Imposto a pagar:", impostoTotal));
			sb.append("Detalhamento: \n");
			sb.append(String.format(" ◦ 15%% sobre o total de %,.2f (%,.2f)\n", getRendaBruta(), impostoBase));
			sb.append(String.format(" ◦ 10%% sobre o adicional de %.2f (%,.2f)", excessoValor, impostoAdicional));
		}
		return sb.toString();
	}
}
