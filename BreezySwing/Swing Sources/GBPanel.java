//Copyright Martin Osborne and Ken Lambert 1998-2001
//All rights reserved

package BreezySwing;
import javax.swing.*;
import java.awt.event.*;

/**
 * The class GBFrame (short for Grid Bag Panel) provides a high-level panel.
 * The panel comes with built-in mouse event handling for motion, clicks,
 * presses, releases, and dragging.
 * </pre>
 */
public class GBPanel extends JPanel{

/**
 * Creates a panel.
 */
   public GBPanel(){
      addMouseListener(new GBPanMouseListener(this));
      addMouseMotionListener(new GBPanMouseMotionListener(this));
   }

/**
 * The GBPanel subclass must implement this method in order to handle mouse clicks in
 * the window.
 * If no event handling is desired, this method need no be implemented.
 * @param  x The x coordinate of the mouse in the window.
 * @param  y The y coordinate of the mouse in the window.
 */
  public void mouseClicked(int x, int y){}

/**
 * The GBPanel subclass must implement this method in order to handle mouse pressed events in
 * the window.
 * If no event handling is desired, this method need no be implemented.
 * @param  x The x coordinate of the mouse in the window.
 * @param  y The y coordinate of the mouse in the window.
 */
   public void mousePressed(int x, int y){}

/**
 * The GBPanel subclass implement this method in order to handle mouse released events in
 * the window.
 * If no event handling is desired, this method need no be implemented.
 * @param  x The x coordinate of the mouse in the window.
 * @param  y The y coordinate of the mouse in the window.
 */
   public void mouseReleased(int x, int y){}

   /**
 * The GBPanel subclass implement this method in order to handle mouse entered events in
 * the window.
 * If no event handling is desired, this method need no be implemented.
 * @param  x The x coordinate of the mouse in the window.
 * @param  y The y coordinate of the mouse in the window.
 */
   public void mouseEntered(int x, int y){}
/**
 * The GBPanel subclass implement this method in order to handle mouse exited events in
 * the window.
 * If no event handling is desired, this method need no be implemented.
 * @param  x The x coordinate of the mouse in the window.
 * @param  y The y coordinate of the mouse in the window.
 */
   public void mouseExited(int x, int y){}

/**
 * The GBPanel subclass implement this method in order to handle mouse moved events in
 * the window.
 * If no event handling is desired, this method need no be implemented.
 * @param  x The x coordinate of the mouse in the window.
 * @param  y The y coordinate of the mouse in the window.
 */
   public void mouseMoved(int x, int y){}

/**
 * The GBPanel subclass implement this method in order to handle mouse dragged events in
 * the window.
 * If no event handling is desired, this method need no be implemented.
 * @param  x The x coordinate of the mouse in the window.
 * @param  y The y coordinate of the mouse in the window.
 */
   public void mouseDragged(int x, int y){}

}

// Controller class to handle mouse events clicked, pressed, and released.

class GBPanMouseListener extends MouseAdapter{

   GBPanel myPanel;

   public GBPanMouseListener(GBPanel pan){
      myPanel = pan;
   }

   public void mouseClicked(MouseEvent e){
      myPanel.mouseClicked(e.getX(), e.getY());
   }

   public void mousePressed(MouseEvent e){
      myPanel.mousePressed(e.getX(), e.getY());
   }

   public void mouseReleased(MouseEvent e){
      myPanel.mouseReleased(e.getX(), e.getY());
   }

   public void mouseEntered(MouseEvent e){
      myPanel.mouseEntered(e.getX(), e.getY());
   }

   public void mouseExited(MouseEvent e){
      myPanel.mouseExited(e.getX(), e.getY());
   }
}

// Controller class to handle mouse motion events moved and dragged.

class GBPanMouseMotionListener extends MouseMotionAdapter{

   GBPanel myPanel;

   public GBPanMouseMotionListener(GBPanel pan){
      myPanel = pan;
   }

   public void mouseMoved(MouseEvent e){
      myPanel.mouseMoved(e.getX(), e.getY());
   }

   public void mouseDragged(MouseEvent e){
      myPanel.mouseDragged(e.getX(), e.getY());
   }
}


