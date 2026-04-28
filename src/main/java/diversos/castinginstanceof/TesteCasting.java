package diversos.castinginstanceof;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TesteCasting {

	public static void main(String[] args) {

		// Upcasting (implícito ou explícito)
		Professor professor2 = new Professor();
		Pessoa pessoaProfessor = professor2; // upcasting implícito
		Pessoa aluno3 = new Aluno(); // upcasting implícito

		// Instâncias para testes
		Pessoa pessoa = new Pessoa();
		Pessoa alunoRef = new Aluno(); // referência tipo Pessoa apontando para Aluno
		Pessoa professor = new Professor();

		// Downcasting seguro com pattern matching (Java 14+) ou clássico
		if (alunoRef instanceof Aluno aluno) { // Java 14+ (recomendado)
			aluno.setNome("João Pedro Oliveira");
			// Aqui você pode usar 'aluno' diretamente (já está castado)

			// Exemplo: chamar métodos específicos de Aluno
			aluno.metodoTeste(); // se existir esse método na classe Aluno
		} else {
			System.out.println("Não era um Aluno (improvável neste caso)");
		}

		// Verificações didáticas (úteis para aprendizado)
		System.out.println("pessoaProfessor é do tipo Aluno → " + ((pessoaProfessor instanceof Aluno) ? "sim" : "não"));
		System.out.println("aluno3 é do tipo Professor → " + ((aluno3 instanceof Professor) ? "sim" : "não"));
		System.out.println("alunoRef é do tipo Aluno → " + ((alunoRef instanceof Aluno) ? "sim" : "não"));
		System.out.println("professor2 é do tipo Professor → " + ((professor2 instanceof Professor) ? "sim" : "não"));

		// Configurando datas e endereços
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date data = sdf.parse("10-03-2000");

			if (alunoRef instanceof Aluno aluno) {
				aluno.setDtnasc(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		pessoa.setEndereco("Rua A, 123, Jd. Planalto");

		if (alunoRef instanceof Aluno aluno) {
			aluno.setEndereco("Rua B, 456, Sete Colinas");
		}

		professor.setEndereco("Rua C, 789, Centro");

		// Saída formatada
		SimpleDateFormat sdfSaida = new SimpleDateFormat("dd/MM/yyyy");

		if (alunoRef instanceof Aluno aluno) {
			System.out.println("\nNome Aluno: " + aluno.getNome());
			System.out.println("Data de Nascimento: "
					+ (aluno.getDtnasc() != null ? sdfSaida.format(aluno.getDtnasc()) : "não informada"));
			// System.out.println("CPF: " + aluno.getCpf()); // descomente se existir
			// getCpf()
			System.out.println(aluno.obterEtiquetaEndereco());
		}

		System.out.println();
		System.out.println(pessoa.obterEtiquetaEndereco());
		System.out.println(professor.obterEtiquetaEndereco());

		System.out.println();
		Teste2 teste2 = new Teste2();
		System.out.println(teste2.obj4);

	}

}
