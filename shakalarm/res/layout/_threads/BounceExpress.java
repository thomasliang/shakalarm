package _threads;

/**
 @version 1.32 2004-07-27
 @author Cay Horstmann

 @author Updated by S.C. Cheung 2009
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Shows an animated bouncing ball.
 */
public class BounceExpress {
	public static void main(String[] args) {
		JFrame frame = new ExpressBounceFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

/**
 * The frame with panel and buttons.
 */
class ExpressBounceFrame extends AbstractBounceFrame {
	/**
	 * Constructs the frame with the panel for showing the bouncing ball and
	 * Start and Close buttons
	 */
	public ExpressBounceFrame() {
		super("ExpressBounce");
		addButton(buttonPanel, "Start", new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				addBall(Thread.MIN_PRIORITY, Color.black);
			}
		});
		addButton(buttonPanel, "Express", new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				addBall(Thread.MAX_PRIORITY, Color.red);
			}
		});

		addButton(buttonPanel, "Close", new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
	}

	/**
	 * Adds a bouncing ball to the canvas and starts a thread to make it bounce
	 */
	public void addBall(int priority, Color color) {
		for (int i = 0; i < 300; i++) {
			Ball b = new Ball(color);
			panel.add(b);
			Runnable r = new BallRunnable(b, panel);
			Thread t = new Thread(r);
			t.setPriority(priority); // priority set here
			t.start();
		}
	}
}
