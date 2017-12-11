import javax.swing.*;
import BreezySwing.*;

public class TuityFruity extends GBFrame{

   JList list;                   // The scrolling list
   JTextField field;
   JMenuItem addMI, deleteMI;

   DefaultListModel listModel;   // The scrolling list's model

   public TuityFruity(){

      // Instantiate window objects
      list  = addList(         1,1,1,1);
      field = addTextField("", 1,2,1,1);
      addMI    = addMenuItem("Edit", "Add");
      deleteMI = addMenuItem("Edit", "Delete");

      // Save a reference to the list's model for later use
      listModel = (DefaultListModel)list.getModel();

      // Add initial fruits to the list
      listModel.add(0, "Apple");
      listModel.add(1, "Banana");
      listModel.add(2, "Cherry");
      listModel.add(3, "Orange");
      listModel.add(4, "Peach");
      listModel.add(5, "Pear");

      // Select the first fruit in the list
      list.setSelectedIndex(0);
   }

   public void listItemSelected(JList listObj){

      // Transfer the selected item to the field
      String str = (String)list.getSelectedValue();
      field.setText(str);
   }

   public void menuItemSelected(JMenuItem mi){
      if (mi == addMI){

         // Add the field's text to the end of the list,
         // select it, and scroll to it if necessary
         String str = field.getText();
         listModel.addElement(str);
         list.setSelectedValue(str, true);
      }else if (listModel.size() != 0){

          // Delete the selected item and select the first
          // item if the list is not empty
          listModel.removeElementAt(list.getSelectedIndex());
          if (listModel.size() != 0)
             list.setSelectedIndex(0);
      }
   }

   static public void main(String[] args){
      TuityFruity theGUI = new TuityFruity();
      theGUI.setSize(300, 150);
      theGUI.setVisible(true);
   }
}
