
/** Die Klasse Player verwaltet die Variablen, die die Punkte und die Leben des Spielers
ausmachen. Ebenso verfügt sie über Methoden mit denen Punkte und Leben abgezogen bzw.
hinzugefügt werden können */

public class Player
{
	// Deklaration der Variablen
	private int score;			// Punkte des Spielers
	private int lives;			// Leben des Spielers

	/* Konstruktor */
	public Player()
	{
		lives = 10;
		score = 0;
	}

	/* Liefert die Punkte zurück */
	public int getScore ()
	{
		return score;
	}

	/* Liefert die Leben zurück */
	public int getLives ()
	{
		return lives;
	}

	/* Fügt Punkte hinzu */
	public void addScore (int plus)
	{
		score += plus;
	}

	/* zieht Leben ab */
	public void looseLife ()
	{
		lives --;
	}
}