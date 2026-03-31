package diversos.notasalunos.notas3;

/**
 * Classe Object e método toString
 */

public class TesteAuno3 {// Eclipse -> Github @guilhermeNetogit 11/03/2026 17:53:14
public static void main(String[] args) {
		
		Aluno3 aluno = new Aluno3();
		
		aluno.setCurso("ciência da computação");
		double[] notas = {10, 9, 8, 7};
		aluno.setNotas(notas);
		
		Aluno3 aluno2 = new Aluno3();
		aluno2.setCurso("Ciência da Computação");
		double[] notas2 = {10, 9, 8, 7};
		aluno2.setNotas(notas2);

		System.out.println(aluno);
		System.out.println();
		
		System.out.print("Comparando apenas Curso (Com IgnoreCase): ");
		System.out.println(aluno.equals(aluno2));
		System.out.println();
		
		String s1 = "QWERT";
		String s2 = "qwert";
		
		System.out.printf("Com IngnoreCase (%s = %s ?): ",s1, s2);
		System.out.println(s1.equalsIgnoreCase(s2));
		System.out.printf("Sem IngnoreCase (%s = %s ?): ",s1, s2);
		System.out.println(s1.equals(s2));
		System.out.println();
				
	}

}