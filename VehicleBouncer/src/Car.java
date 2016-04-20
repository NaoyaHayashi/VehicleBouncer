import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;

/**
   Car class is used to draw a car on some window. <br>
   Since this class extends an abstract class Vehicle, 
   Car must provide  implementations for abstract methods defined in the Vehicle class. <br>
*/
public class Car extends Vehicle{
   // These values determine the size of car.
   // If you chgange the value, the program is still be able to draw 
   // a car according to the size of the value.
   private final int CAR_WIDTH = 70;
   private final int CAR_HEIGHT = 40;
   
   /**
         Default constructor, which uses the default constructor of Vehicle.
      */
   public Car(){
      super();
   }
   
   
   /**
         Constructor with one parameter.
         , which uses the constructor of Vehicle with one parameter. <br>
         @param color color for the car when drawing it
      */
   public Car(Color color){
      super(color);
   }
   
   
   /**
         Draws a Car on a window. <br>
         Since this class extends Vehicle class, this method can be called by Vehicle object, too.
         @param g2 Graphics2D object
      */
   public void drawVehicle(Graphics2D g2){
      // draw the car frame (car body)
      Rectangle carFrame = new Rectangle(getXCoordinate()
         , (getYCoordinate() + (CAR_HEIGHT / 3))
         , CAR_WIDTH, (CAR_HEIGHT / 2));
      g2.setColor(getColorComponent());
      g2.fill(carFrame);
      g2.setColor(Color.black);
      g2.draw(carFrame);
      
      // draw car wheels
      final int DIAMETER = CAR_WIDTH / 6;
      Ellipse2D.Double wheel1 = new Ellipse2D.Double
         ((getXCoordinate() + (CAR_WIDTH / 6))
         , (getYCoordinate() + (CAR_HEIGHT * 2 / 3))
         , DIAMETER, DIAMETER);
      Ellipse2D.Double wheel2 = new Ellipse2D.Double
         (((getXCoordinate() + CAR_WIDTH) - (2 * (CAR_WIDTH / 6)))
         , (getYCoordinate() + (CAR_HEIGHT * 2 / 3))
         , DIAMETER, DIAMETER);
      g2.setColor(getColorComponent());
      g2.fill(wheel1);
      g2.fill(wheel2);
      g2.setColor(Color.black);
      g2.draw(wheel1);
      g2.draw(wheel2);
      
      
      // draw the roof (or upper body) of the car
      // define 4 points of the roof
      int upLeftX = getXCoordinate() + CAR_WIDTH / 3;
      int upLeftY = getYCoordinate();
      int upRightX = getXCoordinate() + CAR_WIDTH / 3 + CAR_WIDTH / 3;
      int upRightY = getYCoordinate();
      int downLeftX = getXCoordinate() + CAR_WIDTH / 6;
      int downLeftY = getYCoordinate() + CAR_HEIGHT / 3;
      int downRightX = getXCoordinate() + CAR_WIDTH - (CAR_WIDTH / 6);
      int downRightY = getYCoordinate() + CAR_HEIGHT / 3;
      Polygon carRoof = new Polygon();
      // add points defined above to the Polygon object
      carRoof.addPoint(upLeftX, upLeftY);
      carRoof.addPoint(upRightX, upRightY);
      carRoof.addPoint(downRightX, downRightY);
      carRoof.addPoint(downLeftX, downLeftY);
      carRoof.addPoint(upLeftX, upLeftY);
      g2.setColor(getColorComponent());
      g2.fill(carRoof);
      g2.setColor(Color.black);
      g2.draw(carRoof);
   }
   
   
   /**
         Returns car width (, which is a constant). <br>
         This method overrides the method in Vehicle class.
         @return car's width
      */
   public int getVehicleWidth(){
      return CAR_WIDTH;
   }
   
   
   /**
         Returns car height (, which is a constant). <br>
         This method overrides the method in Vehicle class.
         @return car's height
      */
   public int getVehicleHeight(){
      return CAR_HEIGHT;
   }
}



