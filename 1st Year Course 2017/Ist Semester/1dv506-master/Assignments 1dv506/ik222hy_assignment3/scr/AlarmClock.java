package ik222hy_assignment3;

public class AlarmClock {
	
		// current time
		private int hrs = 0;
		private int mins = 0;

		// time of the alarm(s)
		private int AlarmHours = 0;
		private int AlarmMinutes = 0;

		// whether alarm is on or off
		private boolean AlarmOn = false;

		// constructor
		public AlarmClock(int h, int m) {
			{
				hrs = h;
				mins = m;
			}
			// set both alarms to off
		}

		public void displayTime() {
			System.out.println("Time= " + hrs + "Hour" + mins + "Minute");
		}

		public void setAlarm(int h, int m) {
			AlarmHours = h;
			AlarmMinutes = m;
			AlarmOn = true;
		}

		public void displayAlarm() {
			System.out.println("Alarm Time= " + AlarmHours + "Hour"
		+ AlarmMinutes + "Minute");
		}

		public void tickTime() {
			mins = mins + 1;
			if (mins == 60) {
				hrs = hrs + 1;
				mins = 0;
				}
			if(hrs==24) {
				hrs=0;
			}
			CheckAlarm();
		}

		private void CheckAlarm() {
			if (AlarmOn && mins == AlarmMinutes && hrs == AlarmHours)
				;
			System.out.println("It's Time to WakeUp!");
		}
	}
