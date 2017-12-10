
import java.awt.*;

/**
 * Example9Applet
 *
 * This is a template applet for animation.
 * It illustrates how to animate multiple frames using
 * the MediaTracker class.
 *
 * @author Arthur van Hoff
 */
public
class Example9Applet extends java.applet.Applet implements Runnable {
    int frame;
    int delay;
    Thread animator;

    Dimension offDimension;
    Image offImage;
    Graphics offGraphics;

    MediaTracker tracker;
    Image frames[];

    /**
     * Initialize the applet and compute the delay between frames.
     */
    public void init() {
	String str = getParameter("fps");
	int fps = (str != null) ? Integer.parseInt(str) : 10;
	delay = (fps > 0) ? (1000 / fps) : 100;

	tracker = new MediaTracker(this);
	frames = new Image[10];
	for (int i = 1 ; i <= 10 ; i++) {
	    frames[i-1] = getImage(getCodeBase(), "duke/T" + i + ".gif");
	    tracker.addImage(frames[i-1], 0);
	}
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
	update(g);
    }

    /**
     * Paint a frame of animation.
     */
    public void paintFrame(Graphics g) {
	// Only paint when all images have arrived
	if (tracker.statusID(0, true) == MediaTracker.COMPLETE) {
	    g.drawImage(frames[frame % 10], 0, 0, null);
	}
    }
}

