package ik222hy_assignment3;

public class AlarmMain {

	public static void main(String[] args) {
		AlarmClock alarmClk = new AlarmClock(23, 48);
		alarmClk.displayTime();
		alarmClk.setAlarm(6, 15);
		
		for (int i = 1; i <= 500; i++) {
			alarmClk.tickTime();
		}
		alarmClk.displayTime();
	}

}
