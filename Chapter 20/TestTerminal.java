/* TestTerminal.java
A simple demonstration of terminal I/O.
*/

import java.io.*;

public class TestTerminal{

   public static void main (String[] args){
      String name;
      int age;
      double weight;
      
      while (true){
         try{
            //Instantiate a buffered reader on System.in
            InputStreamReader reader = new InputStreamReader(System.in);
            BufferedReader buffer = new BufferedReader(reader);
         
            //Prompt the user for a name.
            //Read the name and save it in a string variable.
            System.out.print("\nEnter your name or \"quit\": ");
            name = buffer.readLine();
            
            //Break if name equals "quit"
            if (name.equals ("quit")) break;
         
            //Prompt the user for an age.
            //Read the age as a string, convert it to an integer, and 
            //store it in an int variable.
            System.out.print("Enter your age: ");
            age = (Integer.valueOf(buffer.readLine())).intValue();
         
            //Prompt the user for a weight.
            //Read the weight as a string, convert it to a double, 
            //and store it in a double variable.
            System.out.print("Enter your weight: ");
            weight = (Double.valueOf(buffer.readLine())).doubleValue();
         
            //Output the results
            if (age <= 15)
               System.err.println
               ("Sorry: use of this program is restricted to those over 15!");
            else
               System.out.println
               (name + ", age " + age + ", weight " + weight);
            
         }catch(Exception e){
         
            //Flag input errors
            System.err.println("Input error -- " + e.toString());
            
         }
      }
      
      System.out.println("Done");
   }
}



