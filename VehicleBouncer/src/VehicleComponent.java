import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.Scanner;
import java.awt.Rectangle;

/**
   This class holds a component part of vehicles. <br>
   It extends JComponent. <br>
   The framewidth and frameheight must be passed from main since 
   getHeight() and getWidth() don't return the value we expect. <br>
   The array vehicles stores all the vehicles' data for drawing. <br>
   Note: This VehicleComponent is different from the one used for VehicleViewer
            (in spite of the same name).
   
   @version 2.0
*/
public class VehicleComponent extends JComponent{
   // instance variables
   private Vehicle[] vehicles;
   private int frameWidth;
   private int frameHeight;
   
   
   /**
         Default construcor. <br>
         Using this construcor doesn't make sense (because it doesn't give frame size
         and the size of an array), <br> 
         but provided in the case that another class may need to
         extend this class (so that super constructor can be used).
      */
   public VehicleComponent(){
      frameWidth = 0;
      frameHeight = 0;
      vehicles = new Vehicle[0];
   }
   
   
   /**
         Constructor with 3 parameters. <br>
         This is the construcotor used in main to initialize VehicleComponent object. <br>
         @param width width of the window
         @param height height of the window
         @param n the size of an array to be initicalized
      */
   public VehicleComponent(int width, int height, int n){
      frameWidth = width;
      frameHeight = height;
      vehicles = new Vehicle[n];
      generateRandomVehicles();
   }
   
   
   /**
         Overwrites the paintComponent method of JComponent.  <br>
         Draws every vehicle stored in the array.
         @param g a Graphics object
      */
   public void paintComponent(Graphics g){
      Graphics2D g2 = (Graphics2D) g;
      for(int i=0;i<vehicles.length; i++){
         vehicles[i].drawVehicle(g2);
      }
   }
   
   
   //---------------------------------------------------------------------------------------------------------------------------
   // This method fills the array (vehicles[]) with either car or truck object with random x & y coordinates.
   // The choice of the Car or Truck object is also random.
   // precondition: None
   // postcondition: fills the array with a car or truck with random x and y 
   //---------------------------------------------------------------------------------------------------------------------------
   private void generateRandomVehicles(){
      final int KIND_OF_VEHICLE = 2; // 2 kind: car and truck
      
      for(int i=0; i<vehicles.length; i++){
         // These variables are used to define a random color for vehicles
         final float RED_COMPONENT = (float) Math.random();
         final float GREEN_COMPONENT = (float) Math.random();
         final float BLUE_COMPONENT = (float) Math.random();
         Color color = new Color(RED_COMPONENT, GREEN_COMPONENT, BLUE_COMPONENT);
         
         // kindOfVehicle becomes either 0 or 1
         int kindOfVehicle = generateRandomInt() % KIND_OF_VEHICLE;
         
         // if kindOfVehicle is 0, it means car object is choosen
         if(kindOfVehicle == 0){
            vehicles[i] = new Car(color);
            // set random x and y coordinates to a car
            // -17 and -36 are needed to put the car inside the window
            vehicles[i].setXCoordinate(generateRandomInt() 
               % ((frameWidth - vehicles[i].getVehicleWidth()) - 17));
            vehicles[i].setYCoordinate(generateRandomInt() 
               % ((frameHeight - vehicles[i].getVehicleHeight()) - 36));
            
/////////////////////////// Different Part (Beginning) ////////////////////////////////////////////////////////////////
            for(int j=0; j<i; j++){
               Rectangle r1 = new Rectangle(vehicles[i].getXCoordinate()
                  , vehicles[i].getYCoordinate(), vehicles[i].getVehicleWidth()
                  , vehicles[i].getVehicleHeight());
               Rectangle r2 = new Rectangle(vehicles[j].getXCoordinate()
                  , vehicles[j].getYCoordinate(), vehicles[j].getVehicleWidth()
                  , vehicles[j].getVehicleHeight());
               
               // If intersection bewtween vehicles occures, 
               // regenerate the random coordinate for vehicles[i], and compare the occurence of overlap again
               if(r1.intersects(r2)){
                  vehicles[i].setXCoordinate(generateRandomInt() 
                     % ((frameWidth - vehicles[i].getVehicleWidth()) - 17));
                  vehicles[i].setYCoordinate(generateRandomInt() 
                     % ((frameHeight - vehicles[i].getVehicleHeight()) - 36));
                  // need to compare to the first vehicle coordinate so j must be 0
                  j = 0;
               }
            }
/////////////////////////////// Different Part (End) //////////////////////////////////////////////////////////////////
         }
         // else means vehicle is 1, so it means truck object is choosen
         else{
            vehicles[i] = new Truck(color);
            // set random x and y coordinates to a truck
            // -17 and -39 are needed to put the truck inside the window
            vehicles[i].setXCoordinate(generateRandomInt() 
               % ((frameWidth - vehicles[i].getVehicleWidth()) - 17));
            vehicles[i].setYCoordinate(generateRandomInt() 
               % ((frameHeight - vehicles[i].getVehicleHeight()) - 39));
            
/////////////////////////////// Different Part (Beginning) //////////////////////////////////////////////////////////////
            for(int j=0; j<i; j++){
               Rectangle r1 = new Rectangle(vehicles[i].getXCoordinate()
                  , vehicles[i].getYCoordinate(), vehicles[i].getVehicleWidth()
                  , vehicles[i].getVehicleHeight());
               Rectangle r2 = new Rectangle(vehicles[j].getXCoordinate()
                  , vehicles[j].getYCoordinate(), vehicles[j].getVehicleWidth()
                  , vehicles[j].getVehicleHeight());
               
               // If intersection bewtween vehicles occures, 
               // regenerate the random coordinate for vehicles[i], and compare the occurence of overlap again
               if(r1.intersects(r2)){
                  vehicles[i].setXCoordinate(generateRandomInt() 
                     % ((frameWidth - vehicles[i].getVehicleWidth()) - 17));
                   vehicles[i].setYCoordinate(generateRandomInt() 
                     % ((frameHeight - vehicles[i].getVehicleHeight()) - 36));
                  // need to compare to the first vehicle coordinate so j must be 0
                  j = 0;
               }
            }
///////////////////////////////// Different Part (End) //////////////////////////////////////////////////////////////////
         }
      }
   }
   
   
   //---------------------------------------------------------------------------------------------------------------------------
   // This method returns a certain big random integer.
   // 100000000 is my arbitrary choice to make the return value large.
   // Precondition: None
   // Postcondition: return a big random integer
   //---------------------------------------------------------------------------------------------------------------------------
   private int generateRandomInt(){
      // want to make random large, so 100000000 can be replaced with any large number
      return (int) (Math.random() * 100000000);
   }
   

   
   /**
         Moves vehicles and the vehicles bounce each other. <br>
         Vehicles also bounce when they hit the corner or edge of the window.
         Precondition: The initial position of vehicles must not overlap.
      */
   public void bounce(){
      boolean intersect = false;
      
      // In order to determine which object to bounce, I have to check all vehicles in the array.
      for(int i=0; i<vehicles.length; i++){
         intersect = false;
         vehicles[i].moveByDXandDY();
         
         // These if statements check if the vehicle is at the edge.
         // If so, the vehicle takes opposite direction from the next move.
         // The idea of the codes are taken from DiscComponent & DiscBouncer files
         // written by Dr. Monagan (my instructor)
         if(vehicles[i].getXCoordinate() >= ((frameWidth 
            - vehicles[i].getVehicleWidth()) - 17)){
               vehicles[i].setDx(-vehicles[i].getDx());
            }   
         if(vehicles[i].getXCoordinate() <= 0){
            vehicles[i].setDx(-vehicles[i].getDx());
         }
         if(vehicles[i].getYCoordinate() >= ((frameHeight 
            - vehicles[i].getVehicleHeight()) - 37)){
               vehicles[i].setDy(-vehicles[i].getDy());
            }
         if(vehicles[i].getYCoordinate() <= 0){
            vehicles[i].setDy(-vehicles[i].getDy());
         }
         
         
         // Continue checking if one vehicle intersects another vehicle.
         // If a vehicle is found to intersect at least one other vehicle, it stops checking for other vehicles
         // because  the vehicle has to change dx and dy anyway.        
         for(int j=0; (!intersect) && (j < vehicles.length);j++){
            if(j == i){
               // do nothing if i and j refers to the same object
            }
            else{
               // Initialize two rectangle objects in order to use 'intersect' method.
               Rectangle r1 = new Rectangle(vehicles[i].getXCoordinate()
                  , vehicles[i].getYCoordinate(), vehicles[i].getVehicleWidth()
                  , vehicles[i].getVehicleHeight());
               Rectangle r2 = new Rectangle(vehicles[j].getXCoordinate()
                  , vehicles[j].getYCoordinate(), vehicles[j].getVehicleWidth()
                  , vehicles[j].getVehicleHeight());
               
               // If one vehicle intersects another, it changes its dx and dy into opposite direction.
               if(r1.intersects(r2)){
                  intersect = true;
                  vehicles[i].setDx(-vehicles[i].getDx());
                  vehicles[i].setDy(-vehicles[i].getDy());
               }
            }
         }
      }
      repaint();
   }
}


