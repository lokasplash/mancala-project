import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.LinkedList;

/**
 * Created by andrew on 5/5/17.
 */
public abstract class PitPanel extends JPanel {
	private int stoneSize;
	protected StoneIcon stoneType;
	protected Shape pit;
	LinkedList<Point2D> relativeStoneLocations = new LinkedList<>();

	PitPanel(StoneIcon stoneType, int numStones) {
		this.setLayout(null);
		setSize(100, 100);
		this.stoneType = stoneType;
		setNumStones(numStones);
		updatePitSize();
	}

	@Override
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
	}

	/**
	 * Draws the background of the pit.
	 * @param g2 Graphics2D instance
	 */
	protected abstract void drawBackground(Graphics2D g2);

	protected abstract void updatePitSize();

	protected abstract void drawPit(Graphics2D g2);

	private void updateStoneSize() {
		stoneSize = (int) (getStoneRatio() * (getWidth() + getHeight()) / 2);
	}

	protected void drawStones(Graphics2D g2) {
		for (Point2D ratio : relativeStoneLocations) {
			int pitWidth = pit.getBounds().width;
			int pitHeight = pit.getBounds().height;

			int dx = (getWidth() - pitWidth) / 2;
			int dy = (getHeight() - pitHeight) / 2;

			stoneType.paintIcon(this, g2, (int) (pitWidth * ratio.getX() * 0.7 + dx), (int) (pitHeight * ratio.getY() * 0.7+ dy));
		}
	}

	/**
	 * The ratio of the stone size to the size of the entire pit bounds
	 * @return
	 */
	protected abstract double getStoneRatio();

	public int getNumStones() {
		return getComponents().length;
	}

	public int getStoneSize() {
		return stoneSize;
	}

	public void setNumStones(int numStones) {
		relativeStoneLocations.clear();
		placeStones(numStones);
		repaint();
	}

	/**
	 * The algorithm for placing the stones. This method is called everytime the number of stones is set.
	 * @param numStones
	 */
	protected abstract void placeStones(int numStones);

	public Shape getShape() {
		return pit;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(getWidth(), getHeight());
	}

}
