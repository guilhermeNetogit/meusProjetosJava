package diversos.trycatch.exceptionpropria;

@SuppressWarnings("serial")
public class DivisaoNaoExata extends Exception {// Eclipse -> Github @guilhermeNetogit 19/03/2026 22:11:42

	private int num;
	private int dem;
	
	public DivisaoNaoExata(int num, int dem) {
		super();
		this.num = num;
		this.dem = dem;
	}

	@Override
	public String toString() {
		return "Resultado de " + num + "/" + dem + " não é um inteiro!";
	}
	
}