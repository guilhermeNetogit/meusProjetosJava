package meusprojetosjava.Veiculos;

public class Veiculos {// Eclipse -> Github @guilhermeNetogit 24/02/2026 11:59:33
	
	String marca;
	String modelo;
	int ano;
	int numPassageiros;
	double capTanqComb;
	double consumoCombustivel;
	
    // Array fixo (catálogo de combustíveis possíveis)
    public static final String[] Tipo_Comb = {
           "Gasolina",               // [0]
           "Álcool",                 // [1]
           "Flex (Gasolina/Álcool)", // [2]
           "Diesel",                 // [3]
           "Elétrico",               // [4]
           "Híbrido",                // [5]
           "Gás"                     // [6]
    };
    
    // Array que guarda os combustíveis do carro específico	
	String[] tipoComb;
	
	@Override
	public String toString() {
		
	    String combustiveis = (tipoComb != null)
	            ? String.join(", ", tipoComb)
	            : "Não informado";
		
		return "Marca: " + marca +
				"\nModelo: " + modelo +
				"\nAno: " + ano +
				"\nPassageiros: " + numPassageiros + 
				"\nTipo de Combustivel: " + combustiveis +
				"\nCap. Tanque Comb.: " + capTanqComb + " lts" + 
				"\nConsumo médio de Combustível: " + consumoCombustivel + " km/l";
				
	}
}
