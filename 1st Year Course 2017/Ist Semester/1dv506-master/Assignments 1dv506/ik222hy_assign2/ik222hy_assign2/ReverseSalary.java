package ik222hy_assign2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ReverseSalary {

	public static void main(String[] args) {
		System.out.print("Enter salaries (and terminate input with 'X'): ");
		ArrayList<String> salary=new ArrayList<>();
		
		Scanner scn=new Scanner(System.in);
		DecimalFormat n=new DecimalFormat("0");
		
		while(scn.hasNextInt()){
			salary.add(scn.next());
		}
		int amount=salary.size();
		Collections.sort(salary);
		
		//Middle
		double middle;
		if(amount%2==0){
			middle=(Integer.parseInt(salary.get(amount/2))
					+Integer.parseInt(salary.get(amount/2-1)))/2.0;
		}
		else{
			middle=Double.parseDouble(salary.get(amount/2));
		}
		System.out.println("Median: "+n.format(middle));
		//average
				double average;
				int total=0;
				for(int i=0; i<amount; i++){
					total=total+Integer.parseInt(salary.get(i));
				}
				average=total/amount;
				System.out.println("Average: "+n.format(average));
				
				//Gap			
				int gap=Integer.parseInt(salary.get(amount-1))
						-Integer.parseInt(salary.get(0));
				System.out.println("Gap: "+gap);
				
				scn.close();
	}

}
