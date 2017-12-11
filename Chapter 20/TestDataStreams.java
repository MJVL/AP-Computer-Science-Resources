/* TestDataStreams.java
1) Write randomly generated integers to a data output stream.  A command line
   parameter specifies the number of integers to generate. 
2) Read them back in using a data input stream and display them in
   the terminal window.
*/

import java.io.*;

public class TestDataStreams{

   public static void main (String[] args){

      //Obtain the number of ints from the command line parameters.
      int number = Integer.valueOf(args[0]).intValue();

      //Generate random ints and write them to a data output stream.
      try{
         FileOutputStream foStream = new FileOutputStream("ints.dat");
         DataOutputStream doStream = new DataOutputStream(foStream);
         int i;
         for (i = 0; i < number; i++)
            doStream.writeInt((int) (Math.random() * number + 1));
         doStream.close();
      }catch(IOException e){
         System.err.println("Error during output: " + e.toString());
      }

      //Read the ints from a data input stream and display them in 
      //a terminal window.
      try{
         FileInputStream fiStream = new FileInputStream("ints.dat");
         DataInputStream diStream = new DataInputStream(fiStream);
         while (true){
            int i = diStream.readInt();
            System.out.println(i);
         }
      }catch(EOFException e){
          System.out.println("\nAll done.");
      }catch(IOException e){
          System.err.println("Error in input" + e.toString());
      }
   }
}

