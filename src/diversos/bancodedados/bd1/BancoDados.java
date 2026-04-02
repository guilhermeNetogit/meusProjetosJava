package diversos.bancodedados.bd1;

public interface BancoDados extends SqlDCL, SqlDDL, SqlDML {
	
	void abrirConexao();
	void fecharConexao();
}
