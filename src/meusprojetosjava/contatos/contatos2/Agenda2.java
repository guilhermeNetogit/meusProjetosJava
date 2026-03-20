package meusprojetosjava.contatos.contatos2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Agenda2 {// Eclipse -> Github @guilhermeNetogit 20/03/2026 17:18:58

	private Contatos2[] contatos;

	public Agenda2() {
		contatos = new Contatos2[10];
	}

	public void adicionarContato(Contatos2 c) throws AgendaCheiaException {

		boolean cheia = true;
		for (int i = 0; i < contatos.length; i++) {
			if (contatos[i] == null) {
				contatos[i] = c;
				cheia = false;
				break;
			}
		}

		if (cheia) {
			throw new AgendaCheiaException();
		}
	}

	public List<Contatos2> buscaContatosPorParte(String termo) throws ContatoNaoExisteException {

		List<Contatos2> resultados = new ArrayList<>();

		for (Contatos2 c : contatos) {
			if (c != null && c.getNome() != null) {
				// .toLowerCase() garante que a busca não diferencie maiúsculas de minúsculas
				// .contains() verifica se o termo está em qualquer parte do nome
				if (c.getNome().toLowerCase().contains(termo.toLowerCase())) {
					resultados.add(c);
				}
			}
		}

		if (resultados.isEmpty()) {
			throw new ContatoNaoExisteException(termo);
		}
		return resultados;
	}

	/*
	 * public int consultaContatoPorNome(String nome) throws
	 * ContatoNaoExisteException {
	 * 
	 * for (int i = 0; i < contatos.length; i++) { if (contatos[i] != null &&
	 * contatos[i].getNome().equalsIgnoreCase(nome)) { return i; } } throw new
	 * ContatoNaoExisteException(nome); }
	 */
	@Override
	public String toString() {
		String s = String.format("Agenda2 \n[contatos=%s]", Arrays.toString(contatos));

		for (Contatos2 c : contatos) {
			if (c != null) {
				s += c.toString() + "\n";
			}

		}
		return s;
	}

}
