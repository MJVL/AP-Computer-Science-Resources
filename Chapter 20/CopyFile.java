import javax.swing.*;
import java.io.*;
import BreezySwing.*;

public class CopyFile extends GBFrame{

   private JButton copyButton;

   public CopyFile(){
      copyButton = addButton("Copy a file",1,1,1,1);
   }

   public void buttonClicked (JButton buttonObj){
      JFileChooser chooser = new JFileChooser();
      File file;
      FileInputStream inputStream;
      FileOutputStream outputStream;
      int data;

         // Open input file.
      int result = chooser.showOpenDialog(this);
      if (result == JFileChooser.APPROVE_OPTION)
         try{
            file = chooser.getSelectedFile();
            inputStream = new FileInputStream(file);
            result = chooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION){
               file = chooser.getSelectedFile();
               outputStream = new FileOutputStream(file);
               // Copy the data.
               data = inputStream.read();
               while (data != -1){
                  outputStream.write(data);
                  data = inputStream.read();
               }
            }
         }catch (IOException e){
            System.err.println("File IO error " + e.toString());
      }
   }

   public static void main (String[] args){
      JFrame frm = new CopyFile();
      frm.setSize (100, 100);
      frm.setVisible(true);
   }
}

