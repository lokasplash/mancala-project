import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * A panel for drawing the pit.
 * @author Andrew Jong
 */
public class PitPanel extends JPanel {
	// the ratio of the size of the pit to the entire PitPanel
	private static final double RATIO = 0.80;

	// weight of the outline of the pit
	private static final float STROKE_WEIGHT = 0.02f;

	private static final Color BACKGROUND_FILL_COLOR = Color.orange;

	private static final Color PIT_FILL_COLOR = Color.pink;

	private static final Color PIT_OUTLINE_COLOR = Color.black;
	
	private Ellipse2D.Double pit;
	
	public Ellipse2D.Double getShape(){
		return pit;
	}

	PitPanel(){
		pit = new Ellipse2D.Double();
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(getWidth(), getHeight());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		//draw the background
		g2.setColor(BACKGROUND_FILL_COLOR);
		g2.fillRect(0,0, this.getWidth(), this.getHeight());

		// math for the pit
		int pitWidth = (int) (this.getWidth() * RATIO);
		int pitHeight = (int) (this.getHeight() * RATIO);
		int pitX = getWidth() / 2 - pitWidth / 2;
		int pitY = getHeight() / 2 - pitHeight / 2;
		
		pit.setFrame(pitX, pitY, pitWidth, pitHeight);
		
		// draw the pit
		g2.setColor(PIT_FILL_COLOR);
		
		g2.draw(pit);
		g2.fillOval(pitX, pitY, pitWidth, pitHeight);
		g2.setColor(PIT_OUTLINE_COLOR);

		int strokeWidth = (int) ((getWidth() + getHeight()) / 2 * STROKE_WEIGHT);
		g2.setStroke(new BasicStroke(strokeWidth));

		g2.drawOval(pitX, pitY, pitWidth, pitHeight);
	}


}
