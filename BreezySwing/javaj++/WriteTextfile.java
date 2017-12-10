import java.io.*;
class WriteTextFile
{
  public static void main ( String[] args ) 
  {

    // Get filename and create the file
    FileWriter writer = null;
    BufferedReader user = new BufferedReader(
        new InputStreamReader( System.in ) );
    String fileName = "";

    System.out.print("Enter Filename-->"); System.out.flush();
    try
    {
      fileName = user.readLine().trim();
      writer = new FileWriter( fileName );
    }
    catch ( IOException iox )
    {
      System.out.println("Error in creating file");
      return;
    }
      
    // Write out an example of a Power table.
    try
    {  
      int value = 1;
      writer.write( "Power\tValue\n"  );
      for ( int pow=0; pow<=20; pow++ )
      {
        writer.write( pow + "\t" + value + "\n"  );  
        value = value*2;
      }
      writer.close();
    }
    catch ( IOException iox )
    {
      System.out.println("Problem writing " + fileName );
    }
  }
}

