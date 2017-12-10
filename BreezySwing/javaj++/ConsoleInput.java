/**
*	15-100, 15-100 Intro sections
*	Class ConsoleInput
*	@author		Jim Roberts, Mark Stehlik, Don Slater
*	@Date		09/20/01
*/
import java.io.*;

public class ConsoleInput {

	/**
	* InputStreamReader object and BufferedReader object
	*/ 
	private InputStreamReader input;
	private BufferedReader keyboard;

	/**
	* initializes the InputStreamBuffer and the BufferedReader for the keyboard
	*/
	public ConsoleInput() {
		input = new InputStreamReader(System.in);
		keyboard = new BufferedReader(input, 1);

	}

	/**
	* write obj to the monitor
	* @param Object obj
	*/
	public void write(Object obj) {

		System.out.print(obj);

	}

	/**
	* write str to the monitor
	* @param String str
	*/
	public void write(String str) {

		System.out.print(str);

	}

	/**
	* write obj to the monitor with an endl of line at the end
	* @param Object obj
	*/
	public void writeln(Object obj) {

		System.out.println(obj);

	}
	
	

	/**
	* write str to the monitor with an endl of line at the end
	* @param String str
	*/
	public void writeln(String str) {

		System.out.println(str);

	}

	/**
	* write empty line to the monitor with an endl of line at the end
	*/
	public void writeln() {

		System.out.println();

	}

	/**
	* write str to the monitor
	* read and return a String from the keyboard
	* using instance variable br
	* @param String str
	* @return String
	*/
	public String promptAndRead(String str) {

		write(str);
		return readString();
	}

	/**
	* write str to the monitor
	* read and return an int from the keyboard
	* using instance variable keyboard
	* @param String str
	* @return int
	*/
	public int promptForInt(String str){

		write(str);
		return readInt();
	}

	/** 
	* write str to the monitor
	* read and return a double from the keyboard
	* using instance variable keyboard
	* @param String str
	* @return double
	*/
	public double promptForDouble(String str) {

		write(str);
		return readDouble();
	}
	
	/** 
	* write str to the monitor
	* read and return a char from the keyboard
	* using instance variable keyboard
	* @param String str
	* @return char
	*/
	public char promptForChar(String str) {

		write(str);
		return readChar();
	}
	
	/** 
	* write str to the monitor
	* read and return a boolean from the keyboard
	* using instance variable keyboard
	* @param String str
	* @return boolean
	*/
	public boolean promptForBoolean(String str) {

		write(str);
		return readBoolean();
	}
	
	/**
	* read and return an int from the keyboard
	* using instance variable keyboard
	* catches Exception
	* @return int
	*/
	public int readInt() {
		try {
			String str = keyboard.readLine();
			return Integer.parseInt(str);
		} catch (Exception e) {
			writeln("Your input was not a valid integer.");
			e.printStackTrace();
			writeln("The program will now terminate.");
			System.exit(1);
		}
		return 0;
	}
	
	/**
	* read and return a double from the keyboard
	* using instance variable keyboard
	* catches Exception
	* @return double
	*/
	public double readDouble() {
		try {
			String str = keyboard.readLine();
			//return Double.parseDouble(str);
			double d=(new Double(str)).doubleValue();
			
			return d;
		} catch (Exception e) {
			writeln("Your input was not a valid double.");
			e.printStackTrace();
			writeln("The program will now terminate.");
			System.exit(1);
		}
		return 0.0;
	}

	/**
	* read and return a boolean from the keyboard
	* using instance variable keyboard
	* catches Exception
	* @return boolean
	*/
	public boolean readBoolean() {
		try {
			String str = keyboard.readLine();
			if (! (str.equalsIgnoreCase("true") || str.equalsIgnoreCase("false"))) {
				writeln("Your input was not a valid boolean value.");
				writeln("The program will now terminate.");
				System.exit(1);
			}
			return Boolean.valueOf(str).booleanValue();
		} catch (Exception e) {
			writeln("Your input was not a valid boolean value.");
			e.printStackTrace();
			writeln("The program will now terminate.");
			System.exit(1);
		}
		return false;
	}
	
	/**
	* read and return a char from the keyboard
	* using instance variable keyboard
	* catches Exception
	* @return char
	*/
	public char readChar() {
		try {
			String str = keyboard.readLine();
			if (str.length() != 1) {
				writeln("Your input was not a valid character value.");
				writeln("The program will now terminate.");
				System.exit(1);
			}
			return str.charAt(0);
		} catch (Exception e) {
			writeln("Your input was not a valid character value.");
			e.printStackTrace();
			writeln("The program will now terminate.");
			System.exit(1);
		}
		return ' ';
	}

	/**
	* read and return a String from the keyboard
	* using instance variable keyboard
	* catches Exception
	* @return String
	*/
	public String readString() {
		try {
			String str = keyboard.readLine();
			if (str.length() == 0) {
				writeln("Your input was not a valid String value.");
				writeln("The program will now terminate.");
				System.exit(1);
			}
			return str;
		} catch (Exception e) {
			writeln("Your input was not a valid character value.");
			e.printStackTrace();
			writeln("The program will now terminate.");
			System.exit(1);
		}
		return "";
	}
}