public class Popper extends EPanel 
{
	public Popper()                                            // 1
	{	add (new AnswerButton().text ("Where is this?"));       // 2
		add (new ELabel ("", "Rome.jpg"));//photo without text  // 3
	}	//======================

	private class AnswerButton extends EButton                 // 4
	{	public void onClick()                                   // 5
		{	popUp ("Italy");                                     // 6
		}                                                       // 7
	}	//======================
}



class PopperApp  // to create a frame from the command window
{
	public static void main (String[ ] args) 
	{	new EFrame (new Popper());
	}	//======================
}

