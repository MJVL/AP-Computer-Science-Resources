//BreezyGUI Copyright Martin Osborne and Ken Lambert 1998-2000
//All rights reserved

package BreezyGUI;
import java.awt.*;
import java.applet.*;

// Import, for events.
import java.awt.event.*;


public class GBApplet extends Applet{
   GridBagLayout gbl = new GridBagLayout();
   GridBagConstraints gbc = new GridBagConstraints();


   public GBApplet(){
      setLayout(gbl);
      gbc.weightx = 100;
      gbc.weighty = 100;
      gbc.insets.bottom = 1;
      gbc.insets.left = 2;
      gbc.insets.right = 2;
      gbc.insets.top = 1;

      // Add listeners for the window itself.
      addMouseListener(new GBAppletMouseListener(this));
      addMouseMotionListener(new GBApplMouseMotionListener(this));
   }

   public void messageBox (double num){
      MessageBox mb = new MessageBox (new Frame(), "" + num);
      mb.show();
   }

   public void messageBox (String msg){
      MessageBox mb = new MessageBox (new Frame(), msg);
      mb.show();
   }

   public void messageBox (Object msg){
      MessageBox mb = new MessageBox (new Frame(), msg.toString());
      mb.show();
   }

   // Abstract event handlers for buttons, lists, and mice.

   public void listItemSelected (List listObj){
   }

   public void buttonClicked(Button buttonObj){
      messageBox ("You need a 'buttonClicked' method");
   }

    public void listDoubleClicked (List listObj, String itemClicked){
      messageBox ("You need a 'listDoubleClicked' method");
   }

   public void mouseClicked(int x, int y){
      //messageBox("You need a 'mouseClicked' method");
   }

   public void mousePressed(int x, int y){
      //messageBox("You need a 'mousePressed' method");
   }

   public void mouseReleased(int x, int y){
      //messageBox("You need a 'mouseReleased' method");
   }

   public void mouseMoved(int x, int y){
      //messageBox("You need a 'mouseMoved' method");
   }

   public void mouseDragged(int x, int y){
      //messageBox("You need a 'mouseDragged' method");
   }


   // Methods to add window objects to the interface.  Listeners
   // are added for buttons and lists.

   public Label addLabel (String text, int row, int col, int width, int height){
      gbc.fill = GridBagConstraints.NONE;
      gbc.anchor = GridBagConstraints.NORTHWEST;
      Label control = new Label (text);
      add (control, row, col, width, height);
      return control;
   }

   public Button addButton (String text, int row, int col, int width, int height){
      gbc.fill = GridBagConstraints.NONE;
      gbc.anchor = GridBagConstraints.CENTER;
      Button control = new Button (text);
      control.addActionListener(new GBAppletButtonListener(this));
      add (control, row, col, width, height);
      return control;
   }

   public TextField addTextField (String text, int row, int col, int width, int height){
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.anchor = GridBagConstraints.NORTHWEST;
      TextField control = new TextField (text);
      add (control, row, col, width, height);
      return control;
   }

   public TextArea addTextArea (String text, int row, int col, int width, int height){
      gbc.fill = GridBagConstraints.NONE;
      gbc.anchor = GridBagConstraints.NORTHWEST;
      TextArea control = new TextArea (text, height*2, width*15);
      add (control, row, col, width, height);
      control.setFont (new Font ("Courier", Font.PLAIN, 12));
      return control;
   }

   public List addList (int row, int col, int width, int height){
      gbc.fill = GridBagConstraints.NONE;
      gbc.anchor = GridBagConstraints.NORTHWEST;
      List control = new List (height*2, false);
      control.addActionListener(new GBAppletListListener(this));
      control.addItemListener(new GBAppletListItemListener(this));
      add (control, row, col, width, height);
      return control;
   }

   public Choice addChoice (int row, int col, int width, int height){
      gbc.fill = GridBagConstraints.NONE;
      gbc.anchor = GridBagConstraints.NORTHWEST;
      Choice control = new Choice();
      add (control, row, col, width, height);
      return control;
   }

   public IntegerField addIntegerField (int num, int row, int col, int width, int height){
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.anchor = GridBagConstraints.NORTHWEST;
      IntegerField control = new IntegerField (num);
      add (control, row, col, width, height);
      return control;
   }

   public DoubleField addDoubleField (double num, int row, int col, int width, int height){
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.anchor = GridBagConstraints.NORTHWEST;
      DoubleField control = new DoubleField (num);
      add (control, row, col, width, height);
      return control;
   }

   public Checkbox addCheckbox (String text, int row, int col, int width, int height){
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.anchor = GridBagConstraints.NORTHWEST;
      Checkbox control = new Checkbox (text);
      add (control, row, col, width, height);
      return control;
   }

   private void add( Component c,int y, int x, int w, int h){
      gbc.gridx = x-1;
      gbc.gridy = y-1;
      gbc.gridwidth = w;
      gbc.gridheight = h;
      gbl.setConstraints(c, gbc);
      add(c);
   }
}


// Controller class to handle button events.

class GBAppletButtonListener implements ActionListener{

   GBApplet myFrame;

   public GBAppletButtonListener(GBApplet frm){
      myFrame = frm;
   }

   public void actionPerformed(ActionEvent e){
      myFrame.buttonClicked((Button) e.getSource());
   }
}


// Controller class to handle list events.

class GBAppletListListener implements ActionListener{

   GBApplet myFrame;

   public GBAppletListListener(GBApplet frm){
      myFrame = frm;
   }

   public void actionPerformed(ActionEvent e){
      myFrame.listDoubleClicked((List) e.getSource(), e.getActionCommand());
   }
}

// Controller class to handle list item selection events.

class GBAppletListItemListener implements ItemListener{

   GBApplet myFrame;

   public GBAppletListItemListener(GBApplet frm){
      myFrame = frm;
   }

   public void itemStateChanged(ItemEvent e){
      List list = (List) e.getItemSelectable();
      myFrame.listItemSelected(list);
   }
}

// Controller class to handle mouse events clicked, pressed, and released.

class GBAppletMouseListener extends MouseAdapter{

   GBApplet myFrame;

   public GBAppletMouseListener(GBApplet frm){
      myFrame = frm;
   }

   public void mouseClicked(MouseEvent e){
      myFrame.mouseClicked(e.getX(), e.getY());
   }

   public void mousePressed(MouseEvent e){
      myFrame.mousePressed(e.getX(), e.getY());
   }

   public void mouseReleased(MouseEvent e){
      myFrame.mouseReleased(e.getX(), e.getY());
   }
}

// Controller class to handle mouse motion events moved and dragged.

class GBApplMouseMotionListener extends MouseMotionAdapter{

   GBApplet myFrame;

   public GBApplMouseMotionListener(GBApplet frm){
      myFrame = frm;
   }

   public void mouseMoved(MouseEvent e){
      myFrame.mouseMoved(e.getX(), e.getY());
   }

   public void mouseDragged(MouseEvent e){
      myFrame.mouseDragged(e.getX(), e.getY());
   }
}


