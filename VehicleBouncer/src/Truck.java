import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

/**
   Truck class is used to draw a car on some window. <br>
   Since this class extends an abstract class Vehicle, 
   Truck must provide implementations for abstract methods defined in the Vehicle class. <br>
*/
public class Truck extends Vehicle{
   // These values determine the size of truck.
   // If you chgange the value, the program is still be able to draw 
   // a car according to the size of the value.
   private final int TRUCK_WIDTH = 80;
   private final int TRUCK_HEIGHT = 60;
   
   
   /**
         Default constructor, which uses the default constructor of Vehicle.
      */
   public Truck(){
      super();
   }
   
   
   /**
         Constructor with one parameter
         , which uses the constructor of Vehicle with one parameter. <br>
         @param color color for the truck when drawing it
      */
   public Truck(Color color){
      super(color);
   }
   
   
   /**
         Draws a Truck on a window. <br>
         Since this class extends Vehicle class, this method can be called by Vehicle object, too.
         @param g2 Graphics2D object
      */
   public void drawVehicle(Graphics2D g2){
      // diameter of the car wheel
      final int DIAMETER = TRUCK_WIDTH * 4 / 30;
      
      // draw left (small) body of a truck
      Rectangle leftBody = new Rectangle(getXCoordinate()
         , (getYCoordinate() + (TRUCK_HEIGHT * 5 / 21))
         , TRUCK_WIDTH * 7 / 30
         , (TRUCK_HEIGHT - (TRUCK_HEIGHT * 5 / 21) - DIAMETER));
      g2.setColor(getColorComponent());
      g2.fill(leftBody);
      g2.setColor(Color.black);
      g2.draw(leftBody);
      
      // draw right (big) body of a truck
      Rectangle rightBody = new Rectangle(getXCoordinate() + TRUCK_WIDTH * 7 / 30
         , getYCoordinate(), (TRUCK_WIDTH - (TRUCK_WIDTH * 7 / 30))
         , (TRUCK_HEIGHT - DIAMETER));
      g2.setColor(getColorComponent());
      g2.fill(rightBody);
      g2.setColor(Color.black);
      g2.draw(rightBody);
      
      // draw truck wheels
      final int YCOORD = getYCoordinate() + TRUCK_HEIGHT - DIAMETER; // in common for all wheels
      Ellipse2D.Double wheel1 = new Ellipse2D.Double
         (getXCoordinate(), YCOORD, DIAMETER, DIAMETER);
      // x coordinate of wheel2 must be the same as x coordinate of rightBody
      Ellipse2D.Double wheel2 = new Ellipse2D.Double
         (getXCoordinate() + TRUCK_WIDTH * 7 / 30, YCOORD, DIAMETER, DIAMETER);
      Ellipse2D.Double wheel3 = new Ellipse2D.Double
         (getXCoordinate() + TRUCK_WIDTH * 7 / 30 + DIAMETER, YCOORD, DIAMETER, DIAMETER);
      Ellipse2D.Double wheel4 = new Ellipse2D.Double
         (getXCoordinate() + TRUCK_WIDTH - DIAMETER - DIAMETER, YCOORD, DIAMETER, DIAMETER);
      Ellipse2D.Double wheel5 = new Ellipse2D.Double
         (getXCoordinate() + TRUCK_WIDTH - DIAMETER, YCOORD, DIAMETER, DIAMETER);
      g2.setColor(getColorComponent());
      g2.fill(wheel1);
      g2.fill(wheel2);
      g2.fill(wheel3);
      g2.fill(wheel4);
      g2.fill(wheel5);
      g2.setColor(Color.black);
      g2.draw(wheel1);
      g2.draw(wheel2);
      g2.draw(wheel3);
      g2.draw(wheel4);
      g2.draw(wheel5);
   }
   
   
   /**
         Returns truck width (, which is a constant). <br>
         This method overrides the method in Vehicle class.
         @return truck's width
      */
   public int getVehicleWidth(){
      return TRUCK_WIDTH;
   }
   
   
   /**
         Returns truck height (, which is a constant). <br>
         This method overrides the method in Vehicle class.
         @return truck's height
      */
   public int getVehicleHeight(){
      return TRUCK_HEIGHT;
   }
}


