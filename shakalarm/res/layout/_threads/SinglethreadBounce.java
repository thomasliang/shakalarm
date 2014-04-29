package _threads;

/**
 @version 1.32 2004-07-27
 @author Cay Horstmann
 
 @author Updated by S.C. Cheung 2009
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Shows an animated bouncing ball.
 */
public class SinglethreadBounce {
	public static void main(String[] args) {
		JFrame frame = new SinglethreadBounceFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

/**
 * The frame with panel and buttons.
 */
class SinglethreadBounceFrame extends AbstractBounceFrame {

	SinglethreadBounceFrame() {
		super("SinglethreadBounce");
		addButton(buttonPanel, "Start", new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				addBall();
			}
		});
		addButton(buttonPanel, "Close", new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
	}

	/**
	 * Adds a bouncing ball to the panel and makes it bounce 1,000 times.
	 */
	public void addBall() {
		try {
			Ball ball = new Ball();
			panel.add(ball);

			for (int i = 1; i <= STEPS; i++) {
				ball.move(panel.getBounds());
				panel.paint(panel.getGraphics());				
				Thread.sleep(DELAY);
			}
		} catch (InterruptedException e) {
		}
	}

	public static final int STEPS = 1000;
	public static final int DELAY = 3;
}
