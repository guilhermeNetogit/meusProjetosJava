package meusprojetosjava.veiculos.veiculos1;

public class Veiculos {// Eclipse -> Github @guilhermeNetogit 11/03/2026 13:38:16

	String marca;
	String modelo;
	int ano;
	int numPassageiros;
	double capTanqComb;
	double consumoCombustivelCid;
	double consumoCombustivelEst;
	double valor;
	private boolean msgAutonomiaExibir = false;

	void exibirAutonomia(String tipoRodagem, double consumo) {
		System.out.printf(
				"A autonomia do veículo na %s (via void) é: %.2f km\n", tipoRodagem, (capTanqComb * consumo));
	}

	double obterAutonomia(double consumo) {
		if (!msgAutonomiaExibir) {
			System.out.println("Método obterAutonomia foi chamado.");
			msgAutonomiaExibir = true;
		}
		return capTanqComb * consumo;
	}

	double calcularCombustivel(double km, double consumo) {
		return km / consumo;
	}

	double calcularQtdTanques(double combustivelNecessario) {
		return combustivelNecessario / capTanqComb;
	}

	double calcularCustoCombustivel(double litros, double valor) {
		return litros * valor;
	}

	// Array fixo (catálogo de combustíveis possíveis)
	public static final String[] TIPO_COMB = { "Gasolina", // [0]
			"Álcool", // [1]
			"Flex (Gasolina/Álcool)", // [2]
			"Diesel", // [3]
			"Elétrico", // [4]
			"Híbrido", // [5]
			"Gás" // [6]
	};

	// Array que guarda os combustíveis do carro específico
	String[] tipoComb;

	@Override
	public String toString() {

		String combustiveis = (tipoComb != null) ? String.join(", ", tipoComb) : "Não informado";

		return "Marca: " + marca + "\nModelo: " + modelo + "\nAno: " + ano + "\nValor (tabela FIPE): "
				+ String.format("R$ %,.2f", valor) + "\nPassageiros: " + numPassageiros + "\nTipo de Combustivel: "
				+ combustiveis + "\nCap. Tanque Comb.: " + capTanqComb + " lts"
				+ "\nConsumo Médio de Combustível na Cidade: " + consumoCombustivelCid + " km/l"
				+ "\nConsumo Médio de Combustível na Estrada: " + consumoCombustivelEst + " km/l";

	}
}
