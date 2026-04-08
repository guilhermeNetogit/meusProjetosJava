package diversos.impostoderenda.irdinamico;

/**
 * Representa uma Pessoa Física contribuinte do IRPF.
 * O cálculo de INSS e IR é delegado ao objeto {@link RegrasFiscais},
 * que encapsula as tabelas e alíquotas de cada ano-base.
 *
 * Para calcular com regras de 2025: setRegras(RegrasFiscais.de2025())
 * Para calcular com regras de 2026: setRegras(RegrasFiscais.de2026())
 *
 * @author GitHub guilhermeNetogit
 * @since 16/03/2026 20:43:58
 * @version 07/04/2026 20:18:35
 */

public class PessoaFisica2 extends Contribuinte2 {

	private String cpf;
	private String faixaInss;
	private String faixaIr;
	private String tipoDescontoIr;
	private double irCalculado;
	private double inssCalculado;
	private double baseUtilizada;
	private int    qtdDependentes;
	private RegrasFiscais regras = null;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getQtdDependentes() {
		return qtdDependentes;
	}

	public void setQtdDependentes(int qtdDependentes) {
		this.qtdDependentes = qtdDependentes;
	}

	public RegrasFiscais getRegras() {
		return regras;
	}

	public void setRegras(RegrasFiscais regras) {
		this.regras = regras;
	}

	public double calcularInss() {

		if (regras == null) throw new IllegalStateException(
                "RegrasFiscais não configurada. Chame setRegras() antes de calcular.");
		double renda = getRendaBruta();
		this.inssCalculado = regras.calcularInss(renda);
		this.faixaInss = regras.faixaInss(renda);
		System.out.println("DEBUG faixaInss = [" + faixaInss + "]"); // <-- adiciona isso
		return this.inssCalculado;
	}

	@Override
	public double calcularIR() {

		double renda = getRendaBruta();
		double inss = calcularInss();
		double totalDep = qtdDependentes * regras.deducaoDependente;

		// Opção 1: Desconto legal (INSS + dependentes)
		double descontoLegal = inss + totalDep;
		double baseLegal = renda - descontoLegal;
		double baseLegalRelatorio = Math.max(0, baseLegal);
		double impostoLegal = regras.calcularIr(baseLegal);
		String faixaLegal = regras.faixaIr(baseLegal);

		// Opção 2: Desconto simplificado
		double descontoSimplificado = regras.descontoSimplificado;
		double baseSimplificada = renda - descontoSimplificado;
		double baseSimplificadaRelatorio = Math.max(0, baseSimplificada);
		double impostoSimplificado = regras.calcularIr(baseSimplificada);
		String faixaSimplificada = regras.faixaIr(baseSimplificada);

		// Escolhe o menor imposto (desempate: maior desconto)

		boolean usaLegal = descontoLegal > descontoSimplificado;

		if (usaLegal) {
			baseUtilizada = baseLegalRelatorio;
			faixaIr = faixaLegal;
			tipoDescontoIr = String.format("Dedução calculada (INSS: R$ %,.2f + Dep.: R$ %,.2f): totalizando R$ %,.2f",
					inss, totalDep, descontoLegal);
			irCalculado = impostoLegal;

		} else {
			baseUtilizada = baseSimplificadaRelatorio;
			faixaIr = faixaSimplificada;
			tipoDescontoIr = String.format("Desconto Simplificado R$ %,.2f", descontoSimplificado);
			irCalculado = impostoSimplificado;
		}

		return this.irCalculado;
	}

	public double getAliquotaEfetivaInss() {
		return (getRendaBruta() == 0) ? 0 : (this.inssCalculado / this.getRendaBruta()) * 100;
	}

	public double getAliquotaEfetivaIr() {
		return (getRendaBruta() == 0) ? 0 : (this.irCalculado / this.getRendaBruta()) * 100;
	}

	// Método especial para gerar a explicação do cálculo
	@Override
	public String gerarRelatorio() {

		calcularIR();

		StringBuilder sb = new StringBuilder();
		sb.append("--------------------------------------------------------------------\n");
		sb.append(String.format("Pessoa Física — Ano-base %d\n\n", regras.ano));
		sb.append("Contribuinte\n");
		sb.append(String.format("%-20s %s\n", "Nome:", super.toString()));
		// sb.append(super.toString()).append("%-20s\n");
		sb.append(String.format("%-20s %s\n", "CPF:", getCpf()));
		sb.append(String.format("%-20s %s\n", "Dependentes:", getQtdDependentes()));
		sb.append(String.format("%-20s R$ %,.2f\n", "Renda Bruta:", getRendaBruta()));

		sb.append("\nResumo Financeiro\n");
		sb.append(String.format("%-20s R$ %,.2f\n", "Desconto INSS:", inssCalculado));
		sb.append(String.format("%-20s %s\n", "Desconto IRPF:", tipoDescontoIr));
		sb.append(String.format("%-20s R$ %,.2f\n", "Imposto de Renda pagar:", irCalculado));

		sb.append(String.format("\nDetalhamento: \n"));
		sb.append(String.format("  ◦ Base INSS: R$ %,.2f%n", getRendaBruta()));
		sb.append(String.format("    -> faixa INSS: %s, alíquota efetiva é de %.2f%%\n", faixaInss,
				getAliquotaEfetivaInss()));
		sb.append(String.format("  ◦ Base IRPF: R$ %,.2f%n", baseUtilizada));
		sb.append(String.format("    -> faixa IRPF: %s, alíquota efetiva é de %.2f%%\n", faixaIr,
				getAliquotaEfetivaIr()));
		sb.append(String.format("--------------------------------------------------------------------"));

		return sb.toString();
	}
}
