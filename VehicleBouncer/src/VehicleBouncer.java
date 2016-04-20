import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
   Vehicle Bouncer class is a tester class for VehicleComponent, Vehicle, Car and Truck classes. <br>
   This asks the user for the number of Vehicles to draw, and then, draw either car or truck
   with random choice of location (coordinates). <br>
   The choice of car or truck is also random. <br>
   This program requires the Vehicles not to overlap at their initial positions, and the task (for no overlap)
   is done in VehicleComponent class. <br>
   Thus, too large number of vehicles makes it impossible to put vehicles without overlap in a window.
   Precondition: Input must not be a large integer.
   
   @author Naoya Hayashi
   @date March 5th, 2013
   @version 1.0
*/
public class VehicleBouncer{
   private static final int FRAME_WIDTH = 800;
   private static final int FRAME_HEIGHT = 600;
   
   /**
         Main method which actually executes the program implementing all classes. <br>
         Precondition: Integer for input must not be too large.
         @param args args is not used
      */
   public static void main(String[] args){
      String inputStr;
      int inputNum;
      
      inputStr = JOptionPane.showInputDialog(null
            , "How many behicles should be displayed?\n"
            + "(give an integer from 0 to 20)", "Input"
            , JOptionPane.QUESTION_MESSAGE);
      
      while((inputStr == null) || (inputStr.isEmpty())){
         // Check if the cancel button was hit.
         // If so, the program terminates.
         if(inputStr == null){
            JOptionPane.showMessageDialog(null
               , "Cancel button was clicked.\n" 
               + "So, the program terminates.");
            System.exit(1);
         }
         else if(inputStr.isEmpty()){
            JOptionPane.showMessageDialog(null
               , "No value is typed.\n" + "Please type an integer.");
            inputStr = JOptionPane.showInputDialog(null
            , "How many behicles should be displayed?\n"
            + "(give an integer from 0 to 20)", "Input"
            , JOptionPane.QUESTION_MESSAGE);
         }
      }
      
      inputNum = Integer.parseInt(inputStr);
      
      
      final VehicleComponent VC = new VehicleComponent
         (FRAME_WIDTH, FRAME_HEIGHT, inputNum);
      
      
      class VehicleTimerListener implements ActionListener{
         public void actionPerformed(ActionEvent event){
            VC.bounce(); 
            // bounce method includes repaint(), so I don't have to write it here.
         }
      }
      ActionListener listener = new VehicleTimerListener();
      // milliseconds between timer ticks
      final int MILLISECS = 10;
      Timer t = new Timer(MILLISECS, listener);
      t.start();
      
      
      JFrame frame = new JFrame();
      frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
      frame.setTitle("VehicleBouncer");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(VC);
      frame.setVisible(true);
   }
}