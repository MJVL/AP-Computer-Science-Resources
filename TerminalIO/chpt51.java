/**
 * This class can take a variable number of parameters on the command
 * line. Program execution begins with the main() method. The class
 * constructor is not invoked unless an object of type 'Class1'
 * created in the main() method.
 */import TerminalIO.*;
public class chpt51
{
	/**
	 * The main entry point for the application. 
	 *
	 * @param args Array of parameters passed to the application
	 * via the command line.
	 */

			      public static void main(String argv[]) {
		           //
	                   // call constructor to initialize vars and allocate storage.
	                   KeyboardReader reader=new KeyboardReader();
					  Receipt r = new Receipt("The Right Price - School Supplies");
	                   r.addItem("pencil", 0.39);
	                   r.addItem("eraser", 0.99);
	                   r.addItem("paper", 1.49);
	
	                   Receipt r2 = new Receipt("Fast Mart");
	                   r2.addItem("candy", 0.50);
                   r2.addItem("eraser", 0.99);
	                   r2.addItem("staples", 1.41);
	
	                   r.printReceipt();
	                   r2.printReceipt();
					  reader.pause();
	               }
	           

	
}
