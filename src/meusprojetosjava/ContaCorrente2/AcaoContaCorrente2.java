package meusprojetosjava.ContaCorrente2;

import java.util.Scanner;

public class AcaoContaCorrente2 {// VSCode -> Github @guilhermeNetogit 04/03/2026 20:45:54

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        ContaCorrente2 conta = new ContaCorrente2("123456", "0123", true, 500, 0, 10);
        /*conta.numero = "123456";
        conta.agencia = "0123";
        conta.especial = true;
        conta.limiteEspecial = 500;
        conta.valorEspecialUsado = 0;
        conta.saldo = 10;*/

        int opcao;

        do {
            System.out.println("======= MENU =======");
            System.out.println("Conta: " + conta.getNumero());
            System.out.println("[1] Sacar");
            System.out.println("[2] Depositar");
            System.out.println("[3] Consultar saldo");
            System.out.println("[0] Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                	System.out.println();
                    System.out.print("Informe o valor para saque: ");
                    double valorSaque = scanner.nextDouble();

                    boolean saqueRealizado = conta.realizarSaque(valorSaque);

                    if (saqueRealizado) {
                        System.out.println("Saque realizado com sucesso!\n");
                    } else {
                        System.out.println("Saldo insuficiente.\n");
                    }
                    break;

                case 2:
                	System.out.println();
                    System.out.print("Informe o valor para depósito: ");
                    double valorDeposito = scanner.nextDouble();
                    
                    conta.depositar(valorDeposito);
                    System.out.println("Depósito realizado com sucesso!\n");
                    break;
                    
                case 3:
                    conta.consultarSaldo();
                    
                    if (conta.verificarUsoChequeEspecial()) {
                    	double valorUsado = conta.getSaldo()* -1;
                        System.out.println("A conta está utilizando " + valorUsado + " do cheque especial.\n");
                    } else {
                        System.out.println("A conta NÃO está utilizando cheque especial.\n");
                    }
                    break; 

                case 0:
                    System.out.println("\nEncerrando atendimento...");
                    break;

                default:
                    System.out.println("Opção inválida.\n");
            }

        } while (opcao != 0);

        scanner.close();
    }
}