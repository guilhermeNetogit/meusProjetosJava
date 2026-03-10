package meusprojetosjava.herenca;

public class Professor extends Pessoa {

	private String nomeCurso;
	private String cargo;
	private double salario;

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public double calcularSalarioLiquido() {
		return 0;
	}

	public String obterEtiquetaEndereco() {

		String s = "Endereço do Professor: ";
		s += this.getEndereco();

		return s;
	}
}
