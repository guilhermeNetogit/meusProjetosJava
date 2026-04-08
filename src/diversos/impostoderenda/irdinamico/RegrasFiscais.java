package diversos.impostoderenda.irdinamico;

/**
 * Encapsula todas as regras fiscais de um ano-base específico.
 * Para adicionar um novo ano, basta criar um novo método estático de fábrica.
 *
 * @author GitHub guilhermeNetogit
 * @since 07/04/2026 20:03:56
 * @version 07/04/2026 20:03:56
 */

public class RegrasFiscais {
	
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
	public final double[][] faixasIr;

	private RegrasFiscais(int ano, double tetoInss, double deducaoDependente, 
							double descontoSimplificado,
							double[][] faixasInss, double[][] faixasIr) {
		this.ano = ano;
		this.tetoInss = tetoInss;
		this.deducaoDependente = deducaoDependente;
		this.descontoSimplificado = descontoSimplificado;
		this.faixasInss = faixasInss;
		this.faixasIr = faixasIr;
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
        double[][] ir = {
            { 2259.20,          0.000,    0.00  },
            { 2826.65,          0.075,  182.16  },
            { 3751.05,          0.150,  394.16  },
            { 4664.68,          0.225,  675.49  },
            { Double.MAX_VALUE, 0.275,  908.73  }
        };

        return new RegrasFiscais(2025, 8157.41, 189.59, 607.2, inss, ir);
    }
    
    /**
     * Regras fiscais para o ano-base 2026.
     * Salário: R$ 1621,00
     * Teto INSS: R$ 8.475,55
     * Isenção IR: até R$ 2.428,80
     */
    public static RegrasFiscais de2026() {

        // { limiteMaximo, aliquota, deducaoProgressiva }
        double[][] inss = {
            { 1621.00,          0.075,   0.00  },
            { 2902.84,          0.09,   24.32  },
            { 4354.27,          0.12,  111.40  },
            { 8475.55,          0.14,  198.49  }
        };

        // { limiteMaximo, aliquota, deducaoProgressiva }
        double[][] ir = {
            { 2428.80,          0.000,    0.00  },
            { 2826.65,          0.075,  182.16  },
            { 3751.05,          0.150,  394.16  },
            { 4664.68,          0.225,  675.49  },
            { Double.MAX_VALUE, 0.275,  908.73  }
        };

        return new RegrasFiscais(2026, 8475.55, 189.59, 607.20, inss, ir);
    }
    
    /**
     * Calcula o INSS sobre a renda bruta usando as faixas do ano configurado.
     * Renda acima do teto é truncada ao teto antes do cálculo.
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
     * Calcula o IR sobre a base de cálculo informada (já descontados INSS e deduções).
     *
     * @param baseCalculo base de cálculo do IR (após deduções)
     * @return valor do IR calculado (nunca negativo)
     */
    public double calcularIr(double baseCalculo) {

        for (double[] faixa : faixasIr) {
            double limite   = faixa[0];
            double aliquota = faixa[1];
            double deducao  = faixa[2];

            if (baseCalculo <= limite) {
                return Math.max(0, (baseCalculo * aliquota) - deducao);
            }
        }

        double[] ultima = faixasIr[faixasIr.length - 1];
        return Math.max(0, (baseCalculo * ultima[1]) - ultima[2]);
    }
    
    /**
     * Retorna o rótulo da faixa do IR para exibição no relatório.
     *
     * @param baseCalculo base de cálculo do IR
     * @return string com a alíquota da faixa (ex: "15%") ou "Isento"
     */
    public String faixaIr(double baseCalculo) {
    	
    	double base = Math.max(0, baseCalculo);

        for (double[] faixa : faixasIr) {
            if (base <= faixa[0]) {
                double aliquota = faixa[1];
                return (aliquota == 0.0) ? "Isento" : formatarAliquota(aliquota);
            }
        }

        double[] ultima = faixasIr[faixasIr.length - 1];
        return formatarAliquota(ultima[1]);
    }
    
    private String formatarAliquota(double aliquota) {
        // Ex: 0.075 -> "7,5%" | 0.15 -> "15%"
        double pct = aliquota * 100;
        if (pct == Math.floor(pct)) {
            return String.format("%.0f%%", pct);
        }
        return String.format("%.1f%%", pct).replace(".", ",");
    }

    @Override
    public String toString() {
        return String.format("RegrasFiscais[ano=%d, tetoINSS=%.2f, isenção IR=%.2f]",
                ano, tetoInss, faixasIr[0][0]);
    }
}
