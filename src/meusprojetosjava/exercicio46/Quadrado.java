package meusprojetosjava.exercicio46;

public class Quadrado extends Figura2D {

	private double lado;

	public double getLado() {
		return lado;
	}

	public void setLado(double lado) {
		this.lado = lado;
	}

	@Override
	public double calcularArea() {
		double area = Math.pow(lado, 2);
		return area;
	}
	
	@Override
	public String gerarInfo() {
	    return String.format("%s%-8s %.2f", super.gerarInfo(), "Lado:", lado);
	}

}
