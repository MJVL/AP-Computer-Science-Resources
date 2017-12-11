import javax.swing.*;
import java.io.*;
import BreezySwing.*;

// Displays the contents of an input file on the screen.
// Reads and converts one character at a time.

public class CharConverter extends GBFrame{
   private JLabel nameLabel;
   private JTextField nameField;
   private JButton displayButton;
   private JTextArea output;

   public CharConverter(){
      nameLabel = addLabel("File Name:", 1, 1, 1, 1);
      nameField = addTextField("", 1, 2, 1, 1);
      displayButton = addButton("Display the Contents", 2, 1, 1, 1);
      output = addTextArea("", 3, 1, 2, 6);
      nameField.requestFocus();
      output.setEditable(false);
      setTitle("Char Converter");
   }

   public void buttonClicked(JButton buttonObj){
      String fileName = nameField.getText();
      try{
         FileInputStream stream = new FileInputStream(fileName);
         readAndProcessData(stream);
         stream.close();
      }
      catch(IOException e){
         messageBox("Error in opening input file:\n" + e.toString());
      }
      nameField.requestFocus();
      nameField.selectAll();
   }

   private void readAndProcessData(FileInputStream stream){
      InputStreamReader reader = new InputStreamReader(stream);
      try{
         output.setText("");
         int data = reader.read();
         while (data != -1){
            char ch = (char) data;
            ch = Character.toUpperCase(ch);
            output.append(ch + "");
            data = reader.read();
         }
      }
      catch(IOException e){
         messageBox("Error in file input:\n" + e.toString());
      }
   }

   public static void main (String[] args){
      JFrame frm = new CharConverter();
      frm.setSize(300, 300);
      frm.setVisible(true);
   }

}

