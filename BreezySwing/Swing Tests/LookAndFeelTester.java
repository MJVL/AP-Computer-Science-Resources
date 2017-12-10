import javax.swing.*;
import BreezySwing.*;

public class LookAndFeelTester extends GBFrame{

   JButton metalBTN = addButton("Metal", 1,1,1,1);
   JButton motifBTN = addButton("Motif", 2,1,1,1);
   JButton otherBTN = addButton("Other (Windows, MacOS)", 3,1,1,1);

   public void buttonClicked(JButton buttonObj){
      String str = "Metal";
      if (buttonObj == metalBTN)
         str = "Metal";
      else if (buttonObj == motifBTN)
         str = "Motif";
      else if (buttonObj == otherBTN)
         str = "Other";
      setLookAndFeel(str);
  }

   public static void main(String[] args){
      JFrame frm = new LookAndFeelTester();
      frm.setSize (200, 300);
      frm.setVisible (true);
   }
}


