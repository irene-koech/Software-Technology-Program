package ik222hy_assignment3;

public class Radio {
	private int volume = 1;
	private boolean on = false;
	private int channel = 1;

	public String getSettings() {

		if (on == false) {
			channel = 1;
			volume = 1;

			String str = "Setting: On " + on + " Channel "
			             + channel + " Volume " + volume;

			return str;

		} else {

			String str = "Setting: On  " + on + " Channel "
			+ channel + " Volume " + volume;

			return str;

		}
	}

	public boolean turnOn() {

		return on = true;
	}

	public boolean turnOff() {

		return on = false;
	}

	public void setChannel(int newChannel) {

		if (on == false) {

			System.out.println("Radio off ==> No adjustment possible");

		}

		if (newChannel < 1 || newChannel > 10)

			System.out.println("New Channel not within range!");

		else {

			channel = newChannel;
		}
	}

	public void setVolume(int newVolume) {

		if (on == false) {

			System.out.println("Radio off ==> No adjustment possible");
		}

		if (newVolume < 0 || newVolume > 5) {

			System.out.println("New volume not within range!");
		}

		else {

			volume = newVolume;
		}

	}

	public void volumeUp() {

		if (on == false) {

			System.out.println("Radio off ==> No adjustment possible");
		}

		else if (volume < 0 || volume >= 5) {

		}

		else {

			volume = volume + 1;
		}
	}

	public void volumeDown() {

		if (on == false) {

			System.out.println("Radio off ==> No adjustment possible");
		}

		else if (volume <= 0 || volume > 5) {

			System.out.println("New volume not within range!");
		}

		else {

			volume--;

		}
	}

	public void channelUp() {

		if (channel < 10)

			channel++;
	}

	public void channelDown() {

		if (channel > 10)

			channel--;
	}

}
