import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 * A panel for drawing the pit.
 * @author Andrew Jong
 */
public class BluePitPanel extends PitPanel {
	// the ratio of the size of the pit to the entire PinkPitPanel
	private static final double RATIO = 0.80;

	// weight of the outline of the pit
	private static final float STROKE_WEIGHT = 0.02f;
	private static final Color BACKGROUND_FILL_COLOR = Color.decode("#f45f42");
	private static final Color PIT_FILL_COLOR = Color.blue;
	private static final Color PIT_OUTLINE_COLOR = Color.black;

	// amount of overlap allowed for stones
	private static final double ALLOWED_STONE_OVERLAP = 0.05;

	BluePitPanel(StoneIcon stoneType) {
		super(stoneType, 4);
	}

	BluePitPanel(StoneIcon stoneType, int numStones) {
		super(stoneType, numStones);
	}

	@Override
	protected double getStoneRatio() {
		return 0.1;
	}

	@Override
	protected void updatePitSize() {
		int pitWidth = (int) (this.getWidth() * RATIO);
		int pitHeight = (int) (this.getHeight() * RATIO);
		int dx = (getWidth() - pitWidth) / 2;
		int dy = (getHeight() - pitHeight) / 2;
		pit = new RoundRectangle2D.Double(dx, dy, pitWidth, pitHeight, 50, 50);
	}

	@Override
	protected void drawPit(Graphics2D g2) {
		g2.setColor(PIT_FILL_COLOR);
		g2.fill(pit);

		g2.setColor(PIT_OUTLINE_COLOR);
		int strokeWidth = (int) ((getWidth() + getHeight()) / 2 * STROKE_WEIGHT);
		g2.setStroke(new BasicStroke(strokeWidth));
		g2.draw(pit);
	}

	@Override
	protected void placeStones(int numStones) {
		for (int i = 0; i < numStones; i++) {
			float x = (float) Math.random();
			float y = (float) Math.random();
			Point2D p = new Point2D.Float(x, y);
			relativeStoneLocations.add(p);
		}
	}
	@Override
	protected void drawBackground(Graphics2D g2) {
		g2.setColor(BACKGROUND_FILL_COLOR);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
}

