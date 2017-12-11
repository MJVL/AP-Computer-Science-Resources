public class ArtQuiz extends EPanel 
{
	private ELabel numRight = new ELabel ("0 right");           // 1
	private ELabel numWrong = new ELabel ("0 wrong");           // 2
	private ELabel toReact = new ELabel("");  // no text        // 3

 /** The View:  Lay out the GUI components. */

	public ArtQuiz()                                            // 4
	{	add (new ELabel ("Mozart was a dancer"));                // 5
		add (new WrongButton().text ("true"));                   // 6
		add (new RightButton().text ("false"));                  // 7
		add (new ELabel ("Rembrandt was a painter"));            // 8
		add (new RightButton().text ("true"));                   // 9
		add (new WrongButton().text ("false"));                  //10
				// several more questions would be here
		add (lineBreak());                                       //11
		add (numRight, toReact, numWrong);                       //12
	}	//======================

 /** Controllers: React to click of button or to ENTER key. */

	private class RightButton extends EButton                   //13
	{	public void onClick()                                    //14
		{	toReact.setPicture ("pic/smileyface.gif");            //15
			int count = 1 + numRight.getText().charAt (0) - '0';  //16
			numRight.setText (count + " right so far");           //17
		}                                                        //18
	}	//======================

	private class WrongButton extends EButton                   //19
	{	public void onClick()                                    //20
		{	toReact.setPicture ("pic/frowneyface.gif");           //21
			int count = 1 + numWrong.getText().charAt (0) - '0';  //22
			numWrong.setText (count + " wrong so far");           //23
		}                                                        //24
	}	//======================
}



class ArtQuizApp  // to create a frame from the command window
{
	public static void main (String[ ] args) 
	{	new EFrame (new ArtQuiz());
	}	//======================
}

