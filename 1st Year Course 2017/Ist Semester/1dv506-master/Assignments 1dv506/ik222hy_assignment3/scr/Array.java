package ik222hy_assignment3;
import java.util.*;

public class Array {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int[] arr = { 3, 4, 5, 6, 7 }; // method sum
		System.out.println("Method sum\n sum= " + sum(arr));
		// Method toString
		System.out.println("Check the Method String: ");
		String intArrayString = Arrays.toString(arr);
		System.out.println("arr= " + intArrayString);

		// method addN
		System.out.println("\nCheck Method addN");
		System.out.print(" Enter n to add it to array:");
		int arrToaddN = scan.nextInt();
		int[] arraddN = addN(arr, arrToaddN);
		System.out.println(" Old array = " + toString(arr));
		System.out.println(" New array = " + toString(arraddN));

		// method reverse
		System.out.println("\ntest Method reverse");
		int[] arrreversed = reverse(arr);
		System.out.println(" Old array = " + toString(arr));
		System.out.println(" New array = " + toString(arrreversed));

		// Method boolean hasN
		System.out.println("\nCheck method hasN");
		System.out.print(" Enter No: ");
		int arrTohasN = scan.nextInt();
		System.out.println(" Old array = " + toString(arr));
		System.out.println(" n in array? " + hasN(arr, arrTohasN));

		// Method replaceAll
		System.out.println("\nMethod replaceAll");
		System.out.println("Old array = " + toString(arr));
		System.out.print(" Enter a No from array:");
		int old = scan.nextInt();
		System.out.print(" Enter a New number:");
		int arrw = scan.nextInt();
		scan.nextLine();
		replaceAll(arr, old, arrw);
		System.out.println(" New array = " + toString(arr));

		// method sort
		System.out.println("\ntest of method sort");
		int[] arrTosort = Sort(arr);
		System.out.println(" sorted array = " + toString(arrTosort));
		
		//method hasSubsequence
				System.out.println("\nCheck method hasSubsequence");
				System.out.print(" Enter subsequence like 3,4,5,...: ");
				StringBuilder subSq=new StringBuilder();
				String tSub=scan.nextLine();
				for(int i=0; i<tSub.length(); i++){
					if(tSub.charAt(i)!=',')
						subSq.append(tSub.charAt(i));
				}
				int subL=subSq.length();
				int[] sub=new int[subL];
				for(int i=0; i<subSq.length(); i++){
					sub[i]=Character.getNumericValue(subSq.charAt(i));
				}
				System.out.println(" Old array = " + toString(arr));
				System.out.println(" Subsequence = " + toString(sub));
				System.out.println(" hasSubsequence? "+ hasSubsequence(arr,sub));
				
				scan.close();
	}
	// sum of all element
	public static int sum(int[] arr) {

		int total = 0;
		for (int i : arr)
			total += i;
		return total;
	}
	// array to String
	public static String toString(int[] arr) {
		String str = "{";
		for (int i = 0; i < arr.length; i++) {
			if (i == arr.length - 1)
				str = str + arr[i];
			else
				str = str + arr[i] + ",";
		}
		str = str + "}";
		return str;
	}
	public static int[] addN(int[] arr, int n) {
		// create a new array with extra index
		int[] newArray = new int[arr.length + 1];// adding n

		// copy the integers from series to newArray
		for (int i = 0; i < arr.length; i++) {
			newArray[i] = arr[i]+1;
		}
		// add the new integer to the last index
		newArray[newArray.length - 1] = n+1;
		return newArray;
	}
	// Method Reverse
	public static int[] reverse(int[] arr) {
		int[] reversedArray = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			reversedArray[i] = arr[arr.length - i - 1];
		}
		return reversedArray;
	}

	// Method Boolean hasN
	public static boolean hasN(int[] arr, int n) {

		for (int Element : arr) {
			if (n == Element) {
				return true;
			}
		}
		return false;
	}

	// Method replaceAll
	public static void replaceAll(int[] arr, int old, int nw) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == old) {
				arr[i] = nw;
			}
		}
	}

	// Method SortArray
	public static int[] Sort(int[] arr) {
		int[] sortedArray = new int[arr.length];
		int temp;
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
					sortedArray = arr;
				}
			}
		}
		java.util.Arrays.sort(sortedArray);
		return sortedArray;
	}
	// boolean hasSubsequence
	public static boolean hasSubsequence(int[] arr, int[] sub) {
		
		    boolean hasSubsequence = false;
		    for (int i = 0; i < arr.length; i++) {
		        for (int a = 0; a < sub.length - 1; a++) {
		            if (arr[i] == sub[a] && arr[i + 1] == sub[a + 1]) {
		            	 hasSubsequence = true;
		            } else {
		            	 hasSubsequence = false;
		            }
		        }
		    }
		    return  hasSubsequence;		
}
	}
