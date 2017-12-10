//BreezyGUI Copyright Martin Osborne and Ken Lambert 1998-2000
//All rights reserved


package BreezyGUI;
import java.awt.*;
import java.awt.event.*;

public class MessageBox extends Dialog implements ActionListener, WindowListener {

   public MessageBox (Frame parent, String message){
      super(parent, "Message", true);
      TextArea ta = new TextArea (message);
      ta.setFont(new Font("Courier", Font.PLAIN, 12));
      add("Center", ta);
      Panel p = new Panel();
      Button b = new Button("OK");
      b.addActionListener(this);
      p.add (b);
      add ("South", p);
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

