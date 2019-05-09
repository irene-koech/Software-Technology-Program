package ik222hy_assignment3;

public class Fraction {
	 // member variables
	   private int Numerator, Denominator;  // stores the fraction data
	   public Fraction()
	   {
	      Numerator = Denominator = 1;
	   }
	   public Fraction(int x, int y){
			Numerator=x;
			Denominator=y;
			if (Denominator == 0){
				System.out.println("\nDenominator can not be 0 ");}}
	   public int getNumerator()
	   {
	      return Numerator; } //Purpose:  provide data to store in numerator member variable
	   public boolean isNegative(int i, int k){  
			
			boolean posF = false;
		
			if (i<0 && k<0){
				posF = false;
			}
			if(i<0 || k<0){
				posF = true;
			}
		return posF;
	}
	   public Fraction addTo(Fraction i) {
	        Fraction sum = new Fraction();
	        sum.Denominator = Denominator * i.Denominator;
	        sum.Numerator = Numerator * i.Denominator 
	                          + Denominator * i.Numerator;
	      
	        return sum;
	    }
	   public Fraction subtract(Fraction i) {
	   Fraction sub = new Fraction();
    sub.Denominator = Denominator * i.Denominator;
    sub.Numerator = Numerator * i.Denominator 
                      - Denominator * i.Numerator;
     return sub;
	   }
	   public Fraction Multiplay(Fraction i){ 
				int a = Numerator*i.Numerator;
				int b= Denominator*i.Denominator;
				
				return new Fraction (a,b);
			}
			public Fraction Divide(Fraction i){ 
				int a = Numerator/i.Numerator;
				int b = Denominator/i.Numerator;
				
				return new Fraction(a,b);
			}
	    public String toString() {
	        return Numerator + "/" + Denominator;
	    }
	    public boolean isEqualsTo(Fraction i) {
	        return (Numerator == i.Numerator) && (Denominator == i.Denominator);
	    }
}


