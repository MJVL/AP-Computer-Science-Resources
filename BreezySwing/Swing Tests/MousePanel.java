import BreezySwing.*;
import java.awt.*;

public class MousePanel extends GBPanel{

   private int mouseX = 10, mouseY = 10;

   public void paintComponent (Graphics g){
      super.paintComponent(g);
      g.drawString("(" + mouseX + "," + mouseY + ")", mouseX, mouseY);
   }

   public void mousePressed(int x, int y){
      mouseX = x;
      mouseY = y;
      repaint();
   }
}
