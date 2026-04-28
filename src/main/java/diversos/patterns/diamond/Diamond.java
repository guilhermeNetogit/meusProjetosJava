package diversos.patterns.diamond;

public class Diamond {// Eclipse -> Github @guilhermeNetogit 25/03/2026 21:40:35

	public static void main(String[] args) {

		int size = 5;

		for (int i = 0; i <= size; i++) {
			for (int j = 0; j < (size - i); j++) {
				System.out.print("  ");
			}
			for (int k = 1; k <= i + 1; k++) {
				System.out.print(k);
			}
			if (i > 0) {
				for (int m = 0; m < (i * 2) - 1; m++) {
					System.out.print(" ");
				}
				for (int k = i + 1; k >= 1; k--) {
					System.out.print(k);
				}
			}

			System.out.println();
		}

		for (int i = size - 1; i >= 0; i--) {
			for (int j = 0; j < (size - i); j++) {
				System.out.print("  ");
			}
			for (int k = 1; k <= i + 1; k++) {
				System.out.print(k);
			}
			if (i > 0) {
				for (int m = 0; m < (i * 2) - 1; m++) {
					System.out.print(" ");
				}

				for (int k = i + 1; k >= 1; k--) {
					System.out.print(k);
				}
			}
			
			System.out.println();
		}

	}

}
