import javax.swing.*;
import java.io.*;
import BreezySwing.*;

public class ConvertText extends GBFrame{

   //Window objects
   private JLabel nameLabel;
   private JTextField nameField;
   private JButton displayButton;
   private JTextArea output;

   //Constructor
   public ConvertText(){
      nameLabel      = addLabel ("File Name:"          ,1,1,1,1);
      nameField  = addTextField (""                    ,1,2,1,1);
      displayButton = addButton ("Display the Contents",2,1,2,1);
      output      = addTextArea (""                    ,3,1,2,6);
      nameField.requestFocus();                 //Move cursor to nameField
      output.setEditable(false);      //Prevent user from modifying output
      setTitle("Convert Text to Uppercase");     //Give the window a title
   }

   //Respond to the command button
   public void buttonClicked(JButton buttonObj){

      //Get the name of the text file
      String fileName = nameField.getText();
      
      try{
      
         //Open an input connection on the file, read and process the file,
         //close the file.
         FileInputStream stream = new FileInputStream(fileName);
         readAndProcessData(stream);
         stream.close();
         
      }catch(IOException e){
      
         //If cannot open the file, then inform the user.
         messageBox("Error in opening input file:\n" + e.toString());
        
      }

      //Get ready for the user's next input
      nameField.requestFocus();     //Move cursor to nameField
      nameField.selectAll();        //and select all text in the field
   }


   //Read and process the data (this is a stub)
   private void readAndProcessData(FileInputStream stream){
      messageBox("Running readAndProcessData\n" +
                 "File opened successfully");
   }

   public static void main (String[] args){
      ConvertText tpo = new ConvertText();
      tpo.setSize(300, 300);
      tpo.setVisible(true);
   }
}



