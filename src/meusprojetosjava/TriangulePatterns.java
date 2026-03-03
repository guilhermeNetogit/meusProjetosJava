package meusprojetosjava;

public class TriangulePatterns {//VS Code -> GitHub @guilhermeNetogit 22:00:13

    public static void main(String[] args) {
        RightTriangulePattern(7);

        LeftTrianglePattern(7);
    }

    // Método para o primeiro padrão
    public static void RightTriangulePattern(int row) {
        for (int i = 0 ; i <= row; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        for (int i = row - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }     
    }   

    // Método para o segundo padrão
    public static void LeftTrianglePattern(int row) {
        for (int i = 0; i <= row; i++) {
            //Loop j: imprime os espaços em branco antes dos asteriscos
            for (int j = 0; j < (row - i); j++) {
                System.out.print("  ");
            }
            //Loop k: imprime os asteriscos
            for (int k = 0; k <= i; k++) {
                System.out.print("* ");
            }
            System.out.println();
        } 
        for (int i = row - 1; i >= 0; i--) {
            for (int j = 0; j < (row - i); j++) {
                System.out.print("  ");
            }
            for (int k = 0; k <= i; k++) {
                System.out.print("* ");
            }
            System.out.println();
        }         
    }
}
