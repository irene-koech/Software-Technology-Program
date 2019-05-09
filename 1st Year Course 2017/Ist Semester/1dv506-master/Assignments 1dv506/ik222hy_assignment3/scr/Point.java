package ik222hy_assignment3;

public class Point {
	private int x = 0, y = 0;

	public Point() {
	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "(" + x + "," + y + ")";
	}

	public boolean isEqualsTo(Point p) {
		return this.x == ((Point) p).x && this.y == ((Point) p).y;
	}

	public double distanceTo(Point p) {
		double distance =0;
				distance =Math.hypot(this.x-p.x, this.y-p.y);
				return distance;
	}
	public void move(int x, int y) {
		this.x = this.x + x;
		this.y += y;
	}

	public void moveToXY(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
