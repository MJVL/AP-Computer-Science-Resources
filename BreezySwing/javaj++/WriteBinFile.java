import java.io.*;
class WriteBinFile
{
 public static void main ( String[] args )
 {
   String fileName = "intData.dat" ;

   try
   {      
     DataOutputStream out = new DataOutputStream(
         new BufferedOutputStream(
         new FileOutputStream( fileName  ) ) );

     for ( int j=0; j<512; j++ )
       out.writeInt( j );//out.writeDouble(   ) to write a double to a binary file  

     out.close();
   }
   catch ( IOException iox )
   {
     System.out.println("Problem writing " + fileName );
   }
 }
}

