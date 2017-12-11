/*  StudentDialog.java

1) This is the dialog for the student test scores program.
2) It displays the student passed to it.
3) The user can then change the data in the dialog's window.
4) If the user clicks the OK button, the student is updated with
   the data in the window.
5) If the user clicks the Cancel button, the dialog closes and returns
   without modifying the student.
*/

import javax.swing.*;
import BreezySwing.*;

public class StudentDialog extends GBDialog{

   //Window objects
   private JLabel nameLabel, test1Label, test2Label, test3Label;
  
   private JTextField nameField;
   private IntegerField test1Field, test2Field, test3Field;

   private JButton btnOK, btnCancel;

   //Instance variables
   private Student student;       //The student being modified

   public StudentDialog (JFrame f, Student stu){
   //Constructor
   //  Preconditions  -- the input parameters are not null
   //  Postconditions -- the dialog's window is initialized
   //                 -- the student variable is set
   //                 -- the student's data are displayed in the
   //                    dialog's window
   
      //Housekeeping required in every dialog
      super (f);    

      //Set the dialog's size and title           
      setSize (250,150);
      setTitle ("Student Dialog");

      //Set the dialog's default value for the close indicator to Cancel.
      //If the user closes the dialog without clicking either the OK or
      //Cancel button, the default takes effect.
      setDlgCloseIndicator ("Cancel");

      //Save the student reference and display the student data in the
      //dialog's window.
      student = stu;

      // Instantiate the window objects
      nameLabel     = addLabel ("Name"     ,1,1,1,1);
      test1Label    = addLabel ("Test 1"   ,2,1,1,1);
      test2Label    = addLabel ("Test 2"   ,3,1,1,1);
      test3Label    = addLabel ("Test 3"   ,4,1,1,1);

      nameField    = addTextField    ("",1,2,2,1);
      test1Field = addIntegerField   (0 ,2,2,1,1);
      test2Field = addIntegerField   (0 ,3,2,1,1);
      test3Field = addIntegerField   (0 ,4,2,1,1);

      btnOK     = addButton ("OK"    ,5,1,1,1);
      btnCancel = addButton ("Cancel",5,2,1,1);

      // Display the student's information
      nameField.setText       (student.getName());
      test1Field.setNumber    (student.getScore(1));
      test2Field.setNumber    (student.getScore(2));
      test3Field.setNumber    (student.getScore(3));
   }

   public void buttonClicked (JButton buttonObj){
   //Responds to the OK and Cancel buttons.
   //  Preconditions  -- one of the two buttons has been clicked
   //  Postconditions -- if the Cancel button then 
   //                      the student is not modified 
   //                      the close indicator equals Cancel
   //                      the dialog is closed
   //                      control returns to the caller 
   //                 -- if the OK button then
   //                      the student is modified
   //                      the close indicator equals OK
   //                      the dialog is closed
   //                      control returns to the caller 

      //Get the data from the screen
      String name = nameField.getText();
      int score1 = test1Field.getNumber();
      int score2 = test2Field.getNumber();
      int score3 = test3Field.getNumber();
      String   validationErrors;

      if (buttonObj == btnCancel)               //Cancel button

         //Close the dialog and return to the caller
         dispose();  
                           
      else{                                    //OK button

            //Update the student with the screen data
            student.setName(name);
            student.setScore(1, score1);
            student.setScore(2, score2);
            student.setScore(3, score3);

            //Set the close indicator to OK, close the dialog, and
            //return to the caller.
            setDlgCloseIndicator ("OK");
            dispose();
      }
   }
}
