package meusprojetosjava.resourcebundle;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class ClassResourceBundle {// Eclipse -> Github @guilhermeNetogit 30/03/2026 00:32:50

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		System.out.println("Locale atual " + Locale.getDefault());
		System.out.println();
		
		// 1. Locale padrão do sistema Português BR
        ResourceBundle rb = ResourceBundle.getBundle("resources.meu-texto");
        System.out.println("=== Idioma padrão ===");
        System.out.println(rb.getString("app.title"));
        System.out.println(MessageFormat.format(rb.getString("greeting"), "Mundo"));
        System.out.println(rb.getString("farewell"));
        System.out.println(rb.getString("error.notfound"));
        System.out.println(rb.getString("error.unauthorized"));

        // 2. Inglês US
        Locale localeEnUs = new Locale("en");
        ResourceBundle bundleUs = ResourceBundle.getBundle("resources.meu-texto", localeEnUs);
        System.out.println("\n=== Inglês (US) ===");
        System.out.println(bundleUs.getString("app.title"));
        System.out.println(MessageFormat.format(bundleUs.getString("greeting"), "Mundo"));
        System.out.println(bundleUs.getString("farewell"));
        System.out.println(bundleUs.getString("error.notfound"));
        System.out.println(bundleUs.getString("error.unauthorized"));
        
        // 3. Alemão GE
        Locale localeGe = new Locale("ge");
        ResourceBundle bundleGe = ResourceBundle.getBundle("resources.meu-texto", localeGe);
        System.out.println("\n=== Alemão ===");
        System.out.println(bundleGe.getString("app.title"));
        System.out.println(MessageFormat.format(bundleGe.getString("greeting"), "Mundo"));
        System.out.println(bundleGe.getString("farewell"));
        System.out.println(bundleGe.getString("error.notfound"));
        System.out.println(bundleGe.getString("error.unauthorized"));
		
	}

}