public class DiceThrow extends EPanel 
{
	private java.util.Random randy = new java.util.Random();  // 1
	private ETimer one = new RollTimer();                     // 2
	private ETimer two = new RollTimer();                     // 3
	private final int NUM_SIDES = 6;                          // 4


  /** The View:  Lay out the GUI components. */

	public DiceThrow()                                        // 5
	{	add (new GoButton().text ("Click to roll the 2 dice"));// 6
		one.setPicture ("1die.jpg");                           // 7
		two.setPicture ("1die.jpg");                           // 8
		add (one, two);    // binds them together              // 9
		add (new StopButton().text ("Click to stop"));         //10
	}	//======================


  /** Controllers: React to click of button or ENTER key. */

	private class GoButton extends EButton                    //11
	{	public void onClick()                                  //12
		{	int n = 1 + randy.nextInt (NUM_SIDES);              //13
			one.delay (150).setPicture (n + "die.jpg");         //14
			n = 1 + randy.nextInt (NUM_SIDES);                  //15
			two.delay (90).setPicture (n + "die.jpg");          //16
		}                                                      //17
	}	//======================


	private class RollTimer extends ETimer                    //18
	{	public void onBeep()                                   //19
		{	int n = this.getPicture().charAt (0) - '0';         //20
			if (n >= NUM_SIDES)                                 //21
			{	n = 1;                                        //22
			}                                                   //23
			else                                                //24
			{	n++;                                          //25
			}                                                   //26
			this.delay (120).setPicture (n + "die.jpg");        //27
		}                                                      //28
	}	//======================


	private class StopButton extends EButton                  //28
	{	public void onClick()                                  //29
		{	one.stop();                                         //30
			two.stop();                                         //31
		}                                                      //32
	}	//======================
}



class DiceThrowApp  // to create a frame from the command window
{
	public static void main (String[ ] args) 
	{	new EFrame (new DiceThrow()).setTitle ("Dice Throw");
	}	//======================
}

