package meusprojetosjava.castinginstanceof;

public class Teste2 {
		
		Object obj1 = obterString();
		String s1 = (String) obj1;
		
		Object obj2 = "Hello World!";
		String s2 = (String) obj2;
		
		Object obj3 = new Object();
		/*String s3 = (String) obj3;*/
		
		Object obj4 = obterDouble();
		double d4 = (double) obj4;
	
	public static String obterString() {
		return "Hallo Welt!";
	}

	public static double obterDouble() {
		return 3.1415925465;
	}
}
