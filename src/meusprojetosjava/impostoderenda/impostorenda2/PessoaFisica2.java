package meusprojetosjava.impostoderenda.impostorenda2;

public class PessoaFisica2 extends Contribuinte2 {// Eclipse -> Github @guilhermeNetogit 16/03/2026 20:43:58

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
		this.faixaInss = faixaInss;
	}

	public String getFaixaIr() {
		return faixaIr;
	}

	public void setFaixaIr(String faixaIr) {
		this.faixaIr = faixaIr;
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

        this.faixaInss = "7,5% (abaixo do mínimo)";
        this.inssCalculado = renda * 0.075;
        return inssCalculado;
    }
		
		// 1. Limita a renda ao teto máximo (ninguém contribui sobre mais que isso)
		double baseCalculoInss = Math.min(renda, 8475.55); //2026
		//double baseCalculoInss = Math.min(renda, 8157.41); //2025

		// 2. O cálculo agora usa a base filtrada Tabela INSS 2026
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
/*
		// Tabela INSS 2025
		if (baseCalculoInss <= 1518.00) {
			this.faixaInss = "7,5%";
			this.inssCalculado = (baseCalculoInss * 0.075);
		} else if (baseCalculoInss <= 2793.88) {
			this.faixaInss = "9%";
			this.inssCalculado = (baseCalculoInss * 0.09) - 22.77;
		} else if (baseCalculoInss <= 4190.83) {
			this.faixaInss = "12%";
			this.inssCalculado = (baseCalculoInss * 0.12) - 106.59;
		} else {
			// if (rendaBruta >= 8157.41)
			this.faixaInss = "14%";
			this.inssCalculado = (baseCalculoInss * 0.14) - 190.40;
		}

		return this.inssCalculado;
	}
	*/
	@Override
	public double calcularIR() {

		double renda = this.getRendaBruta();
		double inss = this.calcularInss();
		double totalDep = qtdDependentes * DEDUCAO_DEPENDENTE;

		double descontoLegal = inss + totalDep;
		double baseLegal = renda - descontoLegal;
		double baseLegalRelatorio = Math.max(0, baseLegal);
		double impostoLegal = calcularIrTabela2026(baseLegal);
		String faixaLegal = faixaIr;
		
		double descontoSimplificado = DESCONTO_SIMPLIFICADO_PADRAO;
		double baseSimplificada = renda - descontoSimplificado;
		double baseSimplificadaRelatorio = Math.max(0, baseSimplificada);
		double impostoSimplificado = calcularIrTabela2026(baseSimplificada);
		String faixaSimplificada = faixaIr;
		
		if (impostoLegal < impostoSimplificado || 
				(impostoLegal == impostoSimplificado && descontoLegal > descontoSimplificado)) {
			baseUtilizada = baseLegalRelatorio;
			faixaIr = faixaLegal;
			tipoDescontoIr = String.format("Dedução calculada (INSS: R$ %,.2f + Dep.: R$ %,.2f): totalizando R$ %,.2f",
						inss, totalDep, descontoLegal);
			irCalculado = Math.max(0, impostoLegal);
			
		} else {
			baseUtilizada = baseSimplificadaRelatorio;
			faixaIr = faixaSimplificada;
			tipoDescontoIr = String.format("Desconto Simplificado R$ %,.2f", descontoSimplificado);
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
		sb.append("--------------------------------------------------------------------\n");
		sb.append("Pessoa Física\n\n");
		sb.append("Contribuinte\n");
		sb.append(String.format("%-20s %s\n", "Nome:", super.toString()));
		//sb.append(super.toString()).append("%-20s\n");
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
