package meusprojetosjava.exercicio46;

public class TesteFigurasGeometricas {

	public static void main(String[] args) {

		Quadrado f1 = new Quadrado();
		f1.setNome("Quadrado");
		f1.setCor("Vermelho");
		f1.setLado(4);

		Circulo f2 = new Circulo();
		f2.setNome("Círculo");
		f2.setCor("Amarelo");
		f2.setRaio(4);

		Triangulo f3 = new Triangulo();
		f3.setNome("Triângulo");
		f3.setCor("Preto");
		f3.setAltura(4);
		f3.setBase(3);

		Cubo f4 = new Cubo();
		f4.setNome("Cubo");
		f4.setCor("Verde");
		f4.setLado(4);

		Esfera f5 = new Esfera();
		f5.setNome("Esfera");
		f5.setCor("Azul");
		f5.setRaio(4);

		Piramide f6 = new Piramide();
		f6.setNome("Pirâmide");
		f6.setCor("Laranja");
		f6.setNumTriLat(4);
		f6.setAltura(3);
		f6.setApotema(4);
		f6.setArestaBase(4);
		f6.setBase(f1);

		FiguraGeometrica[] figura = new FiguraGeometrica[6];
		figura[0] = f1;
		figura[1] = f2;
		figura[2] = f3;
		figura[3] = f4;
		figura[4] = f5;
		figura[5] = f6;

		for (FiguraGeometrica f : figura) {

			System.out.println(f.gerarInfo());

			if (f instanceof Figura2D) {
				Figura2D f2d = (Figura2D) f;
				System.out.printf("%-8s %.6f\n", "Área:", f2d.calcularArea());
			}

			if (f instanceof Figura3D) {
				Figura3D f3d = (Figura3D) f;
				System.out.printf("%-8s %.6f%n", "Área:", f3d.calcularArea());
				System.out.printf("%-8s %.6f%n", "Volume:", f3d.calcularVolume());
			}

			System.out.println("---------------------------");
		}
	}
}
