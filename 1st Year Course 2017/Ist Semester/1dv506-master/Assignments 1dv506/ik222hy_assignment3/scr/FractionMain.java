package ik222hy_assignment3;

public class FractionMain {

	public static void main(String[] args) {
		Fraction Numerator = new Fraction(8, 3);
		Fraction Denominator = new Fraction(4, 5);
		
		System.out.println(Numerator.isNegative(2, 1));
		System.out.println(Denominator.isNegative(3, 2));
		System.out.println(Numerator.addTo(Denominator));
		System.out.println(Numerator.Multiplay(Denominator));
		System.out.println(Numerator.Divide(Denominator));
		System.out.println(Numerator.isEqualsTo(Denominator));
		System.out.println(Numerator.toString());
		System.out.println(Denominator.toString());

	}

}
