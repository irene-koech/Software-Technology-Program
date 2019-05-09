package ik222hy_assignment3;

public class PointMain {

	public static void main(String[] args) {

		Point p1 = new Point();
		Point p2 = new Point(3, 4);
		
		System.out.println(p1.toString()); 
		System.out.println(p2.toString()); 
		
		if(p1.isEqualsTo(p2))
			System.out.println("The two points are equal");
		
		double distance = p1.distanceTo(p2);
		System.out.println("Point Distance: " + distance);
		
		p2.move(5, -2);
		p1.moveToXY(8, 2);
		
		if(p1.isEqualsTo(p2))
			System.out.println("The two points are equal. ");

	}

}
