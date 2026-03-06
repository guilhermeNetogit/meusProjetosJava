package meusprojetosjava.notaaluno.aluno2;

import java.util.Scanner;

public class AcaoAluno2 {// Eclipse -> Github @guilhermeNetogit 05/03/2026 17:34:24
	
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		Aluno2 aluno = new Aluno2();

		// Entrada do Nome do Aluno
		System.out.println("======== Versão 2.0 ========");
		System.out.println("Entre com o nome do aluno");
		String entrada;
		do {
			aluno.setNome(scan.nextLine());
			if (aluno.getNome().trim().isEmpty()) {
				System.out.println("Nome não pode ser vazio. Digite novamente:");
			}
		} while (aluno.getNome().trim().isEmpty());

		// Entrada do Nome do Curso
		System.out.println("\nEntre com o nome do curso");
		do {
			aluno.setNomeCurso(scan.nextLine());
			if (aluno.getNomeCurso().trim().isEmpty()) {
				System.out.println("Curso não pode ser vazio. Digite novamente:");
			}
		} while (aluno.getNomeCurso().trim().isEmpty());

		// Entrada do Numero da Matricula
		System.out.println("\nEntre com a matricula");
		do {
			aluno.setMatricula(scan.nextLine());
			if (aluno.getMatricula().trim().isEmpty()) {
				System.out.println("Matrícula não pode ser vazia. Digite novamente:");
			}
		} while (aluno.getMatricula().trim().isEmpty());

		// Entrada do Nome das Disciplinas
		// aluno.nomeDisciplinas = new String[3];
		System.out.println();
		for (int i = 0; i < aluno.getNomeDisciplinas().length; i++) {
			System.out.println("Entre com o nome da disciplina " + (i + 1));
			do {
				entrada = scan.nextLine().trim();
				if (entrada.isEmpty()) {
					System.out.println("Disciplina não pode ser vazia. Digite novamente:");
				} else {
					aluno.setNomeDisciplinaPos(i, entrada);
				}
			} while (entrada.isEmpty());
			// aluno.nomeDisciplinas[i] = scan.nextLine();
		}

		// Entrada das Notas das Disciplinas
		System.out.println();
		for (int i = 0; i < aluno.getNotasDisciplinas().length; i++) {
			System.out.println("Obtendo notas da disciplina " + aluno.getNomeDisciplinas()[i]);
			for (int j = 0; j < aluno.getNotasDisciplinas()[i].length; j++) {
				double nota = -1;
				boolean entradaValida = false;

				while (!entradaValida) {
					System.out.println("Entre com a nota " + (j + 1));
					String valor = scan.nextLine().trim();

					// Substitui vírgula por ponto (se existir)
					valor = valor.replace(',', '.');

					try {
						nota = Double.parseDouble(valor);
						if (nota >= 0 && nota <= 10) {
							entradaValida = true;
						} else {
							System.out.println("Nota inválida. Digite entre 0 e 10.");
						}
					} catch (NumberFormatException e) {
						System.out.println("Entrada inválida. Use apenas números e separador . ou ,");
					}
				}

				aluno.setNotaDisciplinaPosIJ(i, j, nota);
			}
		}

		System.out.println();
		aluno.mostrarInfo();
		System.out.println();

		for (int i = 0; i < aluno.getNomeDisciplinas().length; i++) {

			double media = aluno.obterMedia(i);
			String status = aluno.verificarAprovado(i) ? "foi aprovado" : "reprovado";

			System.out.printf("Disciplina %s - %s | Média: %.2fn", aluno.getNomeDisciplinas()[i], status, media);

			scan.close();
		}
	}
}