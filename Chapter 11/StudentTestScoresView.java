/* StudentTestScoresView.java

1) This program maintains a list of students and displays their names
   in a scrolling list control.
2) Single clicking on a name displays the student's information.
3) Double clicking on a name allows the student's information to be
   changed.
4) Other operations are supported by means of an Edit menu with the options:
   Add    -- adds a new student to the end of the list and selects the
             name in the scrolling list
   Modify -- modifies the selected student
   Delete -- deletes the selected student
5) The selected student is the one corresponding to the highlighted
   name in the scrolling list control.

*/

import javax.swing.*;
import BreezySwing.*;

public class StudentTestScoresView extends GBFrame{

   //Window objects
   private JLabel namesLB, detailedInfoLB;
   private JList nameList;
   private JTextArea detailedInfoField;
   private JMenuItem addMI, modifyMI, deleteMI, 
             sortByNameMI, sortByHighScoreMI, sortByAverageScoreMI;

   // Other instance variables ---------------------------------
   
   private StudentTestScoresModel model;
   
   // Constructor-----------------------------------------------

   public StudentTestScoresView(){
      setTitle("Student Test Scores");
      model = new StudentTestScoresModel();

      // Instaniate window objects
      namesLB              = addLabel ("Names"               ,1,1,1,1);
      detailedInfoLB       = addLabel ("Detailed Information",1,2,1,1);

      nameList     = addList              (                    2,1,1,5);
      detailedInfoField = addTextArea (""                 ,2,2,1,5);


      addMI    = addMenuItem ("Edit", "Add");
      modifyMI = addMenuItem ("Edit", "Modify");
      deleteMI = addMenuItem ("Edit", "Delete");
      sortByNameMI         = addMenuItem ("Sort", "By Name");
      sortByHighScoreMI    = addMenuItem ("Sort", "By High Score");
      sortByAverageScoreMI = addMenuItem ("Sort", "By Average Score ");
      displayCurrentStudent();
   } 

   public void listItemSelected (JList listObj){
   //Displays the information for the student whose name is selected.
   //  Preconditions  -- a name in the name list is single clicked
   //  Postconditions -- the student corresponding to the name
   //                    is selected and her info is displayed
   
      String name = (String)nameList.getSelectedValue();
      model.setCurrentStudent(name);
      displayCurrentStudent();
   }

   public void listDoubleClicked (JList listObj, String itemClicked){
   //Opens a modify dialog on the student whose name is selected.
   //Note: double clicking on a name automatically triggers the single click
   //      event first.
   //  Preconditions  -- a name in the list is double clicked
   //  Postconditions -- see the modifySelectedStudent method
   
      modify();
   }

   public void menuItemSelected (JMenuItem mi){
   //Responds to a menu selection
   //  Preconditions  -- none
   //  Postconditions -- see the methods corresponding to the menu items
   
      if      (mi == addMI)    add();
      else if (mi == modifyMI) modify();
      else if (mi == deleteMI) delete();
      // Handling sort options is an exercise
   }   

   // Private methods-------------------------------------------

   private void add(){
   //Adds a new student.
   //  Preconditions  -- none
   //  Postconditions -- if the user cancels the dialog, then no change
   //                 -- else the new student is selected
   //                      she is added to the end of the student list
   //                      her name is added to the end of the name list 
   //                      her info is displayed
   //                      she becomes the selected item in both lists

      Student tempStu = new Student();
      StudentDialog studentDialog
                     = new StudentDialog (this, tempStu);
      studentDialog.show();
      if (studentDialog.getDlgCloseIndicator().equals ("OK")){
         String message = model.add (tempStu);
         if (message != null){
            messageBox(message);
            return;
         }
         model.setCurrentStudent(tempStu.getName());
         displayCurrentStudent();
      }
   }

   private void modify(){
     // Completion of this method is left as an exercise
   }

   private void delete(){
     // Completion of this method is left as an exercise
   }

   private void displayCurrentStudent(){
      Student stu = model.getCurrentStudent();
      DefaultListModel listModel = (DefaultListModel) nameList.getModel();
      if (stu == null){
         listModel.clear();
         detailedInfoField.setText("");
      }else{
         String[] names = model.getNames();
         listModel.clear();
         for (int i = 0; i < names.length; i++)
            listModel.add(i, names[i]);
         nameList.setSelectedValue(stu.getName(), true);
         detailedInfoField.setText(stu.toString());
      }
   }
         
   // main------------------------------------------------------

   public static void main (String[] args){
      StudentTestScoresView theGUI = new StudentTestScoresView();
      theGUI.setSize (300, 300);               
      theGUI.setVisible(true);    
   }                    
}

