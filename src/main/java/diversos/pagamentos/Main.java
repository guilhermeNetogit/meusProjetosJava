package diversos.pagamentos;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Pagamento p1 = new CartaoCredito(100.0, "1234");
        Pagamento p2 = new Pix(50.0);

        p1.processarPagamento();
        p2.processarPagamento();

        if (p1 instanceof Autenticavel) {

            Autenticavel auth = (Autenticavel) p1;

            int tentativas = 0;
            int maxTentativas = 3;
            boolean autenticado = false;

            while (tentativas < maxTentativas) {

                System.out.print("Digite a senha do cartão: ");
                String senhaDigitada = scanner.nextLine();

                if (auth.autenticar(senhaDigitada)) {
                    autenticado = true;
                    break;
                } else {
                    tentativas++;
                    System.out.println("Senha incorreta!");

                    if (tentativas < maxTentativas) {
                        System.out.println("Tentativas restantes: " + (maxTentativas - tentativas));
                    }
                }
            }

            if (autenticado) {
                System.out.println("Cartão autenticado!\n");
                System.out.println("Transação Realizada com Sucesso!\n");
            } else {
                System.out.println("Cartão bloqueado por excesso de tentativas!");
            }
        }

        scanner.close();
    }
}