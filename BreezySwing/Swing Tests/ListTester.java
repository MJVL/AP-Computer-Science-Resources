import javax.swing.*;
import BreezySwing.*;

public class ListTester extends GBFrame{

   JLabel listLabel = addLabel ("Names",1,1,1,1);
   JLabel taLabel = addLabel ("Results",1,2,1,1);
   JMenuItem fileOpen = addMenuItem("File", "Open");
   JMenuItem fileSave = addMenuItem("File", "Save");
   JMenuItem editCut = addMenuItem("Edit", "Cut");
   JMenuItem editPaste = addMenuItem("Edit", "Paste");
   JList list = addList(2, 1, 1, 4);
   JTextArea ta = addTextArea("", 2, 2, 1, 4);

   public ListTester(){
      DefaultListModel model = (DefaultListModel) list.getModel();
      for (int i = 1; i <= 10; i++)
         model.addElement("Ken" + i);
   }

   public void listItemSelected(JList listObj){
      ta.append((String)listObj.getSelectedValue() + "\n");
   }

   public void listDoubleClicked(JList listObj, String s){
      messageBox(s);
   }


   public void menuItemSelected(JMenuItem mi){
      if (mi == fileOpen)
         ta.append("Open\n");
      else if (mi == fileSave)
         ta.append("Save\n");
      else if (mi == editCut)
         ta.append("Cut\n");
      else if (mi == editPaste)
         ta.append("Paste\n");
   }

   public static void main(String[] args){
      JFrame frm = new ListTester();
      frm.setSize (300, 300);
      frm.setVisible (true);
   }
}


