import Controller.Controller;
import View.UserInterface;

import java.io.IOException;

public class Main {

    public static void main (String [] args) throws IOException{

         UserInterface ui = new UserInterface();
         Controller control = new Controller();

         control.show_menu();
    }
}
