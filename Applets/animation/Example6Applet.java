
import java.awt.*;

/**
 * Example6Applet
 *
 * This is a template applet for animation.
 * It illustrates how to use double buffering.
 *
 * @author Arthur van Hoff
 */
public
class Example6Applet extends java.applet.Applet implements Runnable {
    int frame;
    int delay;
    Thread animator;

    Dimension offDimension;
    Image offImage;
    Graphics offGraphics;

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
	offImage = null;
	offGraphics = null;
    }

    /**
     * Update a frame of animation.
     */
    public void update(Graphics g) {
	Dimension d = size();

	// Create the offscreen graphics context
	if ((offGraphics == null)
	 || (d.width != offDimension.width)
	 || (d.height != offDimension.height)) {
	    offDimension = d;
	    offImage = createImage(d.width, d.height);
	    offGraphics = offImage.getGraphics();
	}

	// Erase the previous image
	offGraphics.setColor(getBackground());
	offGraphics.fillRect(0, 0, d.width, d.height);
	offGraphics.setColor(Color.black);

	// Paint the frame into the image
	paintFrame(offGraphics);

	// Paint the image onto the screen
	g.drawImage(offImage, 0, 0, null);
    }

    /**
     * Paint the previous frame (if any).
     */
    public void paint(Graphics g) {
	if (offImage != null) {
	    g.drawImage(offImage, 0, 0, null);
	}
    }

    /**
     * Paint a frame of animation.
     */
    public void paintFrame(Graphics g) {
	Dimension d = size();
	int h = d.height / 2;
	for (int x = 0 ; x < d.width ; x++) {
	    int y1 = (int)((1.0 + Math.sin((x - frame) * 0.05)) * h);
	    int y2 = (int)((1.0 + Math.sin((x + frame) * 0.07)) * h);
	    g.drawLine(x, y1, x, y2);
	}
    }
}
