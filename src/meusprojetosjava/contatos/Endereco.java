package meusprojetosjava.contatos;

public class Endereco {// Eclipse -> Github @guilhermeNetogit 08/03/2026 22:20:41
	
	private String tipoEnd;
	private String nomeEnd;
	private String bairro;
	private String numero;
	private String cep;
	private String cidade;
	private String uf;
	
	public String getTipoEnd() {
		return tipoEnd;
	}
	public void setTipoEnd(String tipoEnd) {
		this.tipoEnd = tipoEnd;
	}
	public String getNomeEnd() {
		return nomeEnd;
	}
	
	public void setNomeEnd(String nomeEnd) {
		this.nomeEnd = nomeEnd;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getUf() {
		return uf;
	}
	public void setUf(String estado) {
		this.uf = estado;
	}	
}
