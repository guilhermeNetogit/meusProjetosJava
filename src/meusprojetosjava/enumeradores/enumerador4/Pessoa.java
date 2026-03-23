package meusprojetosjava.enumeradores.enumerador4;

public class Pessoa {// Eclipse -> Github @guilhermeNetogit 23/03/2026 16:53:34

	private TipoDocumento tipoDocumento;
	private String numeroDocumento;

	public Pessoa() {
		super();
	}

	public Pessoa(TipoDocumento tipoDocumento, String numeroDocumento) {
		super();
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	@Override
	public String toString() {
		String tipoPessoa = (tipoDocumento != null && tipoDocumento.name().equals("CPF")) ? "Física" : "Jurídica";
		return String.format("Pessoa %s: [tipoDocumento => %s, numeroDocumento => %s]", tipoPessoa, tipoDocumento,
				numeroDocumento);
	}

}
