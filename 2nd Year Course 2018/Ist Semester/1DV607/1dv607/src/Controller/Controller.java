package Controller;

import Model.Member;
import View.UserInterface;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Controller {
    Scanner scan = new Scanner(System.in);

    public static UserInterface ui = new UserInterface();
    private static ArrayList<Member> member_arr = new ArrayList<>();
    private Map<Integer, String> map = new HashMap<>();
    private static String output_text;
    private static String input_text;
    int ans;

    public void show_menu() throws IOException{

            ui.menu();
        ans = ui.inputReader();

            switch (ans){
                case 1: manage_member(); break;
                case 2: listMembers(); break;
                case 3: viewMember(); break;
                case 4: manage_boats(); break;

        }
    }

    public void listMembers()throws IOException{

            ui.listing();
        ans = ui.inputReader();

        switch (ans){
                case 1: compactList(); break;
                case 2: verboseList(); break;
                case 3: show_menu(); break;
                case 4: show_menu(); break;
            }

    }

    public void compactList(){

    }

    public void verboseList(){

    }

    public void manage_member() throws IOException{

            ui.manageMembers();
        ans = ui.inputReader();

            switch (ans){
                case 1: addMember(); break;
                case 2: deleteMember(); break;
                case 3: editMember(); break;
                case 4: show_menu(); break;
            }

    }

    public void addMember() throws IOException{
        System.out.print("Enter your name: ");
        String name = scan.nextLine();
        System.out.print("Enter your personal number: ");
        String personal_number = scan.nextLine();

       // int add = member_arr.get(member_arr.size()-1).getMember_id() + 1;

        if(member_arr.size() == 0){
            member_arr.add(new Member(name, personal_number, 1));
        }
        else{
            member_arr.add(new Member(name, personal_number, member_arr.get(member_arr.size()-1).getMember_id() + 1));
        }

        //write to our database(i.e textfile)
        writeText();
    }

    public void deleteMember() throws IOException{

        boolean member_found = false;

        System.out.println("Enter the name of the member to be deleted - ");
        String del = scan.nextLine();

        for(int i=0; i < member_arr.size(); i++){
                member_found = true;
            if(member_arr.get(i).getName().equalsIgnoreCase(del)){
                int del_id = member_arr.get(i).getMember_id();
                member_arr.remove(i);

            }
        }
        writeText();
        //if member_found is true, delete boat by member id
    }

    public void editMember(){

    }

    public void viewMember(){

    }

    public void manage_boats() throws IOException{

            ui.manageBoats();
        ans = ui.inputReader();

            switch (ans){
                case 1: registerBoat(); break;
                case 2: deleteBoat(); break;
                case 3: editBoat(); break;
                case 4: show_menu(); break;
            }

    }

    public void registerBoat(){

    }

    public void deleteBoat(){

    }

    public void editBoat(){

    }

    private static void readText(String file) throws IOException {
        // BufferedReader reader = new BufferedReader(new
        // FileReader("HistoryOfProgramming.txt"));
        // text = new String(reader.readLine());
        Path p = Paths.get(file);
        input_text = new String(Files.readAllLines(p).toString());

        // reader.close();

        try {
            readText("/Users/as/Desktop/input_data.txt");
            System.out.println(input_text);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeText() throws IOException {
        String out_file = "/Users/as/Desktop/output_data.txt";

        BufferedWriter writer = new BufferedWriter(
                new FileWriter(out_file));
        try {
          //  writeText();

            for(Member member: member_arr){
             /*   writer.write(member.getName());
                writer.write(member.getPersonal_number());
                writer.write(member.getMember_id());
                */
             writer.write(member.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File " + out_file + " not found.");
        }
        writer.close();
    }

}
