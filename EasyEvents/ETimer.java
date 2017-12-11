public abstract class ETimer extends ELabel
				implements java.awt.event.ActionListener
{
	private javax.swing.Timer itsTimer = null;


	public ETimer()
	{	super ("");   // initially no text on the label
	}	//======================


	public ETimer delay (int millis)
	{	itsTimer = new javax.swing.Timer (millis, this);
		itsTimer.setRepeats (false);
		itsTimer.start();
		return this;
	}	//======================


	public void stop()
	{	if (itsTimer != null)
			itsTimer.stop();
	}	//======================


	public void actionPerformed (java.awt.event.ActionEvent e)
	{	onBeep();
	}	//======================


	public abstract void onBeep();
}

