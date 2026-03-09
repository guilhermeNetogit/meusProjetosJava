package meusprojetosjava.notaaluno.curso;

public class Curso {

	private String nomeCurso;
	private String horario;
	private Professor professor;
	private Aluno[] alunos;
	
	public Aluno[] getAlunos() {
		return alunos;
	}
	public void setAlunos(Aluno[] alunos) {
		this.alunos = alunos;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	
	public String obterInfo(){
        String info = "Nome do Curso = " + nomeCurso + "\n"
        		+ "Horário: " + horario + "\n";
        
        if (professor != null){
            info += professor.obterInfo() + "\n\n";
        }
        
        if (alunos != null){
            System.out.println("---Alunos---");
            for (Aluno aluno : alunos){
                if (aluno != null){
                    info += aluno.obterInfo();
                    info += "\n\n";
               }    
            }
            
            info += "\nMédia da turma = " + obterMediaTurma();
        }
        
        return info;
    }
    
    public double obterMediaTurma(){
        double soma = 0;
        for (Aluno aluno : alunos){
            if (aluno != null){
                soma += aluno.obterMedia();
            } 
        }
        return soma/alunos.length;
    }
}
