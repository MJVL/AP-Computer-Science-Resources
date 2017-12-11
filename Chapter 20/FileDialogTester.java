import javax.swing.*;
import BreezySwing.*;
import java.io.*;

public class FileDialogTester extends GBFrame{

   private JMenuItem openMI, saveMI;

   public FileDialogTester(){
      openMI = addMenuItem("File", "Open");
      saveMI = addMenuItem("File", "Save");
   }

   public void menuItemSelected(JMenuItem mi){
      if (mi == openMI)
         openFile();
      else
         saveFile();
   }

   private void openFile(){
      JFileChooser chooser = new JFileChooser("c:\\Javafiles");
      int result = chooser.showOpenDialog(this);
      if (result == JFileChooser.CANCEL_OPTION)
         messageBox ("The dialog was cancelled.");
      else
         try{
            File file = chooser.getSelectedFile();
            messageBox ("File name: " + file.getName());
         }catch(Exception e){
            messageBox("Error opening input file " + e.toString());
         }
   }

   private void saveFile(){
   }

   public static void main(String[] args){
      JFrame frm = new FileDialogTester();
      frm.setSize(200, 200);
      frm.setVisible(true);
   }
}
