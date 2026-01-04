package meusProjetosJava;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class calculoIdadeVoto {  // Github Guilherme passou aqui 04/01/26 19:55; Duda passou aqui 18:52

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("=== Cálculo de Idade e Situação Eleitoral ===\n");

        System.out.print("Digite seu nome completo: ");
        String nome = teclado.nextLine().trim();

        LocalDate dataNascimento = null;
        while (dataNascimento == null) {
            System.out.print("Digite sua data de nascimento (dd/MM/yyyy): ");
            String entrada = teclado.nextLine().trim();

            try {
                dataNascimento = LocalDate.parse(entrada, formatter);
                if (dataNascimento.isAfter(LocalDate.now())) {
                    System.out.println("Erro: Data não pode ser no futuro!\n");
                    dataNascimento = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Erro: Data inválida! Use formato dd/MM/yyyy\n");
            }
        }

        LocalDate hoje = LocalDate.now();
        int idade = Period.between(dataNascimento, hoje).getYears();

        String situacao;
        if (idade < 16) {
            situacao = "Você NÃO pode votar ainda.";
        } else if (idade == 16 || idade == 17 || idade > 70) {
            situacao = "Seu voto é OPCIONAL.";
        } else {
            situacao = "Você é OBRIGADO a votar.";
        }

        System.out.println("\n--- Resultado ---");
        System.out.println("Data atual: " + hoje.format(formatter));
        System.out.println("Nome: " + nome);
        System.out.println("Data de nascimento: " + dataNascimento.format(formatter));
        System.out.println("Idade: " + idade + " anos");
        System.out.println("Situação eleitoral: " + situacao);

        teclado.close();
    }

}





