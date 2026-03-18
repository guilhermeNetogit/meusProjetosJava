package meusprojetosjava.bancodedados;

public interface SqlDCL {
	
	void grant(String access);
	void remoke(String access);
}
