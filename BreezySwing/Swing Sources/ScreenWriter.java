//Copyright Martin Osborne and Ken Lambert 1998-2001
//All rights reserved

package TerminalIO;

/**
 * The class ScreenWriter contains output methods for terminal I/O.  Type-specific
 * methods write Boolean, characters, integers, decimal numbers, strings, and objects to the 
 * terminal window. Examples:
 * <pre>
 *
 *   ScreenWriter writer = new ScreenWriter();
 *   writer.println("The cat sat on the mat.");
 *
 * </pre>
 */
 
public class ScreenWriter{

/**
 * Prints 'true' or 'false'.
 * @param    x the value to be printed.
 */
   public void print(boolean x){
      System.out.print (x);
   }
    
/**
 * Prints a character.
 * @param    x the value to be printed.
 */
   public void print(char x){
      System.out.print(x);
   } 

/**
 * Prints a double.
 * @param    x the value to be printed.
 */
   public void print(double x){ 
      System.out.print(x);
   }

/**
 * Prints a float.
 * @param    x the value to be printed.
 */
   public void print(float x){
      System.out.print(x);
   } 

/**
 * Prints an integer.
 * @param    x the value to be printed.
 */
   public void print(int x){
      System.out.print(x);
   } 

/**
 * Prints a long.
 * @param    x the value to be printed.
 */
   public void print(long x){
      System.out.print(x);
   } 

/**
 * Prints the string representation of an object.
 * @param    x the object to be printed.
 */
   public void print(Object x){
      System.out.print(x);
   } 

/**
 * Prints a string.
 * @param    x the value to be printed.
 */
   public void print(String x){
      System.out.print(x);
   } 

/**
 * Prints a new line.
 */
   public void println(){
      System.out.println();
   } 

/**
 * Prints a boolean followed by a new line.
 * @param    x the value to be printed.
 */
   public void println(boolean x){ 
      System.out.println(x);
   } 

/**
 * Prints a char followed by a new line.
 * @param    x the value to be printed.
 */
   public void println(char x){ 
      System.out.println(x);
   } 
   
/**
 * Prints a double followed by a new line.
 * @param    x the value to be printed.
 */
   public void println(double x){ 
      System.out.println(x);
   }  

/**
 * Prints a float followed by a new line.
 * @param    x the value to be printed.
 */
   public void println(float x){ 
      System.out.println(x);
   }  

/**
 * Prints an integer followed by a new line.
 * @param    x the value to be printed.
 */
   public void println(int x){ 
      System.out.println(x);
   }  

/**
 * Prints a long followed by a new line.
 * @param    x the value to be printed.
 */
   public void println(long x){ 
      System.out.println(x);
   }  

/**
 * Prints the string representation of an object followed by a new line.
 * @param    x the object to be printed.
 */
   public void println(Object x){ 
      System.out.println(x);
   }  

/**
 * Prints a string followed by a new line.
 * @param    x the value to be printed.
 */
   public void println(String x){ 
      System.out.println(x);
   }  
}


