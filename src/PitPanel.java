import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

/**
 * A Pit Panel represents a single slot on the overall mancala board. It contains information of how to draw the pit.
 * It also contains information about the number of stones and draws these stones. The stones are drawn based on the
 * specified StoneIcon.
 */
public abstract class PitPanel extends JPanel {
	private int stoneSize;
	protected StoneIcon stoneIcon;
	protected Shape pit;
	protected Color clickableColor = Color.CYAN;
	private boolean mouseHover = false;
	private int strokeWidth = 5;
	LinkedList<Point2D> relativeStoneLocations = new LinkedList<>();

	/**
	 * Constructor with a stone icon and no stones specified. Stones default to 4.
	 * @param stoneIcon the stone type
	 */
	PitPanel(StoneIcon stoneIcon) {
		this(stoneIcon, 4);
	}

	/**
	 * Constructor with a stone icon and stones.
	 * @param stoneIcon the stone type
	 * @param numStones how many stones inside
	 */
	PitPanel(StoneIcon stoneIcon, int numStones) {
		this.setLayout(null);
		setSize(100, 100);

		this.stoneIcon = stoneIcon;
		updatePitSize();
		setStones(numStones);
		
	}

	@Override
	/**
	 * Paints a PitPanel. Uses the template pattern to draw background, draw pit, update the pit based on resizing
	 * of window, and reorder the stones. See abstract methods.
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		//draw the background
		drawBackground(g2);
		// draw the pit
		updatePitSize();
		drawPit(g2);
		// Draw the num stones
		updateStoneSize();
		drawStones(g2);
		drawClickable(g2);
	}

	/**
	 * To show if the pit can be clicked.
	 */
	private void drawClickable(Graphics2D g2) {
		if (isEnabled() && mouseHover) {
			Rectangle2D highlightRect = new Rectangle2D.Double();
			Stroke basic = new BasicStroke(strokeWidth);
			g2.setStroke(basic);
			g2.setColor(clickableColor);
			g2.drawRect(0,0,getWidth(), getHeight());
		}
	}

	/**
	 * Draws the background of the pit.
	 * @param g2 Graphics2D instance
	 */
	protected abstract void drawBackground(Graphics2D g2);

	/**
	 * This method is intended to be implemented to appropriately update the size of the pit Shape paintComponent()
	 * is called. Unfortunately a Shape interface object cannot be resized directly.
	 */
	protected abstract void updatePitSize();

	/**
	 * Implementation to draw the internal part of the pit.
	 * @param g2 Graphics 2D
	 */
	protected abstract void drawPit(Graphics2D g2);

	/**
	 * Updates the size of the stones.
	 */
	private void updateStoneSize() {
		stoneSize = (int) (getStoneRatio() * (getWidth() + getHeight()) / 2);
	}

	/**
	 * Draws the stones.
	 * @param g2 Graphics 2D
	 */
	protected void drawStones(Graphics2D g2) {

		for (Point2D ratio : relativeStoneLocations) {
			int pitWidth = pit.getBounds().width;
			int pitHeight = pit.getBounds().height;

			int dx = (getWidth() - pitWidth) / 2;
			int dy = (getHeight() - pitHeight) / 2;


			double scaledX = getWidth()/pitWidth;
			stoneIcon.paintIcon(this, g2, (int) (pitWidth*ratio.getX()*scaledX/2)+dx, (int) (pitHeight *ratio.getY()*scaledX/2 )+dy);


		}
		g2.drawRect( (int) pit.getBounds().getX(), (int) pit.getBounds().getY(), pit.getBounds().width, pit.getBounds().height);
	}

	/**
	 * The ratio of the stone size to the size of the entire pit bounds
	 * @return ratio between 0 and 1.
	 */
	protected abstract double getStoneRatio();

	/**
	 * Get the number of stones in the pit
	 * @return number stones
	 */
	public int getNumStones() {
		return relativeStoneLocations.size();
	}

	/**
	 * Get the current stone size
	 * @return current stone size
	 */
	public int getStoneSize() {
		return stoneSize;
	}

	/**
	 * Set the number of stones in the pit. This method will call the placeStones() method to reset the stone's
	 * relative positions.
	 * @param numStones the stones to set
	 */
	public void setStones(int numStones) {
		if (numStones != getNumStones()) {
			relativeStoneLocations.clear();
			placeStones(numStones);
			repaint();
		}
	}

	/**
	 * The algorithm for placing the stones. This method is called everytime the number of stones is set.
	 * @param numStones the number of stones to place
	 */
	protected abstract void placeStones(int numStones);

	/**
	 * Get the current shape of the pit panel
	 * @return the shape
	 */
	public Shape getShape() {
		return pit;
	}

	@Override
	/**
	 * Set the preferred size to current width and height.
	 */
	public Dimension getPreferredSize() {
		return new Dimension(getWidth(), getHeight());
	}

	public void setMouseHover(boolean mouseHover) {
		this.mouseHover = mouseHover;
	}
}
