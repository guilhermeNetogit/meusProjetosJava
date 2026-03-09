package meusprojetosjava.notaaluno.curso;

public class Professor {

	private String nomeProf;
	private String departamento;
	private String emailProf;
	
	public String getEmailProf() {
		return emailProf;
	}
	public void setEmailProf(String emailProf) {
		this.emailProf = emailProf;
	}
	
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	
	public String getNomeProf() {
		return nomeProf;
	}
	public void setNomeProf(String nomeProf) {
		this.nomeProf = nomeProf;
	}
	
    public String obterInfo(){
        return "Professor = " + nomeProf;
    }
	
}
