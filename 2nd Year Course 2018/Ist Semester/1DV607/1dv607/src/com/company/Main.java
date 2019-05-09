package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	Scanner scan = new Scanner(System.in);

        System.out.println("Enter your Name: ");
        String name = scan.nextLine();

        System.out.println("Enter your Personal Number: ");
        int personal_number = scan.nextInt();
        scan.close();
    }
}