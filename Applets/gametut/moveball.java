import java.applet.*;
import java.awt.*;

public class Ballbewegung1 extends Applet implements Runnable
{
	// Initialisierung der Variablen
	int x_pos = 10;		// x - Position des Balles
	int y_pos = 100;	// y - Position des Balles
	int radius = 20;	// Radius des Balles

	public void init()
	{
		setBackground (Color.blue);
	}

	public void start ()
	{
		// Schaffen eines neuen Threads, in dem das Spiel läuft
		Thread th = new Thread (this);
		// Starten des Threads
		th.start ();
	}

	public void stop()
	{

	}

	public void destroy()
	{

	}

	public void run ()
	{
		// Erniedrigen der ThreadPriority um zeichnen zu erleichtern
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

		// Solange true ist läuft der Thread weiter
		while (true)
		{
			// Verändern der x- Koordinate
			x_pos ++;

			// Neuzeichnen des Applets
			repaint();

			try
			{
				// Stoppen des Threads für in Klammern angegebene Millisekunden
				Thread.sleep (20);
			}
			catch (InterruptedException ex)
			{
				// do nothing
			}

			// Zurücksetzen der ThreadPriority auf Maximalwert
			Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		}
	}


	public void paint (Graphics g)
	{
		g.setColor  (Color.red);

		g.fillOval (x_pos - radius, y_pos - radius, 2 * radius, 2 * radius);
	}

}
