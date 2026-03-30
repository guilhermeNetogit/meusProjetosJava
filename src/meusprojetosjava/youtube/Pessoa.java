package meusprojetosjava.youtube;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

public abstract class Pessoa {

	protected String nome;
	protected String cpf;
	protected Date dtnasc;
	protected String genero;
	protected double experiencia;
	
	public Pessoa(String nome, String cpf, Date dtnasc, String genero) {
		this.nome = nome;
		this.cpf = cpf;
		this.dtnasc = dtnasc;
		this.genero = genero;
		this.experiencia = 0;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Date getDtnasc() {
		return dtnasc;
	}
	public void setDtnasc(Date dtnasc) {
		this.dtnasc = dtnasc;
	}
	
	public int getIdade() {
		LocalDate nascimento = dtnasc.toLocalDate();
		LocalDate hoje = LocalDate.now();

		return Period.between(nascimento, hoje).getYears();
	}

	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public double getExperiencia() {
		return experiencia;
	}
	public void setExperiencia(double experiencia) {
		this.experiencia = experiencia;
	}

	@Override
	public String toString() {
		return "Pessoa \n[nome=" + nome + ", cpf=" + cpf + ", dtnasc=" + dtnasc 
				+ ", idade=" + getIdade() + " anos" + ", genero=" + genero
				+ ", experiencia=" + experiencia + "]\n";
	}

}
