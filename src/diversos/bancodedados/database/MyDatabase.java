package diversos.bancodedados.database;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;

import io.github.cdimascio.dotenv.Dotenv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe responsável por estabelecer conexão com o banco de dados SQL Server
 * e realizar a listagem de produtos de uma tabela.
 * 
 * <p>Esta classe utiliza o pacote {@code io.github.cdimascio.dotenv} para gerenciar
 * credenciais sensíveis de forma segura através de um arquivo .env.</p>
 *
 * @author GitHub guilhermeNetogit
 * @since 02/04/2026 19:55:48
 */

public class MyDatabase {

	/**
     * Logger para registro de eventos e erros do sistema seguindo a fachada SLF4J.
     */
	private static final Logger logger = LoggerFactory.getLogger(MyDatabase.class);

	/**
     * Ponto de entrada principal da aplicação. 
     * Carrega as configurações de ambiente, conecta ao banco e imprime os produtos no console.
     * 
     * @param args Argumentos de linha de comando (não utilizados).
     * @throws IOException Caso ocorra um erro ao carregar o arquivo de configuração .env.
     */
	public static void main(String[] args) throws IOException {

		/** Carrega as variáveis de ambiente do arquivo .env */
		Dotenv dotenv = Dotenv.load();

		String url = dotenv.get("DB_URL");
		String usuario = dotenv.get("DB_BENUTZER");
		String senha = dotenv.get("DB_PASSWORT");
		
		/**
         * Query SQL para buscar dados de campos específicos da tabela.
         */		
		String sql = "SELECT CODPROD, DESCRPROD, EANGTIN, CODVOL, DTCREATED, DTALTER FROM TGFPRO";

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		/** Try-with-resources garante o fechamento automático dos recursos JDBC */
		try (Connection conn = DriverManager.getConnection(url, usuario, senha);
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();) {

			logger.info("✅ Conexão estabelecida com sucesso!!");

			System.out.println("\n📦 Produtos Cadastrados:\n");
			System.out.println(
					"ID | Nome                             | EAN            | UN |    Data Cadastro    | Data Alteração");
			System.out.println(
					"---|----------------------------------|----------------|----|---------------------|---------------------");

			while (rs.next()) {

				Timestamp dataInc = rs.getTimestamp("DTCREATED");
				String dataIncFormatada = (dataInc != null) ? sdf.format(dataInc) : "null";

				Timestamp dataAlt = rs.getTimestamp("DTALTER");
				String dataAltFormatada = (dataAlt != null) ? sdf.format(dataAlt) : "null";

				System.out.printf("%-3d| %-32s | %-14s | %-2s | %s | %s%n",
						rs.getInt("CODPROD"),
						rs.getString("DESCRPROD"), 
						rs.getString("EANGTIN"), 
						rs.getString("CODVOL"), 
						dataIncFormatada,
						dataAltFormatada);
			}

		} catch (SQLException e) {
			/** Log de erro simplificado para segurança e clareza ao usuário */
			logger.error("\n❌ Erro ao acessar o banco!");
			System.out.println("\nMotivo: " + e.getMessage());
			System.err.println("Dica: Verifique se as credenciais estão corretas.");
		}
	}
}