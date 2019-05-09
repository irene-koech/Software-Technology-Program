import java.io.IOException;

public class Main {

    public static void main(String args[]) throws IOException, InterruptedException{
    	DiningPhilosopher dp = new DiningPhilosopher(); // create an instance to DiningPhilosopher class
    	dp.DEBUG = true;
        int simulationTime = 10000;
        int seed = 100;
        if(args.length > 0) // check if parameters are passed as argument
        	simulationTime = Integer.parseInt(args[0]); // the first parameter is the simulation time
        
    	dp.initialize(simulationTime, seed); // initialize the required objects
    	dp.start(); // start the simulation process

    	dp.printTable();
    }
}