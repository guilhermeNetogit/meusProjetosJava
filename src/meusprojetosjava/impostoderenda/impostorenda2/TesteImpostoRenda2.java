package meusprojetosjava.impostoderenda.impostorenda2;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class TesteImpostoRenda2 {// Eclipse -> Github @guilhermeNetogit 15/03/2026 22:31:30// Eclipse -> Github @guilhermeNetogit 15/03/2026 13:30:14

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		// Listas de contribuintes cadastrados
		List<PessoaJuridica2> pessoasJuridicas = new ArrayList<>();
		List<PessoaFisica2> pessoasFisicas = new ArrayList<>();

		// Cadastro de Pessoas Jurídicas
		PessoaJuridica2 pj1 = new PessoaJuridica2();
		pj1.setNome("Empresa Pessoa Jurídica 1");
		pj1.setCnpj("44.379.462/0001-37");
		pj1.setRendaBruta(15350);

		PessoaJuridica2 pj2 = new PessoaJuridica2();
		pj2.setNome("Empresa Pessoa Jurídica 2");
		pj2.setCnpj("17.686.485/0001-11");
		pj2.setRendaBruta(20000.99);

		PessoaJuridica2 pj3 = new PessoaJuridica2();
		pj3.setNome("Empresa Pessoa Jurídica 3");
		pj3.setCnpj("66.905.259/0001-90");
		pj3.setRendaBruta(50000);

		PessoaJuridica2 pj4 = new PessoaJuridica2();
		pj4.setNome("Empresa Pessoa Jurídica 4");
		pj4.setCnpj("35.244.591/0001-27");
		pj4.setRendaBruta(150000);

		pessoasJuridicas.add(pj1);
		pessoasJuridicas.add(pj2);
		pessoasJuridicas.add(pj3);

		// Cadastro de Pessoas Físicas
		PessoaFisica2 pf1 = new PessoaFisica2();
		pf1.setNome("Pessoa Física 1");
		pf1.setCpf("307.961.650-25");
		pf1.setRendaBruta(2428.80);// 2428.95

		PessoaFisica2 pf2 = new PessoaFisica2();
		pf2.setNome("Pessoa Física 2");
		pf2.setCpf("681.816.970-81");
		pf2.setRendaBruta(3585.51);

		PessoaFisica2 pf3 = new PessoaFisica2();
		pf3.setNome("Pessoa Física 3");
		pf3.setCpf("321.861.300-09");
		pf3.setRendaBruta(6592.9);

		PessoaFisica2 pf4 = new PessoaFisica2();
		pf4.setNome("Pessoa Física 4");
		pf4.setCpf("187.550.887-71");
		pf4.setRendaBruta(42192.71);

		PessoaFisica2 pf5 = new PessoaFisica2();
		pf5.setNome("Pessoa Física 5");
		pf5.setCpf("298.661.998-82");
		pf5.setRendaBruta(4192.71);

		pessoasFisicas.add(pf1);
		pessoasFisicas.add(pf2);
		pessoasFisicas.add(pf3);
		pessoasFisicas.add(pf4);
		pessoasFisicas.add(pf5);

		int opcaoTipoPessoa;
		Contribuinte2 contribuinteSelecionado = null;

		do {
			// Menu de seleção de tipo de pessoa
			System.out.println("══════════════════════════════════════════");
			System.out.println("             Imposto de Renda");
			System.out.println("══════════════════════════════════════════");
			System.out.println("Selecione o tipo de pessoa:\n");
			System.out.println("[1] Imposto de Renda Pessoa Física (IRPF)");
			System.out.println("[2] Imposto de Renda Pessoa Jurídica (IRPJ)");
			System.out.println("[0] Sair do sistema");
			System.out.print("\nOpção selecionada: ");
			opcaoTipoPessoa = scanner.nextInt();
			scanner.nextLine(); // limpa buffer

			switch (opcaoTipoPessoa) {
			case 1:
				contribuinteSelecionado = escolherPessoaFisica(scanner, pessoasFisicas);
				break;
			case 2:
				contribuinteSelecionado = escolherPessoaJuridica(scanner, pessoasJuridicas);
				break;
			case 0:
				System.out.println("\nEncerrando atendimento...");
				scanner.close();
				return; // encerra o programa
			default:
				System.out.println("\nOpção inválida. Tente novamente.\n");
				continue; // volta ao início do do-while
			}

			if (contribuinteSelecionado != null) {

				System.out.println(contribuinteSelecionado.gerarRelatorio());
				System.out
						.println("-----------------------------------------------------------------------------------");
				pausar(scanner);
			}
		} while (opcaoTipoPessoa != 0);
	}

	// Método para escolher uma Pessoa Física (mostra toda a lista de CPF's)
	private static PessoaFisica2 escolherPessoaFisica(Scanner scanner, List<PessoaFisica2> lista) {

		while (true) {
			System.out.println("\n--- Pessoas Físicas Cadastradas ---");
			for (int i = 0; i < lista.size(); i++) {
				PessoaFisica2 pf = lista.get(i);
				System.out.printf("[%d] %s (CPF: %s)\n", i + 1, pf.getNome(), pf.getCpf());
			}
			System.out.println("\n[0] Voltar ao menu principal");
			System.out.print("\nEscolha o código da pessoa física (1 a " + lista.size() + "): \n");

			int codigo;

			try {
				codigo = scanner.nextInt();
			} catch (Exception e) {
				scanner.nextLine(); // limpa entrada inválida
				System.out.println("Entrada inválida. Digite apenas números.");
				continue;
			}

			scanner.nextLine();

			if (codigo == 0) {
				return null; // volta ao menu principal
			}

			if (codigo < 1 || codigo > lista.size()) {
				System.out.println("Código inválido! Tente novamente.");
				continue;
			}

			PessoaFisica2 original = lista.get(codigo - 1);

			// Solicita a renda bruta, permitindo Enter para usar o valor cadastrado
			double renda;
			while (true) {
				System.out.print("Digite a renda bruta para " + original.getNome()
						+ " (ou Enter para usar a renda cadastrada): R$ ");
				String entradaRenda = scanner.nextLine();
				
				if (entradaRenda.trim().isEmpty()) {
					renda = original.getRendaBruta();
					break;
				}
				try {
					renda = Double.parseDouble(entradaRenda.replace(",", "."));
					break;
				} catch (NumberFormatException e) {
					System.out.println("Valor inválido. Digite um número ou pressione Enter.");
				}
			}
			
			// Solicita a quantidade de dependentes
			int dependentes = 0;
			
			while (true) {
	            System.out.print("Quantidade de dependentes (máx. 12): ");
	            String entradaDep = scanner.nextLine();
	            
	            if (entradaDep.trim().isEmpty()) {
	                dependentes = 0;
	                break;
	            }
	            try {
	                dependentes = Integer.parseInt(entradaDep);
	                if (dependentes < 0 || dependentes > 12) {
	                    System.out.println("O máximo permitido é 12 dependentes.");
	                    continue;
	                }
	                break;
	            } catch (NumberFormatException e) {
	                System.out.println("Entrada inválida. Digite um número inteiro de 0 a 12.");
	            }
	        }

			// Cria um novo objeto com os mesmos dados, mas com a renda informada
			PessoaFisica2 nova = new PessoaFisica2();
			nova.setNome(original.getNome());
			nova.setCpf(original.getCpf());
			nova.setRendaBruta(renda);
			nova.setQtdDependentes(dependentes);
			
			return nova;
		}
	}

	// Método para escolher uma Pessoa Jurídica (mostra toda a lista de CNPJ's)
	private static PessoaJuridica2 escolherPessoaJuridica(Scanner scanner, List<PessoaJuridica2> lista) {
		while (true) {
			System.out.println("\n--- Pessoas Jurídicas Cadastradas ---");
			for (int i = 0; i < lista.size(); i++) {
				PessoaJuridica2 pj = lista.get(i);
				System.out.printf("[%d] %s (CNPJ: %s)\n", i + 1, pj.getNome(), pj.getCnpj());
			}
			System.out.println("\n[0] Voltar ao menu principal");
			System.out.print("Escolha o código da pessoa jurídica (1 a " + lista.size() + "): ");

			int codigo;
			try {
				codigo = scanner.nextInt();
			} catch (Exception e) {
				scanner.nextLine(); // limpa entrada inválida
				System.out.println("Entrada inválida. Digite um número.");
				continue;
			}
			scanner.nextLine();

			if (codigo == 0) {
				return null;
			}
			if (codigo < 1 || codigo > lista.size()) {
				System.out.println("Código inválido! Tente novamente.\n");
				continue;
			}

			PessoaJuridica2 original = lista.get(codigo - 1);

			double renda;
			while (true) {
				System.out.print("Digite a renda bruta para " + original.getNome()
						+ " (ou Enter para usar a renda cadastrada:) R$ ");
				String entradaRenda = scanner.nextLine();
				if (entradaRenda.trim().isEmpty()) {
					renda = original.getRendaBruta();
					break;
				}
				try {
					renda = Double.parseDouble(entradaRenda.replace(",", "."));
					break;
				} catch (NumberFormatException e) {
					System.out.println("Valor inválido. Digite um número ou pressione Enter.\n");
				}
			}

			PessoaJuridica2 nova = new PessoaJuridica2();
			nova.setNome(original.getNome());
			nova.setCnpj(original.getCnpj());
			nova.setRendaBruta(renda);
			return nova;
		}
	}

	/*
	 * // Método 2 para escolher uma Pessoa Física (sem exibir a lista) private
	 * static PessoaFisica escolherPessoaFisica(Scanner scanner, List<PessoaFisica>
	 * lista) { System.out.print("\nDigite o código da pessoa física (1 a " +
	 * lista.size() + "): "); int codigo = scanner.nextInt(); scanner.nextLine();
	 * 
	 * if (codigo < 1 || codigo > lista.size()) {
	 * System.out.println("Código inválido!\n"); return null; }
	 * 
	 * PessoaFisica original = lista.get(codigo - 1);
	 * 
	 * System.out.print("Digite a renda bruta para " + original.getNome() +
	 * ": R$ "); double renda = scanner.nextDouble(); scanner.nextLine();
	 * 
	 * PessoaFisica nova = new PessoaFisica(); nova.setNome(original.getNome());
	 * nova.setCpf(original.getCpf()); nova.setRendaBruta(renda);
	 * 
	 * return nova; }
	 * 
	 * // Método 2 para escolher uma Pessoa Jurídica (sem exibir a lista) private
	 * static PessoaJuridica escolherPessoaJuridica(Scanner scanner,
	 * List<PessoaJuridica> lista) {
	 * System.out.print("\nDigite o código da pessoa jurídica (1 a " + lista.size()
	 * + "): "); int codigo = scanner.nextInt(); scanner.nextLine();
	 * 
	 * if (codigo < 1 || codigo > lista.size()) {
	 * System.out.println("Código inválido!\n"); return null; }
	 * 
	 * PessoaJuridica original = lista.get(codigo - 1);
	 * 
	 * System.out.print("Digite a renda bruta para " + original.getNome() +
	 * ": R$ "); double renda = scanner.nextDouble(); scanner.nextLine();
	 * 
	 * PessoaJuridica nova = new PessoaJuridica(); nova.setNome(original.getNome());
	 * nova.setCnpj(original.getCnpj()); nova.setRendaBruta(renda);
	 * 
	 * return nova; }
	 */
	private static void pausar(Scanner scanner) {
		System.out.println("\nPressione Enter para continuar...");
		scanner.nextLine();
	}
}
