import java.awt.Component;

public class EPanel extends javax.swing.JPanel
{
	public EPanel()
	{	this.setBackground (java.awt.Color.white);
	}	//======================


	public void add (Component one, Component two)
	{	javax.swing.JPanel pane = new javax.swing.JPanel();
		pane.add (one);
		pane.add (two);
		this.add (pane);
	}	//======================


	public void add (Component one, Component two, Component three)
	{	javax.swing.JPanel pane = new javax.swing.JPanel();
		pane.add (one);
		pane.add (two);
		pane.add (three);
		this.add (pane);
	}	//======================


	public javax.swing.JPanel lineBreak()
	{	javax.swing.JPanel pane = new javax.swing.JPanel();
		pane.setPreferredSize (new java.awt.Dimension (2000, 2));
		pane.setBackground (this.getBackground());
		return pane;
	}	//======================


	public void popUp (String message)
	{	javax.swing.JOptionPane.showMessageDialog (this, message);
	}	//======================


	public String prompt (String message)
	{	return javax.swing.JOptionPane.showInputDialog (this, message);
	}	//======================


	public boolean confirm (String message)
	{	return javax.swing.JOptionPane.showConfirmDialog 
				(this, message) == javax.swing.JOptionPane.YES_OPTION;
	}	//======================



	public static java.applet.AudioClip newAudioClip (String title)
	{	try
		{	return java.applet.Applet.newAudioClip
				(new java.io.File (title).toURL());
		} catch (Exception e)
		{  }
		return null;
	}	//======================

	/* An AudioClip object has 3 methods:  
		play() plays the clip one tie
		loop() plays the clip over and over
		stop() makes the clip stop playing, if it is
	*/
}


/* Inherited from javax.swing.JComponent:
	setToolTip(String s)  for the box you see when the cursor lingers on it
	setPreferredSize(Dimension d) within the limits of the LayoutManager
	setBackground (Color c) to set the background color
   Inherited from java.awt.Container:
	add(Component c) adds the component to the end of the list of components
	getComponent(index i) returns the ith component added (numbered from 0)
	setLayout(LayoutManager x) allows coordinates if x is null
*/

