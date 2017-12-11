public class BankView extends EPanel 
{
	private BankAccount modelAccount = new BankAccount();       // 1
	private ETextArea itsOutput = new ETextArea (10, 42);       // 2


  /** The View:  Lay out the GUI components. */

	public BankView()                                           // 3
	{	add (new LastFiveButton().text                           // 4
				("show last five transactions"));            // 5
		add (new ELabel ("Enter deposit(+) or check(-)"),        // 6
				new UpdateField().width (5).text ("0"));     // 7
		add (itsOutput);                                         // 8
	}	//======================


  /** Controllers: React to click of button or ENTER key. */

	private class LastFiveButton extends EButton                // 9
	{	public void onClick()                                    //10
		{	itsOutput.say (modelAccount.lastFiveTransactions());  //11
		}                                                        //12
	}	//======================


	private class UpdateField extends EField                    //13
	{	public void onEnter()                                    //14
		{	String input = this.getText();                        //15
			if (input.length() > 0)                               //16
			{	modelAccount.makeChange (input);                //17
				itsOutput.say (modelAccount.currentBalance() +  //18
					" = balance.");                           //19
			}                                                     //20
			this.setText ("");  // clear the entry field          //21
		}                                                        //22
	}	//======================
}



class BankViewApp  // to create a frame from the command window
{
	public static void main (String[ ] args) 
	{	new EFrame (new BankView());
	}	//======================
}



class BankAccount
{
	private int itsBalance = 0;
	private String itsFive = ".. .. .. .. ..";


	/** If the given String is not a properly-formed integer, do nothing;
	    otherwise add it to the balance (which decreases the balance if it
	    is a negative number, representing a check) and update 
	    the last 5 transactions. */

	public void makeChange (String given)
	{	try
		{	int input = Integer.parseInt (given.trim());
			itsBalance += input;
			itsFive = itsFive.substring (itsFive.indexOf (" ") + 1) +
						" " + input;
		} catch (Exception e)
		{   }   // do nothing if given is not a properly-formed integer
	}	//======================



	/** Tell the balance currently in the bank account. */

	public int currentBalance()
	{	return itsBalance;
	}	//======================



	/** Display the last 5 transactions, or all of them if less than 5. */

	public String lastFiveTransactions()
	{	return itsFive;
	}	//======================


	/* EXERCISE:  Change this so that there are 2 blanks between entries
			  instead of just 1.
	   EXERCISE:  Change this so that, for instance, a display of 48 -20 
			  becomes dep48 chk20.
	*/
}

