import javax.swing.*;
import java.io.*;
import BreezySwing.*;

// Attempts to open an input file.

public class OpenTheFile extends GBFrame{
   private JLabel nameLabel;
   private JTextField nameField;
   private JButton displayButton;
   private JTextArea output;

   public OpenTheFile(){
      nameLabel = addLabel("File Name:", 1, 1, 1, 1);
      nameField = addTextField("", 1, 2, 1, 1);
      displayButton = addButton("Display the Contents", 2, 1, 1, 1);
      output = addTextArea("", 3, 1, 2, 6);
      nameField.requestFocus();
      output.setEditable(false);
      setTitle("Open the File");
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
      messageBox("Running readAndProcessData\n" +
                 "File opened successfully");
   }

   public static void main (String[] args){
      JFrame frm = new OpenTheFile();
      frm.setSize(300, 300);
      frm.setVisible(true);
   }

}

