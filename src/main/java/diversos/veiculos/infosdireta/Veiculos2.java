package diversos.veiculos.infosdireta;

import java.text.DecimalFormat;

public class Veiculos2 {// Eclipse -> Github @guilhermeNetogit 03/03/2026 22:24:06

	private String marca;
	private String modelo;
	private int ano;
	int numPassageiros;
	private double capTanqComb;
	private double consumoCombustivel;
	private double valor;
		
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getNumPassageiros() {
		return numPassageiros;
	}

	public void setNumPassageiros(int numPassageiros) {
		this.numPassageiros = numPassageiros;
	}

	public double getCapTanqComb() {
		return capTanqComb;
	}

	public void setCapTanqComb(double capTanqComb) {
		this.capTanqComb = capTanqComb;
	}

	public double getConsumoCombustivel() {
		return consumoCombustivel;
	}

	public void setConsumoCombustivel(double consumoCombustivel) {
		this.consumoCombustivel = consumoCombustivel;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Veiculos2() {
		System.out.println("Teste instancia Classe Veiculos2");
		numPassageiros = 23  ;
	}
	
	public Veiculos2(String marca, String modelo, int ano, int numPassageiros, String[] tipoComb, double capTanqComb,
			double consumoCombustivel, double valor) {
		
		this();
		
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
		this.numPassageiros = numPassageiros;
		this.tipoComb = tipoComb;
		this.capTanqComb = capTanqComb;
		this.consumoCombustivel = consumoCombustivel;
		this.valor = valor;
	}

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
