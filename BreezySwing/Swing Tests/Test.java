import javax.swing.*;
import BreezySwing.*;

public class Test extends GBFrame{

   JLabel wLabel = addLabel("Width", 1,1,1,1);
   JLabel hLabel = addLabel("Height", 2,1,1,1);
   IntegerField wField = addIntegerField(100, 1,2,1,1);
   IntegerField hField = addIntegerField(100, 2,2,1,1);
   JButton testBTN = addButton("Test", 3,1,2,1);

   public void buttonClicked(JButton buttonObj){
      messageBox("Hello world!", wField.getNumber(), hField.getNumber());
   }

   public static void main (String[] args){
      JFrame frm = new Test();
      frm.setSize (200, 150);
      frm.setVisible (true);
   }
}
