package javaprograms;

public class PatternPyramidprogram {

	public static void main(String[] args) {

		for (int i = 1; i <= 10; i++)

		{
			for (int space = 1; space <= 10 - i; space++)
				
			{

				System.out.print(" ");
			}

			for (int j = 1; j <= i; j++) {
				System.out.print(j);
			}
			for (int j = i - 1; j >= 1; j--) {
				System.out.print(j);
			}
			System.out.println();
		}
		

	}
}