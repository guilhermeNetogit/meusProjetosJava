package diversos.dateformatting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
//import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DiferencaDatas {// Eclipse -> Github @guilhermeNetogit 29/03/2026 16:37:41

	public static void main(String[] args) {
		
		// primeiro ano bissexto a partir de 1970 foi 1972;

	    long milisEntrada = 1774878613020L;
	    
	    converterMilisParaData(milisEntrada);
		
		try {
			diferencaDataAteJava7();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		System.out.println("-------------");
		diferencaDataJava8();

		System.out.println("-------------");
		diferencaDataJava8ComTempo();

		System.out.println("-------------");
		diferencaDataJava8ComChronoUnit();
	
	}

	public static void diferencaDataAteJava7() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS");
		Date primeiraDt = sdf.parse("28/04/2012 08:50:00:00");
		//Date segundaDt = sdf.parse("24/06/1987 23:53:00:00"); // data pode ser alterada manualmente;
		Date segundaDt = new Date();

		long diffEmMil = Math.abs(segundaDt.getTime() - primeiraDt.getTime());

		long dias = TimeUnit.DAYS.convert(diffEmMil, TimeUnit.MILLISECONDS);

		long horas = (long) (diffEmMil / (1000 * 60 * 60 /** 24 */
		));

		long minutos = (long) (diffEmMil / (1000 * 60 /** 24 */
		));

		long segundos = (long) (diffEmMil / (1000 /** 60 * 24 */
		));

		System.out.println("Primeira data: " + primeiraDt.getTime());
		System.out.println("Segunda data: " + segundaDt.getTime());
		System.out.println("Milissegundos: " + diffEmMil);
		System.out.println("Segundos: " + segundos);
		System.out.println("Minutos: " + minutos);
		System.out.println("Horas: " + horas);
		System.out.println("Dias: " + dias);

	}

	public static void diferencaDataJava8() {
		LocalDateTime data1 = LocalDateTime.of(2012, 4, 28, 8, 50);
		LocalDateTime data2 = LocalDateTime.now();
		// LocalDateTime data2 = LocalDateTime.of(2024, 4, 28, 8, 51); // para usar uma
		// data especifica

		Period periodo = Period.between(data1.toLocalDate(), data2.toLocalDate());

		LocalDateTime inicioPeriodo = data1.plus(periodo);
		Duration duracao = Duration.between(inicioPeriodo, data2);

		long segundos = duracao.toSecondsPart();
		long minutos = duracao.toMinutesPart();
		long horas = duracao.toHoursPart();
		int dias = periodo.getDays();
		int meses = periodo.getMonths();
		int anos = periodo.getYears();

		System.out.printf("%-9s %d%n", "Anos:", anos);
		System.out.printf("%-9s %d%n", "Meses:", meses);
		System.out.printf("%-9s %d%n", "Dias:", dias);
		System.out.printf("%-9s %d%n", "Horas:", horas);
		System.out.printf("%-9s %d%n", "Minutos:", minutos);
		System.out.printf("%-9s %d%n", "Segundos:", segundos);

	}

	public static void diferencaDataJava8ComTempo() {
		LocalDateTime data1 = LocalDateTime.of(2019, 1, 1, 6, 30);
		LocalDateTime data2 = LocalDateTime.of(2019, 1, 1, 8, 30);

		Duration duracao = Duration.between(data1, data2);
		long diff = duracao.toHours();

		System.out.println(diff + " horas");
	}

	public static void diferencaDataJava8ComChronoUnit() {
		LocalDateTime data1 = LocalDateTime.of(2019, 1, 1, 8, 30);
		LocalDateTime data2 = LocalDateTime.of(2019, 1, 2, 8, 30);

		long diff = ChronoUnit.HOURS.between(data1, data2);
		long diffDias = ChronoUnit.DAYS.between(data1, data2);

		System.out.println(diff + " horas");
		System.out.println(diffDias + " dias");
	}

	public static void converterMilisParaData(long milissegundos) {
		// Converte milis para LocalDateTime usando o fuso horário padrão do sistema
		LocalDateTime dataGerada = LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(milissegundos),
				java.time.ZoneId.systemDefault());

		System.out.println("Data gerada a partir dos Milissegundos informado: "
				+ dataGerada.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss \n")));
	}

}