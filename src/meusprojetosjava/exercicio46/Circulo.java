package meusprojetosjava.exercicio46;

public class Circulo extends Figura2D {
	
	private double raio;

	public double getRaio() {
		return raio;
	}

	public void setRaio(double raio) {
		this.raio = raio;
	}

	@Override
	public double calcularArea() {
		double area = Math.PI * Math.pow(raio, 2);
		return area;
	}
	@Override
	public String gerarInfo() {
	    return String.format("%s%-8s %.2f", super.gerarInfo(), "Raio:", raio);
	}
}
