//Copyright Martin Osborne and Ken Lambert 1998-2001
//All rights reserved

package BreezySwing;
import java.awt.*;
import java.applet.*;
import javax.swing.*;

import java.awt.event.*;
import javax.swing.event.*;

/**
 * The class GBApplet (short for Grid Bag Applet) provides a high-level applet window.
 * The window comes with a built in GridBagLayout.  Components are added
 * to the window by specifying their row position, column position, width (in columns),
 * and height (in rows)in the grid.
 * <BR>
 * <BR>
 * Events of different types, such as button clicks and list item selections,
 * are handled by implementing the appropriate GBApplet methods in the application.
 * <BR>
 * <BR>
 * Unlike GBFrame, GBApplet does not support pull down menus.
 * <BR>
 * <BR>
 * Example use of GBApplet:
 *
 * <pre>
 *    // Hello world applet with BreezyGUI.<BR>
 *    import java.awt.*;
 *    import BreezyGUI.*;<BR>
 *
 *    public class HelloWorld extends GBApplet{<BR>
 *
 *        // Add a labeled button to the window at row 1, column 1, width 1, height 1.
 *        Button clickMe = addButton("Click me", 1, 1, 1, 1);<BR>
 *
 *        // Implement the method to handle the button click.
 *        public void buttonClicked(Button buttonObj){
 *           messageBox("Hello world!");
 *        }
 *    }
 * </pre>
 */
 public class GBApplet extends JApplet{
   GridBagLayout gbl = new GridBagLayout();
   GridBagConstraints gbc = new GridBagConstraints();
   private Container contentPane;

/**
 * Creates a GBApplet.
 */
    public GBApplet(){
      contentPane = getContentPane();
      contentPane.setLayout(gbl);
      gbc.weightx = 100;
      gbc.weighty = 100;
      gbc.insets.bottom = 1;
      gbc.insets.left = 2;
      gbc.insets.right = 2;
      gbc.insets.top = 1;
   }

/**
 * Pops up a message box containing the number and an OK button.
 */
    public void messageBox (double num){
      MessageBox mb = new MessageBox (new JFrame(), "" + num);
      mb.show();
   }

/**
 * Pops up a message box containing the string and an OK button.
 */
    public void messageBox (String msg){
      MessageBox mb = new MessageBox (new JFrame(), msg);
      mb.show();
   }

/**
 * Pops up a message box containing the string representation of the object and an OK button.
 */
    public void messageBox (Object msg){
      MessageBox mb = new MessageBox (new JFrame(), msg.toString());
      mb.show();
   }

   public void messageBox (double num, int width, int height){
      MessageBox mb = new MessageBox (new JFrame(), "" + num);
      mb.setSize(width, height);
      mb.show();
   }

   public void messageBox (String msg, int width, int height){
      MessageBox mb = new MessageBox (new JFrame(), msg);
      mb.setSize(width, height);
      mb.show();
   }

   public void messageBox (Object msg, int width, int height){
      MessageBox mb = new MessageBox (new JFrame(), msg.toString());
      mb.setSize(width, height);
      mb.show();
   }

/**
 * Allows the application to set the look and feel of the window
 * and all its subcomponents to a platform-specific look and feel.
 * If no event handling is desired, this method need not be implemented.
 * @param  type A String, either "METAL", "MOTIF", or "OTHER"
 */
    public void setLookAndFeel(String type){
      int value = 0;
      UIManager.LookAndFeelInfo[] looks;
      looks = UIManager.getInstalledLookAndFeels();
      if (type.equalsIgnoreCase("METAL"))
         value = 0;
      else if (type.equalsIgnoreCase("MOTIF"))
         value = 1;
      else
         value = 2;
      try{
         UIManager.setLookAndFeel(looks[value].getClassName());
         SwingUtilities.updateComponentTreeUI(this);
      }catch(Exception e){
         messageBox("Error: \n" + e.toString());
      }
   }

   // Abstract event handlers for buttons and lists.

 /**
 * The application must implement this method in order to handle a selection
 * (single click) on an item in a scrolling list.
 * If no event handling is desired, this method need not be implemented.
 * @param  listObj The JList in which the selection occurred.
 */
   public void listItemSelected (JList listObj){
   }

/**
 * The application must implement this method in order to handle button clicks.
 * If no buttons are added to the window, this method need not be implemented.
 * @param  buttonObj The JButton in which the click occurred.
 * Example:
 * <pre>
 *    // Add the buttons to the window.
 *    JButton okButton = addButton("OK", 1, 1, 1, 1);
 *    JButton cancelButton = addButton("Cancel", 1, 2, 1, 1);
 *    // Handle the button selection.
 *    public void buttonClicked(JButton buttonObj){
 *       if (buttonObj == okButton)
 *          messageBox("OK selected");
 *       else
 *          messageBox("Cancel selected");
 *    }
 * </pre>
 */
    public void buttonClicked(JButton buttonObj){
      messageBox ("You need a 'buttonClicked' method");
   }

/**
 * The application must implement this method in order to handle a
 * double click on an item in a scrolling list.
 * If no double click is anticipated, this method need not be implemented.
 * @param  listObj The JList in which the double click occurred.
 * @param  itemClicked The string selected by the double click.
 */
    public void listDoubleClicked (JList listObj, String itemClicked){
      messageBox ("You need a 'listDoubleClicked' method");
   }
   
   // Methods to add window objects to the interface.  Listeners
   // are added for buttons and lists.

/**
 * Adds a label with the specified name to the specified position, with the specified
 * width and height.
 * @param  text The name of the label.
 * @param  row The beginning row (starting from 1) of the window's grid in which the label is displayed.
 * @param  col The beginning column (starting from 1) of the window's grid in which the label is displayed.
 * @param  width The number of columns of the window's grid occuppied by the label.
 * @param  height The number of rows of the window's grid occuppied by the label.
 * @return  the JLabel
 */
    public JLabel addLabel (String text, int row, int col, int width, int height){
      gbc.fill = GridBagConstraints.NONE;
      gbc.anchor = GridBagConstraints.NORTHWEST;
      JLabel control = new JLabel (text);
      add (control, row, col, width, height);
      return control;
   }

/**
 * Adds a JButton with the specified name to the specified position, with the specified
 * width and height.
 * @param  text The name of the Button.
 * @param  row The beginning row (starting from 1) of the window's grid in which the Button is displayed.
 * @param  col The beginning column (starting from 1) of the window's grid in which the Button is displayed.
 * @param  width The number of columns of the window's grid occuppied by the Button.
 * @param  height The number of rows of the window's grid occuppied by the Button.
 * @return the button.
 * Example:
 * <pre>
 *    JButton okButton = addButton("OK", 1, 1, 1, 1);
 *    // Adds a button at position row 1, column 1,
 *    // with a width of 1 column and a height of one row.
 * </pre>
 */
    public JButton addButton (String text, int row, int col, int width, int height){
      gbc.fill = GridBagConstraints.NONE;
      gbc.anchor = GridBagConstraints.CENTER;
      JButton control = new JButton (text);
      control.addActionListener(new GBAppletButtonListener(this));
      add (control, row, col, width, height);
      return control;
   }

/**
 * Adds a JTextField containing the specified string to the specified position, with the specified
 * width and height.
 * @param  text The string to be displayed initially.
 * @param  row The beginning row (starting from 1) of the window's grid in which the JTextField is displayed.
 * @param  col The beginning column (starting from 1) of the window's grid in which the JTextField is displayed.
 * @param  width The number of columns of the window's grid occuppied by the JTextField.
 * @param  height The number of rows of the window's grid occuppied by the JTextField.
 * @return the JTextField.
 */
    public JTextField addTextField (String text, int row, int col, int width, int height){
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.anchor = GridBagConstraints.NORTHWEST;
      JTextField control = new JTextField (text);
      add (control, row, col, width, height);
      return control;
   }

/**
 * Adds a JTextArea containing the specified string to the specified position, with the specified
 * width and height.
 * @param  text The string to be displayed initially.
 * @param  row The beginning row (starting from 1) of the window's grid in which the JTextArea is displayed.
 * @param  col The beginning column (starting from 1) of the window's grid in which the JTextArea is displayed.
 * @param  width The number of columns of the window's grid occuppied by the JTextArea.
 * @param  height The number of rows of the window's grid occuppied by the JTextArea.
 * @return the TextArea.
 */
    public JTextArea addTextArea (String text, int row, int col, int width, int height){
      gbc.fill = GridBagConstraints.BOTH;
      gbc.anchor = GridBagConstraints.NORTHWEST;
      gbc.weightx = 500;
      gbc.weighty = 500;
      JTextArea control = new JTextArea (text); //, height*2, width*15);
      add (new JScrollPane(control), row, col, width, height);
      gbc.weightx = 100;
      gbc.weighty = 100;
      control.setFont (new Font ("Courier", Font.PLAIN, 12));
      return control;
   }

/**
 * Adds a JList (a scrolling list) to the specified position, with the specified
 * width and height.
 * @param  row The beginning row (starting from 1) of the window's grid in which the JList is displayed.
 * @param  col The beginning column (starting from 1) of the window's grid in which the JList is displayed.
 * @param  width The number of columns of the window's grid occuppied by the JList.
 * @param  height The number of rows of the window's grid occuppied by the JList.
 * @return the JList
 */
    public JList addList (int row, int col, int width, int height){
      gbc.fill = GridBagConstraints.BOTH;
      gbc.anchor = GridBagConstraints.NORTHWEST;
      gbc.weightx = 500;
      gbc.weighty = 500;
      JList control = new JList (new DefaultListModel()); //height*2, false);
      control.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      control.addMouseListener(new GBAppletDCListener(control, this));
      add (new JScrollPane(control), row, col, width, height);
      gbc.weightx = 100;
      gbc.weighty = 100;
      return control;
   }

/**
 * Adds a JComboBox (pull down list) to the specified position, with the specified
 * width and height.
 * @param  row The beginning row (starting from 1) of the window's grid in which the JComboBox is displayed.
 * @param  col The beginning column (starting from 1) of the window's grid in which the JComboBox is displayed.
 * @param  width The number of columns of the window's grid occuppied by the JComboBox.
 * @param  height The number of rows of the window's grid occuppied by the JComboBox.
 * @return the JComboBox.
 */
    public JComboBox addComboBox (int row, int col, int width, int height){
      gbc.fill = GridBagConstraints.NONE;
      gbc.anchor = GridBagConstraints.NORTHWEST;
      JComboBox control = new JComboBox();
      add (control, row, col, width, height);
      return control;
   }

/**
 * Adds an IntegerField containing the specified integer to the specified position, with the specified
 * width and height.
 * @param  num The integer to be displayed initially.
 * @param  row The beginning row (starting from 1) of the window's grid in which the IntegerField is displayed.
 * @param  col The beginning column (starting from 1) of the window's grid in which the IntegerField is displayed.
 * @param  width The number of columns of the window's grid occuppied by the IntegerField.
 * @param  height The number of rows of the window's grid occuppied by the IntegerField.
 * @returns the IntegerField.
 */
    public IntegerField addIntegerField (int num, int row, int col, int width, int height){
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.anchor = GridBagConstraints.NORTHWEST;
      IntegerField control = new IntegerField (num);
      add (control, row, col, width, height);
      return control;
   }

/**
 * Adds a DoubleField containing the specified number to the specified position, with the specified
 * width and height.
 * @param  num The number to be displayed initially.
 * @param  row The beginning row (starting from 1) of the window's grid in which the DoubleField is displayed.
 * @param  col The beginning column (starting from 1) of the window's grid in which the DoubleField is displayed.
 * @param  width The number of columns of the window's grid occuppied by the DoubleField.
 * @param  height The number of rows of the window's grid occuppied by the DoubleField.
 * @returns the DoubleField.
 */
    public DoubleField addDoubleField (double num, int row, int col, int width, int height){
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.anchor = GridBagConstraints.NORTHWEST;
      DoubleField control = new DoubleField (num);
      add (control, row, col, width, height);
      return control;
   }

/**
 * Adds a JCheckBox to the specified position, with the specified
 * width and height.
 * @param  row The beginning row (starting from 1) of the window's grid in which the JCheckBox is displayed.
 * @param  col The beginning column (starting from 1) of the window's grid in which the JCheckBox is displayed.
 * @param  width The number of columns of the window's grid occuppied by the JCheckBox.
 * @param  height The number of rows of the window's grid occuppied by the JCheckBox.
 * @return the JCheckBox.
 */
    public JCheckBox addCheckBox (String text, int row, int col, int width, int height){
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.anchor = GridBagConstraints.NORTHWEST;
      JCheckBox control = new JCheckBox (text);
      add (control, row, col, width, height);
      return control;
   }

/**
 * Adds a JRadioButton to the specified position, with the specified
 * width and height.
 * @param  row The beginning row (starting from 1) of the window's grid in which the JRadioButton is displayed.
 * @param  col The beginning column (starting from 1) of the window's grid in which the JRadioButton is displayed.
 * @param  width The number of columns of the window's grid occuppied by the JRadioButton.
 * @param  height The number of rows of the window's grid occuppied by the JRadioButton.
 * @return the JRadioButton.
 */
    public JRadioButton addRadioButton (String text, int row, int col, int width, int height){
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.anchor = GridBagConstraints.NORTHWEST;
      JRadioButton control = new JRadioButton (text);
      add (control, row, col, width, height);
      return control;
   }
   
/**
 * Adds a GBPanel to the specified position, with the specified
 * width and height.
 * @param  row The beginning row (starting from 1) of the window's grid in which the GBPanel is displayed.
 * @param  col The beginning column (starting from 1) of the window's grid in which the GBPanel is displayed.
 * @param  width The number of columns of the window's grid occuppied by the GBPanel.
 * @param  height The number of rows of the window's grid occuppied by the GBPanel.
 * @return the GBPanel.
 */
    public GBPanel addPanel(GBPanel panel, int row, int col, int width, int height){
      gbc.fill = GridBagConstraints.BOTH;
      gbc.anchor = GridBagConstraints.NORTHWEST;
      gbc.weightx = 500;
      gbc.weighty = 500;
      add (panel, row, col, width, height);
      gbc.weightx = 100;
      gbc.weighty = 100;
      return panel;
   }

   private void add( Component c,int y, int x, int w, int h){
      gbc.gridx = x-1;
      gbc.gridy = y-1;
      gbc.gridwidth = w;
      gbc.gridheight = h;
      gbl.setConstraints(c, gbc);
      contentPane.add(c);
   }
}

// Controller class to handle button events.

class GBAppletButtonListener implements ActionListener{

   GBApplet myFrame;

   public GBAppletButtonListener(GBApplet frm){
      myFrame = frm;
   }

   public void actionPerformed(ActionEvent e){
      myFrame.buttonClicked((JButton) e.getSource());
   }
}

// Controller class to handle list selection and double click events.

class GBAppletDCListener extends MouseAdapter{

   private JList list;
   private GBApplet app;

   public GBAppletDCListener(JList list, GBApplet app){
      this.list = list;
      this.app = app;
   }

   public void mouseClicked(MouseEvent e) {
      if (((DefaultListModel)list.getModel()).isEmpty())
         return;
      if (e.getClickCount() == 2) {
         int index = list.locationToIndex(e.getPoint());
         String s = (String) ((DefaultListModel) list.getModel()).elementAt(index);
         app.listDoubleClicked(list, s);
      }
      else if (e.getClickCount() == 1)
         app.listItemSelected(list);
   }
}
