package diversos.castinginstanceof;

import java.util.Date;

public class Aluno extends Pessoa {

	private String curso;
	private double[] notas;

	public void verificaAcesso() {

		super.nomeVisivel = "TesteNome";
		this.nomeVisivel = "TesteNome2";
	}

	public Aluno() {
		super();
	}

	public Aluno(String nome, Date dtnasc, String endereco, String telefone, String curso) {
		super(nome, dtnasc, endereco, telefone);
		this.curso = curso;
	}

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

	public double calcularMedia() {
		return 0;
	}

	public boolean aprovacao() {
		return true;
	}

	public void metodoTeste() {
		super.setCpf("98074558897");
		// this.setCpf("70966799447");
	}

	public String obterEtiquetaEndereco() {

		String s = "Endereço do Aluno: ";
		s += this.getEndereco();

		return s;
	}
}
