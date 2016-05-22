package uk.stqa.sandbox;

public class Training {
	
	public static void main(String[] args){
		System.out.println("Hello, world!!(one more time)");

		// task #2
    Point p1 = new Point(1, 1);
		Point p2 = new Point(5, 5);
		System.out.println("Distance =" + distance(p1, p2));
	}

  public static double distance(Point p1, Point p2){
    return Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2));
  }

}