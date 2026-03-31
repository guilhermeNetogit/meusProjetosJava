package diversos.polimorfismo;

import java.util.Scanner;

public class TestePolimofismo {// Eclipse -> Github @guilhermeNetogit 11/03/2026 23:56:32

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		ContaBancaria contaSimples = new ContaBancaria();
		contaSimples.setNomeCliente("Enzo Gorlami Oliveira da Silva Albuquerque Araujo de Alcantara Machado");
		contaSimples.setAgencia("0123");
		contaSimples.setNumConta("362541");
		contaSimples.setTipoConta("Conta Corrente Simples");
		contaSimples.setSaldo(19.73);

		ContaPoupanca contaPoupanca = new ContaPoupanca();
		contaPoupanca.setNomeCliente("Enzo Gorlami Oliveira da Silva Albuquerque Araujo de Alcantara Machado");
		contaPoupanca.setAgencia("0123");
		contaPoupanca.setNumConta("695847");
		contaPoupanca.setTipoConta("Conta Poupança");
		contaPoupanca.setSaldo(300);
		contaPoupanca.setDiaRendimento(13);
		
		ContaEspecial contaEspecial = new ContaEspecial();
		contaEspecial.setNomeCliente("Enzo Gorlami Oliveira da Silva Albuquerque Araujo de Alcantara Machado");
		contaEspecial.setAgencia("0123");
		contaEspecial.setNumConta("938271");
		contaEspecial.setTipoConta("Conta Corrente Especial");
		contaEspecial.setEspecial(true);
		contaEspecial.setSaldo(500);
		contaEspecial.setLimite(1000);
		//contaEspecial.setValorEspecialUsado(0);

		int opcaoConta;
		ContaBancaria contaAtual = null; // Referência polimórfica para a conta escolhida

		do {
			// Menu de seleção de conta
			System.out.println("╔══════════════════════════════════════════╗");
			System.out.println("║        Bem-vindo ao Banco do Povo        ║");
			System.out.println("╚══════════════════════════════════════════╝");
			System.out.println("Selecione a conta que deseja acessar:\n");
			System.out.println("[1] Conta Poupança");
			System.out.println("[2] Conta Corrente Simples");
			System.out.println("[3] Conta Corrente Especial");
			System.out.println("[0] Sair do sistema");
			System.out.print("\nOpção selecionada: ");
			opcaoConta = scanner.nextInt();
			scanner.nextLine(); // limpa buffer

			switch (opcaoConta) {
			case 1:
				contaAtual = contaPoupanca;
				break;
			case 2:
				contaAtual = contaSimples;
				break;
			case 3:
				contaAtual = contaEspecial;
				break;	
			case 0:
				System.out.println("\nEncerrando atendimento...");
				scanner.close();
				return; // encerra o programa
			default:
				System.out.println("\nOpção inválida. Tente novamente.\n");
				continue; // volta ao início do do-while
			}

			// Se chegou aqui, uma conta válida foi selecionada
			// Agora entra no menu de operações para a conta escolhida
			int opcaoOperacao;
			do {
				System.out.println("\n" + contaAtual); // usa o toString() da conta

				System.out.println("\n╔════════════ OPERACÕES ════════════╗");
				System.out.println("║ [1] Sacar                         ║");
				System.out.println("║ [2] Depositar                     ║");
				System.out.println("║ [3] Consultar saldo               ║");
				System.out.println("║ [0] Voltar                        ║");
				System.out.println("╚═══════════════════════════════════╝");
				System.out.print("\nOpção selecionada: ");
				
				opcaoOperacao = scanner.nextInt();
				scanner.nextLine();

				switch (opcaoOperacao) {

				case 1:
					System.out.println();
					System.out.print("Informe o valor para saque: ");
					double valorSaque = scanner.nextDouble();
					scanner.nextLine();

					boolean saqueRealizado = contaAtual.realizarSaque(valorSaque);

					if (saqueRealizado) {
						System.out.println("\nSaque realizado com sucesso!");
						pausar(scanner);
					} else {
						System.out
								.println("\nSaldo insuficiente para saque de " + String.format("R$ %,.2f", valorSaque));
						pausar(scanner);
					}
					break;

				case 2:
					System.out.println();
					System.out.print("Informe o valor para depósito: ");
					double valorDeposito = scanner.nextDouble();
					scanner.nextLine();

					contaAtual.depositar(valorDeposito);
					System.out.println("\nDepósito realizado com sucesso!");
					pausar(scanner);
					break;

				case 3:
					contaAtual.consultarSaldo();
					pausar(scanner);
					break;

				case 0:
					System.out.println("\nRetornando à seleção de conta...\n");
					break;

				default:
					System.out.println("\nOpção inválida.");
					pausar(scanner);
				}

			} while (opcaoOperacao != 0);

		} while (true); // O loop externo só termina com return no case 0 do menu de contas
	}

	private static void pausar(Scanner scanner) {
		System.out.println("\nPressione Enter para continuar...");
		scanner.nextLine();
	}
}
