import java.io.*;

public class Employee extends Object
                      implements Cloneable, Comparable, Serializable{
                      
   // Class variables (actually, constants)
   static public final int MAX_DAYS = 5;
   static public final int MAX_REGULAR_HOURS = MAX_DAYS * 8;
   static public final double OVERTIME_RATE = 1.5;

   // Instance variables
   private String name;
   private double payRate;
   private int[] days;

   // Constructor methods

   // Initialize a new employee's name to the empty string and her 
   // pay rate and hours worked to 0
   public Employee(){
      name = "";
      payRate = 0;
      days = new int[MAX_DAYS];
      for (int i = 0; i < MAX_DAYS; i++)
         days[i] = 0; 
   }
    
   // Initialize a new employee's attributes to the given parameters
   // Preconditions: payRate > 0 
   //                size of array == MAX_DAYS
   public Employee(String name, double payRate, int[] hoursWorked){
      if (hoursWorked.length != MAX_DAYS)
         throw new IllegalArgumentException(
               "Array must have " + MAX_DAYS + " days");
      if (payRate <= 0)
         throw new IllegalArgumentException("Pay rate must be > 0");
      this.name = name;
      this.payRate = payRate;
      days = new int[MAX_DAYS];
      for (int i = 0; i < MAX_DAYS; i++)
         days[i] = hoursWorked[i]; 
   }

   // Set an employee's name
   public void setName (String name){
      this.name = name;
   }
    
   // Get an employee's name
   public String getName (){
      return name;
   }
  
   // Set an employee's pay rate
   // Precondition: newRate > 0
   public void setPayRate (double newRate){
      if (newRate < 0)
         throw new IllegalArgumentException("new rate must be > 0");
      payRate = newRate;
   }
    
   // Get an employee's pay rate
   public double getPayRate (){
      return payRate;
   }
  
   // Set the hours worked on the indicated day
   // Preconditions: 1 <= whichDay <= MAX_DAYS
   //                hours >= 0
   public void setHours (int whichDay, int hours){
      if (whichDay < 1 || whichDay > MAX_DAYS)
         throw new IllegalArgumentException(
               "Day must be >= 1 and <= " + MAX_DAYS);
      if (hours < 0)
         throw new IllegalArgumentException("hours must be >= 0");

      days[whichDay - 1] = hours;
   }

   // Get the hours worked on the indicated day
   public int getHours (int whichDay){
      if (whichDay < 1 || whichDay > MAX_DAYS)
         throw new IllegalArgumentException(
               "Day must be > 1 and <= " + MAX_DAYS);
      return days[whichDay - 1];
   }
   
   // Compute and return an employee's total hours
   public int getTotalHours(){
      int total = 0;
      for (int i = 0; i < MAX_DAYS; i++)
         total += days[i];
      return total;
   }
    
   // Compute and return an employee's weekly pay
   public double computePay(){
      int total = getTotalHours();
      int overtimeHours = total - MAX_REGULAR_HOURS;
      if (overtimeHours > 0)
         return payRate * OVERTIME_RATE * overtimeHours +
                payRate * MAX_REGULAR_HOURS;
      else
         return payRate * total;
   }
    
   // Return a string representation of a employee's name, pay rate
   // and hours worked.
   public String toString(){
      String str = "Name:      " + name  + "\n" + 
                   "Pay rate:  " + payRate + "\n" +
                   "Hours worked:\n"; 
      for (int i = 0; i < MAX_DAYS; i++)
         str += "Day " + (i + 1) + ": " + days[i] + "\n";         
      return str;
   }
   
   // Compare two employees for equality
   public boolean equals(Object other){
      if (! (other instanceof Employee))
         throw new IllegalArgumentException("Object not an employee");
         
      Employee employee = (Employee)other;
      return name.equals(employee.name);
   }
   
   // Compare two employees for order
   public int compareTo(Object other){
      if (! (other instanceof Employee))
         throw new IllegalArgumentException("Object not an employee");
         
      Employee employee = (Employee)other;
      return name.compareTo(employee.name);
   }
   
   // Clone a new employee
   public Object clone(){
      int[] newDays = new int[MAX_DAYS];
      for (int i = 0; i < MAX_DAYS; i++)
         newDays[i] = days[i];
      return new Employee (name, payRate, newDays);
   }
  
}
