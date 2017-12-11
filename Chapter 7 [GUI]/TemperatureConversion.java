import java.awt.*;
import BreezyGUI.*;

public class TemperatureConversion extends GBFrame {

   private Label fahrenheitLabel         
                        = addLabel  ("Degrees Fahrenheit",1,1,1,1);
   private Label centigradeLabel        
                        = addLabel  ("Degrees Centigrade",2,1,1,1);
   private IntegerField fahrenheitField 
                        = addIntegerField (32            ,1,2,1,1);
   private IntegerField centigradeField 
                        = addIntegerField (0             ,2,2,1,1);
   private Button fahrenheitButton      
                        = addButton ("Compute Fahrenheit",3,1,1,1);
   private Button centigradeButton      
                        = addButton ("Compute Centigrade",3,2,1,1);

   public void buttonClicked (Button buttonObj){
      int fahrenheit, centigrade;
      if (buttonObj == fahrenheitButton){
         centigrade = centigradeField.getNumber();
         fahrenheit = centigrade * 9 / 5 + 32;
         fahrenheitField.setNumber (fahrenheit);
      }else{
         fahrenheit = fahrenheitField.getNumber(); 
         centigrade = (fahrenheit  - 32) * 5 / 9;
         centigradeField.setNumber (centigrade);
      }
   }

   public static void main (String[] args){
      Frame frm = new TemperatureConversion ();
      frm.setSize (200, 150);
      frm.setVisible (true);
   }
}

