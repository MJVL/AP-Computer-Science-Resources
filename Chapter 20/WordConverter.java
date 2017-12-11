import javax.swing.*;
import java.io.*;
import BreezySwing.*;

// Displays the contents of an input file on the screen.
// Reads and converts one word at a time.

public class WordConverter extends GBFrame{
   private JLabel nameLabel;
   private JTextField nameField;
   private JButton displayButton;
   private JTextArea output;

   public WordConverter(){
      nameLabel = addLabel("File Name:", 1, 1, 1, 1);
      nameField = addTextField("", 1, 2, 1, 1);
      displayButton = addButton("Display the Contents", 2, 1, 1, 1);
      output = addTextArea("", 3, 1, 2, 6);
      nameField.requestFocus();
      output.setEditable(false);
      setTitle("Word Converter");
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
      BufferedReader bufReader = new BufferedReader(iStrReader);
      StreamTokenizer reader = new StreamTokenizer(bufReader);
      try{
         output.setText("");
         reader.nextToken();
         while (reader.ttype != StreamTokenizer.TT_EOF){
            String data = reader.sval;
            output.append(data + "\n");
            reader.nextToken();
         }
      }
      catch(IOException e){
         messageBox("Error in file input:\n" + e.toString());
      }
   }

   public static void main (String[] args){
      JFrame frm = new WordConverter();
      frm.setSize(300, 300);
      frm.setVisible(true);
   }

}

