package meusprojetosjava.exercicio46;

public class Triangulo extends Figura2D {

	private double base;
	private double altura;

	public double getBase() {
		return base;
	}

	public void setBase(double base) {
		this.base = base;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	@Override
	public double calcularArea() {
		double area = (base * altura) / 2;
		return area;
	}

	@Override
	public String gerarInfo() {
		return String.format("%s%-8s %.2f\n%-8s %.2f", super.gerarInfo(), "Base:", base, "Altura:", altura);
	}
}
