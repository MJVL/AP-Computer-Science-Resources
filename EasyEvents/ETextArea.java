public class ETextArea extends javax.swing.JScrollPane
{
	private javax.swing.JTextArea itsArea;


	public ETextArea (int rows, int columns)
	{	itsArea = new javax.swing.JTextArea (rows, columns);
		this.setViewportView (itsArea);
	}	//======================


	/** Show the message in the scrolled text area. */

	public void say (String message)
	{	itsArea.append (message + "\n");
	}	//======================


	public void setText (String message)
	{	itsArea.setText (message);
	}	//======================


	public String getText()
	{	return itsArea.getText();
	}	//======================
}


