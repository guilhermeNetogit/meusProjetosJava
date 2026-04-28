package diversos.notasalunos.notas1;

public class Aluno {// Eclipse -> Github @guilhermeNetogit 24/02/2026 20:39:49
    
    String nome;
    String matricula;
    String nomeCurso;
    String[] nomeDisciplinas = new String[3];
    double[][] notasDisciplinas = new double[3][4];
    
    void mostrarInfo(){
        System.out.println("Nome: " + nome);
        System.out.println("Matrícula: " + matricula);
        System.out.println("Nome do curso: " + nomeCurso);
        
        for (int i=0; i<notasDisciplinas.length; i++){
            System.out.println("Notas da disciplina " + nomeDisciplinas[i]);
            for (int j=0; j<notasDisciplinas[i].length; j++){
                System.out.print(notasDisciplinas[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    boolean verificarAprovado(int indice){
        
        if (obterMedia(indice) >= 7){
            return true;
        } 
        
        return false;
    }
    
    double obterMedia(int indice){
        
        double soma = 0;
        
        for (int i=0; i<notasDisciplinas[indice].length; i++){
            soma += notasDisciplinas[indice][i];
        }
        
        double media = soma / notasDisciplinas[indice].length;
        
        return media;
    }
}