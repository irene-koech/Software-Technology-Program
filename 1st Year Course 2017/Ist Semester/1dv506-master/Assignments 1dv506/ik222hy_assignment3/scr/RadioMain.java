package ik222hy_assignment3;

public class RadioMain {

	public static void main(String[] args) {
		System.out.println("Radio 1");
		Radio r1 = new Radio();
		System.out.println(r1.getSettings()); // Default settings

		// Update Radio 1 settings
		r1.turnOn();
		r1.setVolume(3);
		r1.channelUp();
		r1.channelUp();
		r1.channelUp();
		System.out.println(r1.getSettings()); // New settings
		r1.turnOff();
		System.out.println(r1.getSettings()); // Reset default settings

		System.out.println("\nRadio");
		Radio r2 = new Radio();
		r2.volumeUp(); // Radio off ==> No adjustment possible

		r2.turnOn();
		r2.volumeDown(); // volume = 0 ==> OK!
		r2.volumeDown(); // volume < 0 ==> error and neglect
		r2.setChannel(15); // out of range = ==> error and neglect
		System.out.println(r2.getSettings());
	}

}
