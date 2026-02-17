package meusprojetosjava;

import java.util.Scanner;

public class SwitchCase {// Eclipse -> Github @guilhermeNetogit 17/02/2026 20:09:23

    // Enum já com nome formatado
    enum DiaSemana {
        DOMINGO("Domingo"),
        SEGUNDA("Segunda-feira"),
        TERCA("Terça-feira"),
        QUARTA("Quarta-feira"),
        QUINTA("Quinta-feira"),
        SEXTA("Sexta-feira"),
        SABADO("Sábado");

        private final String nomeFormatado;

        DiaSemana(String nomeFormatado) {
            this.nomeFormatado = nomeFormatado;
        }

        public String getNome() {
            return nomeFormatado;
        }
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {

            System.out.println("Digite um número de 1 a 7: ");
            int numero = scan.nextInt();

            if (numero < 1 || numero > 7) {
                System.out.println("Não é um dia da semana válido!");
                System.out.println("Tente novamente...\n");
                continue;
            }

            // ============================
            // ✅ OPÇÃO 1 - ENUM PROFISSIONAL
            // ============================

            DiaSemana dia = DiaSemana.values()[numero - 1];
            System.out.println(dia.getNome());

            // ============================
            // ❌ OPÇÃO 2 - SWITCH-CASE
            // (mantido comentado como solicitado)
            // ============================

            /*
            switch(numero) {
                case 1: System.out.println("Domingo"); break;
                case 2: System.out.println("Segunda-feira"); break;
                case 3: System.out.println("Terça-feira"); break;
                case 4: System.out.println("Quarta-feira"); break;
                case 5: System.out.println("Quinta-feira"); break;
                case 6: System.out.println("Sexta-feira"); break;
                case 7: System.out.println("Sábado"); break;
            }
            */

            System.out.println("\nDeseja consultar outro número? (s/n)");
            String resposta = scan.next();

            if (!resposta.equalsIgnoreCase("s")) {
                continuar = false;
                System.out.println("Fim da consulta.");
            }

            System.out.println();
        }

        scan.close();
    }
}
