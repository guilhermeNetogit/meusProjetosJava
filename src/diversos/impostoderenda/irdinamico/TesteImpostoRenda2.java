package diversos.impostoderenda.irdinamico;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

/**
 * Classe principal do simulador de Imposto de Renda.
 * Suporta seleção do ano-base (2025 ou 2026) antes de calcular.
 *
 * @author GitHub guilhermeNetogit
 * @since 16/03/2026 20:43:34
 * @version 07/04/2026 14:01:48
 */

public class TesteImpostoRenda2 {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		// Listas de contribuintes cadastrados
		List<PessoaJuridica2> pessoasJuridicas = new ArrayList<>();
		List<PessoaFisica2> pessoasFisicas = new ArrayList<>();

		// Cadastro de Pessoas Jurídicas
		pessoasJuridicas.add(criarPJ("Empresa Pessoa Jurídica 1", "44.379.462/0001-37", 15350.00));
		pessoasJuridicas.add(criarPJ("Empresa Pessoa Jurídica 2", "17.686.485/0001-11", 20000.99));
		pessoasJuridicas.add(criarPJ("Empresa Pessoa Jurídica 3", "66.905.259/0001-90", 50000.00));
		pessoasJuridicas.add(criarPJ("Empresa Pessoa Jurídica 4", "35.244.591/0001-27", 150000.00));

		// Cadastro de Pessoas Físicas
		pessoasFisicas.add(criarPF("Pessoa Física 1", "307.961.650-25", 2428.80));
		pessoasFisicas.add(criarPF("Pessoa Física 2", "681.816.970-81", 3585.51));
		pessoasFisicas.add(criarPF("Pessoa Física 3", "321.861.300-09", 6592.90));
		pessoasFisicas.add(criarPF("Pessoa Física 4", "187.550.887-71", 42192.71));
		pessoasFisicas.add(criarPF("Pessoa Física 5", "298.661.998-82", 4192.71));

		int opcaoTipoPessoa;

		do {
			// Menu de seleção de tipo de pessoa
			System.out.println("════════════════════════════════════════════════════════════════════");
			System.out.println("                          Imposto de Renda");
			System.out.println("════════════════════════════════════════════════════════════════════");
			System.out.println("Selecione o tipo de pessoa:\n");
			System.out.println("[1] Imposto de Renda Pessoa Física (IRPF)");
			System.out.println("[2] Imposto de Renda Pessoa Jurídica (IRPJ)");
			System.out.println("[0] Sair do sistema");
			System.out.print("\nOpção selecionada: ");
			opcaoTipoPessoa = lerInt(scanner);
			scanner.nextLine(); // limpa buffer

			switch (opcaoTipoPessoa) {

			case 1: {
				// 1. Escolhe o ano-base
				RegrasFiscais regras = escolherAnoPF(scanner);
				if (regras == null)
					break; // voltou ao menu

				// 2. Escolhe o contribuinte e preenche os dados
				PessoaFisica2 pf = escolherPessoaFisica(scanner, pessoasFisicas);
				if (pf == null)
					break;

				// 3. Injeta as regras e gera o relatório
				pf.setRegras(regras);
				String relatorio = pf.gerarRelatorio();
				System.out.println(relatorio);

				perguntarSalvar(scanner, relatorio, pf.getNome());
				pausar(scanner);
				break;
			}

			case 2: {
				PessoaJuridica2 pj = escolherPessoaJuridica(scanner, pessoasJuridicas);
				if (pj == null)
					break;

				String relatorio = pj.gerarRelatorio();
				System.out.println(relatorio);

				perguntarSalvar(scanner, relatorio, pj.getNome());
				pausar(scanner);
				break;
			}

			case 0:
				System.out.println("\nEncerrando atendimento...");
				scanner.close();
				return; // encerra o programa

			default:
				System.out.println("\nOpção inválida. Tente novamente.\n");
			}

		} while (opcaoTipoPessoa != 0);
	}

	// Seleção do ano-base (somente para PF por enquanto)

	private static RegrasFiscais escolherAnoPF(Scanner scanner) {
		while (true) {
			System.out.println("\n--- Selecione o ano-base ---");
			System.out.println("[1] 2025");
			System.out.println("[2] 2026");
			System.out.println("[0] Voltar");
			System.out.print("\nOpção: ");
			int op = lerInt(scanner);
			scanner.nextLine();

			switch (op) {
			case 1:
				return RegrasFiscais.de2025();
			case 2:
				return RegrasFiscais.de2026();
			case 0:
				return null;
			default:
				System.out.println("Opção inválida.");
			}
		}
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

			int codigo = lerInt(scanner);
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
						+ "\n(ou Enter para usar a renda cadastrada): R$ ");
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

			int codigo = lerInt(scanner);
			scanner.nextLine();

			if (codigo == 0) {
				return null;
			}
			if (codigo < 1 || codigo > lista.size()) {
				System.out.println("Código inválido! Tente novamente.\n");
				continue;
			}

			PessoaJuridica2 original = lista.get(codigo - 1);

			// 1. SOLICITA O FATURAMENTO
			System.out.print(
					"Digite o faturamento bruto para " + original.getNome() + "\n(ou Enter para o original): R$ ");
			String entradaFat = scanner.nextLine();
			double faturamento = entradaFat.trim().isEmpty() ? original.getRendaBruta()
					: Double.parseDouble(entradaFat.replace(",", "."));

			// Cria o novo objeto PJ com os dados informados
			PessoaJuridica2 nova = new PessoaJuridica2();
			nova.setNome(original.getNome());
			nova.setCnpj(original.getCnpj());
			nova.setRendaBruta(faturamento);

			return nova;
		}
	}

	// Utilitários

	private static void perguntarSalvar(Scanner scanner, String relatorio, String nome) {
		System.out.print("Deseja salvar este relatório em arquivo? (S/N): ");
		String salvar = scanner.nextLine().trim().toUpperCase();
		if (salvar.equals("S")) {
			salvarRelatorioArquivo(relatorio, nome);
		}
	}

	private static void pausar(Scanner scanner) {
		System.out.println("Pressione Enter para continuar...");
		scanner.nextLine();
	}

	private static int lerInt(Scanner scanner) {
		while (true) {
			try {
				return scanner.nextInt();
			} catch (Exception e) {
				scanner.nextLine();
				System.out.print("Entrada inválida. Digite um número: ");
			}
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

	private static void salvarRelatorioArquivo(String conteudoRelatorio, String nomeContribuinte) {
		// Cria um nome de arquivo único com data e hora para não sobrescrever
		LocalDateTime agora = LocalDateTime.now();
		String timestamp = agora.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
		String dataFormatada = agora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
		String nomeArquivo = "Relatorio_" + nomeContribuinte.replace(" ", "_") + "_" + timestamp + ".txt";

		File arquivo = new File(nomeArquivo);

		try (FileWriter fw = new FileWriter(nomeArquivo); PrintWriter pw = new PrintWriter(fw)) {

			pw.println("====================================================================");
			pw.println("             RELATORIO DE SIMULACAO DE IMPOSTO DE RENDA");
			pw.println("                   Gerado em: " + dataFormatada);
			pw.println("====================================================================\n");
			pw.println(conteudoRelatorio);
			System.out.println("\n✅ Relatório salvo com sucesso: " + nomeArquivo);
			System.out.println("📍 Local do arquivo: " + arquivo.getAbsolutePath());

		} catch (IOException e) {
			System.err.println("❌ Erro ao salvar o arquivo: " + e.getMessage());
		}
	}

	private static PessoaFisica2 criarPF(String nome, String cpf, double renda) {
		PessoaFisica2 pf = new PessoaFisica2();
		pf.setNome(nome);
		pf.setCpf(cpf);
		pf.setRendaBruta(renda);
		return pf;
	}

	private static PessoaJuridica2 criarPJ(String nome, String cnpj, double renda) {
		PessoaJuridica2 pj = new PessoaJuridica2();
		pj.setNome(nome);
		pj.setCnpj(cnpj);
		pj.setRendaBruta(renda);
		return pj;
	}
}
