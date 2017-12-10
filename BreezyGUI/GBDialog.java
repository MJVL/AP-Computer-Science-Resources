//BreezyGUI Copyright Martin Osborne and Ken Lambert 1998-2000
//All rights reserved

package BreezyGUI;

import java.awt.*;
import java.io.*;

// Import, for events.
import java.awt.event.*;


public class GBDialog extends Dialog{
   GridBagLayout gbl = new GridBagLayout();
   GridBagConstraints gbc = new GridBagConstraints();
   //MenuBar menuBar = new MenuBar();                 //mo 6-25-98        
   GBDialogWindowListener windowListener;
   
   private String dlgCloseIndicator = "Cancel";       //mo 6-25-98
   
   private Frame theMainFrame;                        //mo 6-25-98

   public GBDialog(Frame theMainFrm){
      super (theMainFrm, true);
      theMainFrame = theMainFrm;
      
      setSize (300,300);
   
      setLayout(gbl);
      gbc.weightx = 100;
      gbc.weighty = 100;
      gbc.insets.bottom = 1;
      gbc.insets.left = 2;
      gbc.insets.right = 2;
      gbc.insets.top = 1;
      setTitle(" ");

      // Add listeners for the window itself.
      addMouseListener(new GBDialogMouseListener(this));
      addMouseMotionListener(new GBDialMouseMotionListener(this));
      windowListener = new GBDialogWindowListener(this);
      addWindowListener(windowListener);
   }

   // mo 6-25-98
   public void setDlgCloseIndicator (String str){
      dlgCloseIndicator = str;
   }
   
   // mo 6-25-98
   public String getDlgCloseIndicator(){
      return dlgCloseIndicator;
   }

   public void setSize(int width, int height){
      super.setSize(width, height);
      //if (menuBar.getMenuCount() > 0)                mo 6-25-98
      //   setMenuBar (menuBar);                       mo 6-25-98
   }

   public void messageBox (double num){                          
      MessageBox mb = new MessageBox (theMainFrame, "" + num);  //mo 6-25-98
      mb.show();
   }

   public void messageBox (String msg){                         
      MessageBox mb = new MessageBox (theMainFrame, msg);       //mo 6-25-98
      mb.show();
   }

   public void messageBox (Object msg){
      MessageBox mb = new MessageBox (theMainFrame, msg.toString()); //mo 6-25-98
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
      control.addActionListener(new GBDialogButtonListener(this));
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
      control.addActionListener(new GBDialogListListener(this));
      control.addItemListener(new GBDialogListItemListener(this));
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

class GBDialogWindowListener extends WindowAdapter{

   GBDialog myDialog;

   public GBDialogWindowListener(GBDialog dlg){
      myDialog = dlg;
   }

   public void windowClosing(WindowEvent e){
      //System.exit(0);                        //mo 6-25-98
      myDialog.dispose();                      //mo 6-25-98
   }
}

// Controller class to handle button events.

class GBDialogButtonListener implements ActionListener{

   GBDialog myDialog;

   public GBDialogButtonListener(GBDialog dlg){
      myDialog = dlg;
   }

   public void actionPerformed(ActionEvent e){
      myDialog.buttonClicked((Button) e.getSource());
   }
}

// Controller class to handle list double click events.

class GBDialogListListener implements ActionListener{

   GBDialog myDialog;

   public GBDialogListListener(GBDialog dlg){
      myDialog = dlg;
   }

   public void actionPerformed(ActionEvent e){
      myDialog.listDoubleClicked((List) e.getSource(), e.getActionCommand());
   }
}

// Controller class to handle list item selection events.

class GBDialogListItemListener implements ItemListener{

   GBDialog myDialog;

   public GBDialogListItemListener(GBDialog dlg){
      myDialog = dlg;
   }

   public void itemStateChanged(ItemEvent e){
      List list = (List) e.getItemSelectable();
      myDialog.listItemSelected(list);
   }
}


// Controller class to handle mouse events clicked, pressed, and released.

class GBDialogMouseListener extends MouseAdapter{

   GBDialog myDialog;

   public GBDialogMouseListener(GBDialog dlg){
      myDialog = dlg;
   }

   public void mouseClicked(MouseEvent e){
      myDialog.mouseClicked(e.getX(), e.getY());
   }

   public void mousePressed(MouseEvent e){
      myDialog.mousePressed(e.getX(), e.getY());
   }

   public void mouseReleased(MouseEvent e){
      myDialog.mouseReleased(e.getX(), e.getY());
   }
}

// Controller class to handle mouse motion events moved and dragged.

class GBDialMouseMotionListener extends MouseMotionAdapter{

   GBDialog myDialog;

   public GBDialMouseMotionListener(GBDialog dlg){
      myDialog = dlg;
   }

   public void mouseMoved(MouseEvent e){
      myDialog.mouseMoved(e.getX(), e.getY());
   }

   public void mouseDragged(MouseEvent e){
      myDialog.mouseDragged(e.getX(), e.getY());
   }
}


