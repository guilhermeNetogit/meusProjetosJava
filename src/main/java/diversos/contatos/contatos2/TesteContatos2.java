package diversos.contatos.contatos2;

import java.util.List;
import java.util.Scanner;

public class TesteContatos2 {// Eclipse -> Github @guilhermeNetogit 20/03/2026 17:19:15

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		obterOpcaoMenu(scan);

	}

	public static void obterOpcaoMenu(Scanner scan) {

		int opcao = -1;
		Agenda2 agenda = new Agenda2();
		boolean inicializado = false;

		do {
			try {
				if (!inicializado) {

					// Contato 1
					Contatos2 c1 = new Contatos2();
					c1.setTipoContato(new String[] { Contatos2.TIPO_CONTATO[2] });
					c1.setNome("Elf Atirador");
					c1.setEndereco("Floresta da Clareira Flamejante");
					c1.setTelefones(new String[] { "11 9999-8888", "11 9777-6666" });
					c1.setEmail("elf@grimorium.com");
					agenda.adicionarContato(c1);

					// Contato 2
					Contatos2 c2 = new Contatos2();
					c2.setTipoContato(new String[] { Contatos2.TIPO_CONTATO[0] });
					c2.setNome("Mago Cinzento");
					c2.setEndereco("Torre Mais Alta do Castelo da Magia");
					c2.setTelefones(new String[] { "21 8888-7777" });
					c2.setEmail("mago@grimorium.com");
					agenda.adicionarContato(c2);

					// Contato 3
					Contatos2 c3 = new Contatos2();
					c3.setTipoContato(new String[] { Contatos2.TIPO_CONTATO[4] });
					c3.setNome("Sir Tristan de Lioncour");
					c3.setEndereco("Fortaleza do Santo Sepulcro, Sala do Trono");
					c3.setTelefones(new String[] { "11 7777-5555" });
					c3.setEmail("tristan.templario@ordem.org");
					agenda.adicionarContato(c3);

					// Contato 4
					Contatos2 c4 = new Contatos2();
					c4.setTipoContato(new String[] { Contatos2.TIPO_CONTATO[4] });
					c4.setNome("Korg, o Intancável");
					c4.setEndereco("Quartel General de Ferro, bloco 08");
					c4.setTelefones(new String[] { "15 9999-0000" });
					c4.setEmail("korg.vitoria@legiao.com");
					agenda.adicionarContato(c4);

					// Contato 5
					Contatos2 c5 = new Contatos2();
					c5.setTipoContato(new String[] { Contatos2.TIPO_CONTATO[1] });
					c5.setNome("Mestre Malakar");
					c5.setEndereco("Criptas Esquecidas do Vale das Sombras dos Mortos");
					c5.setTelefones(new String[] { "13 6666-1313" });
					c5.setEmail("malakar@pos-vida.net");
					agenda.adicionarContato(c5);

					inicializado = true;
				}

				System.out.println("======= Agenda =======");
				System.out.println("[1] Consultar Contato");
				System.out.println("[2] Adicionar Contato");
				System.out.println("[3] Editar Contato");
				System.out.println("[0] Sair\n");
				System.out.print("Escolha uma opção: ");

				String entrada = scan.nextLine();
				opcao = Integer.parseInt(entrada);

				switch (opcao) {

				case 1:
					consultarContato(scan, agenda);
					break;

				case 2:
					adicionarContato(scan, agenda);
					break;

				case 3:
					editarContato(scan, agenda);
					break;

				case 0:
					System.out.println("\nSaindo...");
					break;

				default:
					System.out.println("\nOpção inválida. Digite novamente...\n");
				}
			} catch (Exception e) {
				System.out.println("\nERRO: Entrada inválida! Por favor, digite apenas números...\n");
				opcao = -1;
			}

		} while (opcao != 0);
	}

	public static void consultarContato(Scanner scan, Agenda2 agenda) {
		String nomePesquisa = lerInformacaoString(scan, "Digite o nome (ou parte dele): ");

		try {
			List<Contatos2> encontrados = agenda.buscaContatosPorParte(nomePesquisa);

			System.out.println("\n--- Contatos encontrados (" + encontrados.size() + ") ---");
			for (Contatos2 contato : encontrados) {
				System.out.println(contato);
				System.out.println("-------------------------");
			}

		} catch (ContatoNaoExisteException e) {
			System.out.println(e.getMessage());
		}
	}

	public static String lerInformacaoString(Scanner scan, String msg) {
		System.out.print(msg);
		String entrada = scan.nextLine();
		return entrada;
	}

	public static void adicionarContato(Scanner scan, Agenda2 agenda) {

		try {
			System.out.println("Criando um contato, entre com as informações");
			System.out.println("Qual o tipo do contato? ");
			String tipo = lerInformacaoString(scan,
					"Tipos: [0]Cliente [1]Fornecedor [2]Funcionário [3]Usuário [4]Motorista [5]Transportadora\n");
			int indiceTipo = Integer.parseInt(tipo);
			String tipoExtenso = Contatos2.TIPO_CONTATO[indiceTipo];
			String nome = lerInformacaoString(scan, "Entre com o nome do contato: ");
			String endereco = lerInformacaoString(scan, "Entre com o endereço do contato: ");
			String telefonesBrutos = lerInformacaoString(scan, "Digite os telefones separados por vírgula: ");
			String[] arrayTelefones = telefonesBrutos.split(",");
			// Remove espaços extras de cada telefone
			for (int i = 0; i < arrayTelefones.length; i++)
				arrayTelefones[i] = arrayTelefones[i].trim();

			String email = lerInformacaoString(scan, "Entre com o email do contato: ");

			Contatos2 newContato = new Contatos2();
			newContato.setTipoContato(new String[] { tipoExtenso });
			newContato.setNome(nome);
			newContato.setEndereco(endereco);
			newContato.setTelefones(arrayTelefones);
			newContato.setEmail(email);

			System.out.println("Contato a ser criado: ");
			System.out.println(newContato);

			agenda.adicionarContato(newContato);
			System.out.println("Contato adicionado com sucesso!");
			System.out.println(newContato);

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("ERRO: Tipo de contato inválido (use de 0 a 5).");
		} catch (NumberFormatException e) {
			System.out.println("ERRO: Digite um número para o tipo de contato.");
		} catch (AgendaCheiaException e) {
			System.out.println(e.getMessage());

			System.out.println("Contatos da agenda");
			System.out.println(agenda);
		}
	}

	public static void editarContato(Scanner scan, Agenda2 agenda) {
		String nomeBusca = lerInformacaoString(scan, "Digite o nome para pesquisar e editar: ");

		try {
			List<Contatos2> encontrados = agenda.buscaContatosPorParte(nomeBusca);

			if (encontrados.isEmpty()) {
				throw new ContatoNaoExisteException(nomeBusca);
			}

			// SELETOR: Se houver mais de um, o usuário escolhe pelo número
			System.out.println("\nQual contato deseja editar?");
			for (int i = 0; i < encontrados.size(); i++) {
				System.out.println("[" + (i + 1) + "] " + encontrados.get(i).getNome());
			}

			System.out.print("Escolha o número: ");
			int index = Integer.parseInt(scan.nextLine()) - 1;

			if (index < 0 || index >= encontrados.size()) {
				System.out.println("Opção inválida.");
				return;
			}

			Contatos2 contato = encontrados.get(index);

			System.out.println("\n--- Editando: " + contato.getNome() + " ---");
			System.out.println("(Pressione ENTER para manter o dado atual)");

			String novoNome = lerInformacaoString(scan, "Novo nome: ");
			if (!novoNome.isEmpty())
				contato.setNome(novoNome);

			String novoEnd = lerInformacaoString(scan, "Novo endereço: ");
			if (!novoEnd.isEmpty())
				contato.setEndereco(novoEnd);

			String novosTels = lerInformacaoString(scan, "Novos telefones (separados por vírgula): ");
			if (!novosTels.isEmpty()) {
				String[] arrayTels = novosTels.split(",");
				for (int i = 0; i < arrayTels.length; i++)
					arrayTels[i] = arrayTels[i].trim();
				contato.setTelefones(arrayTels);
			}

			String novoEmail = lerInformacaoString(scan, "Novo e-mail: ");
			if (!novoEmail.isEmpty())
				contato.setEmail(novoEmail);

			System.out.println("\nContato atualizado com sucesso!\n");

		} catch (ContatoNaoExisteException e) {
			System.out.println("\nErro: Contato não encontrado.\n");
		} catch (Exception e) {
			System.out.println("\nErro na edição: Verifique os dados digitados.\n");
		}
	}

}
