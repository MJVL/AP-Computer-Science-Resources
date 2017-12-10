import java.io.*;

class ReadBinFileEOF
{
  public static void main ( String[] args ) 
  {
    String fileName = "ints.dat" ;   long sum = 0;

    try
    {
      DataInputStream instr = 
        new DataInputStream(
          new BufferedInputStream(
            new FileInputStream( fileName ) ) );

      try
      {
        while ( true )
          sum += instr.readInt();//readDouble() to read a double
      }

      catch ( EOFException  eof )
      {
        System.out.println( "The sum is: " + sum );
        instr.close();
      }

    }

    catch ( IOException iox )
    {
      System.out.println("IO Problems with " + fileName );
    }

  }
}

