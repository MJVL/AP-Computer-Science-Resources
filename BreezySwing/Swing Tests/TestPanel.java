import javax.swing.*;
import BreezySwing.*;
import java.awt.*;

public class TestPanel extends GBFrame{

   GBPanel northWest = addPanel(new GBPanel(), 1,1,1,1);
   GBPanel southWest = addPanel(new GBPanel(), 1,2,1,1);
   GBPanel northEast = addPanel(new GBPanel(), 2,1,1,1);
   GBPanel southEast = addPanel(new GBPanel(), 2,2,1,1);

   public TestPanel(){
      northWest.setBackground(Color.red);
      southWest.setBackground(Color.green);
      northEast.setBackground(Color.blue);
      southEast.setBackground(Color.yellow);
   }

   public static void main (String[] args){
      TestPanel tpo = new TestPanel();
      tpo.setSize (200, 200);
      tpo.setVisible (true);
   }
}


