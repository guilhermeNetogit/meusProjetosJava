package diversos.dateformatting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Classe utilitária para demonstração de diferentes formas de cálculo
 * de diferença entre datas em Java.
 *
 * <p>Contém exemplos utilizando:
 * <ul>
 *   <li>API antiga (até Java 7) com {@link java.util.Date} e {@link java.text.SimpleDateFormat}</li>
 *   <li>API moderna (Java 8+) com {@link java.time.LocalDateTime}</li>
 *   <li>Uso de {@link java.time.Duration}</li>
 *   <li>Uso de {@link java.time.temporal.ChronoUnit}</li>
 * </ul>
 *
 * <p>Também inclui conversão de milissegundos (epoch) para data formatada.
 *
 * <p>Observação: Os cálculos consideram diferenças absolutas e podem ser ajustados
 * conforme necessidade (datas fixas ou dinâmicas).
 *
 * @author GitHub guilhermeNetogit
 * @since 28/03/2026 18:49:28
 * @version 22/04/2026 12:27:53
 */

public class DiferencaDatas {// Eclipse -> Github @guilhermeNetogit 28/03/2026 18:49:28
	
	/**
	 * Método principal responsável por executar todos os exemplos
     * de cálculo de diferença entre datas.
     *
	 * @param args argumentos de linha de comando (não utilizados)
	 */
	public static void main(String[] args) {

		// primeiro ano bissexto a partir de 1970 foi 1972;

		long milisEntrada = 1774878613020L;

		converterMilisParaData(milisEntrada);

		try {
			diferencaDataAteJava7();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		System.out.println("--------------------------------------------");
		diferencaDataJava8();
		System.out.println("--------------------------------------------");

		System.out.println("\n-------------");
		diferencaDataJava8ComTempo();

		System.out.println("-------------");
		diferencaDataJava8ComChronoUnit();

	}

	/**
     * Calcula a diferença entre duas datas utilizando a API legada (até Java 7).
     *
     * <p>Utiliza {@link java.util.Date}, {@link java.text.SimpleDateFormat}
     * e {@link java.util.concurrent.TimeUnit} para conversões de tempo.
     *
     * <p>A primeira data é fixa e a segunda é a data atual.
     *
     * @throws ParseException caso ocorra erro ao converter a String para data
     */
	public static void diferencaDataAteJava7() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS");
		Date primeiraDt = sdf.parse("28/04/2012 08:50:00:00");
		// Date segundaDt = sdf.parse("24/06/1987 23:53:00:00"); // data pode ser alterada manualmente;
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
		System.out.println();

	}

	/**
     * Calcula a diferença entre duas datas utilizando a API moderna do Java 8.
     *
     * <p>O cálculo é feito em partes:
     * <ul>
     *   <li>Anos</li>
     *   <li>Meses</li>
     *   <li>Dias</li>
     *   <li>Horas, minutos e segundos via {@link java.time.Duration}</li>
     * </ul>
     *
     * <p>Também calcula quanto tempo falta para completar o próximo ano
     * a partir da data inicial.
     */
	public static void diferencaDataJava8() {
		LocalDateTime data1 = LocalDateTime.of(2012, 4, 28, 8, 50);
		LocalDateTime data2 = LocalDateTime.now();
		// LocalDateTime data2 = LocalDateTime.of(2024, 4, 28, 8, 51); // para usar uma
		// data especifica

		// 1. Cálculo de quanto tempo se passou
		LocalDateTime tempPassado = LocalDateTime.from(data1);
		long anos = tempPassado.until(data2, ChronoUnit.YEARS);
		tempPassado = tempPassado.plusYears(anos);

		long meses = tempPassado.until(data2, ChronoUnit.MONTHS);
		tempPassado = tempPassado.plusMonths(meses);

		long dias = tempPassado.until(data2, ChronoUnit.DAYS);
		tempPassado = tempPassado.plusDays(dias);

		Duration duracaoPassada = Duration.between(tempPassado, data2);

		int horas = duracaoPassada.toHoursPart();
		int minutos = duracaoPassada.toMinutesPart();
		int segundos = duracaoPassada.toSecondsPart();

		System.out.println("--- Tempo Passado ---");
		System.out.printf("%-9s %d%n" + "%-9s %d%n" + "%-9s %d%n" + "%-9s %d%n" + "%-9s %d%n" + "%-9s %d%n", "Anos:",
				anos, "Meses:", meses, "Dias:", dias, "Horas:", horas, "Minutos:", minutos, "Segundos:", segundos);

		// 2. Cálculo de quanto falta para o próximo ano cheio
		// Alvo = data original + (anos que já passaram + 1)
		LocalDateTime proximoAniversario = data1.plusYears(anos + 1);

		LocalDateTime tempFalta = LocalDateTime.from(data2);
		long anosFalta = tempFalta.until(proximoAniversario, ChronoUnit.YEARS);
		tempFalta = tempFalta.plusMonths(anosFalta);

		long mesesFalta = tempFalta.until(proximoAniversario, ChronoUnit.MONTHS);
		tempFalta = tempFalta.plusMonths(mesesFalta);

		long diasFalta = tempFalta.until(proximoAniversario, ChronoUnit.DAYS);
		tempFalta = tempFalta.plusDays(diasFalta);

		// Ajuste para o cálculo de tempo exato (horas/minutos) faltantes
		Duration duracaoFaltante = Duration.between(tempFalta, proximoAniversario);

		long segundosFalta = duracaoFaltante.toSecondsPart();
		long minutosFalta = duracaoFaltante.toMinutesPart();
		long horasFalta = duracaoFaltante.toHoursPart();

		System.out.println("\n--- Quanto Falta para completar " + (anos + 1) + " anos ----");
		System.out.printf("%-9s %d%n" + "%-9s %d%n" + "%-9s %d%n" + "%-9s %d%n" + "%-9s %d%n" + "%-9s %d%n", "Anos:",
				anosFalta, "Meses:", mesesFalta, "Dias:", diasFalta, "Horas:", horasFalta, "Minutos:", minutosFalta,
				"Segundos:", segundosFalta);
	}

	/**
     * Exemplo simples de cálculo de diferença entre duas datas
     * utilizando {@link java.time.Duration}.
     *
     * <p>Retorna a diferença total em horas.
     */
	public static void diferencaDataJava8ComTempo() {
		LocalDateTime data1 = LocalDateTime.of(2019, 1, 1, 6, 30);
		LocalDateTime data2 = LocalDateTime.of(2019, 1, 1, 8, 30);

		Duration duracao = Duration.between(data1, data2);
		long diff = duracao.toHours();

		System.out.println(diff + " horas");
	}

	/**
     * Exemplo de cálculo de diferença utilizando {@link java.time.temporal.ChronoUnit}.
     *
     * <p>Permite calcular diretamente diferenças em unidades específicas,
     * como horas e dias.
     */
	public static void diferencaDataJava8ComChronoUnit() {
		LocalDateTime data1 = LocalDateTime.of(2019, 1, 1, 8, 30);
		LocalDateTime data2 = LocalDateTime.of(2019, 1, 2, 8, 30);

		long diff = ChronoUnit.HOURS.between(data1, data2);
		long diffDias = ChronoUnit.DAYS.between(data1, data2);

		System.out.println(diff + " horas");
		System.out.println(diffDias + " dias");
	}

	/**
     * Converte um valor em milissegundos (epoch) para uma data formatada.
     *
     * <p>A conversão utiliza o fuso horário padrão do sistema.
     *
     * @param milissegundos valor em milissegundos desde 01/01/1970 (Epoch)
     */
	public static void converterMilisParaData(long milissegundos) {
		// Converte milis para LocalDateTime usando o fuso horário padrão do sistema
		LocalDateTime dataGerada = LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(milissegundos),
				java.time.ZoneId.systemDefault());

		System.out.println("Data gerada a partir dos Milissegundos informado: "
				+ dataGerada.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss \n")));
	}

}