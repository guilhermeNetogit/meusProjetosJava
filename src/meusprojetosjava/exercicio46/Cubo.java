package meusprojetosjava.exercicio46;

public class Cubo extends Figura3D{
	
	private double lado;

	public double getLado() {
		return lado;
	}

	public void setLado(double lado) {
		this.lado = lado;
	}

	@Override
	public double calcularArea() {
		double area = 6 * Math.pow(lado, 2);
		return area;
	}

	@Override
	public double calcularVolume() {
		double volume = Math.pow(lado, 3);
		return volume;
	}
	@Override
	public String gerarInfo() {
	    return String.format("%s%-8s %.2f", super.gerarInfo(), "Lado:", lado);
	}
}
