//Copyright Martin Osborne and Ken Lambert 1998-2001
//All rights reserved


package TerminalIO;

import java.io.*;

/**
 * The class KeyboardReader contains input methods for terminal I/O.  Type-specific
 * methods read characters, integers, doubles, and strings.  Another
 * method pauses output and waits for the user to press the enter key 
 * to continue. Examples:
 * <pre>
 *
 *   KeyboardReader reader = new KeyboardReader();
 *   char letter = reader.readChar  ("Enter a letter: ");
 *   double    d = reader.readDouble("Enter a real number: ");
 *   int       i = reader.readInt   ("Enter an integer: ");
 *   String name = reader.readLine  ("Enter your full name: ");
 *   reader.pause();
 *
 * </pre>
 */
 
public class KeyboardReader{

   private InputStreamReader reader;
   private BufferedReader    buffer;

   public KeyboardReader(){
      reader = new InputStreamReader (System.in);
      buffer = new BufferedReader (reader);
   }   

/**
 * Used with non-GUI applications to prevents a "fly-by" 
 * disappearance of the terminal window
 * in some environments by pausing execution until the user
 * presses the Enter key.  Usage: reader.pause();
 */
   public void pause(){
      System.out.print ("\nPress Enter to continue . . . ");
      try {
         buffer.readLine();
      }catch (Exception e){
         System.exit(0);
      }
   }

/**
 * Prompts the user and waits for integer input.  Throws an exception
 * if the input doesn't represent an integer.  Returns the integer entered.
 * @param    prompt the prompt to the user.
 */
   public int readInt(String prompt){
      int value = 0;
      String s = "";
      System.out.print (prompt);
      try {
         s = (buffer.readLine()).trim();
         value = (new Integer(s)).intValue();
      }catch (Exception e){
         System.out.println
         ("\n\nError: your input doesn't represent a valid integer value\n");
         pause();
         System.exit(0);
      }
      return value;
   }
   
/**
 * Waits for integer input without prompting the user.  Throws an exception
 * if the input doesn't represent an integer.  Returns the integer entered.
 */
   public int readInt(){
      return readInt ("");
   }

 /**
 * Prompts the user and waits for double input.  Throws an exception
 * if the input doesn't represent a double.  Returns the double entered.
 * @param    prompt the prompt to the user.
 */
   public double readDouble(String prompt){
      double value = 0;
      String s = "";
      System.out.print (prompt);
      try {
         s = (buffer.readLine()).trim();
         value = (new Double(s)).doubleValue();
      }catch (Exception e){
         System.out.println
         ("\n\nError: your input doesn't represent a valid double value\n");
         pause();
         System.exit(0);
      }
      return value;
   }
   
 /**
 * Waits for double input without prompting the user.  Throws an exception
 * if the input doesn't represent a double.  Returns the double entered.
 */
   public double readDouble(){
      return readDouble("");
   }
   
/**
 * Prompts the user and waits for character input.  Returns the char entered.
 * @param    prompt the prompt to the user.
 */
   public char readChar(String prompt){
      int value = 0;
      String s = "";
      System.out.print (prompt);
      try {
         s = buffer.readLine();
         s += "?";
         value = s.charAt(0);
      }catch (Exception e){
         System.out.println
         ("\n\nError in method readChar:\n" + e.toString() + "\n");
         pause();
         System.exit(0);
      }
      return (char)value;
   }
   
/**
 * Waits for character input without prompting the user.  Returns the char entered.
 */
   public char readChar(){
      return readChar("");
   }
   
/**
 * Prompts the user and waits for string input.  Returns the entire line
 * of text entered.
 * @param    prompt the prompt to the user.
 */
   public String readLine(String prompt){
      String value = "";
      System.out.print (prompt);
      try {
         value = buffer.readLine();
      }catch (Exception e){
         System.out.println
         ("\n\nError in Console.readLine\n" + e.toString() + "\n");
         pause();
         System.exit(0);
      }
      return value;
   }

/**
 * Waits for string input without prompting the user.  Returns the entire line
 * of text entered.
 */
   public String readLine(){
      return readLine("");
   }
}


