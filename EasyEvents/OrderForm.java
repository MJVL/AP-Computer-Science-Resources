public class OrderForm extends EPanel 
{
	private EField itsName = new EField().width (20);           // 1
	private EField itsAddress = new EField().width (20);        // 2
	private EField itsCard = new EField().width (20);           // 3
	private EButton itsAds = new AdsButton().text ("yes");      // 4


 /** The View:  Lay out the GUI components. */

	public OrderForm()                                          // 5
	{	add (new ELabel ("What is your name?"), itsName);        // 6
		add (new ELabel ("What is your address?"), itsAddress);  // 7
		add (new ELabel ("What is your credit card #?"),         // 8
				itsCard);                                    // 9
		add (new ELabel ("Can we send you email ads?"), itsAds); //10
		add (new SubmitButton().text ("Click here when done"));  //11
	}	//======================


 /** Controllers: React to click of button. */

	private class AdsButton extends EButton                     //12
	{	public void onClick()                                    //13
		{	if (this.getText().equals ("yes"))                    //14
			{	this.setText ("no");                            //15
			}                                                     //16
			else                                                  //17
			{	this.setText ("yes");                           //18
			}                                                     //19
		}                                                        //20
	}	//======================


	private class SubmitButton extends EButton                  //21
	{	public void onClick()                                    //22
		{	EOutputFile file = new EOutputFile ("data.txt");      //23
			file.println (itsName.getText());                     //24
			file.println (itsAddress.getText());                  //25
			file.println (itsCard.getText());                     //26
			file.println (itsAds.getText());                      //27
			file.close();                                         //28
		}                                                        //29
	}	//======================
}



class OrderFormApp  // to create a frame from the command window
{
	public static void main (String[ ] args) 
	{	new EFrame (new OrderForm());
	}	//======================
}



