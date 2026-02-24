package meusprojetosjava.ContaCorrente;

public class ContaCorrente {// Eclipse -> Github @guilhermeNetogit 24/02/2026 18:32:54
	
    String numero;
    String agencia;
    boolean especial;
    double limiteEspecial;
    double valorEspecialUsado;
    double saldo;

    boolean realizarSaque(double valorSaque) {
    	
    	if (saldo >= valorSaque) {
    		saldo -= valorSaque;
    		return true;
    		
    	} else { //não tem saldo na conta
    		if (especial) {
    			//verifica se o limite especial tem saldo
    			double limite = limiteEspecial + saldo;
    			if (limite >= valorSaque) {
    				saldo -= valorSaque;
    	    		return true;
    			} else {
    				return false;
    			}
    		} else {
    			return false;
    		}
    	}
    }
    
    void depositar(double valorDepositado){
        
        saldo += valorDepositado;
    }
    
    void consultarSaldo(){
    	System.out.println();
        System.out.println("Saldo atual da conta = " + saldo); 
    }
    
    boolean verificarUsoChequeEspecial(){
        return saldo < 0;
    }
}