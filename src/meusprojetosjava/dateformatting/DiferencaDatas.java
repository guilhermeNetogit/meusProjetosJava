package meusprojetosjava.dateformatting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DiferencaDatas {// Eclipse -> Github @guilhermeNetogit 28/03/2026 18:49:28

	public static void main(String[] args) {

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
		Date segundaDt = sdf.parse("28/03/2026 18:32:00:00");

		long diffEmMil = Math.abs(segundaDt.getTime() - primeiraDt.getTime());

		long dias = TimeUnit.DAYS.convert(diffEmMil, TimeUnit.MILLISECONDS);

		long horas = (long) (diffEmMil / (1000 * 60 * 60 /** 24 */
		));

		long minutos = (long) (diffEmMil / (1000 * 60 /** 24 */
		));

		System.out.println("Primeira data: " + primeiraDt.getTime());
		System.out.println("Segunda data: " + segundaDt.getTime());
		System.out.println("Milissegundos: " + diffEmMil);
		System.out.println("Minutos: " + minutos);
		System.out.println("Horas: " + horas);
		System.out.println("Dias: " + dias);

	}

	public static void diferencaDataJava8() {
		LocalDateTime data1 = LocalDateTime.of(2012, 4, 28, 8, 50);
		LocalDateTime data2 = LocalDateTime.now();

		Period periodo = Period.between(data1.toLocalDate(), data2.toLocalDate());

		LocalDateTime inicioPeriodo = data1.plus(periodo);
		Duration duracao = Duration.between(inicioPeriodo, data2);

		long segundos = duracao.toSecondsPart();
		long minutos = duracao.toMinutesPart();
		long horas = duracao.toHours();
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
}