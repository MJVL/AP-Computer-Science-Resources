//BreezyGUI Copyright Martin Osborne and Ken Lambert 1998-2000
//All rights reserved

package BreezyGUI;
import java.awt.*;
import java.io.*;

import java.awt.event.*;


public class GBFrame extends Frame{
   GridBagLayout gbl = new GridBagLayout();
   GridBagConstraints gbc = new GridBagConstraints();
   MenuBar menuBar = new MenuBar();
   GBFrameWindowListener windowListener;

   public GBFrame(){
      setLayout(gbl);
      gbc.weightx = 100;
      gbc.weighty = 100;
      gbc.insets.bottom = 1;
      gbc.insets.left = 2;
      gbc.insets.right = 2;
      gbc.insets.top = 1;
      setTitle(" ");

      // Add listeners for the window itself.
      addMouseListener(new GBFrameMouseListener(this));
      addMouseMotionListener(new GBFramMouseMotionListener(this));
      windowListener = new GBFrameWindowListener(this);
      addWindowListener(windowListener);
   }

   public static void pause(){
      System.out.print ("\nHit Enter to continue: ");
      try {
         InputStreamReader reader = new InputStreamReader (System.in);
         BufferedReader buffer = new BufferedReader (reader);
         buffer.readLine();
      }
      catch (IOException e){
         System.exit(0);
      }
   }


   public void setSize(int width, int height){
      super.setSize(width, height);
      if (menuBar.getMenuCount() > 0)
         setMenuBar (menuBar);
   }

   public void messageBox (double num){
      MessageBox mb = new MessageBox (this, "" + num);
      mb.show();
   }

   public void messageBox (String msg){
      MessageBox mb = new MessageBox (this, msg);
      mb.show();
   }

   public void messageBox (Object msg){
      MessageBox mb = new MessageBox (this, msg.toString());
      mb.show();
   }

   // Abstract event handlers for menus, buttons, lists, and mice.

   public void menuItemSelected (MenuItem mi){
      messageBox ("You need a 'menuItemSelected' method");
   }

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
   // are added for menu items, buttons, and lists.

   public MenuItem addMenuItem (String menuName, String itemName){

      Menu menu = null;
      int i;
      for (i = 0; i < menuBar.getMenuCount(); i++){
         menu = menuBar.getMenu (i);
         if (menuName.equals (menu.getLabel())) break;
      }
      if (i == menuBar.getMenuCount()){
         menu = new Menu (menuName);
         menuBar.add (menu);
      }
      MenuItem menuItem = new MenuItem (itemName);
      menuItem.addActionListener(new GBFrameMenuListener(this));
      menu.add (menuItem);
      return menuItem;
   }

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
      control.addActionListener(new GBFrameButtonListener(this));
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
      control.addActionListener(new GBFrameListListener(this));
      control.addItemListener(new GBFrameListItemListener(this));
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


// Controller class to handle window event closing.

class GBFrameWindowListener extends WindowAdapter{

   GBFrame myFrame;

   public GBFrameWindowListener(GBFrame frm){
      myFrame = frm;
   }

   public void windowClosing(WindowEvent e){
      System.exit(0);
   }
}

// Controller class to handle button events.

class GBFrameButtonListener implements ActionListener{

   GBFrame myFrame;

   public GBFrameButtonListener(GBFrame frm){
      myFrame = frm;
   }

   public void actionPerformed(ActionEvent e){
      myFrame.buttonClicked((Button) e.getSource());
   }
}

// Controller class to handle menu events.

class GBFrameMenuListener implements ActionListener{

   GBFrame myFrame;

   public GBFrameMenuListener(GBFrame frm){
      myFrame = frm;
   }

   public void actionPerformed(ActionEvent e){
      myFrame.menuItemSelected((MenuItem) e.getSource());
   }
}

// Controller class to handle list double click events.

class GBFrameListListener implements ActionListener{

   GBFrame myFrame;

   public GBFrameListListener(GBFrame frm){
      myFrame = frm;
   }

   public void actionPerformed(ActionEvent e){
      myFrame.listDoubleClicked((List) e.getSource(), e.getActionCommand());
   }
}

// Controller class to handle list item selection events.

class GBFrameListItemListener implements ItemListener{

   GBFrame myFrame;

   public GBFrameListItemListener(GBFrame frm){
      myFrame = frm;
   }

   public void itemStateChanged(ItemEvent e){
      List list = (List) e.getItemSelectable();
      myFrame.listItemSelected(list);
   }
}


// Controller class to handle mouse events clicked, pressed, and released.

class GBFrameMouseListener extends MouseAdapter{

   GBFrame myFrame;

   public GBFrameMouseListener(GBFrame frm){
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

class GBFramMouseMotionListener extends MouseMotionAdapter{

   GBFrame myFrame;

   public GBFramMouseMotionListener(GBFrame frm){
      myFrame = frm;
   }

   public void mouseMoved(MouseEvent e){
      myFrame.mouseMoved(e.getX(), e.getY());
   }

   public void mouseDragged(MouseEvent e){
      myFrame.mouseDragged(e.getX(), e.getY());
   }
}


