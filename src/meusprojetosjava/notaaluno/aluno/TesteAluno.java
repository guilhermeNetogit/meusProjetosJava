package meusprojetosjava.notaaluno.aluno;

import java.util.Scanner;

public class TesteAluno {// Eclipse -> Github @guilhermeNetogit 24/02/2026 20:39:39
    
	  public static void main(String[] args){
	        
	        Scanner scan = new Scanner(System.in);
	        
	        Aluno aluno = new Aluno();
	        
	        System.out.println("Entre com o nome do aluno");
	        do {
	            aluno.nome = scan.nextLine();
	            if (aluno.nome.trim().isEmpty()) {
	                System.out.println("Nome não pode ser vazio. Digite novamente:");
	            }
	        } while (aluno.nome.trim().isEmpty());
	        
	        System.out.println("\nEntre com o nome do curso");
	        do {
	            aluno.nomeCurso = scan.nextLine();
	            if (aluno.nomeCurso.trim().isEmpty()) {
	                System.out.println("Curso não pode ser vazio. Digite novamente:");
	            }
	        } while (aluno.nomeCurso.trim().isEmpty());
	        
	        System.out.println("\nEntre com a matricula");
	        do {
	            aluno.matricula = scan.nextLine();
	            if (aluno.matricula.trim().isEmpty()) {
	                System.out.println("Matrícula não pode ser vazia. Digite novamente:");
	            }
	        } while (aluno.matricula.trim().isEmpty());
	        
	        //aluno.nomeDisciplinas = new String[3];
	        System.out.println();
	        for (int i=0; i<aluno.nomeDisciplinas.length; i++){
	            System.out.println("Entre com o nome da disciplina " + (i + 1));
		        do {
		            aluno.nomeDisciplinas[i] = scan.nextLine();
		            if (aluno.nomeDisciplinas[i].trim().isEmpty()) {
		                System.out.println("Disciplina não pode ser vazia. Digite novamente:");
		            }
		        } while (aluno.nomeDisciplinas[i].trim().isEmpty());
	            //aluno.nomeDisciplinas[i] = scan.nextLine();
	        }
	        
	        System.out.println();
	        for (int i = 0; i < aluno.notasDisciplinas.length; i++) {
	            System.out.println("Obtendo notas da disciplina " + aluno.nomeDisciplinas[i]);
	            for (int j = 0; j < aluno.notasDisciplinas[i].length; j++) {
	                double nota = -1;
	                boolean entradaValida = false;
	                
	                while (!entradaValida) {
	                    System.out.println("Entre com a nota " + (j + 1));
	                    String entrada = scan.nextLine().trim();
	                    
	                    // Substitui vírgula por ponto (se existir)
	                    entrada = entrada.replace(',', '.');
	                    
	                    try {
	                        nota = Double.parseDouble(entrada);
	                        if (nota >= 0 && nota <= 10) {
	                            entradaValida = true;
	                        } else {
	                            System.out.println("Nota inválida. Digite entre 0 e 10.");
	                        }
	                    } catch (NumberFormatException e) {
	                        System.out.println("Entrada inválida. Use apenas números e separador . ou ,");
	                    }
	                }
	                
	                aluno.notasDisciplinas[i][j] = nota;
	            }
	        }
	        
	        System.out.println();
	        aluno.mostrarInfo();
        	System.out.println();
	        
	        for (int i=0; i<aluno.nomeDisciplinas.length; i++){
	        	
	        	double media = aluno.obterMedia(i);
	        	
	            if (aluno.verificarAprovado(i)){
	                System.out.println("Disciplina " + aluno.nomeDisciplinas[i] + " - foi aprovado | Média: " + media);
	            } else {
	                System.out.println("Disciplina " + aluno.nomeDisciplinas[i] + " - reprovado | Média: " + media);
	            }
	        }
	        scan.close();
	    }
	}