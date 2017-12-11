public class MathQuiz extends EPanel 
{
 /** The View:  Lay out the GUI components. */

	public MathQuiz()                                           // 1
	{	add (new ELabel ("What is the square of 30?"));          // 2
		add (new SquareField().width (8));     //in characters   // 3
		add (lineBreak());                                       // 4
		add (new ELabel ("How many bytes in a KB?"));            // 5
		add (new BytesField().width (8));     //in characters    // 6
	}	//======================


 /** Controllers: React to ENTER key. */

	private class SquareField extends EField                    // 7
	{	public void onEnter()                                    // 8
		{	if (this.getText().equals ("900"))                    // 9
			{	this.setText ("good!");                         //10
			}                                                     //11
			else                                                  //12
			{	this.setText ("you're wrong.");                 //13
			}                                                     //14
		}                                                        //15
	}	//======================


	private class BytesField extends EField                     //16
	{	public void onEnter()                                    //17
		{	if (this.getText().equals ("1024"))                   //18
			{	this.setText ("good!");                         //19
			}                                                     //20
			else                                                  //21
			{	this.setText ("you're wrong.");                 //22
			}                                                     //23
		}                                                        //24
	}	//======================
}



class MathQuizApp  
{
	public static void main (String[] args) 
	{	new EFrame (new MathQuiz());
	}	//======================
}

