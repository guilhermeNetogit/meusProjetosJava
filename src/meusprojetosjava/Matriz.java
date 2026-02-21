package meusprojetosjava;

import java.text.DecimalFormat;

public class Matriz {// Eclipse -> Github @guilhermeNetogit 21/02/2026 16:08:03

	public static void main(String[] args) {

		double [][] notasAlunos = new double [5][4];
		
		notasAlunos [0][0] = 10;
		notasAlunos [0][1] = 7;
		notasAlunos [0][2] = 8;
		notasAlunos [0][3] = 9.5;
		
		notasAlunos [1][0] = 9;
		notasAlunos [1][1] = 8;
		notasAlunos [1][2] = 7;
		notasAlunos [1][3] = 9;
		
		notasAlunos [2][0] = 6.8;
		notasAlunos [2][1] = 7.1;
		notasAlunos [2][2] = 7.9;
		notasAlunos	[2][3] = 8.3;
		
		notasAlunos [3][0] = 8.6;
		notasAlunos [3][1] = 6.8;
		notasAlunos [3][2] = 6;
		notasAlunos [3][3] = 6.5;
		
		notasAlunos [4][0] = 6;
		notasAlunos [4][1] = 3.5;
		notasAlunos [4][2] = 4;
		notasAlunos [4][3] = 7.2;
		
		for (int i=0; i<notasAlunos.length; i++){
			//System.out.print(notasAlunos[i] + " ");
			for (int j=0; j<notasAlunos[i].length; j++){
				System.out.print(notasAlunos[i][j] + " - ");
			}
			System.out.println();
		}
		
		notasAlunos[1][3] = 8;
		
		System.out.println();
		
		for (int i=0; i<notasAlunos.length; i++){
			for (int j=0; j<notasAlunos[i].length; j++){
				System.out.print(notasAlunos[i][j] + " - ");
			}
			System.out.println();
		}
		
		System.out.println("\nCalculando a média de cada aluno");
		
		DecimalFormat df = new DecimalFormat("#.##");
		double soma;
		for (int i=0; i<notasAlunos.length; i++){
			
			soma = 0;
			for (int j=0; j<notasAlunos[i].length; j++){
				soma += notasAlunos[i][j];
			}
			/*System.out.println("Média do aluno " + (i+1) + " é = " + df.format(soma/4)); //ou use */
			
            double media = soma / notasAlunos[i].length;

            System.out.println("Média do aluno " + (i + 1) + " = " + df.format(media));
			
		}
			System.out.println();
		}
	}