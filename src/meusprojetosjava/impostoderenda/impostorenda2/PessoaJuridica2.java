package meusprojetosjava.impostoderenda.impostorenda2;

public class PessoaJuridica2 extends Contribuinte2 {// Eclipse -> Github @guilhermeNetogit 16/03/2026 20:43:41

	private String cnpj;
	private String regimeTributario;
	private final double tetoMensalAdicional = 20000.00;

	private double baseCalculo;
	private double irpjBase;
	private double irpjAdicional;
	private double csll;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRegimeTributario() {
		return regimeTributario;
	}

	public void setRegimeTributario(String regime) {
		this.regimeTributario = regime;
	}

	@Override
	public double calcularIR() {
		double faturamento = getRendaBruta();
		// Se ninguém definiu o regime na Main, assume "Lucro Presumido"
	    if (this.regimeTributario == null) {
	        this.regimeTributario = "Lucro Presumido";
	    }


		if ("Simples Nacional".equalsIgnoreCase(regimeTributario)) {
			calcularSimplesNacional(faturamento);
		} else {
			double presuncao = "Lucro Arbitrado".equalsIgnoreCase(regimeTributario) ? 0.384 : 0.32;
			this.baseCalculo = faturamento * presuncao;
			this.irpjBase = baseCalculo * 0.15;
			this.csll = baseCalculo * 0.09;
			double excesso = baseCalculo - tetoMensalAdicional;
			this.irpjAdicional = (excesso > 0) ? excesso * 0.10 : 0;
		}
		return irpjBase + irpjAdicional + csll;
	}

	private void calcularSimplesNacional(double faturamento) {
		double aliquota = (faturamento <= 15000) ? 0.06 : (faturamento <= 30000) ? 0.112 : 0.135;
		this.irpjBase = faturamento * aliquota;
		this.irpjAdicional = 0;
		this.csll = 0;
		this.baseCalculo = faturamento;
	}

	private double calcularImpostoPorRegime(String regime, double faturamento) {
		if (faturamento <= 0)
			return 0;
		if ("Simples Nacional".equalsIgnoreCase(regime)) {
			double aliquota = (faturamento <= 15000) ? 0.06 : (faturamento <= 30000) ? 0.112 : 0.135;
			return faturamento * aliquota;
		}
		double presuncao = "Lucro Arbitrado".equalsIgnoreCase(regime) ? 0.384 : 0.32;
		double base = faturamento * presuncao;
		double excesso = base - tetoMensalAdicional;
		return (base * 0.15) + (base * 0.09) + ((excesso > 0) ? excesso * 0.10 : 0);
	}

	public String gerarRelatorio() {
		double fat = getRendaBruta();

		double impSimples = calcularImpostoPorRegime("Simples Nacional", fat);
		double impPresumido = calcularImpostoPorRegime("Lucro Presumido", fat);
		double impReal = calcularImpostoPorRegime("Lucro Real", fat);
		double impArbitrado = calcularImpostoPorRegime("Lucro Arbitrado", fat);

		double melhor = Math.min(Math.min(impSimples, impPresumido), Math.min(impReal, impArbitrado));
		double pior = Math.max(Math.max(impSimples, impPresumido), Math.max(impReal, impArbitrado));

		// 1. Identificamos o nome exato do vencedor
		String vencedor;
		if (melhor == impSimples) vencedor = "Simples Nacional";
		else if (melhor == impArbitrado) vencedor = "Lucro Arbitrado";
		else vencedor = "Lucro Presumido/Real";
		
		regimeTributario = vencedor;
		
		String veredito = vencedor.toUpperCase();
		
		StringBuilder sb = new StringBuilder();
		sb.append("==========================================================\n");
		sb.append("      COMPARATIVO DE PLANEJAMENTO TRIBUTARIO (PJ)\n");
		sb.append("==========================================================\n");
		sb.append("Empresa: ").append(getNome()).append("\n");
		sb.append("CNPJ: ").append(getCnpj()).append("\n");
		sb.append(String.format("Faturamento: R$ %,.2f\n\n", fat));

		// CABEÇALHO SEM STRING.FORMAT PARA EVITAR ERRO
		sb.append("REGIME                 | IMPOSTO        | ALIQ. %\n");
		sb.append("----------------------------------------------------------\n");

		// LINHAS DA TABELA (Usando printf style fixo)
		sb.append(String.format("Simples Nacional       | R$ %,11.2f | %.2f%%\n", impSimples, (fat > 0 ? (impSimples / fat) * 100 : 0)));
		sb.append(String.format("Lucro Presumido        | R$ %,11.2f | %.2f%%\n", impPresumido, (fat > 0 ? (impPresumido / fat) * 100 : 0)));
		sb.append(String.format("Lucro Real             | R$ %,11.2f | %.2f%%\n", impReal, (fat > 0 ? (impReal / fat) * 100 : 0)));
		sb.append(String.format("Lucro Arbitrado        | R$ %,11.2f | %.2f%%\n", impArbitrado, (fat > 0 ? (impArbitrado / fat) * 100 : 0)));

		sb.append("----------------------------------------------------------\n");
		sb.append(">>> VEREDITO: O melhor é o ").append(veredito).append("\n");
		sb.append(String.format(">>> ECONOMIA MENSAL: R$ %,.2f\n", (pior - melhor)));
		sb.append("==========================================================\n");

		double totalEscolhido = calcularIR();
		sb.append("\nDETALHAMENTO DO REGIME ATUAL (").append(regimeTributario).append("):\n");
		if (!"Simples Nacional".equalsIgnoreCase(regimeTributario)) {
			sb.append(String.format(" - Base: R$ %,.2f\n", baseCalculo));
			sb.append(String.format(" - IRPJ: R$ %,.2f | CSLL: R$ %,.2f\n", irpjBase, csll));
			if (irpjAdicional > 0) {
				sb.append(String.format(" - Adicional (10%%): R$ %,.2f\n", irpjAdicional));
			}
		} else {
			sb.append(String.format(" - Guia Unica (DAS): R$ %,.2f\n", irpjBase));
		}
		sb.append(String.format("TOTAL A PAGAR: R$ %,.2f\n", totalEscolhido));
		sb.append(String.format("----------------------------------------------------------"));

		return sb.toString();
	}
}