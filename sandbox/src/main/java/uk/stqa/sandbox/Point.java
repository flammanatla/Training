package uk.stqa.sandbox;

/**
 * Created by natla on 22/05/2016.
 */

public class Point {

  public double x;
  public double y;

  public Point(double x, double y){
    this.x = x;
    this.y = y;
  }

  public double distance(Point other){
    return Math.sqrt(Math.pow((this.x - other.x), 2) + Math.pow((this.y - other.y), 2));
  }

}
