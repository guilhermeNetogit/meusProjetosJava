package diversos.notasalunos.curso;

import java.util.Scanner;

public class TesteCurso {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		System.out.println("Entre com o nome do Curso: ");
		String nomeCurso = scan.nextLine();

		System.out.println("Entre com o horário do Curso: ");
		String horario = scan.nextLine();

		System.out.println("Entre com o nome do professor: ");
		String nomeProf = scan.nextLine();

		System.out.println("Entre com o departamento do professor: ");
		String departamento = scan.nextLine();

		System.out.println("Entre com o email do professor: ");
		String emailProf = scan.nextLine();

		Curso curso = new Curso();
		curso.setNomeCurso(nomeCurso);
		curso.setHorario(horario);

		Professor professor = new Professor();
		professor.setNomeProf(nomeProf);
		professor.setDepartamento(departamento);
		professor.setEmailProf(emailProf);

		curso.setProfessor(professor);

		System.out.println("\n---Alunos---");

		Aluno[] alunos = new Aluno[5];
		for (int i = 0; i < 5; i++) {

			System.out.println("Entre com o nome do aluno " + (i + 1) + ":");
			String nomeAluno = scan.nextLine();

			System.out.println("Entre com a matrícula do aluno " + (i + 1) + ":");
			String matAluno = scan.nextLine();

			double[] notas = new double[4];

			for (int j = 0; j < 4; j++) {
				System.out.println("Entre com a nota " + (j + 1));
				notas[j] = scan.nextDouble();
			}

			scan.nextLine();

			System.out.println();

			Aluno aluno = new Aluno();
			aluno.setNomeAluno(nomeAluno);
			aluno.setMatricula(matAluno);
			aluno.setNota(notas);

			alunos[i] = aluno;
		}

		curso.setAlunos(alunos);

		System.out.println(curso.obterInfo());

		scan.close();

	}

}
