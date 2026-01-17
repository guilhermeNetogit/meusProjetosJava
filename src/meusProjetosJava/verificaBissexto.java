package meusProjetosJava;

import java.util.Scanner;

public class verificaBissexto {// Eclipse <- Github @arauj0duy passou aqui em 16/01/2026 21:26:54
	
	public static void main(String[] args) {
	      try (Scanner teclado = new Scanner(System.in)) {
			System.out.println("Digite um ano para verificação:");
			  	int ano = teclado.nextInt();
				//int ano = localDate.getYear();
				String mes[]={"Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
			 	 int dias[]={31,28,31,30,31,30,31,31,30,31,30,31};
				String anobi;
				int anodias;
			  if ((ano %400 ==0) || ((ano % 4 == 0) && (ano % 100 != 0))){anobi = " é bissexto!";}
			  else {anobi = " não é bissexto.";}
			  if (anobi == " é bissexto"){dias[1]=29;anodias=366;}else{dias[1]=28;anodias=365;}
			  System.out.println("O ano de " +ano + anobi +" e tem " + anodias + " dias."); 
				//for (int c=0;c<=5;c++)
				for (int c=0;c<mes.length;c++) {
				  System.out.println("O mês de: " + mes[c] + " tem " + dias[c] + " dias.");
			}
		}
	}
}
