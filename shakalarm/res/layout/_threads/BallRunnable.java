package _threads;

/**
 * @author Updated by S.C. Cheung 2009
 */

import javax.swing.JComponent;

/**
 * A runnable task that animates a bouncing ball.
 * The run() method will be executed by a thread.
 */
class BallRunnable implements Runnable {
	/**
	 * Constructs the runnable.
	 * 
	 * @aBall the ball to bounce
	 * @aPanel the component in which the ball bounces
	 */
	public BallRunnable(Ball aBall, JComponent aComponent) {
		ball = aBall;
		component = aComponent;
	}

	public void run() {
		try {
			for (int i = 1; i <= STEPS; i++) {
				ball.move(component.getBounds());
				component.repaint();
				Thread.sleep(DELAY);
			}
		} catch (InterruptedException e) {
		}
	}

	private Ball ball;
	private JComponent component;
	public static final int STEPS = 1000;
	public static final int DELAY = 5;
}
