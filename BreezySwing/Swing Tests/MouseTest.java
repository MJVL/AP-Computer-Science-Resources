import javax.swing.*;
import BreezySwing.*;

public class MouseTest extends GBFrame{

   GBPanel panel = addPanel(new MousePanel(), 1,1,1,1);

   public static void main (String[] args){
      MouseTest tpo = new MouseTest();
      tpo.setSize (250, 300);
      tpo.setVisible (true);
   }
}


