package meusprojetosjava.heranca;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TesteHerenca {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {

		Constantes url = new Constantes();
		
		// Professor professor = new Professor();

		Pessoa pessoa = new Pessoa();
		Pessoa aluno = new Aluno();
		Pessoa professor = new Professor();

		Aluno aluno2 = new Aluno();
		aluno.setNome("João Pedro Oliveira");

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date data = sdf.parse("10-03-2000");
			aluno.setDtnasc(data);
		} catch (Exception e) {
			e.printStackTrace();
		}

		pessoa.setEndereco("Rua A, 123, Jd. Planalto");
		aluno.setEndereco("Rua B, 456, Sete Colinas");
		professor.setEndereco("Rua C, 789, Centro");

		aluno2.metodoTeste();

		SimpleDateFormat sdfSaida = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println(
				"Nome Aluno: " + aluno.getNome() + "\n" + "Data de Nascimento: " + sdfSaida.format(aluno.getDtnasc())
						+ "\n" + "CPF: " + aluno2.getCpf() + "\n" + aluno.obterEtiquetaEndereco() + "\n");

		System.out.println("Pessoa: " + pessoa.obterEtiquetaEndereco());
		System.out.println(aluno.obterEtiquetaEndereco());
		System.out.println(professor.obterEtiquetaEndereco());
		
		System.out.println();
		System.out.println(url.URL_SITE);
	}

}
