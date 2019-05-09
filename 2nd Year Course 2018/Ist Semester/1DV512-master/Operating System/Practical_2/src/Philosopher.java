import java.util.Random;

public class Philosopher implements Runnable {

	private final ChopStick leftChopStick;
	private final ChopStick rightChopStick;

	private Random randomGenerator = new Random();

	private int numberOfEatingTurns = 0;
	private int numberOfThinkingTurns = 0;
	private int numberOfHungryTurns = 0;

	private double thinkingTime = 0;
	private double eatingTime = 0;
	private double hungryTime = 0;

	private int id;
	public boolean Stop;
	public boolean debug;
	private boolean hungry;
	private State state;

	public enum State{
		Eating, 
		Hungry,
		Thinking
	}
	public Philosopher(int id, ChopStick leftChopStick, ChopStick rightChopStick, int seed) {
		this.id = id;
		this.leftChopStick = leftChopStick;
		this.rightChopStick = rightChopStick;

		/*
		 * set the seed for this philosopher. To differentiate the seed from the other
		 * philosophers, we add the philosopher id to the seed. the seed makes sure that
		 * the random numbers are the same every time the application is executed the
		 * random number is not the same between multiple calls within the same program
		 * execution
		 * 
		 * NOTE In order to get the same average values use the seed 100, and set the id
		 * of the philosopher starting from 0 to 4 (0,1,2,3,4). Each philosopher sets
		 * the seed to the random number generator as seed+id. The seed for each
		 * philosopher is as follows: P0.seed = 100 + P0.id = 100 + 0 = 100 P1.seed =
		 * 100 + P1.id = 100 + 1 = 101 P2.seed = 100 + P2.id = 100 + 2 = 102 P3.seed =
		 * 100 + P3.id = 100 + 3 = 103 P4.seed = 100 + P4.id = 100 + 4 = 104 Therefore,
		 * if the ids of the philosophers are not 0,1,2,3,4 then different random
		 * numbers will be generated.
		 */

		randomGenerator.setSeed(id + seed);
	}

	public int getId() {
		return id;
	}

	// Divides the total time with the turns to get the total averages.
	public double Calculator(int state, double timer) {
		if (state == 0)
			return 0.0;
		else
			return (timer / state);
	}

	public double getAverageThinkingTime() {
		double average = Calculator(numberOfThinkingTurns, thinkingTime);
		return average;
	}

	public double getAverageEatingTime() {
		double average = Calculator(numberOfEatingTurns, eatingTime);
		return average;
	}

	public double getAverageHungryTime() {
		double average = Calculator(numberOfHungryTurns, hungryTime);
		return average;
	}

	public int getNumberOfThinkingTurns() {
		return numberOfThinkingTurns;
	}

	public int getNumberOfEatingTurns() {
		return numberOfEatingTurns;
	}

	public int getNumberOfHungryTurns() {
		return numberOfHungryTurns;
	}

	public double getTotalThinkingTime() {
		return thinkingTime;
	}

	public double getTotalEatingTime() {
		return eatingTime;
	}

	public double getTotalHungryTime() {
		return hungryTime;
	}

	@Override
	public void run() {
		/*
		 * TODO Think, Hungry, Eat, Repeat until thread is interrupted Increment the
		 * thinking/eating turns after thinking/eating process has finished. Add
		 * comprehensive comments to explain your implementation, including deadlock
		 * prevention/detection
		 */
		try {
			//while philosophers are not finished eating or not eaten
			while (!Stop) {

//				philosopher thinks
				Think();
//				hunger time
				long hungryTime = System.currentTimeMillis();

//				gets hungry
				state = State.Hungry;

				hungry = true;
				if (debug)
					System.out.println("PHILOSOPHER" + this.getId() + " is "+ state);
//				while philosopher is hungry picks up chopstick starting with the left chopstick then right
				while (hungry) {

					if (leftChopStick.pickUp(this, "leftChopstick")) {

						if (rightChopStick.pickUp(this, "rightChopstick")) {
//						counts the hunger time
							long fullTime = System.currentTimeMillis();
							long hunger = fullTime - hungryTime;
							this.hungryTime += hunger;
							numberOfHungryTurns++;
							
//							philosopher eats now
							Eat();
//							is full now
							hungry = false;
//							puts down the chopsticks one by one
							rightChopStick.putDown(this, "rightChopstick");

						}
						
						leftChopStick.putDown(this, "leftChopstick");

					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void Eat() throws InterruptedException {
		state = State.Eating;

//		eating time is generated and incremented randomly 
		int Eating = randomGenerator.nextInt(1000) + 0;
		
		if (debug)
			System.out.println("PHILOSOPHER" + this.getId() +" "+ state + " turns:" + numberOfEatingTurns + " for "
					+ Eating + " Ms");
		
//		puts the thread to sleep
		Thread.sleep(Eating);
		eatingTime += Eating;
//		increments eating time
		numberOfEatingTurns++;

	}

	private void Think() throws InterruptedException {
		state = State.Thinking;
//		thinking time is generated and incremented randomly 
		int Thinking = randomGenerator.nextInt(1000) + 0;

		if (debug)
			System.out.println("PHILOSOPHER" + this.getId()+ " "+ state + " turns:" + numberOfThinkingTurns + " for "
					+ Thinking + " Ms");
		
//		puts the thread to sleep
		Thread.sleep(Thinking);
		thinkingTime += Thinking;
//		increments thinking time
		numberOfThinkingTurns++;

	}
}
