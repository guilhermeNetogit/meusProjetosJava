package meusprojetosjava.contatos;

public class TesteContatos {// Eclipse -> Github @guilhermeNetogit 08/03/2026 22:20:06

	public static void main(String[] args) {

		Contatos contato = new Contatos();
		contato.setNome("Luciano Andrade");
		// contato.setEndereco("Avenida Leopoldino de Oliveira, 5652 - Centro - 38066340, Uberaba/MG");
		// contato.setTelefone("34999867574");
		
		// criar objeto endereco completo
		Endereco endCompleto = new Endereco();
		endCompleto.setTipoEnd("Avenida");
		endCompleto.setNomeEnd("Leopoldino de Oliveira");
		endCompleto.setNumero("5652");
		endCompleto.setBairro("Centro");
		endCompleto.setCep("38066340");
		endCompleto.setCidade("Uberaba");
		endCompleto.setUf("MG");
		
		contato.setEndereco(endCompleto);
		
		Telefone telefone = new Telefone();
		telefone.setDdd("34");
		telefone.setTelefone("999867574");
		
		Telefone telefone2 = new Telefone();
		telefone2.setDdd("34");
		telefone2.setTelefone("33363232");
		
		Telefone[] telefones = new Telefone[2];
		telefones[0] = telefone;
		telefones[1] = telefone2;
		
		contato.setTelefones(telefones);
		
		System.out.println("Nome: " + contato.getNome());
		
		if (contato != null && contato.getEndereco() != null){		
			System.out.println("Endereço: " 
					+ contato.getEndereco().getTipoEnd() + " "
					+ contato.getEndereco().getNomeEnd() + ", "
					+ contato.getEndereco().getNumero() + " - "
					+ contato.getEndereco().getBairro() + " - "
					+ contato.getEndereco().getCep() + ", "
					+ contato.getEndereco().getCidade() + "/"
					+ contato.getEndereco().getUf()
					);
		}
		
		/*if (contato != null && contato.getTelefone() != null){
		System.out.println("Telefone: (" 
				+ contato.getTelefone().getDdd() +") "
				+ contato.getTelefone().getTelefone()
				);
		}*/
		
		if (contato != null && contato.getTelefones() != null){
			int i = 1;
			for (Telefone t : contato.getTelefones()) {
				System.out.println("Telefone " + i++ + ": ("
						+ t.getDdd() + ") "
						+ t.getTelefone()
				);
			}
		}
	}
}
