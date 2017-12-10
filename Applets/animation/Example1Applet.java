/**
 * Example1Applet
 *
 * This is a template applet for animation.
 * It shows how to write the basic applet so
 * that it draws one frame of animation 
 * at intervals defined by a frames per second (fps)
 * parameter to the applet.
 *
 * @author Arthur van Hoff
 */
public
class Example1Applet extends java.applet.Applet implements Runnable {
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
	while (Thread.currentThread() == animator) {
	    // Display the next frame of animation.
	    repaint();
	    
	    // Delay for a while
	    try {
		Thread.sleep(delay);
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
}








