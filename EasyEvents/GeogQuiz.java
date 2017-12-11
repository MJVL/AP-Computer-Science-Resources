public class GeogQuiz extends EPanel 
{
 /** The View:  Lay out the GUI components. */

	public GeogQuiz()                                          // 1
	{	add (new ELabel ("Where is Italy?"));                   // 2
		add (new RightButton().text ("Europe"));                // 3
		add (new WrongButton().text ("Asia"));                  // 4
		add (new ELabel ("Where is India?"));                   // 5
		add (new WrongButton().text ("Europe"));                // 6
		add (new RightButton().text ("Asia"));                  // 7
		add (new ELabel("St. Peter's", "Rome.jpg")); //a photo  // 8
	}	//======================


 /** Controllers: React to click of button. */

	private class RightButton extends EButton                  // 9
	{	public void onClick()                                   //10
		{	this.setText ("right!");                             //11
		}                                                       //12
	}	//======================


	private class WrongButton extends EButton                  //13
	{	public void onClick()                                   //14
		{	this.setText ("you're wrong.");                      //15
		}                                                       //16
	}	//======================
}



class GeogQuizApp  // to create a frame from the command window
{
	public static void main (String[ ] args) 
	{	new EFrame (new GeogQuiz());
	}	//======================
}

