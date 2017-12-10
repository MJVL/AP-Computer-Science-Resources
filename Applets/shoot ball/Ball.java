import java.applet.*;
import java.awt.*;
import java.util.*;
import java.net.*;

public class Ball
{
	/** Diese Klasse enthält alle Funktionen, die für die Bewegung, Konstruktion,
	Positionsbestimmung, Graphik und Abschiesen des Ball - Objektes von Bedeutung sind. */

	// Deklaration der Variablen
	private int pos_x;				// Variable für die X - Position des Balles
	private int pos_y; 				// Variable für die Y - Position des Balles
	private int x_speed;			// Geschwindigkeit in x - Richtung
	private int y_speed;			// Geschwindigkeit in y - Richtung
	private int radius;				// Radius des Balles

	private int first_x;			// Start x - Position
	private int first_y;			// Start y - Position

	private int maxspeed;			// Gibt den Maximalen Speed des Balles an

	// Deklaration der Konstanten (Grenzen des Applets bei einer Gesamtgröße von 380 x 380)
	private final int x_leftout = 10;
	private final int x_rightout = 370;
	private final int y_upout = 45;
	private final int y_downout = 370;

	// Farbe des Balles
	Color color;

	// AudioClip out
	AudioClip out;

	// Refferenz auf das Playerobjekt des Spiels
	Player player;

	// Erzeugen des zum Erzeugen von Zufallszahlen nötigen Objektes
	Random rnd = new Random ();

	// Construktor
	public Ball (int radius, int x, int y, int vx, int vy, int ms, Color color, AudioClip out, Player player)
	{
		// Initialisierung der Variablen
		this.radius = radius;

		pos_x = x;
		pos_y = y;

		first_x = x;
		first_y = y;

		x_speed = vx;
		y_speed = vy;

		maxspeed = ms;

		this.color = color;

		this.out = out;

		this.player = player;

	}

	// Move - Methode, berechnet die Bewegung des Balls
	public void move ()
	{
		pos_x += x_speed;
		pos_y += y_speed;
		isOut();
	}

	/** Methode wird nach einem Treffer des Balls aufgerufen, dabei wird x und y übergeben um Ball an die
	Ausgangsposition zurückzusetzten */
	public void ballWasHit ()
	{
		// Zurücksetzen der Position
		pos_x = first_x;
		pos_y = first_y;

		// Bestimmung einer neuen Flugrichtung in x - Richtung per Zufall
		x_speed = (rnd.nextInt ()) % maxspeed;
	}

	/** Methode zur Berechnung, ob der Ball getroffen werden konnte oder nicht.
	Dies wird mit Hilfe des Skalarproduktes berechnet. Hierbei wird zunächst
	der Abstandsvektor zwischen Ball und Mausereigniss und dann seine Länge bestimmt.
	Ist diese kleiner als 15, dann gilt der Ball als getroffen und true wid als
	Argument übergeben. Ansonsten ist der Wert der Funktion false */
	public boolean userHit (int maus_x, int maus_y)
	{
		// Bestimmen der Verbindungsvektoren
		double x = maus_x - pos_x;
		double y = maus_y - pos_y;

		// Berechnen der Distanz
		double distance = Math.sqrt ((x*x) + (y*y));

		// Wenn Distanz kleiner als 15 gilt Ball als getroffen
		if (distance < 15)
		{
			player.addScore (10*Math.abs(x_speed) + 10);
			return true;
		}
		else return false;
	}

	/** Test ob sich der Ball im Aus befindet, wird von Main in jedem Threaddurchlauf
	aufgerufen */
	private boolean isOut ()
	{
		// Ball im Linken Aus
		if (pos_x < x_leftout)
		{
			// Setzen der x - Position
			pos_x = first_x;
			pos_y = first_y;

			// Abspielen des Audioclips
			out.play();

			// Neue Flugrichtung
			x_speed = (rnd.nextInt ()) % maxspeed;

			// Leben verlieren
			player.looseLife();

			// Rückgabewert
			return true;
		}
		// Ball im rechten Aus
		else if (pos_x > x_rightout)
		{
			// Setzen der x - Position
			pos_x = first_x;
			pos_y = first_y;

			// Abspielen des Audioclips
			out.play();

			// Neue Flugrichtung
			x_speed = (rnd.nextInt ()) % maxspeed;

			// Leben verlieren
			player.looseLife();

			// Rückgabewert
			return true;
		}
		// Ball im oberen Aus
		else if (pos_y < y_upout)
		{
			// Setzen der x - Position
			pos_x = first_x;
			pos_y = first_y;

			// Abspielen des Audioclips
			out.play();

			// Neue Flugrichtung
			x_speed = (rnd.nextInt ()) % maxspeed;

			// Leben verlieren
			player.looseLife();

			// Rückgabewert
			return true;
		}
		// Ball im unteren Aus
		else if (pos_y > y_downout)
		{
			// Setzen der x - Position
			pos_x = first_x;
			pos_y = first_y;

			// Abspielen des Audioclips
			out.play();

			// Neue Flugrichtung
			x_speed = (rnd.nextInt ()) % maxspeed;

			// Leben verlieren
			player.looseLife();

			// Rückgabewert
			return true;
		}
		else return false;
	}

	// Diese Methode zeichnet den Ball in das Spielfeld
	public void DrawBall (Graphics g)
	{
		g.setColor (color);
		g.fillOval (pos_x - radius, pos_y - radius, 2 * radius, 2 * radius);
	}

}
