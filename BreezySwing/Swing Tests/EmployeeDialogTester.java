import javax.swing.*;
import BreezySwing.*;


public class EmployeeDialogTester extends GBFrame{
    
   JTextArea outputArea = addTextArea("", 1,1,3,5);
   JButton displayBtn = addButton("Modify", 6,1,2,1);

   private Employee emp;
   
   public EmployeeDialogTester(){
      int [] hours = {8, 10, 6, 8, 9};
      emp = new Employee("Sue", 6.50, hours);
      outputArea.setText(emp.toString());
      setTitle("Employee Dialog Tester");
   }
   
   public void buttonClicked(JButton buttonObj){
      EmployeeDialog dlg = new EmployeeDialog(this, emp);
      dlg.show();
      if (dlg.getDlgCloseIndicator().equals("OK"))
         outputArea.setText(emp.toString());
   }
     
   public static void main(String[] args){
      JFrame frm = new EmployeeDialogTester();
      frm.setSize(400, 300);
      frm.setVisible(true);         
   }
}
