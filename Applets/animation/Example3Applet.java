
import java.awt.*;

/**
 * Example3Applet
 *
 * This is a template applet for animation.
 * It illustrates how to use a graphics context
 * to draw to the screen.
 *
 * @author Arthur van Hoff
 */
public
class Example3Applet extends java.applet.Applet implements Runnable {
    int frame;
    int delay;
    Thread animator;

    /**
     * Initialize the applet and compute the delay between frames.
     */
    public void init() {
	String str = getParameter("fps");
	int fps = (str != null) ? Integer.parseInt(str) : 10;
	delay = (fps > 0) ? (1000 / fps) : 100;
    }

    /**
     * This method is called when the applet becomes visible on
     * the screen. Create a thread and start it.
     */
    public void start() {
	animator = new Thread(this);
	animator.start();
    }

    /**
     * This method is called by the thread that was created in
     * the start method. It does the main animation.
     */
    public void run() {
	// Remember the starting time
	long tm = System.currentTimeMillis();
	while (Thread.currentThread() == animator) {
	    // Display the next frame of animation.
	    repaint();

	    // Delay depending on how far we are behind.
	    try {
		tm += delay;
		Thread.sleep(Math.max(0, tm - System.currentTimeMillis()));
	    } catch (InterruptedException e) {
		break;
	    }

	    // Advance the frame
	    frame++;
	}
    }

    /**
     * This method is called when the applet is no longer
     * visible. Set the animator variable to null so that the
     * thread will exit before displaying the next frame.
     */
    public void stop() {
	animator = null;
    }

    /**
     * Paint a frame of animation.
     */
    public void paint(Graphics g) {
	g.setColor(Color.black);
	g.drawString("Frame " + frame, 0, 30);
    }
}
