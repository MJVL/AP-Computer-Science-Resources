
public class EOutputFile extends java.io.PrintWriter
{
	public EOutputFile (String fileName)
	{	super (openFile (fileName));
	}	//======================


	// This round-about method avoids an IOException

	private static java.io.FileWriter openFile (String fileName)
	{	try
		{	return new java.io.FileWriter (fileName);
		}catch (java.io.IOException e)
		{	try
			{	return new java.io.FileWriter ("efiledata.txt");
			}catch (java.io.IOException e2)
			{	return null;
			}
		}
	}	//======================
}

/* Inherited from java.io.PrintWriter:
	print(String s)  and any other kind of parameter, e.g., int, boolean
	println (String s) and any other kind of parameter
	close() must be done to assure the entire output is written
*/

