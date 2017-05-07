import java.awt.*;
import javax.swing.*;

/**
 * Represents a mancala board with 3 components: left, right, and center
 * Each component is a standalone JPanel
 * @author Vincent Diep
 */
public class BoardPanel extends JPanel {

	private JPanel left;
	private JPanel right;
	private JPanel center;

	/**
	 * How much of the width of a BoardPanel an InternalBoardPanel should take up
	 */
	private static final double RATIO = 0.15;


	/**
	 * Constructor for BoardPanel.
	 */
	public BoardPanel() {
//		left = new JPanel();
//		right = new JPanel();

		left = new InternalBoardPanel();
		right = new InternalBoardPanel();
		center = new JPanel();

		// this makes stretching of panels occur
		left.setLayout(new BorderLayout());
		right.setLayout(new BorderLayout());
		center.setLayout(new BorderLayout());

		this.setLayout(new BorderLayout());

		this.add(left, BorderLayout.WEST);
		this.add(right, BorderLayout.EAST);
		this.add(center, BorderLayout.CENTER);

	}

	/**
	 * Add a component to the left side.
	 * @param component JComponent to add
	 */
	public void addLeft(JComponent component) {
		left.add(component);
	}

	/**
	 * Add a component to the center.
	 * @param component JComponent to add
	 */
	public void addCenter(JComponent component) {
		center.add(component);
	}

	/**
	 * Add a component to the right side.
	 * @param component JComponent to add
	 */
	public void addRight(JComponent component) {
		right.add(component);
	}

	/**
	 * Remove all components from the left side.
	 */
	public void removeLeft() {
		left.removeAll();
	}

	/**
	 * Remove all components from the center.
	 */
	public void removeCenter() {
		center.removeAll();
	}

	/**
	 * Remove all components from the right side.
	 */
	public void removeRight() {
		right.removeAll();
	}

	/**
	 * Remove all components.
	 */
	public void removeAll() {
		removeLeft();
		removeCenter();
		removeRight();
	}

	/**
	 * A component JPanel of BoardPanel that resizes to maintain BoardPanel.RATIO
	 */
	public class InternalBoardPanel extends JPanel {

		@Override
		public Dimension getPreferredSize() {
			Dimension parent = this.getParent().getSize();
			double x = parent.width * RATIO;

			return new Dimension((int) x, this.getHeight());
		}
	}

}
