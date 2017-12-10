// Copyright 2001
// Kenneth A. Lambert and Martin Osborne

package BreezySwing;
import javax.swing.*;
import java.awt.*;
import java.io.*;

import java.awt.event.*;
import javax.swing.event.*;

/**
 * The class GBFrame (short for Grid Bag Frame) provides a high-level application window.
 * The window comes with a built in GridBagLayout.  Components are added
 * to the window by specifying their row position, column position, width (in columns),
 * and height (in rows)in the grid.
 * <BR>
 * <BR>
 * Events of different types, such as button clicks and menu item selections,
 * are handled by implementing the appropriate GBFrame methods in the application.
 * <BR>
 * <BR>
 * Example use of GBFrame:
 *
 * <pre>
 *    // Hello world application with BreezySwing.<BR>
 *    import javax.swing.*;
 *    import BreezySwing.*;<BR>
 *
 *    public class HelloWorld extends GBFrame{<BR>
 *
 *        // Add a labeled button to the window at row 1, column 1, width 1, height 1.
 *        JButton clickMe = addButton("Click me", 1, 1, 1, 1);<BR>
 *
 *        // Implement the method to handle the button click.
 *        public void buttonClicked(JButton buttonObj){
 *           messageBox("Hello world!");
 *        }<BR>
 *
 *        // Implement the method that runs when the application is launched.
 *        public static void main(String[] args){
 *           Frame frm = new HelloWorld();
 *           frm.setSize(100, 100);
 *           frm.setVisible(true);
 *        }
 *    }
 * </pre>
 */
 public class GBFrame extends JFrame{
   GridBagLayout gbl = new GridBagLayout();
   GridBagConstraints gbc = new GridBagConstraints();
   JMenuBar menuBar = new JMenuBar();
   GBFrameWindowListener windowListener;
   Container contentPane;

/**
 * Creates a GBFrame application window.
 */
    public GBFrame(){
      contentPane = getContentPane();
      contentPane.setLayout(gbl);
      gbc.weightx = 100;
      gbc.weighty = 100;
      gbc.insets.bottom = 1;
      gbc.insets.left = 2;
      gbc.insets.right = 2;
      gbc.insets.top = 1;
      setTitle(" ");

      windowListener = new GBFrameWindowListener(this);
      addWindowListener(windowListener);
   }

/**
 * Used with non-GUI applications (tester programs) to pause execution
 * until the user hits the Enter key.
 * Prevents a "fly-by" disappearance of the terminal window
 * in some environments by pausing execution until the user
 * hits the Enter key.  Usage: GBFrame.pause();
 */
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

   public void setSize(int width, int height){
      super.setSize(width, height);
      if (menuBar.getMenuCount() > 0)
         setJMenuBar (menuBar);
   }

/**
 * Pops up a message box containing the number and an OK button.
 */
    public void messageBox (double num){
      MessageBox mb = new MessageBox (this, "" + num);
      mb.show();
   }

/**
 * Pops up a message box containing the string and an OK button.
 */
    public void messageBox (String msg){
      MessageBox mb = new MessageBox (this, msg);
      mb.show();
   }

/**
 * Pops up a message box containing the string representation of the object and an OK button.
 */
   public void messageBox (Object msg){
      MessageBox mb = new MessageBox (this, msg.toString());
      mb.show();
   }

   public void messageBox (double num, int width, int height){
      MessageBox mb = new MessageBox (this, "" + num);
      mb.setSize(width, height);
      mb.show();
   }

   public void messageBox (String msg, int width, int height){
      MessageBox mb = new MessageBox (this, msg);
      mb.setSize(width, height);
      mb.show();
   }

   public void messageBox (Object msg, int width, int height){
      MessageBox mb = new MessageBox (this, msg.toString());
      mb.setSize(width, height);
      mb.show();
   }

   // Abstract event handlers for menus, buttons, lists, and mice.

/**
 * The application must implement this method in order to handle menu events.  If no menus
 * are added to the window, this method need not be implemented.
 * @param  mi The MenuItem in which the selection occurred.
 * Example:
 * <pre>
 *    // Add the menu items to the window.
 *    JMenuItem newFileMI = addMenuItem("File", "New");
 *    JMenuItem openFileMI = addMenuItem("File", "Open");
 *    JMenuItem saveFileMI = addMenuItem("File", "Save");
 *    // Handle the menu selection.
 *    public void menuItemSelected(JMenuItem mi){
 *       if (mi == newFileMI)
 *          messageBox("File/New selected");
 *       else if (mi == openFileMI)
 *          messageBox("File/Open selected");
 *       else
 *          messageBox("File/Save selected");
 *    }
 * </pre>
 */
    public void menuItemSelected (JMenuItem mi){
      messageBox ("You need a 'menuItemSelected' method");
   }

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
   // are added for menu items, buttons, and lists.

/**
 * Adds an item with the specified name to a menu with the specified name
 * in the window.
 * @param  menuName The name of the menu.
 * @param  item name The name of the menu item (selection).
 * @return The JMenuItem.
 * Example:
 * <pre>
 *    JMenuItem newFileMI = addMenuItem("File", "New");
 *    JMenuItem openFileMI = addMenuItem("File", "Open");
 *    JMenuItem saveFileMI = addMenuItem("File", "Save");
 * </pre>
 */
    public JMenuItem addMenuItem (String menuName, String itemName){

      JMenu menu = null;
      int i;
      for (i = 0; i < menuBar.getMenuCount(); i++){
         menu = menuBar.getMenu (i);
         if (menuName.equals (menu.getText())) break;
      }
      if (i == menuBar.getMenuCount()){
         menu = new JMenu (menuName);
         menuBar.add (menu);
      }
      JMenuItem menuItem = new JMenuItem (itemName);
      menuItem.addActionListener(new GBFrameMenuListener(this));
      menu.add (menuItem);
      return menuItem;
   }

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
      control.addActionListener(new GBFrameButtonListener(this));
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
      control.addMouseListener(new GBFrameDCListener(control, this));
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
      myFrame.buttonClicked((JButton) e.getSource());
   }
}

// Controller class to handle menu events.

class GBFrameMenuListener implements ActionListener{

   GBFrame myFrame;

   public GBFrameMenuListener(GBFrame frm){
      myFrame = frm;
   }

   public void actionPerformed(ActionEvent e){
      myFrame.menuItemSelected((JMenuItem) e.getSource());
   }
}

// Controller class to handle list selection and double click events.

class GBFrameDCListener extends MouseAdapter{

   private JList list;
   private GBFrame frm;

   public GBFrameDCListener(JList list, GBFrame frm){
      this.list = list;
      this.frm = frm;
   }

   public void mouseClicked(MouseEvent e) {
      if (((DefaultListModel)list.getModel()).isEmpty())
         return;
      if (e.getClickCount() == 2) {
         int index = list.locationToIndex(e.getPoint());
         String s = (String) ((DefaultListModel) list.getModel()).elementAt(index);
         frm.listDoubleClicked(list, s);
      }
      else if (e.getClickCount() == 1)
         frm.listItemSelected(list);
   }
}
