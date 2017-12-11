public class ELabel extends javax.swing.JLabel
{
	private String itsPicture = "";


	public ELabel (String title)
	{	super (title);
	}	//======================


	public ELabel (String title, String picture)
	{	super (title);
		this.setPicture (picture);
	}	//======================


	public void setPicture (String picture)
	{	this.setIcon (new javax.swing.ImageIcon 
				(ELabel.class.getResource (picture))); // the URL
		this.setHorizontalTextPosition (javax.swing.JLabel.CENTER);
		this.setVerticalTextPosition (javax.swing.JButton.BOTTOM);
		itsPicture = picture;
	}	//======================


	public String getPicture()
	{	return itsPicture;
	}	//======================
}

/* Inherited from javax.swing.JLabel:
	setText(String s)
	getText() returns String
	setIcon(ImageIcon x)
   Inherited from javax.swing.JComponent:
	setToolTipText(String s)  what you see when the cursor lingers on it
	setPreferredSize(Dimension d) within the limits of the LayoutManager
	setBackground(Color c) to set the background color
	setForeground(Color c) to set the color of the characters
*/

