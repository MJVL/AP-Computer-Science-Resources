 /* Employee.java
 An employee knows how to 
 1) read his name and basic payroll information from an employee file, 
 2) read his hours worked from an hours file,
 3) write column headers to a report file, and
 3) compute and write the wages to the same report file.
*/

import java.io.*;
import BreezySwing.Format;       //Import just the Format class from BreezyGUI

public class Employee extends Object{

   private static final double TAX_RATE  = 0.15; //Base tax rate
   private static final double DEDUCTION = 0.02; //Deduction per dependent
   private String firstName = "";                //First name
   private String lastName = "";                 //Last name
   private int    dependents;                    //Number of dependents
   private int    regularHours;                  //Regular hours worked
   private int    overtimeHours;                 //Overtime hours worked
   private double hourlyRate;                    //Hourly pay rate


   //Get the employee's name
   public String getName(){
      return firstName + " " + lastName;
   }

   //Return a string representation of an employee.
   public String toString(){
      return lastName     + " "
           + firstName    + " "
           + hourlyRate   + " "
           + dependents   + " "
           + regularHours + " "
           + overtimeHours;
   }

   //Read an employee's name, dependents, and pay rate.
   //Return true if the data is read successfully else return false.
   public boolean readEmployee(StreamTokenizer stream){
      try{
         stream.nextToken();  lastName   = stream.sval;
         stream.nextToken();  firstName  = stream.sval;
         stream.nextToken();  dependents = (int) stream.nval;
         stream.nextToken();  hourlyRate = stream.nval;
         return (stream.ttype != StreamTokenizer.TT_EOF);
      }catch (Exception e){
         return false;
      }
   }

   //Read an employee's name and hours.
   //Return true if data is read successfully else return false.
   //The name read from the file must match the employee's name.
   public boolean readHoursWorked(StreamTokenizer stream){
      String tempFirstName = "", tempLastName = "";
      try{
         stream.nextToken();  tempLastName  = stream.sval;
         stream.nextToken();  tempFirstName = stream.sval;
         stream.nextToken();  regularHours  = (int) stream.nval;
         stream.nextToken();  overtimeHours = (int) stream.nval;
         if (stream.ttype == StreamTokenizer.TT_EOF) 
            return false;
         return (tempLastName.equals(lastName) &&  
                 tempFirstName.equals(firstName));  
      }catch(Exception e){
         return false;
      }
   }

   //Compute and print the employee's pay.
   //Net pay = gross pay - taxes.
   //Gross pay = hourly rate times regular hours + one and half the hourly
   //            rate for overtime hours.
   //The tax rate equals TAX_RATE less DEDUCTION per dependent.
   public void computeAndPrintPay(PrintWriter stream){
      double regPay = hourlyRate * regularHours;
      double overPay = hourlyRate * 1.5 * overtimeHours;
      double grossPay = regPay + overPay;
      double tax = 
             Math.max (0, grossPay * (TAX_RATE - dependents * DEDUCTION));
      double netPay = grossPay - tax;
      stream.println(
           Format.justify('l', lastName + ", " + firstName, 15)
         + Format.justify('r', dependents                 , 12)
         + Format.justify('r', regPay                     , 10, 2)
         + Format.justify('r', overPay                    , 10, 2)
         + Format.justify('r', grossPay                   , 11, 2)
         + Format.justify('r', tax                        ,  7, 2)
         + Format.justify('r', netPay                     ,  9, 2));
   }

   //Write column headers to the report file.
   public void printHeader(PrintWriter stream){
      stream.println(
           Format.justify('l', "Employee Name", 15)
         + Format.justify('r', "Dependents"   , 12)
         + Format.justify('r', "Reg. Pay"     , 10)
         + Format.justify('r', "O.T. Pay"     , 10)
         + Format.justify('r', "Gross Pay"    , 11)
         + Format.justify('r', "Tax"          ,  7)
         + Format.justify('r', "Net Pay"      ,  9));
      stream.println(
           Format.justify('l', "-------------", 15)
         + Format.justify('r', "----------"   , 12)
         + Format.justify('r', "--------"     , 10)
         + Format.justify('r', "--------"     , 10)
         + Format.justify('r', "---------"    , 11)
         + Format.justify('r', "---"          ,  7)
         + Format.justify('r', "-------"      ,  9));
   }
}
