package meusprojetosjava.notaaluno.aluno3;

import java.util.Arrays;
//import java.util.Objects;

public class Aluno3 {// Eclipse -> Github @guilhermeNetogit 11/03/2026 17:53:24

	private String curso;
	private double[] notas;

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public double[] getNotas() {
		return notas;
	}

	public void setNotas(double[] notas) {
		this.notas = notas;
	}

	@Override
	public String toString() {
		return "Nome do Aluno\n[curso=" + curso + ", notas=" + Arrays.toString(notas) + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;

		Aluno3 other = (Aluno3) obj;
		// return Objects.equals(curso, other.curso) && Arrays.equals(notas,
		// other.notas);

		if (curso.equalsIgnoreCase(other.getCurso())) {
			return true;
		}

		return false;
	}

}
