import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by andrew on 5/5/17.
 */
public class BluePitPanel extends PitPanel{

	// the ratio of the size of the pit to the entire PinkPitPanel
	private static final double RATIO = 0.80;

	// weight of the outline of the pit
	private static final float STROKE_WEIGHT = 0.02f;

	private static final Color BACKGROUND_FILL_COLOR = Color.green;

	private static final Color PIT_FILL_COLOR = Color.blue;

	private static final Color PIT_OUTLINE_COLOR = Color.black;

	BluePitPanel(StoneIcon stoneType, int numStones){
		super(stoneType);
		this.numStones = numStones;
		pit = new Ellipse2D.Double();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(getWidth(), getHeight());
	}

	@Override
	protected void drawStones(Graphics2D g2) {
		// Draw the num stones
		int pitWidth = (int) (this.getWidth() * RATIO);
		int pitHeight = (int) (this.getHeight() * RATIO);
		g2.setFont(new Font("TimesRoman", Font.PLAIN, pitWidth / 3));
		g2.drawString(String.valueOf(numStones), pitWidth / 2, pitHeight / 2);
	}

	@Override
	protected void drawPit(Graphics2D g2) {
		g2.setColor(PIT_FILL_COLOR);

		int pitWidth = (int) (this.getWidth() * RATIO);
		int pitHeight = (int) (this.getHeight() * RATIO);

		g2.draw(pit);
		g2.fill(pit);
		g2.setColor(PIT_OUTLINE_COLOR);

		int strokeWidth = (int) ((getWidth() + getHeight()) / 2 * STROKE_WEIGHT);
		g2.setStroke(new BasicStroke(strokeWidth));

		g2.draw(pit);
	}

	@Override
	protected void drawBackground(Graphics2D g2) {
		g2.setColor(BACKGROUND_FILL_COLOR);
		g2.fillRect(0,0, this.getWidth(), this.getHeight());
	}
}
