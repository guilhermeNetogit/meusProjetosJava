package diversos.youtube;

import java.sql.Date;

public class Aluno extends Pessoa {
	
	private String login;
	private int totAssistido;

	public Aluno(String nome, String cpf, Date dtnasc, String genero, String login) {
		super(nome, cpf, dtnasc, genero);
		this.login = login;
		this.totAssistido = 0;
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	public int getTotAssistido() {
		return totAssistido;
	}
	public void setTotAssistido(int totAssistido) {
		this.totAssistido = totAssistido;
	}

	@Override
	public String toString() {
		return "Aluno \n" + super.toString() + "[login=" + login + ", totAssistido=" + totAssistido + "]\n";
	}
	
}
