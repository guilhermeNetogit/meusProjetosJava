package diversos.formasgeometricas;

public class Esfera extends Figura3D {

	private double raio;

	public double getRaio() {
		return raio;
	}

	public void setRaio(double raio) {
		this.raio = raio;
	}

	@Override
	public double calcularArea() {
		double area = 4 * Math.PI * Math.pow(raio, 2);
		return area;
	}

	@Override
	public double calcularVolume() {
		double volume = (4/3.0) * Math.PI * Math.pow(raio, 3);
		return volume;
	}
	@Override
	public String gerarInfo() {
	    return String.format("%s%-8s %.2f", super.gerarInfo(), "Raio:", raio);
	}
}
