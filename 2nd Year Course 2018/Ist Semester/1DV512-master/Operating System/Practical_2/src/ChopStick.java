import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChopStick {
	private final int id;
	public boolean debug = false;
	Lock myLock = new ReentrantLock(true);

	public ChopStick(int id) {
		this.id = id;
	}

	/*
	 * TODO Implement the pickup and put down chopstick logic Please note that the
	 * same chopstick can not be picked up by more than one philosopher at a time.
	 * Use the myLock to lock this chopstick. Print the logs only when the lock has
	 * been acquired. The myLock.tryLock() method provides a boolean value
	 * indicating whether the lock was acquired or not.
	 */
	// Locks the chopstick for sometimes

	public boolean pickUp(Philosopher p, String chopstick) throws InterruptedException {
		if (myLock.tryLock(500, TimeUnit.MILLISECONDS)) {
			if (debug)
				System.out.println("PHILOSOPHER " + p.getId() + " pickes up " + chopstick + " " + this);

			return true;
		}
		return false;
	}

	// Unlocks the chopstick

	public void putDown(Philosopher p, String flag) {
		myLock.unlock();
		if (debug)
			System.out.println("PHILOSOPHER_" + p.getId() + " put down " + flag + " " + this);
	}

	@Override
	public String toString() {
		return "Chopstick-" + id;
	}
}
