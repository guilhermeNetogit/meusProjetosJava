package meusprojetosjava.veiculos.veiculos1;

public class Veiculos {// Eclipse -> Github @guilhermeNetogit 23/03/2026 21:26:00

	String marca;
	String modelo;
	int ano;
	int numPassageiros;
	double capTanqComb;
	double consumoGasolinaCid;
	double consumoGasolinaEst;
	double consumoAlcoolCid;
	double consumoAlcoolEst;
	double valor;
	private boolean msgAutonomiaExibir = false;

	void exibirAutonomia(String tipoRodagem, double consumo) {
		System.out.printf("\nA autonomia do veículo na %s (via void) é: %.2f km\n", tipoRodagem,
				(capTanqComb * consumo));
	}

	double calcularQtdTanques(double combustivelNecessario) {
		return combustivelNecessario / capTanqComb;
	}

	// ========== Métodos para a Gasolina ==========
	double obterAutonomiaGasolina(double consumoGasolina) {
		if (!msgAutonomiaExibir) {
			System.out.println("Método obterAutonomia foi chamado.");
			msgAutonomiaExibir = true;
		}
		return capTanqComb * consumoGasolina;
	}

	double calcularCombustivelGasolina(double km, double consumo) {
		return km / consumo;
	}

	double calcularCustoGasolina(double litros, double valorGasolina) {
		return litros * valorGasolina;
	}

	// ========== Métodos para o Álcool ==========
	double obterAutonomiaAlcool(double consumoAlcool) {
		return capTanqComb * consumoAlcool;
	}

	double calcularCombustivelAlcool(double km, double consumo) {
		return km / consumo;
	}

	double calcularCustoAlcool(double litros, double valorAlcool) {
		return litros * valorAlcool;
	}

	// ========== Método de Comparação ==========

	/**
	 * Compara custos entre gasolina e álcool e retorna recomendação
	 */
	String compararCustos(double km, double valorGasolina, double valorAlcool, String tipoRodagem,
			double consumoGasolina, double consumoAlcool) {
		double litrosGasolina = calcularCombustivelGasolina(km, consumoGasolina);
		double litrosAlcool = calcularCombustivelAlcool(km, consumoAlcool);

		double custoGasolina = calcularCustoGasolina(litrosGasolina, valorGasolina);
		double custoAlcool = calcularCustoAlcool(litrosAlcool, valorAlcool);

		double economia = Math.abs(custoGasolina - custoAlcool);
		String maisBarato = (custoAlcool < custoGasolina) ? "ÁLCOOL" : "GASOLINA";

		return String.format(
            "\n╔════════════════════════════════════════════════╗" +
            "\n║     COMPARATIVO DE CUSTOS - %s" +
            "\n╠════════════════════════════════════════════════╣" +
            "\n║ Gasolina: R$ %,.2f (%d litros)" +
            "\n║ Álcool:   R$ %,.2f (%d litros)" +
            "\n╠════════════════════════════════════════════════╣" +
            "\n║ RECOMENDAÇÃO: Abasteça com %s" +
            "\n║ Economia: R$ %,.2f" +
            "\n╚════════════════════════════════════════════════╝",
				tipoRodagem.toUpperCase(), custoGasolina, (int) Math.ceil(litrosGasolina), custoAlcool,
				(int) Math.ceil(litrosAlcool), maisBarato, economia);
	}

	// Array fixo (catálogo de combustíveis possíveis)
	public static final String[] TIPO_COMB = {
			"Gasolina",				  // [0]
			"Álcool",				  // [1]
			"Flex (Gasolina/Álcool)", // [2]
			"Diesel",				  // [3]
			"Elétrico",				  // [4]
			"Híbrido",				  // [5]
			"Gás"					  // [6]
	};

	// Array que guarda os combustíveis do carro específico
	String[] tipoComb;

	@Override
	public String toString() {

		String combustiveis = (tipoComb != null) ? String.join(", ", tipoComb) : "Não informado";

		String s = String.format(
				  "\n┌────────────────────────────────────────┐" 
				+ "\n│  Marca:  %-29s │"
				+ "\n│  Modelo: %-29s │"
				+ "\n│  Ano:    %-29d │"
				+ "\n│  Passageiros: %-24d │"
				+ "\n│  Valor FIPE: R$ %,-22.2f │"
				+ "\n│  Combustivel: %-24s │"
				+ "\n│  Tanque: %-7.2f litros                │"
				+ "\n├────────────────────────────────────────┤"
				+ "\n│  Consumo Médio                         │" 
				+ "\n│    Gasolina Cidade:  %5.2f km/l        │"
				+ "\n│    Gasolina Estrada: %5.2f km/l        │"
				+ "\n│    Álcool Cidade:    %5.2f km/l        │"
				+ "\n│    Álcool Estrada:   %5.2f km/l        │"
				+ "\n└────────────────────────────────────────┘"
				, marca, modelo, ano, numPassageiros, valor,  combustiveis, capTanqComb
				, consumoGasolinaCid, consumoGasolinaEst, consumoAlcoolCid, consumoAlcoolEst);

		return (s);
	}
}
