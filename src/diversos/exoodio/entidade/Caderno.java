package diversos.exoodio.entidade;

import diversos.exoodio.entidade.constantes.Tipo;

public class Caderno extends Produto {

	// Tipo do caderno.
	private Tipo tipo;

	public Caderno() {
	}

	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	// {@inheritDoc}.

	@Override
	public String getNome() {
		return "Caderno " + tipo;
	}

	@Override
	public double calcularFrete() {
		return (getPreco() * getQtdneg()) * (1 + tipo.getFator());
	}

	@Override
	public String toString() {
		return "Caderno \n{tipo='" + tipo
				+ ", código()=" + getCodigo()
				+ ", preço()=" + getPreco()
				+ "}";
	}

}
