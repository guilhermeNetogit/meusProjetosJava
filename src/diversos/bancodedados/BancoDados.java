package diversos.bancodedados;

public interface BancoDados extends SqlDCL, SqlDDL, SqlDML {
	
	void abrirConexao();
	void fecharConexao();
}
