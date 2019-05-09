package ik222hy_assign2;
import java.util.Scanner;
public class SecondLargest {

	public static void main(String[] args) {
		Scanner sec = new Scanner(System.in);
		System.out.print("Provide 10 integers: ");
		int s;	
		int maxi=sec.nextInt();
		int sl=sec.nextInt();	
		for(int i=1; i<=8; i++){
			s=sec.nextInt();
			if(s>maxi){    
				sl=maxi;
				maxi=s;
			}
			if(s<maxi && s>sl)
				sl=s;
		}
		System.out.println("The second largest is: "+sl);
		sec.close();
	}

}
