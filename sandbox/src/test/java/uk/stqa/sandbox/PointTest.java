package uk.stqa.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by natla on 31/05/2016.
 */
public class PointTest {

  @Test
  public void testDestance(){
    Point p1 = new Point(1, 1);
    Point p2 = new Point(6, 6);
    Assert.assertEquals(p1.distance(p2), Math.sqrt(50));
    Assert.assertEquals(p1.distance(p2), p2.distance(p1));
  }

}
