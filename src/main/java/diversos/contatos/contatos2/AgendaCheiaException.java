package diversos.contatos.contatos2;

public class AgendaCheiaException extends Exception {// Eclipse -> Github @guilhermeNetogit 20/03/2026 17:18:33

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {

		return "Agenda já está cheia!";
	}
}
