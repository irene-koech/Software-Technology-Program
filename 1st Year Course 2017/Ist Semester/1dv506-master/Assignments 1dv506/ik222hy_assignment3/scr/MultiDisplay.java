package ik222hy_assignment3;

public class MultiDisplay {
	private String msg= "";
	private int count = 0;

	public void setDisplayMessage(String message) {
		this.msg = message;
	}

	public void setDisplayCount(int count) {
		this.count = count;
	}

	public void display() {
		for (int i = 1; i <= count; i++) {
			System.out.println(msg);
		}
	}

	public void display(String message, int count) {
		this.msg = message;
		this.count = count;
		for (int i = 1; i <= count; i++) {
			System.out.println(message);
		}
	}

	public String getDisplayMessage() {
		return msg;
	}
}
