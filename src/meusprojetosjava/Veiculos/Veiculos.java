package meusprojetosjava.Veiculos;

import java.text.DecimalFormat;

public class Veiculos {// Eclipse -> Github @guilhermeNetogit 24/02/2026 16:19:15

	String marca;
	String modelo;
	int ano;
	int numPassageiros;
	double capTanqComb;
	double consumoCombustivel;
	double valor;	
	
	private static final DecimalFormat df = new DecimalFormat("#.##");
		static {
			df.setDecimalSeparatorAlwaysShown(false);
		}

	void exibirAutonomia() {
		System.out.println("A autonomia do veículo (via void) é: " + capTanqComb * consumoCombustivel + " km");
	}

	double obterAutonomia() {
		System.out.println("Método obterAutonomia foi chamado.");
		return capTanqComb * consumoCombustivel;
	}

	double calcularCombustivel(double km) {
		return km/consumoCombustivel;
	}
	
	// Array fixo (catálogo de combustíveis possíveis)
	public static final String[] Tipo_Comb = { 
			"Gasolina",				  // [0]
			"Álcool", 				  // [1]
			"Flex (Gasolina/Álcool)", // [2]
			"Diesel", 				  // [3]
			"Elétrico", 			  // [4]
			"Híbrido", 				  // [5]
			"Gás" 		       		  // [6]
	};

	// Array que guarda os combustíveis do carro específico
	String[] tipoComb;

	@Override
	public String toString() {

		String combustiveis = (tipoComb != null) ? String.join(", ", tipoComb) : "Não informado";

		return "Marca: " + marca + "\nModelo: " + modelo + "\nAno: " + ano + "\nPassageiros: " + numPassageiros
				+ "\nTipo de Combustivel: " + combustiveis + "\nCap. Tanque Comb.: " + capTanqComb + " lts"
				+ "\nConsumo médio de Combustível: " + consumoCombustivel + " km/l" + "\nValor, segundo a tabela FIPE: "
				+ String.format("R$ %,.2f", valor);

	}
}
