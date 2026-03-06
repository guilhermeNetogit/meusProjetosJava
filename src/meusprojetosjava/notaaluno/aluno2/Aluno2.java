package meusprojetosjava.notaaluno.aluno2;

public class Aluno2 {// Eclipse -> Github @guilhermeNetogit 05/03/2026 17:34:34
	
	private String nome;
    private String matricula;
    private String nomeCurso;
    private String[] nomeDisciplinas;
    private double[][] notasDisciplinas;
    
    public Aluno2() {
    	setNomeDisciplinas(new String[3]);
    	setNotasDisciplinas(new double[3][4]);
	}

	public Aluno2(String nome, String matricula, String nomeCurso) {
		this.nome = nome;
		this.matricula = matricula;
		this.nomeCurso = nomeCurso;
		this.nomeDisciplinas = new String[3];
		this.notasDisciplinas = new double[3][4];
	}

	public double[][] getNotasDisciplinas() {
		return notasDisciplinas;
	}

	public void setNotasDisciplinas(double[][] notasDisciplinas) {
		this.notasDisciplinas = notasDisciplinas;
	}

	public String[] getNomeDisciplinas() {
		return nomeDisciplinas;
	}

	public void setNomeDisciplinas(String[] nomeDisciplinas) {
		this.nomeDisciplinas = nomeDisciplinas;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
    
    public void mostrarInfo(){
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
    
    public boolean verificarAprovado(int indice){
        
        if (obterMedia(indice) >= 7){
            return true;
        } 
        
        return false;
    }
    
    public double obterMedia(int indice){
        
        double soma = 0;
        
        for (int i=0; i<notasDisciplinas[indice].length; i++){
            soma += notasDisciplinas[indice][i];
        }
        
        double media = soma / notasDisciplinas[indice].length;
        
        return media;
    }
	public void setNomeDisciplinaPos(int pos, String nomeDisciplina) {
		this.nomeDisciplinas[pos] = nomeDisciplina;
	}
    
    public void setNotaDisciplinaPosIJ(int posI, int posJ, double nota){
        this.notasDisciplinas[posI][posJ] = nota;
    }	
}

