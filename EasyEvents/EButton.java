/** You should define an onEnter method for almost all EButtons, to react
	to a click of the button.  But, rarely, you use an EButton instead
	of an ELabel just because you want the rectangular border around 
	the text (an ELabel has no border).  In such a case, you can omit 
	onClick method. */



public class EButton extends javax.swing.JButton 
				implements java.awt.event.ActionListener
{
	private String itsPicture = "";


	public EButton()
	{	this.addActionListener (this);
	}	//======================


	public EButton text (String title)
	{	this.setText (title);
		return this;
	}	//======================


	public EButton picture (String picture)
	{ 	this.setPicture (picture); 
		return this;
	}	//======================


	public void setPicture (String picture)
	{	this.setIcon (new javax.swing.ImageIcon 
				(EButton.class.getResource (picture))); // the URL
		this.setHorizontalTextPosition (javax.swing.JButton.CENTER);
		this.setVerticalTextPosition (javax.swing.JButton.BOTTOM);
		itsPicture = picture;
	}	//======================


	public String getPicture()
	{	return itsPicture;
	}	//======================


	public void actionPerformed (java.awt.event.ActionEvent e)
	{	onClick();
	}	//======================

	public void onClick()
	{	// override this in a subclass if any action is desired
	}	//======================
}

/* Inherited from javax.swing.AbstractButton:
	setText(String s)
	getText() returns String
	setIcon(ImageIcon x)
	setMnemonic(char c)
	addActionListener(ActionListener a)
	setEnabled (boolean onOrOff)
   Inherited from javax.swing.JComponent:
	setToolTipText(String s)  what you see when the cursor lingers on it
	setPreferredSize(Dimension d) within the limits of the LayoutManager
	setBackground(Color c) to set the background color
	setForeground(Color c) to set the color of the characters
   For java.awt.Button:
	set/getLabel instead of set/getText
	no setIcon or setMnemonic or setToolTipText
*/

