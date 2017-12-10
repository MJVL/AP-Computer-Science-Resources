import java.io.*;
class CopyMaker
{
   String sourceName, destName;
   BufferedReader source;
   BufferedWriter dest;
   String line;

   private boolean openFiles()  // return true if files open, else false
   {
     // open the source
     try
     {      
       source = new BufferedReader(new FileReader( sourceName ));
     }
     catch ( IOException iox )
     {
       System.out.println("Problem opening " + sourceName );
       return false;
     }
     // open the destination
     try
     {      
       dest = new BufferedWriter(new FileWriter( destName ));
     }
     catch ( IOException iox )
     {
       System.out.println("Problem opening " + destName );
       return false;
     }
     return true;
   }

   private boolean copyFiles()  // return true if copy worked, else false
   {
     try
     {      
       line = source.readLine();
       while ( line != null )
       {
         dest.write(line);
         dest.newLine();
         line = source.readLine();
       }
     }
     catch ( IOException iox )
     {
       System.out.println("Problem reading or writing" );
       return false;
     }
     return true;
   }

   private boolean closeFiles()  //return true if files close, else false
   {
     boolean retVal=true;
     // close the source
     try
     {      
       source.close();
     }
     catch ( IOException iox )
     {
       System.out.println("Problem closing " + sourceName );
       retVal = false;
     }
     // close the destination
     try
     {      
       dest.close();
     }
     catch ( IOException iox )
     {
       System.out.println("Problem closing " + destName );
       retVal = false;
     }
     return retVal;
   }

   public boolean copy(String src, String dst )
   {
     sourceName = src ;
     destName   = dst ;
     return openFiles() && copyFiles() && closeFiles();
   }

}

public class CopyTextFile
{
  public static void main ( String[] args ) 
  {
    if ( args.length == 3 && args[1].toUpperCase().equals("TO") )
      new CopyMaker().copy(args[0], args[2]);
    else
      System.out.println("java CopyTextFile source to destination");
  }
}

