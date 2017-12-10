//BreezyGUI Copyright Martin Osborne and Ken Lambert 1998-2000
//All rights reserved


package BreezyGUI;

import java.io.*;

public class Console{

   static InputStreamReader reader;
   static BufferedReader    buffer;

   public static void pause(){
      System.out.print ("\nPress Enter to continue . . . ");
      try {
         if (reader == null){
            reader = new InputStreamReader (System.in);
            buffer = new BufferedReader (reader);
         }
         buffer.readLine();
      }
      catch (Exception e){
         System.exit(0);
      }
   }

   public static int readInt(String prompt){
      int value = 0;
      String s = "";
      System.out.print (prompt);
      try {
         if (reader == null){
            reader = new InputStreamReader (System.in);
            buffer = new BufferedReader (reader);
         }
         s = (buffer.readLine()).trim();
         value = (new Integer(s)).intValue();
      }
      catch (Exception e){
         System.out.println("\n\nError in Console.readInt\n" + e.toString() + "\n");
         Console.pause();
         System.exit(0);
      }
      return value;
   }

   public static double readDouble(String prompt){
      double value = 0;
      String s = "";
      System.out.print (prompt);
      try {
         if (reader == null){
            reader = new InputStreamReader (System.in);
            buffer = new BufferedReader (reader);
         }
         s = (buffer.readLine()).trim();
         value = (new Double(s)).doubleValue();
      }
      catch (Exception e){
         System.out.println("\n\nError in Console.readDouble\n" + e.toString() + "\n");
         Console.pause();
         System.exit(0);
      }
      return value;
   }
   
   public static char readChar(String prompt){
      int value = 0;
      String s = "";
      System.out.print (prompt);
      try {
         if (reader == null){
            reader = new InputStreamReader (System.in);
            buffer = new BufferedReader (reader);
         }
         s = buffer.readLine();
         s += "?";
         value = s.charAt(0);
      }
      catch (Exception e){
         System.out.println("\n\nError in Console.readChar:\n" + e.toString() + "\n");
         Console.pause();
         System.exit(0);
      }
      return (char)value;
   }
   
   public static String readLine(String prompt){
      String value = "";
      System.out.print (prompt);
      try {
         if (reader == null){
            reader = new InputStreamReader (System.in);
            buffer = new BufferedReader (reader);
         }
         value = buffer.readLine();
      }
      catch (Exception e){
         System.out.println("\n\nError in Console.readLine\n" + e.toString() + "\n");
         Console.pause();
         System.exit(0);
      }
      return value;
   }

}


