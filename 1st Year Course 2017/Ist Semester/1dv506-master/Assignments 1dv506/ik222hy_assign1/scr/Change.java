package ik222hy_assign1;

import java.util.Scanner;

public class Change {

	public static void main(String[] args) {
		Scanner C = new Scanner(System.in);
		
		System.out.println("Price in Kr");
		double Price=C.nextDouble();
		
		System.out.println("Payment in Kr");
		double Payment =C.nextDouble();
		
		int Change=(int)Math.rint(Payment-Price);
		System.out.println("Change in kronor"+ Change);
	
		int oneT=(int)(Change /1000);	
		Change=(int)(Change %1000);
		
		int fiveh=(int)(Change /500);	
		Change=(int)(Change %500);
		
		int hunderd=(int)(Change /100);	
		Change=(int)(Change %100);
		
		int fifty=(int)(Change /50);	
		Change=(int)(Change %50);
		
		int twenty=(int)(Change /20);	
		Change=(int)(Change %20);
		
		int ten=(int)(Change /10);	
		Change=(int)(Change %10);
		
		int five=(int)(Change /5);	
		Change=(int)(Change %5);
		
		int one=(int)(Change /1);	
		Change=(int)(Change %1);
		
		System.out.println("amount the reseveing : "+"\n" +oneT+ "\n"+fiveh+"\n"+hunderd+"\n"
				+fifty+"\n"+twenty+"\n"+ten+"\n"+five+"\n"+one);
		
		C.close();
		
	}

}
