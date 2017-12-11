public class GuessNumberGame extends EPanel 
{
	private EField itsInput = new PlayOneGame().width (3);    // 1
	private ELabel itsAnswer = new ELabel ("");               // 2
	private int itsSecret = 50;                               // 3
	private ETimer itsTimer = new SecretTimer().delay (20);   // 4


  /** The View:  Lay out the GUI components. */

	public GuessNumberGame()                                  // 5
	{	add (new StartGame().text ("Click to play the game")); // 6
		add (new ELabel ("Guess 1 to 100"));                   // 7
		add (itsInput);                                        // 8
		add (itsAnswer);                                       // 9
		itsInput.setEnabled (false);                           //10
	}	//======================


  /** Controllers: React to various events. */

	private class SecretTimer extends ETimer                  //11
	{	public void onBeep()                                   //12
		{	itsSecret += 13;                                    //13
			if (itsSecret > 100)                                //14
				itsSecret -= 100;                             //15
			this.delay (20);                                    //16
		}                                                      //17
	}	//======================


	private class StartGame extends EButton                   //18
	{	public void onClick()                                  //19
		{	itsTimer.stop();                                    //20
			itsInput.setEnabled (true);                         //21
		}                                                      //22
	}	//======================


	private class PlayOneGame extends EField                  //23
	{	public void onEnter()                                  //24
		{	int guess = Integer.parseInt (this.getText());      //25
			if (guess > itsSecret)                              //26
			{	itsAnswer.setText ("Too high");               //27
			}                                                   //28
			else if (guess < itsSecret)                         //29
			{	itsAnswer.setText ("Too low");                //30
			}                                                   //31
			else  // don't continue, this game is over          //32
			{	itsAnswer.setText ("Right. Congratulations!");//33
				itsTimer.delay (20);                          //34
				itsInput.setEnabled (false);                  //35
			}                                                   //36
		}                                                      //37
	}	//======================
}



class GuessNumberGameApp  // to create a frame from the command window
{
	public static void main (String[ ] args) 
	{	new EFrame (new GuessNumberGame());
	}	//======================
}

