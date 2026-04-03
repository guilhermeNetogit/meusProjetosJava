package database;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import io.github.cdimascio.dotenv.Dotenv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe responsável por estabelecer conexão com o banco de dados SQL Server
 * e realizar a listagem de produtos de uma tabela com formatação dinâmica no console.
 * 
 * <p>Esta classe utiliza o pacote {@code io.github.cdimascio.dotenv} para gerenciar
 * credenciais sensíveis de forma segura através de um arquivo {@code .env}.</p>
 *
 * <p>A exibição dos dados é feita em formato tabular com auto-ajuste de colunas:
 * os dados são carregados em memória na primeira passagem para calcular a largura
 * máxima de cada coluna, e exibidos na segunda passagem com alinhamento dinâmico.</p>
 *
 * @author GitHub guilhermeNetogit
 * @since 02/04/2026 19:55:48
 * @version 2.0 03/04/2026 16:07
 */

public class MyDatabase {
	
	/**
     * Logger para registro de eventos e erros do sistema, seguindo a fachada SLF4J.
     * Utilizado para indicar sucesso na conexão ou falhas durante a execução.
     */
    private static final Logger logger = LoggerFactory.getLogger(MyDatabase.class);

	/**
     * Ponto de entrada principal da aplicação.
     *
     * <p>Fluxo de execução:</p>
     * <ol>
     *   <li>Carrega as variáveis de ambiente do arquivo {@code .env};</li>
     *   <li>Estabelece conexão com o banco de dados SQL Server via JDBC;</li>
     *   <li>Executa a query e armazena os resultados em uma lista;</li>
     *   <li>Calcula dinamicamente a largura máxima de cada coluna;</li>
     *   <li>Imprime o cabeçalho e os dados formatados no console.</li>
     * </ol>
     *
     * @param args Argumentos de linha de comando (não utilizados).
     * @throws IOException Caso ocorra erro ao carregar o arquivo {@code .env}.
     */
	public static void main(String[] args) throws IOException {

		/** Carrega as variáveis de ambiente do arquivo .env */
		Dotenv dotenv = Dotenv.load();

		String url	   = dotenv.get("DB_URL");
		String usuario = dotenv.get("DB_BENUTZER");
		String senha   = dotenv.get("DB_PASSWORT");
		
		/**
         * Query SQL para buscar dados de campos específicos da tabela.
         */		
		String sql = "SELECT CODPROD, DESCRPROD, EANGTIN, CODVOL, DTCREATED, DTALTER FROM TGFPRO";

		/** Formato de exibição das colunas de data e hora */
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		/** Try-with-resources garante o fechamento automático dos recursos JDBC */
		try (Connection conn = DriverManager.getConnection(url, usuario, senha);
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			logger.info("✅ Conexão estabelecida com sucesso!!");
			
			/**
             * Primeira passagem: armazena todas as linhas do ResultSet em memória.
             * Necessário pois o ResultSet não permite releitura, e precisamos
             * conhecer todos os valores antes de calcular as larguras das colunas.
             */
			List<String[]> rows = new ArrayList<>();
			
			while (rs.next()) {

				Timestamp dataInc = rs.getTimestamp("DTCREATED");
				// String dataIncFormatada = (dataInc != null) ? sdf.format(dataInc) : "null";

				Timestamp dataAlt = rs.getTimestamp("DTALTER");
				// String dataAltFormatada = (dataAlt != null) ? sdf.format(dataAlt) : "null";
			
				rows.add(new String[]{
                    String.valueOf(rs.getInt("CODPROD")),
                    rs.getString("DESCRPROD"),
                    rs.getString("EANGTIN"),
                    rs.getString("CODVOL"),
                    dataInc != null ? sdf.format(dataInc) : "null",
                    dataAlt != null ? sdf.format(dataAlt) : "null"
                });
			}
			
			/**
             * Segunda passagem: calcula a largura máxima de cada coluna.
             * O array {@code w} é inicializado com os tamanhos dos cabeçalhos
             * e expandido conforme o conteúdo de cada linha.
             */
			int[] w = { 
					"ID".length(), 
					"Nome".length(), 
					"EAN".length(), 
					"UN".length(), 
					"Data Cadastro".length(),
					"Data Alteração".length()
					};
				
				for (String[] row : rows) {
					for (int i = 0; i < w.length; i++) {
						if (row[i] != null && row[i].length() > w[i]) {
							w[i] = row[i].length();
						}
					}
				}

			/**
             * Monta o formato de impressão dinâmico usando as larguras calculadas.
             * Cada coluna recebe alinhamento à esquerda com a largura exata ({@code %-Ns}).
             */
			String sfmt = String.format(
					"%%-%ds | %%-%ds | %%-%ds | %%-%ds | %%-%ds | %%-%ds%%n",
					w[0], w[1], w[2], w[3], w[4], w[5]);
			
			
			/**
             * Monta a linha separadora proporcional às larguras das colunas.
             * Cada segmento usa {@code -+-} como delimitador entre colunas.
             */
			String sep = "-".repeat(w[0]) + "-+-"
					   + "-".repeat(w[1]) + "-+-"
					   + "-".repeat(w[2]) + "-+-"
					   + "-".repeat(w[3]) + "-+-"
					   + "-".repeat(w[4]) + "-+-"
					   + "-".repeat(w[5]) + "-";
			
			/** Imprime cabeçalho com os nomes das colunas */
			System.out.println("\n📦 Produtos Cadastrados:\n");
			System.out.printf(sfmt, 
					"ID", "Nome", "EAN", "UN", "Data Cadastro", "Data Alteração");
			System.out.println(sep);
			
			/** Imprime as linhas de produtos com formatação alinhada */
			for (String[] row : rows) {
				System.out.printf(sfmt,
						row[0], row[1], row[2], row[3], row[4], row[5]);
			}

		} catch (SQLException e) {
			/** Log de erro simplificado para segurança e clareza ao usuário */
			logger.error("\n❌ Erro ao acessar o banco!");
			System.out.println("\nMotivo: " + e.getMessage());
			System.err.println("Dica: Verifique se as credenciais estão corretas.");
		}
	}
}