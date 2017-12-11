import TerminalIO.KeyboardReader;

public class KlicksToNauts{

   public static void main (String[] args){
      int klicks, nauts;
	  char letter;
      KeyboardReader reader = new KeyboardReader();
      System.out.print("Enter the number of kilometers ");
      klicks = reader.readInt();
      nauts = klicks * 90 * 60 / 10000;
      System.out.print("The number of nautical miles is ");
      System.out.println (nauts);
	  letter=reader.readChar("Press any key to continue...");
	  
   }
}

