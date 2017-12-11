import javax.swing.*;
import java.io.*;
import BreezySwing.*;

// Displays the contents of an input file on the screen.
// Reads and converts one line at a time.

public class LineConverter extends GBFrame{
   private JLabel nameLabel;
   private JTextField nameField;
   private JButton displayButton;
   private JTextArea output;

   public LineConverter(){
      nameLabel = addLabel("File Name:", 1, 1, 1, 1);
      nameField = addTextField("", 1, 2, 1, 1);
      displayButton = addButton("Display the Contents", 2, 1, 1, 1);
      output = addTextArea("", 3, 1, 2, 6);
      nameField.requestFocus();
      output.setEditable(false);
      setTitle("Line Converter");
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
      InputStreamReader iStrReader = new InputStreamReader(stream);
      BufferedReader reader = new BufferedReader(iStrReader);
      try{
         output.setText("");
         String data = reader.readLine();
         while (data != null){
            data = data.toUpperCase();
            output.append(data + "\n");
            data = reader.readLine();
         }
      }
      catch(IOException e){
         messageBox("Error in file input:\n" + e.toString());
      }
   }

   public static void main (String[] args){
      JFrame frm = new LineConverter();
      frm.setSize(300, 300);
      frm.setVisible(true);
   }

}

