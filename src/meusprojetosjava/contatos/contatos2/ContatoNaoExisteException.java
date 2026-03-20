package meusprojetosjava.contatos.contatos2;

public class ContatoNaoExisteException extends Exception {// Eclipse -> Github @guilhermeNetogit 20/03/2026 17:18:43

	private static final long serialVersionUID = 1L;
	private String nomeContato;

	public ContatoNaoExisteException(String nomeContato) {
		this.nomeContato = nomeContato;
	}

	@Override
	public String getMessage() {
		return "Contato " + nomeContato + " não existe na agenda!\n";
	}

}
