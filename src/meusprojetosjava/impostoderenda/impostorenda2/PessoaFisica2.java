package meusprojetosjava.impostoderenda.impostorenda2;

public class PessoaFisica2 extends Contribuinte2 {// Eclipse -> Github @guilhermeNetogit 15/03/2026 22:31:09

	private String cpf;
	private String faixaInss;
	private String faixaIr;
	private String tipoDescontoIr;
	private double irCalculado;
	private double inssCalculado;
	private double baseUtilizada;
	private int qtdDependentes;
	private static final double DEDUCAO_DEPENDENTE = 189.59;
	private static final double DESCONTO_SIMPLIFICADO_PADRAO = 607.20; 
	
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

	public String getFaixaInss() {
		return faixaInss;
	}

	public void setFaixaInss(String faixaInss) {
		this.faixaInss = faixaIr;
	}

	public String getFaixaIr() {
		return faixaIr;
	}

	public void setFaixaIr(String faixaIr) {
		this.faixaInss = faixaIr;
	}
	
	public String getTipoDescontoIr() {
		return tipoDescontoIr;
	}

	public void setTipoDescontoIr(String tipoDescontoIr) {
		this.tipoDescontoIr = tipoDescontoIr;
	}

	public double calcularInss() {
		
		double renda = getRendaBruta();
		
		if (renda < 1621.00 && renda > 0) {

        this.faixaInss = "Isento (abaixo do mínimo)";
        this.inssCalculado = 0;
        return 0;
    }
		
		// 1. Limita a renda ao teto máximo (ninguém contribui sobre mais que isso)
		double baseCalculoInss = Math.min(renda, 8475.55);

		// 2. O cálculo agora usa a base filtrada
		if (baseCalculoInss <= 1621.00) {
			this.faixaInss = "7,5%";
			this.inssCalculado = (baseCalculoInss * 0.075);
		} else if (baseCalculoInss <= 2902.84) {
			this.faixaInss = "9%";
			this.inssCalculado = (baseCalculoInss * 0.09) - 24.32;
		} else if (baseCalculoInss <= 4354.27) {
			this.faixaInss = "12%";
			this.inssCalculado = (baseCalculoInss * 0.12) - 111.40;
		} else {
			// if (rendaBruta >= 8475.56)
			this.faixaInss = "14%";
			this.inssCalculado = (baseCalculoInss * 0.14) - 198.49;
		}

		return this.inssCalculado;
	}

	@Override
	public double calcularIR() {

		double renda = this.getRendaBruta();
		double inss = this.calcularInss();
		double totalDep = qtdDependentes * DEDUCAO_DEPENDENTE;

		double baseLegal = renda - (inss + totalDep);
		double impostoLegal = calcularIrTabela2026(baseLegal);
		String faixaLegal = faixaIr;
		
		double baseSimplificada = renda - DESCONTO_SIMPLIFICADO_PADRAO;
		double impostoSimplificado = calcularIrTabela2026(baseSimplificada);
		String faixaSimplificadaString = faixaIr;
		
		if (impostoLegal <= impostoSimplificado) {
			baseUtilizada = baseLegal;
			faixaIr = faixaLegal;
			tipoDescontoIr = String.format("\n- Dedução calculada (INSS: R$ %,.2f + Dep.: R$ %,.2f): totalizando R$ %,.2f",
						inss, totalDep, (inss + totalDep));
			irCalculado = Math.max(0, impostoLegal);
			
		} else {
			baseUtilizada = baseSimplificada;
			faixaIr = faixaSimplificadaString;
			tipoDescontoIr = "Desconto Simplificado R$ " + DESCONTO_SIMPLIFICADO_PADRAO;
			irCalculado = Math.max(0, impostoSimplificado);
		}
		
		return this.irCalculado;
	}
	
	private double calcularIrTabela2026(double base) {
		if (base <= 2428.80) {
			this.faixaIr = "Isento";
			return 0;
		} else if (base <= 2826.65) {
			this.faixaIr = "7,5%";
			return (base * 0.075) - 182.16;
		} else if (base <= 3751.05) {
			this.faixaIr = "15%";
			return (base * 0.15) - 394.16;
		} else if (base <= 4664.68) {
			this.faixaIr = "22,5%";
			return (base * 0.225) - 675.49;
		} else {
			this.faixaIr = "27,5%";
			return (base * 0.275) - 908.73;
		}
	}

	public double getAliquotaEfetivaInss() {

		
		return (getRendaBruta() == 0) ? 0 : (this.inssCalculado / this.getRendaBruta()) * 100;
	}

	public double getAliquotaEfetivaIr() {

		return (getRendaBruta() == 0) ? 0 : (this.irCalculado / this.getRendaBruta()) * 100;
	}

	// Método especial para gerar a explicação do cálculo
	public String gerarRelatorio() {

		calcularIR();

		StringBuilder sb = new StringBuilder();
		sb.append("-----------------------------------------------------------------------------------\n");
		sb.append("Pessoa Física\n\n");
		sb.append("Contribuinte\n");
		sb.append(super.toString()).append("\n");
		sb.append(String.format("CPF: %s\n", getCpf()));
		sb.append(String.format("Dependentes: %s\n", getQtdDependentes()));
		sb.append(String.format("Renda Bruta: R$ %,.2f%n", getRendaBruta()));
		sb.append(String.format("Desconto INSS: R$ %,.2f%n", inssCalculado));
		sb.append(String.format("Base INSS: R$ %,.2f%n", getRendaBruta()));
		sb.append(String.format("Base IRPF: R$ %,.2f%n", baseUtilizada));
		sb.append(String.format("Desconto aplicado no IRPF: %s\n", tipoDescontoIr));
		sb.append(String.format("Imposto de Renda pagar: R$ %.2f\n", irCalculado));
		sb.append(String.format("Detalhamento: \n"));
		sb.append(String.format("- faixa INSS: %s, alíquota efetiva é de %.2f%%\n", faixaInss,
				getAliquotaEfetivaInss()));
		sb.append(String.format("- faixa IRPF: %s, alíquota efetiva é de %.2f%%", faixaIr,
				getAliquotaEfetivaIr()));

		return sb.toString();
	}
}
