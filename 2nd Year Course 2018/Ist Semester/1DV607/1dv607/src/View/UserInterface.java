package View;

import java.util.Scanner;

public class UserInterface {
    Scanner scan = new Scanner(System.in);

    public void menu(){
        System.out.println("Welcome, please choose from the menu by writing the number of the option ");
        System.out.println(" 1. Manage Members ");
        System.out.println(" 2. List all members ");
        System.out.println(" 3. View a specific member ");
        System.out.println(" 4. Manage boats ");

    }

    public int inputReader() throws IndexOutOfBoundsException{
        int input = scan.nextInt();
        if( input <= 0 && input >=9 ){
            System.out.println("Provide a number from the menu ONLY!");
        }
        return input;
    }

    public void listing(){
        System.out.println("1. View Compact List ");
        System.out.println("2. View Verbose List ");
        System.out.println("3. EXIT ");


    }
    public void manageMembers(){
        System.out.println(" 1. Register a new member ");
        System.out.println(" 2. Delete a member ");
        System.out.println(" 3. Edit a member ");
        System.out.println(" 4. EXIT ");

    }

    public void addMem_form(){
        System.out.print("Enter your name: ");
        String name = scan.nextLine();
        System.out.print("Enter your personal number: ");
        int personal_number = scan.nextInt();
    }

    public void manageBoats(){
        System.out.println(" 1. Register a new boat ");
        System.out.println(" 2. Delete a boat ");
        System.out.println(" 3. Edit a boat ");
        System.out.println(" 4. EXIT ");

    }

}
