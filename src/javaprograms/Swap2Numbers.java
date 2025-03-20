package javaprograms;

public class Swap2Numbers {

	public static void main(String[] args) {

		int a = 10, b = 20;

		System.out.println("Orginal values are: " + a + " " + b);

		int t = a;

		a = b;
		b = t;

		System.out.println("After swapping the values are : " + a + " " + b);

	}

}
