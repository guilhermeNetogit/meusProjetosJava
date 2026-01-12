package meusProjetosJava;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class calculoIdadeVoto {// @author user Eclipse -> Github @guilhermeNetogit passou aqui em 11/01/2026 21:01:05; Duda passou aqui 18:52

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("===== Cálculo de Idade e Situação Eleitoral =====\n");
        System.out.print("Digite seu nome completo: ");
        String nome = teclado.nextLine().trim();

        LocalDate dataNascimento = null;
        while (dataNascimento == null) {
            System.out.print("Digite sua data de nascimento (dd/MM/yyyy): ");
            String entrada = teclado.nextLine().trim();
            try {
                dataNascimento = LocalDate.parse(entrada, fmt);
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

        /*String situacao;
        if (idade < 16) {
            situacao = "Você NÃO pode votar ainda.";
        } else if (idade == 16 || idade == 17 || idade > 70) {
            situacao = "Seu voto é OPCIONAL.";
        } else {
            situacao = "Você é OBRIGADO a votar.";
        }*/
        
        String situacao = idade < 16 ? "Você NÃO pode votar ainda..."
                : (idade == 16 || idade == 17 || idade > 70) ? "Seu voto é OPCIONAL..."
                : "Você é OBRIGADO a votar...";

        System.out.println("\n----- Resultado -----");
        System.out.println("Data atual: " + hoje.format(fmt));
        System.out.println("Nome: " + nome);
        System.out.println("Data de nascimento: " + dataNascimento.format(fmt));
        System.out.println("Idade: " + idade + " anos");
        System.out.println("Situação eleitoral: " + situacao);

        teclado.close();
    }
}