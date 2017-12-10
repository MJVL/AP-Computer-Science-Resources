import javax.swing.*;
import BreezySwing.*;


public class EmployeeDialog extends GBDialog{
    
   // Lay out the GUI as in an earlier example

   JLabel nameLbl = addLabel("Name", 1,1,1,1);
   JTextField nameFld = addTextField("", 1,2,1,1);
   JLabel payRateLbl = addLabel("Pay Rate", 2,1,1,1);
   DoubleField payRateFld = addDoubleField(0, 2,2,1,1);
   JLabel daysLbl = addLabel("Daily hours",3,1,1,1);
   IntegerField day1Fld = addIntegerField(0, 3,2,1,1);
   IntegerField day2Fld = addIntegerField(0, 3,3,1,1);
   IntegerField day3Fld = addIntegerField(0, 3,4,1,1);
   IntegerField day4Fld = addIntegerField(0, 3,5,1,1);
   IntegerField day5Fld = addIntegerField(0, 3,6,1,1);
   
   JButton okBtn = addButton("OK", 4,1,1,1);
   JButton cancelBtn = addButton("Cancel", 4,2,1,1);

   // The dialog is passed an employee object and must refer to it
   // at several locations thereafter.

   private Employee emp;
   
   public EmployeeDialog(JFrame parent, Employee emp){
      // The next few lines are part of every dialog
      super(parent);                                 // ** REQUIRED **
      setTitle("Employee Dialog");
      setDlgCloseIndicator("Cancel");                // Default is "Cancel"
      setSize(300, 200);                             // Default is (300,300)

      this.emp = emp;
      nameFld.setText(emp.getName());
      payRateFld.setNumber(emp.getPayRate());
      day1Fld.setNumber(emp.getHours(1));
      day2Fld.setNumber(emp.getHours(2));
      day3Fld.setNumber(emp.getHours(3));
      day4Fld.setNumber(emp.getHours(4));
      day5Fld.setNumber(emp.getHours(5));
   }
   
   public void buttonClicked(JButton buttonObj){
      // The choice of buttons is entirely up to the programmer
      // HOWEVER, pull down menus are NOT available.
      if (buttonObj == okBtn){
         emp.setName(nameFld.getText());
         emp.setPayRate(payRateFld.getNumber());
         emp.setHours(1, day1Fld.getNumber());
         emp.setHours(2, day2Fld.getNumber());
         emp.setHours(3, day3Fld.getNumber());
         emp.setHours(4, day4Fld.getNumber());
         emp.setHours(5, day5Fld.getNumber());
         setDlgCloseIndicator("OK");
      }
      dispose();
   }
}
