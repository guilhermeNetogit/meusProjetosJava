package meusprojetosjava.Lampada;

public class Lampada {// Eclipse -> Github @guilhermeNetogit 24/02/2026 16:54:36
        
    boolean ligada;
    
    void ligar(){
        ligada = true;
    }
    
    void desligar(){
        ligada = false;
    }
    
    void mostrarEstado(){
        if (ligada){
            System.out.println("Lâmpada está ligada");
        } else {
            System.out.println("Lâmpada está desligada");
        }
    }
    
    void mudarEstado(){
        if (ligada){
            desligar();
        } else {
            ligar();
        }
    }
}