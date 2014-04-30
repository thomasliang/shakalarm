package _threads;

/**
 * @author Updated by S.C. Cheung 2009
 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The panel that draws the balls.
 */
class BallPanel extends JPanel {
	/**
	 * Add a ball to the panel.
	 * 
	 * @param b
	 *            the ball to add
	 */
	public void add(Ball b) {
		balls.add(b);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (Ball b : balls) {
			g2.setColor(b.getColor());
			g2.fill(b.getShape());
		}
	}

	private ArrayList<Ball> balls = new ArrayList<Ball>();
}

/**
 * The frame with panel and buttons.
 */
abstract public class AbstractBounceFrame extends JFrame {
	/**
	 * Constructs the frame with the panel for showing the bouncing ball and
	 * Start and Close buttons
	 */
	public AbstractBounceFrame(String str) {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setTitle(str);

		panel = new BallPanel();
		add(panel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	/**
	 * Adds a button to a container.
	 * 
	 * @param c
	 *            the container
	 * @param title
	 *            the button title
	 * @param listener
	 *            the action listener for the button
	 */
	public void addButton(Container c, String title, ActionListener listener) {
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}

	/**
	 * Adds a bouncing ball to the panel and makes it bounce 1,000 times.
	 */

	protected BallPanel panel;
	protected JPanel buttonPanel = new JPanel();
	public static final int DEFAULT_WIDTH = 450;
	public static final int DEFAULT_HEIGHT = 350;
}