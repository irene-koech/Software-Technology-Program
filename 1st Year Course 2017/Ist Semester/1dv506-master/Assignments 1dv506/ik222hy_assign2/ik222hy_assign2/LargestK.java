package ik222hy_assign2;

import java.util.Scanner;

public class LargestK {

	public static void main(String[] args) {
		Scanner scn=new Scanner(System.in);
		System.out.print("Enter integer: ");
		int n=scn.nextInt();
		if(n<0){
			System.out.println("Error, must be an positive integer!");
			System.exit(0);
		}
		
		int N=0, sum=0;
		
		while(sum<=n){
			N += 2;
			sum=sum + N;
		}
		System.out.println("N= "+N+"==> 2+4+6....+N"+(N-2));
		scn.close();

	}

}
