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
public class MultithreadBounce {
	public static void main(String[] args) {
		JFrame frame = new MultithreadBounceFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

/**
 * The frame with panel and buttons.
 */
class MultithreadBounceFrame extends AbstractBounceFrame {
	/**
	 * Constructs the frame with the panel for showing the bouncing ball and
	 * Start and Close buttons
	 */
	MultithreadBounceFrame() {
		super("MultithreadBounce");
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
	 * Adds a bouncing ball to the canvas and starts a thread to make it bounce
	 */
	public void addBall() {
		Ball b = new Ball();
		panel.add(b);
		Runnable r = new BallRunnable(b, panel);
		Thread t = new Thread(r);
		t.start();
	}
}
