import java.awt.Color;
import java.awt.Graphics2D;

/**
   Vehicle is an abstract class and stores x and y coordinates and color for vehicles. <br>
   All other conrete vehicle classes such as Car and Truck should 
   extend this class for drawing. <br>
*/
public abstract class Vehicle{
   // instance variables
   private int x;
   private int y;
   private Color color;
   // These variables needed to move vehicles.
   private int dx;
   private int dy;
   
   /**
         Default constructor initializing x and y to 0 and white color. <br>
         Since this Vehicle is an abstract class, this constructor can't be called directly. <br>
         Instead, a concrete subclass that extends this class will call this constructor.
      */
   public Vehicle(){
      x = 0;
      y = 0;
      dx = 1;
      dy = 1;
      // The color white is my arbitrary choice.
      color = Color.white;
   }
   
   
   /**
         Constructor with one parameter. <br>
         Since this Vehicle is an abstract class, this constructor can't be called directly. <br>
         Instead, a concrete subclass that extends this class will call this constructor.
         @param color color for the vehicle when drawing it
      */
   public Vehicle(Color color){
      this.color = color;
      x = 0;
      y = 0;
      dx = 1;
      dy = 1;
   }
   
   
   /**
         Abstract class, so must be overridden in a concrete class. <br>
         In this program, Car and Truck will be a concrete class.
      */
   public abstract void drawVehicle(Graphics2D g2);
   
   
   /**
         Abstract class, so must be overridden in a concrete class. <br>
         In this program, Car and Truck will be a concrete class.
      */
   public abstract int getVehicleWidth();
   
   
   /**
         Abstract class, so must be overridden in a concrete class. <br>
         In this program, Car and Truck will be a concrete class.
      */
   public abstract int getVehicleHeight();
   
   
   /**
         Returns the x coordinate of a Vehicle (, Car or Truck) object.
         @return x
      */
   public int getXCoordinate(){
      return x;
   }
   
   
   /**
         Returns the y coordinate of a Vehicle (, Car or Truck) object.
         @return y
      */
   public int getYCoordinate(){
      return y;
   }
   
   
   /**
         Returns the color of a Vehicle (, Car or Truck) object.
         @return color
      */
   public Color getColorComponent(){
      return color;
   }
   
   
   /**
         Sets x coordinate for the Vehicle (, Car or Truck) object.
         @param xCoord x coordinate
      */
   public void setXCoordinate(int xCoord){
      x = xCoord;
   }
   
   
   /**
         Sets y coordinate for the Vehicle (, Car or Truck) object.
         @param yCoord y coordinate
      */
   public void setYCoordinate(int yCoord){
      y = yCoord;
   }
   
   
   /**
         Sets x and y coordinate by +dx and +dy respectively. <br>
         dx and dy can be a negative value.
      */
   public void moveByDXandDY(){
      x = x + dx;
      y = y + dy;
   }
   
   
   /**
         Sets dx 
      */
   public void setDx(int delta){
      dx = delta;
   }
   
   public void setDy(int delta){
      dy = delta;
   }
   
   
   public int getDx(){
      return dx;
   }
   
   
   public int getDy(){
      return dy;
   }
}