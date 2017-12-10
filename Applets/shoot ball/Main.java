import java.awt.*;
import java.util.*;
import java.applet.*;
import java.net.*;

/** Diese Klasse enthält alle für die Implementierung eines Applets wichtigen Methoden
(init, start, stop), desweiteren die Methoden run (zur Implementierung des Interfaces Run-
abble), sowie die Graphikmethoden paint und update. Die Klasse realisiert die Dopppel-
pufferung in der Methode update, sie hört auf Mausereignisse (mouseDown) und enthält
alle für den Ablauf des Spiels wichtigen Anweisungen */

public class Main extends Applet implements Runnable
{
	// Deklarationen der Variablen
	private int speed;				// Threadgeschwindigkeit

	boolean isStoped = true;		// Zeigt an, ob das Spiel gestopt ist (true) oder läuft (false)

	// Deklaration der Objektreferenzen
	private Player player;			// Refferenz auf das Spielerobjekt
	private Ball redball;			// Refferenz auf den roten Ball
	private Ball blueball;			// Refferenz auf den blauen Ball

	// Thread
	Thread th;						// Thread in dem das Spiel läuft

	// Audiodateien
	AudioClip shotnoise;	// Speichert die Wav - Datei Gun, die nach Schuss abgespielt wird
	AudioClip hitnoise;		// Speichert die Wav - Datei hit, die nach einem Treffer abgespielt wird
	AudioClip outnoise;		// Speichert die Wav - Datei error, die nach einem Treffer abgespielt wird

	// Neue Schrift
    Font f = new Font ("Serif", Font.BOLD, 20);

    // Fadenkreuzmauszeiger
    Cursor c;				// Variable für Cursor

    // Variablen für die Doppelpufferung
	private Image dbImage;
	private Graphics dbg;

	// Init - Methode
	public void init ()
	{
		// Mauszeiger wird zu Fadenkreuz
		c = new Cursor (Cursor.CROSSHAIR_CURSOR);
		this.setCursor (c);

		// Neue Hintergrundfarbe
        Color superblue = new Color (0, 0, 255);

		// Setzen der Hintergrundfarbe
		setBackground (Color.black);

		// Setzten der Schrift
		setFont (f);

		// Speed wird von Parameter speed des Applets bestimmt
		if (getParameter ("speed") != null)
		{
			speed = Integer.parseInt(getParameter("speed"));
		}
		else speed = 15;

		// Laden der Bilder und Audiodateien und einmaliges Abspielen, um längere Ladezeiten während des Spiels zu vermeiden
		hitnoise = getAudioClip (getCodeBase() , "gun.au");
		hitnoise.play();
		hitnoise.stop();
		shotnoise = getAudioClip (getCodeBase() , "miss.au");
		shotnoise.play();
		shotnoise.stop();
		outnoise = getAudioClip (getCodeBase() , "error.au");
		outnoise.play();
		outnoise.stop();

		// Initialisierung der Spielobjekte
		player = new Player ();
		redball = new Ball (10, 190, 250, 1, -1, 4, Color.red, outnoise, player);
		blueball = new Ball (10, 190, 150, 1, 1, 3, Color.blue, outnoise, player);
	}


	// Start - Methode, hier beginnt das Applet zu laufen
	public void start ()
	{
		// Schaffen eines neuen Threads, in dem das Spiel läuft
		th = new Thread (this);
		th.start ();
	}

	// Stop - Methode, hier wird das Applet gestopt
	public void stop ()
	{
		th.stop();
	}

	// Auffangen des Mausereignisses mouseDown
	public boolean mouseDown (Event e, int x, int y)
	{
		// Spiel läuft
		if (!isStoped)
		{
			// Test ob roter Ball getroffen wurde
			if (redball.userHit (x, y))
	        {
				// Abspielen der Audiodatei
	        	hitnoise.play();

				// Ball zu Startwert zurücksetzten
				redball.ballWasHit ();
	        }
			// Test ob blauer Ball getroffen wurde
			if (blueball.userHit (x, y))
	        {
				// Abspielen der Audiodatei
	        	hitnoise.play();

				// Ball zu Startwert zurücksetzten
				blueball.ballWasHit ();
	        }
			else
			{
				// Abspielen des normalen Schussgeräusches
				shotnoise.play();
			}

		}
		// Wenn Spiel noch nicht gestartet ist, oder wieder gestartet wird
		else if (isStoped && e.clickCount == 2)
		{
		    // Alle wichtigen Werte zurücksetzen
			isStoped = false;
			init ();
		}

		return true;
	}

	// Implementierung der Runmethode
	public void run ()
	{
		// Erniedrigen der ThreadPriority um zeichnen zu erleichtern
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

		while (true)
		{
			if (player.getLives() >= 0 && !isStoped)
			{
				redball.move();
				blueball.move();
			}

			repaint();

			try
			{
				// Stoppen des Threads für 10 Millisekunden
				Thread.sleep (speed);
			}
			catch (InterruptedException ex)
			{
				// do nothing
			}

			// Zurücksetzen der ThreadPriority auf Maximalwert
			Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		}
	}

	// Paint - Methode
	public void paint (Graphics g)
	{
		// Wenn noch Leben übrig sind
		if (player.getLives() >= 0)
		{
			// Setzen der Farbe
			g.setColor (Color.yellow);

			// Punktestand und übrige Leben
			g.drawString ("Score: " + player.getScore(), 10, 40);
			g.drawString ("Lives: " + player.getLives(), 300, 40);

			// Zeichnen der Bälle
			redball.DrawBall(g);
			blueball.DrawBall(g);

			// Startbildschirm
			if (isStoped)
			{
				g.setColor (Color.yellow);
				g.drawString ("Doubleclick on Applet to start Game!", 40, 200);
			}
		}
		// Wenn alle Leben verbraucht sind
		else if (player.getLives() < 0)
		{
			g.setColor (Color.yellow);

			// Erreichte Punkte und game over
			g.drawString ("Game over!", 130, 100);
			g.drawString ("You scored " + player.getScore() + " Points!", 90, 140);

			// Bewertung der Punkte
			if (player.getScore() < 300) g.drawString ("Well, it could be better!", 100, 190);
			else if (player.getScore() < 600 && player.getScore() >= 300) g.drawString ("That was not so bad", 100, 190);
			else if (player.getScore() < 900 && player.getScore() >= 600) g.drawString ("That was really good", 100, 190);
			else if (player.getScore() < 1200 && player.getScore() >= 900) g.drawString ("You seem to be very good!", 90, 190);
			else if (player.getScore() < 1500 && player.getScore() >= 1200) g.drawString ("That was nearly perfect!", 90, 190);
			else if (player.getScore() >= 1500) g.drawString ("You are the Champingon!",100, 190);

			g.drawString ("Doubleclick on the Applet, to play again!", 20, 220);

			isStoped = true;	// Zurücksetzen der isStoped Variablen, um wieder neu beginnen zu können
		}
	}

	// Update - Methode, Realisierung der Doppelpufferung zur Reduzierung des Bildschirmflackerns
	public void update (Graphics g)
	{
		// Initialisierung des DoubleBuffers
		if (dbImage == null)
		{
			dbImage = createImage (this.getSize().width, this.getSize().height);
			dbg = dbImage.getGraphics ();
		}

		// Bildschirm im Hintergrund löschen
		dbg.setColor (getBackground ());
		dbg.fillRect (0, 0, this.getSize().width, this.getSize().height);

		// Auf gelöschten Hintergrund Vordergrund zeichnen
		dbg.setColor (getForeground());
		paint (dbg);

		// Nun fertig gezeichnetes Bild Offscreen auf dem richtigen Bildschirm anzeigen
		g.drawImage (dbImage, 0, 0, this);
	}
}


