//Copyright Martin Osborne and Ken Lambert 1998-2001
//All rights reserved

package BreezySwing;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * A MessageBox is a dialog that displays a message and waits for the user to select OK.
 */
public class MessageBox extends JDialog implements ActionListener, WindowListener {

/**
 * Creates a MessageBox.
 */
   public MessageBox (JFrame parent, String message){
      super(parent, "Message", true);
      Container c = getContentPane();
      JTextArea ta = new JTextArea (message);
      ta.setEditable(false);
      ta.setFont(new Font("Courier", Font.PLAIN, 12));
      c.add("Center", new JScrollPane(ta));
      JPanel p = new JPanel();
      JButton b = new JButton("OK");
      b.addActionListener(this);
      p.add (b);
      c.add ("South", p);
      addWindowListener(this);
      setSize(300, 150);
   }

   // Handles click of OK button by closing up.

   public void actionPerformed (ActionEvent evt) {
      dispose();
   }

   public void windowClosing (WindowEvent e){
      //System.exit(0);                           //mo 6-25-98
      dispose();                                  //mo 6-25-98
   }
   
   public void windowActivated (WindowEvent e){}
   public void windowClosed (WindowEvent e){}
   public void windowDeactivated (WindowEvent e){}
   public void windowDeiconified (WindowEvent e){}
   public void windowIconified (WindowEvent e){}
   public void windowOpened (WindowEvent e){}


}

