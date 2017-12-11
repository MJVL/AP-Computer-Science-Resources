
public class EFramelet extends javax.swing.JFrame 
{
	public EFramelet (javax.swing.JPanel newContent)
	{	setContentPane (newContent); 
		setSize (500, 300);   // measured in pixels
		toFront();
		setVisible (true);
	}	//======================
}

/* Inherited from javax.swing.JFrame:  
	getJMenuBar() returns its javax.swing.JMenuBar so you can change it
	setContentPane(Container p) makes p the main panel for components
   Inherited from java.awt.Frame:
	getTitle() returns the title String at the top of the frame
	setTitle(String s) replaces the title 
   Inherited from java.awt.Window (a subclass of java.awt.Container):
	toFront() brings it out in front of other windows on the screen
	toBack() puts it behind other windows on the screen
	dispose() discards the window and its contents
	addWindowListener (WindowListener x)
   Inherited from java.awt.Component:
	setSize (int width, int height)  width and height in pixels
	setVisible (boolean b)  makes it visible on the screen, or not
*/

