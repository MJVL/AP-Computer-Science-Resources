/** You should define an onEnter method for most EFields, to react
	to the ENTER key being pressed inside the EField.  But if it
	is only being used for data entry, with perhaps the click of
	a button used to process the data, you can omit onEnter. */

public class EField extends javax.swing.JTextField 
				implements java.awt.event.ActionListener
{

	public EField()
	{	this.addActionListener (this);
	}	//======================


	public EField text (String title)
	{	this.setText (title);
		return this;
	}	//======================


	public EField width (int wide)
	{	this.setColumns (wide);
		return this;
	}	//======================


	public void actionPerformed (java.awt.event.ActionEvent e)
	{	onEnter();
	}	//======================


	public void onEnter()
	{	// override this in a subclass if any action is desired
	}	//======================
}

/* Inherited from javax.swing.JTextField:
	setColumns(int width) 
	addActionListener(ActionListener a)
/* Inherited from javax.swing.text.JTextComponent:
	setText(String s)
	getText() returns String
   Inherited from javax.swing.JComponent:
	setToolTipText(String s)  what you see when the cursor lingers on it
	setPreferredSize(Dimension d) within the limits of the LayoutManager
	setBackground(Color c) to set the background color
	setForeground(Color c) to set the color of the characters
	grabFocus()
	setEnabled(boolean onOrOff)  true if enabled, false if disabled
*/

