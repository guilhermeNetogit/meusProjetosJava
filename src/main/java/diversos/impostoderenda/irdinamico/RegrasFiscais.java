package diversos.impostoderenda.irdinamico;

/**
 * Encapsula todas as regras fiscais de um ano-base específico.
 * Para adicionar um novo ano, basta criar um novo método estático de fábrica.
 *
 * @author GitHub guilhermeNetogit
 * @since 07/04/2026 20:03:56
 * @version 08/04/2026 13:43:56
 */

public class RegrasFiscais {
	
    @FunctionalInterface
    public interface CalculoIrEspecial {
        /**
         * @param rendaBruta  renda bruta do contribuinte (sem descontos)
         * @param baseCalculo base de cálculo do IR (após INSS e deduções)
         * @return valor do IR calculado (nunca negativo)
         */
        double calcular(double rendaBruta, double baseCalculo);
    }
	
    // -------------------------------------------------------------------------
    // Classe interna: representa uma faixa do IR
    // -------------------------------------------------------------------------
 
    /**
     * Uma faixa da tabela do IR.
     * Pode usar a fórmula padrão (aliquota * base - deducao)
     * ou uma fórmula especial via {@link CalculoIrEspecial}.
     */
    public static class FaixaIr {
 
        public final double limiteMaximo;
        public final double aliquota;
        public final double deducao;
        public final String rotulo;
        public final CalculoIrEspecial calculoEspecial; // null = usa fórmula padrão
 
        /** Construtor para faixa com fórmula padrão. */
        public FaixaIr(double limiteMaximo, double aliquota, double deducao) {
            this.limiteMaximo    = limiteMaximo;
            this.aliquota        = aliquota;
            this.deducao         = deducao;
            this.rotulo          = (aliquota == 0.0) ? "Isento" : formatarPct(aliquota);
            this.calculoEspecial = null;
        }
 
        /** Construtor para faixa com fórmula especial (rótulo customizado). */
        public FaixaIr(double limiteMaximo, double aliquota, String rotulo,
                       CalculoIrEspecial calculoEspecial) {
            this.limiteMaximo    = limiteMaximo;
            this.aliquota        = aliquota;
            this.deducao         = 0;
            this.rotulo          = rotulo;
            this.calculoEspecial = calculoEspecial;
        }
 
        /** Construtor para faixa isenta com rótulo customizado (sem cálculo especial). */
        public FaixaIr(double limiteMaximo, double aliquota, String rotulo) {
            this.limiteMaximo    = limiteMaximo;
            this.aliquota        = aliquota;
            this.deducao         = 0;
            this.rotulo          = rotulo;
            this.calculoEspecial = null;
        }
 
        public boolean temCalculoEspecial() {
            return calculoEspecial != null;
        }
 
        private static String formatarPct(double aliquota) {
            double pct = Math.round(aliquota * 100 * 10.0) / 10.0;
            if (pct == Math.floor(pct)) return String.format("%.0f%%", pct);
            return String.format("%.1f%%", pct).replace(".", ",");
        }
    }
    
	public final int    ano;
    public final double tetoInss;
    public final double deducaoDependente;
    public final double descontoSimplificado;

    /**
     * Faixas do INSS — cálculo progressivo por dentro.
     * Cada linha: { limiteMaximo, aliquota, deducaoProgressiva }
     * Última faixa usa Double.MAX_VALUE como teto (acima do teto, usa a última faixa).
     *
     * Fórmula:  inss = Math.min(base, teto) * aliquota - deducaoProgressiva
     */
    public final double[][] faixasInss;

    /**
     * Faixas do IR — cálculo progressivo por dentro.
     * Cada linha: { limiteMaximo, aliquota, deducaoProgressiva }
     * Última faixa usa Double.MAX_VALUE.
     *
     * Fórmula:  ir = base * aliquota - deducaoProgressiva  (resultado >= 0)
     */
	public final FaixaIr[] tabelaIr;

	private RegrasFiscais(int ano, double tetoInss, double deducaoDependente, double descontoSimplificado,
			double[][] faixasInss, FaixaIr[] tabelaIr) {
		this.ano = ano;
		this.tetoInss = tetoInss;
		this.deducaoDependente = deducaoDependente;
		this.descontoSimplificado = descontoSimplificado;
		this.faixasInss = faixasInss;
		this.tabelaIr = tabelaIr;
	}
	
	/**
     * Regras fiscais para o ano-base 2025.
     * Salário: R$ 1518,00
     * Teto INSS: R$ 8.157,41
     * Isenção IR: até R$ 2.259,20
     */
    public static RegrasFiscais de2025() {

        // { limiteMaximo, aliquota, deducaoProgressiva }
        double[][] inss = {
            { 1518.00,          0.075,   0.00  },
            { 2793.88,          0.09,   22.77  },
            { 4190.83,          0.12,  106.59  },
            { 8157.41,          0.14,  190.40  }
        };

        // { limiteMaximo, aliquota, deducaoProgressiva }
        FaixaIr[] ir = {
            new FaixaIr( 2259.20,          0.000,    0.00  ),
            new FaixaIr( 2826.65,          0.075,  182.16  ),
            new FaixaIr( 3751.05,          0.150,  394.16  ),
            new FaixaIr( 4664.68,          0.225,  675.49  ),
            new FaixaIr( Double.MAX_VALUE, 0.275,  908.73  )
        };

        return new RegrasFiscais(2025, 8157.41, 189.59, 607.2, inss, ir);
    }
    
    /**
     * Regras fiscais para o ano-base 2026 — nova tabela com faixa de transição.
     * Salário: R$ 1621,00
     * Teto INSS: R$ 8.475,55
     * Isenção IR: até R$ 5000,00
     * <p>
     * Para renda bruta > R$ 7.350,00, o cálculo do IR passa a ser o mesmo da tabela de 2025
     * (progressiva normal, utilizando a base de cálculo como referência).
     * <p>
     * Para renda bruta entre R$ 5.000,01 e R$ 7.350,00, aplica-se a fórmula de transição:
     * IR = max(0, IR_pela_tabela_2025(baseCalculo) - (978,62 - 0,133145 × rendaBruta))
     */
    public static RegrasFiscais de2026() {

        // { limiteMaximo, aliquota, deducaoProgressiva }
        double[][] inss = {
            { 1621.00,          0.075,   0.00  },
            { 2902.84,          0.09,   24.32  },
            { 4354.27,          0.12,  111.40  },
            { 8475.55,          0.14,  198.49  }
        };

        // Fórmula de transição correta:
        // 1. Calcula o IR normalmente conforme a tabela de 2025 (progressiva)
        // 2. Subtrai o redutor (978,62 - 0,133145 * rendaBruta)
        CalculoIrEspecial formulaTransicao = (rendaBruta, baseCalculo) -> {
        	double irNormal = calcularIrPelaTabela2025(baseCalculo);
        	double redutor = 978.62 - 0.133145 * rendaBruta;
            return Math.max(0, irNormal - redutor);
        };
        
        // { limiteMaximo, aliquota, deducaoProgressiva }
        // Faixas do IR para renda bruta ≤ 7350
        FaixaIr[] ir = {
            new FaixaIr( 5000.00,          0.000, "Isento" ),
            new FaixaIr( 7350.00,          0.275, "27,5% (transição)", formulaTransicao )
        };

		return new RegrasFiscais(2026, 8475.55, 189.59, 607.20, inss, ir);
	}
    
    private static FaixaIr[] obterFaixas2025() {
        return new FaixaIr[] {
            new FaixaIr( 2259.20,          0.000,    0.00  ),
            new FaixaIr( 2826.65,          0.075,  182.16  ),
            new FaixaIr( 3751.05,          0.150,  394.16  ),
            new FaixaIr( 4664.68,          0.225,  675.49  ),
            new FaixaIr( Double.MAX_VALUE, 0.275,  908.73  )
        };
    }

	/**
     * Calcula o IR conforme a tabela progressiva de 2025 (sem nenhum redutor extra).
     * @param baseCalculo base de cálculo do IR (após INSS e deduções)
     * @return valor do IR calculado (nunca negativo)
     */
    private static double calcularIrPelaTabela2025(double baseCalculo) {
        double base = Math.max(0, baseCalculo);
        FaixaIr[] faixas = obterFaixas2025();

        for (FaixaIr faixa : faixas) {
            if (base <= faixa.limiteMaximo) {
                if (faixa.aliquota == 0.0)
                    return 0;
                return Math.max(0, (base * faixa.aliquota) - faixa.deducao);
            }
        }
        // fallback (não deve ocorrer)
        FaixaIr ultima = faixas[faixas.length - 1];
        return Math.max(0, (base * ultima.aliquota) - ultima.deducao);
    }
    
    /**
     * Retorna a alíquota nominal da tabela de 2025 para uma dada base de cálculo.
     * Ex.: 0.075, 0.15, 0.225, 0.275.
     */
    private static double obterAliquotaNominal2025(double baseCalculo) {
        double base = Math.max(0, baseCalculo);
        for (FaixaIr faixa : obterFaixas2025()) {
            if (base <= faixa.limiteMaximo) {
                return faixa.aliquota;
            }
        }
        return obterFaixas2025()[obterFaixas2025().length - 1].aliquota;
    }
    
	/**
	 * Calcula o INSS sobre a renda bruta usando as faixas do ano configurado. Renda
	 * acima do teto é truncada ao teto antes do cálculo.
	 *
	 * @param rendaBruta renda bruta mensal do contribuinte
	 * @return valor do INSS calculado
	 */
	public double calcularInss(double rendaBruta) {

		// Caso especial: abaixo do salário mínimo da primeira faixa
		if (rendaBruta > 0 && rendaBruta < faixasInss[0][0]) {
			return rendaBruta * faixasInss[0][1]; // 7,5% sem deducao progressiva
		}

		double base = Math.min(rendaBruta, tetoInss);

        for (double[] faixa : faixasInss) {
            double limite    = faixa[0];
            double aliquota  = faixa[1];
            double deducao   = faixa[2];

			if (base <= limite) {
				return (base * aliquota) - deducao;
			}
		}

		// Fallback: aplica a última faixa (não deve ocorrer com Double.MAX_VALUE)
		double[] ultima = faixasInss[faixasInss.length - 1];
		return (base * ultima[1]) - ultima[2];
	}

	/**
	 * Retorna o rótulo da faixa do INSS para exibição no relatório.
	 *
	 * @param rendaBruta renda bruta mensal do contribuinte
	 * @return string com a alíquota da faixa (ex: "9%")
	 */
	public String faixaInss(double rendaBruta) {

		if (rendaBruta > 0 && rendaBruta < faixasInss[0][0]) {
			return formatarAliquota(faixasInss[0][1]) + " (abaixo do mínimo)";
		}

		double base = Math.min(rendaBruta, tetoInss);

		for (double[] faixa : faixasInss) {
			if (base <= faixa[0]) {
				return formatarAliquota(faixa[1]);
			}
		}

		double[] ultima = faixasInss[faixasInss.length - 1];
		return formatarAliquota(ultima[1]);
	}

	/**
	 * Calcula o IR sobre a base de cálculo informada (já descontados INSS e
	 * deduções).
	 *
	 * @param baseCalculo base de cálculo do IR (após deduções)
	 * @return valor do IR calculado (nunca negativo)
	 */
	public double calcularIr(double rendaBruta, double baseCalculo) {

		// Regra especial para 2026: se renda bruta > 7350, usa tabela de 2025
		if (ano == 2026 && rendaBruta > 7350.0) {
            return calcularIrPelaTabela2025(baseCalculo);
        }
		
		double chave = (ano >= 2026) ? rendaBruta : Math.max(0, baseCalculo);
		double base = Math.max(0, baseCalculo);

		for (FaixaIr faixa : tabelaIr) {
			if (chave <= faixa.limiteMaximo) {
				if (faixa.aliquota == 0.0)
					return 0;
				if (faixa.temCalculoEspecial())
					return faixa.calculoEspecial.calcular(rendaBruta, base);
				return Math.max(0, (base * faixa.aliquota) - faixa.deducao);
			}
		}

		FaixaIr ultima = tabelaIr[tabelaIr.length - 1];
		return Math.max(0, (base * ultima.aliquota) - ultima.deducao);
	}
	
	/**
     * Retorna o valor do IR bruto (antes do redutor de transição).
     * - Para ano 2026 com renda entre 5000 e 7350, retorna o IR calculado pela tabela 2025.
     * - Para os demais casos, retorna o mesmo valor que calcularIr().
     */
    public double calcularIrBruto(double rendaBruta, double baseCalculo) {
        if (ano == 2026 && rendaBruta > 5000.0 && rendaBruta <= 7350.0) {
            return calcularIrPelaTabela2025(baseCalculo);
        }
        // Para os demais casos (incluindo >7350 e anos diferentes), o IR bruto é o próprio IR final
        return calcularIr(rendaBruta, baseCalculo);
    }
	
    /**
     * Retorna o valor do redutor aplicado na faixa de transição de 2026.
     * Fora da faixa de transição, retorna 0.
     */
    public double obterRedutorTransicao(double rendaBruta) {
        if (ano == 2026 && rendaBruta > 5000.0 && rendaBruta <= 7350.0) {
            double redutor = 978.62 - 0.133145 * rendaBruta;
            return Math.max(0, redutor); // o redutor nunca deve ser negativo
        }
        return 0.0;
    }
	
	/**
	 * Retorna o rótulo da faixa do IR para exibição no relatório.
	 *
	 * @param baseCalculo base de cálculo do IR
	 * @return string com a alíquota da faixa (ex: "15%") ou "Isento"
	 */
	public String faixaIr(double rendaBruta, double baseCalculo) {
		
		// Caso especial: ano 2026, renda na faixa de transição (5000 < renda <= 7350)
        if (ano == 2026 && rendaBruta > 5000.0 && rendaBruta <= 7350.0) {
            double aliquotaNominal = obterAliquotaNominal2025(baseCalculo);
            String pct = formatarAliquota(aliquotaNominal);
            return pct + " (transição)";
        }
		
		// Regra especial para 2026: se renda bruta > 7350, usa tabela de 2025
        if (ano == 2026 && rendaBruta > 7350.0) {
            return faixaIrComTabela2025(baseCalculo);
        }

		double chave = (ano >= 2026) ? rendaBruta : Math.max(0, baseCalculo);

		for (FaixaIr faixa : tabelaIr) {
			if (chave <= faixa.limiteMaximo)
				return faixa.rotulo;
		}

		return tabelaIr[tabelaIr.length - 1].rotulo;
	}

	/**
     * Retorna o rótulo da faixa conforme a tabela de 2025.
     */
    private String faixaIrComTabela2025(double baseCalculo) {
        double base = Math.max(0, baseCalculo);
        FaixaIr[] faixas2025 = obterFaixas2025();

        for (FaixaIr faixa : faixas2025) {
            if (base <= faixa.limiteMaximo)
                return faixa.rotulo;
        }
        return faixas2025[faixas2025.length - 1].rotulo;
    }
	
	private String formatarAliquota(double aliquota) {
		double pct = Math.round(aliquota * 100 * 10.0) / 10.0;
		if (pct == Math.floor(pct))
			return String.format("%.0f%%", pct);
		return String.format("%.1f%%", pct).replace(".", ",");
	}

	@Override
	public String toString() {
		return String.format("RegrasFiscais[ano=%d, tetoINSS=%.2f]", ano, tetoInss);
	}
}
