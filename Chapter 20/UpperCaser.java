import javax.swing.*;
import java.io.*;
import BreezySwing.*;

public class UpperCaser extends GBFrame{
   JButton findButton = addButton("Make Uppercase", 1, 1, 1, 1);
   JTextArea output = addTextArea("", 2, 1, 2, 6);

   public UpperCaser(){
      setTitle("File UpperCaser");
   }

   public void buttonClicked(JButton buttonObj){
      JFileChooser chooser = new JFileChooser();
      int result = chooser.showOpenDialog(this);
      if (result == JFileChooser.APPROVE_OPTION)
         try{
            File file = chooser.getSelectedFile();
            FileInputStream inputStream = new FileInputStream(file);
            toUpperCase(inputStream);
         }catch(IOException e){
         messageBox ("Error in opening input file" + e.toString());
      }
   }

   private void toUpperCase(FileInputStream inputStream){
      try{
         output.setText("");
         int data = inputStream.read();
         while (data != -1){
            char ch = (char) data;
            ch = Character.toUpperCase(ch);
            output.append(ch + "");
            data = inputStream.read();
         }
      }
      catch(IOException e){
         System.out.println("Error in file input" + e.toString());
      }
   }

   public static void main (String[] args){
      JFrame frm = new UpperCaser();
      frm.setSize(600, 300);
      frm.setVisible(true);
   }

}

