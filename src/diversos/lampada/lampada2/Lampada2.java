package diversos.lampada.lampada2;

public class Lampada2 {// VSCode -> Github @guilhermeNetogit 04/03/2026 19:58:36
    
    private double lumens;
    private String cor;
    private String tipo;
    private String potencia;
    private String tensao;
    private int garantiaMeses;
    private boolean ligada;

    
    public Lampada2() {
    }

    public Lampada2(double lumens, String cor, String tipo, String potencia, String tensao, int garantiaMeses, boolean ligada) {
        this.lumens = lumens;
        this.cor = cor;
        this.tipo = tipo;
        this.potencia = potencia;
        this.tensao = tensao;
        this.garantiaMeses = garantiaMeses;
        this.ligada = ligada;
    }
    
    public double getLumens() {
        return lumens;
    }

    public void setLumens(double lumens) {
        this.lumens = lumens;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPotencia() {
        return potencia;
    }

    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }

    public String getTensao() {
        return tensao;
    }

    public void setTensao(String tensao) {
        this.tensao = tensao;
    }

    public int getGarantiaMeses() {
        return garantiaMeses;
    }

    public void setGarantiaMeses(int garantiaMeses) {
        this.garantiaMeses = garantiaMeses;
    }

    public boolean isLigada() {
        return ligada;
    }

    public void setLigada(boolean ligada) {
        this.ligada = ligada;
    }

    public void ligar(){
        setLigada(true);

    }
    
    public void desligar(){
        setLigada(false);
    }
    
    public void mostrarEstado(){
        if (isLigada()){
            System.out.println("Lâmpada está ligada");
        } else {
            System.out.println("Lâmpada está desligada");
        }
    }
    
    public void mudarEstado(){
        if (isLigada()){
            desligar();
        } else {
            ligar();
        }
    }
}