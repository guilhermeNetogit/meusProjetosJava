package diversos.formasgeometricas;

public class Piramide extends Figura3D {

	private double altura;
	private double arestaBase;
	private double apotema;
	private double numTriLat;

	private Figura2D base;

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public double getArestaBase() {
		return arestaBase;
	}

	public void setArestaBase(double arestaBase) {
		this.arestaBase = arestaBase;
	}

	public double getApotema() {
		return apotema;
	}

	public void setApotema(double apotema) {
		this.apotema = apotema;
	}

	public Figura2D getBase() {
		return base;
	}

	public void setBase(Figura2D base) {
		this.base = base;
	}

	public double getNumTriLat() {
		return numTriLat;
	}

	public void setNumTriLat(double numTriLat) {
		this.numTriLat = numTriLat;
	}

	@Override
	public double calcularArea() {
	    if (base != null) {
	        double valorAresta = (this.arestaBase > 0) ? this.arestaBase : 0;
	        
	        // Verifica se a base é um Quadrado para pegar o lado
	        if (valorAresta == 0 && base instanceof Quadrado) {
	            valorAresta = ((Quadrado) base).getLado();
	        } 
	        // Se no futuro tiver Triangulo como base, você adiciona outro 'else if' aqui
	        
	        double areaLateral = numTriLat * ((valorAresta * apotema) / 2);
	        return areaLateral + base.calcularArea();
	    }
	    return 0;
	}

	@Override
	public double calcularVolume() {

		if (base != null) {
			return (base.calcularArea() * altura) / 3.0; 
		}
		return 0;
	}
	
	@Override
	public String gerarInfo() {
	    StringBuilder sb = new StringBuilder();
	    
	    // Traz Nome e Cor da Pirâmide (da classe FiguraGeometrica)
	    sb.append(super.gerarInfo()).append("");
	    
	    // Dados da Pirâmide
	    sb.append(String.format("%-8s %.2f\n", "Altura:", altura));
	    sb.append(String.format("%-8s %.2f\n", "Apotema:", apotema));
	    
	    // Lógica da Base
	    if (base != null) {
	        sb.append(String.format("%-8s %s\n", "Base:", base.getNome()));
	        
	        if (base instanceof Quadrado) {
	            sb.append(String.format("%-8s %.2f", "Lado:", ((Quadrado) base).getLado()));
	        } else if (base instanceof Circulo) {
	            sb.append(String.format("%-8s %.2f", "Raio:", ((Circulo) base).getRaio()));
	        } else if (base instanceof Triangulo) {
	            sb.append(String.format("%-8s %.2f", "Base T:", ((Triangulo) base).getBase()));
	        }
	    }
	    
	    return sb.toString();
	}


}
