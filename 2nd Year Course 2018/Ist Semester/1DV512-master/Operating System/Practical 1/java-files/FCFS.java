/*
 
/*
 * File:	Process.java
 * Course: 	Operating Systems
 * Code: 	1DV512
 * Author: 	Suejb Memeti
 * Date: 	November, 2018
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FCFS {

	// The list of processes to be scheduled
	public ArrayList<Process> processes;

	// Class constructor
	public FCFS(ArrayList<Process> processes) {
		this.processes = processes;
	}

	// Displays chart
	public static void main(String[] args) {
		FCFS.chartDisplay();
	}

	private static void chartDisplay() {
		ArrayList<Process> processID = new ArrayList<Process>();
		FCFS list = new FCFS(processID);

		Process process_1 = new Process(1, 0, 18);
		Process process_2 = new Process(2, 3, 2);
		Process process_3 = new Process(3, 25, 5);
		Process process_4 = new Process(4, 29, 2);
		Process process_5 = new Process(5, 33, 7);

		processID.add(process_1);
		processID.add(process_2);
		processID.add(process_3);
		processID.add(process_4);
		processID.add(process_5);

		list.run();
		list.printTable();
		System.out.println("");
		list.printGanttChart();
	}

	public void run() {
		// Sorting the processes
		Collections.sort(processes, new Comparator<Process>() {
			@Override
			public int compare(Process process_1, Process process_2) {
				return process_1.getArrivalTime() - process_2.getArrivalTime();
			}
		});

		// Set Time
		int timer = 0;

		for (int i = 0; i < processes.size(); i++) {
			Process a_process = processes.get(i);

			// Calculate the total time
			if (timer >= a_process.getArrivalTime()) {
				timer = timer + a_process.getBurstTime();

			} else {

				timer = a_process.getArrivalTime() + a_process.getBurstTime();
			}

			a_process.setCompletedTime(timer);

			// Calculate Turn around Time
			a_process.setTurnaroundTime(timer - a_process.getArrivalTime());

			// Calculate Waiting Time
			a_process.setWaitingTime(timer - a_process.getArrivalTime() - a_process.getBurstTime());
		}
	}

	public void printTable() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n\n List of Processor \n");
		sb.append("_____________________________________________\n");
		sb.append("P:ID\t| AT\t| BT\t| CT\t| TAT\t| WT \n");
		sb.append("_____________________________________________\n");

		for (Process process : processes)
			sb.append

			(String.format("%d\t| %d\t| %d\t| %d\t| %d\t| %d\n", 
					process.getProcessId(), 
					process.getArrivalTime(),
					process.getBurstTime(),
					process.getCompletedTime(),
					process.getTurnaroundTime(),
					process.getWaitingTime()

			));
		sb.append("_____________________________________________");
		System.out.print(sb.toString());

	}

	public void printGanttChart() {
		System.out.println("\n%%%%%%%%%%%%%%%%%%%%%%% GANTT CHART %%%%%%%%%%%%%%%%%%%%%% ");

		StringBuilder line = new StringBuilder();
		StringBuilder time = new StringBuilder();
		StringBuilder vertical = new StringBuilder();

		int currentTime = 0, idleTime;

		Process pro;
		for (int i = 0; i < processes.size(); i++) {
			line.append("=");

			pro = processes.get(i);

			// Idle Time
			idleTime = pro.getArrivalTime() > currentTime ? pro.getArrivalTime() - currentTime : 0;
			for (int k = 0; k < idleTime; k++) {
				line.append("=");

				if (k == 0) {
					vertical.append("|");
					line.append("==");
					time.append(currentTime);
				}
					vertical.append("#");
				if (k != 0 || currentTime < 10) {
					time.append(" ");

					if (k == idleTime - 1) {
						vertical.append("|");
						time.append(" ");
						currentTime += idleTime;
					}

				}

			}

			int start = pro.getCompletedTime() - currentTime;
			vertical.append("|");
			time.append(currentTime);

			for (int l = 0; l < start; l++) {
				line.append("=");

				int midTime = start % 2 == 0 ? start / 2 : (start + 1) / 2;
				if (l == midTime) {

					vertical.append("P" + pro.getProcessId());

					if (start % 2 != 0) {
						vertical.append(" ");
						time.append(" ");
					}

					for (int j = 0; j < ("P" + pro.getProcessId()).length(); j++) {
						line.append("=");
						time.append(" ");
					}

				} else {

					vertical.append(" ");
					time.append(" ");
				}

			}
			currentTime = pro.getCompletedTime();
			vertical.append("|");
		}

		time.append(currentTime);
		System.out.println(
				line.toString() + "\n" + vertical.toString() + "\n" + line.toString() + "\n" + time.toString());
		System.out.println("\n# -> CPU idle time");

	}

}