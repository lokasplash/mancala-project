import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import stone_icons.StoneIcon;

/**
 * A pit panel for drawing blue pit and orange backgrounds.
 * @author Andrew Jong
 */
public class BluePitPanel extends PitPanel {
	// the ratio of the size of the pit to the entire PinkPitPanel
	private static final double RATIO = 0.80;

	// self explanatory variables
	private static final float STROKE_WEIGHT = 0.02f;
	private static final Color BACKGROUND_FILL_COLOR = Color.decode("#f45f42");
	private static final Color PIT_FILL_COLOR = Color.blue;
	private static final Color PIT_OUTLINE_COLOR = Color.black;

	// amount of overlap allowed for stones
	private static final double ALLOWED_STONE_OVERLAP = 0.05;

	/**
	 * Construct a blue pit panel
	 * @param stoneType StoneIcon's base size should be less than .95 the size of the pit of this PitPanel
	 * Else, may hang while choosing stone placement
	 */
	BluePitPanel(StoneIcon stoneType) {
		super(stoneType, 4);
	}

	/**
	 * Construct a blue pit panel with the number of stones.
	 * @param stoneType StoneIcon's base size should be less than .95 the size of the pit of this PitPanel.
	 *                     Else, may hang while choosing stone placement.
	 * @param numStones the number of stones to draw in this pit
	 *
	 */
	BluePitPanel(StoneIcon stoneType, int numStones) {
		super(stoneType, numStones);
	}

	@Override
	protected double getStoneRatio() {
		return 0.1;
	}

	/**
	 * Draws pit as rounded rectangle
	 */
	@Override
	protected void updatePitSize() {
		int pitWidth = (int) (this.getWidth() * RATIO);
		int pitHeight = (int) (this.getHeight() * RATIO);
		int dx = (getWidth() - pitWidth) / 2;
		int dy = (getHeight() - pitHeight) / 2;
		pit = new RoundRectangle2D.Double(dx, dy, pitWidth, pitHeight, 50, 50);
	}

	/**
	 * Draw method for PitPanel
	 */
	@Override
	protected void drawPit(Graphics2D g2) {
		g2.setColor(PIT_FILL_COLOR);
		g2.fill(pit);

		g2.setColor(PIT_OUTLINE_COLOR);
		int strokeWidth = (int) ((getWidth() + getHeight()) / 2 * STROKE_WEIGHT);
		g2.setStroke(new BasicStroke(strokeWidth));
		g2.draw(pit);
	}
	
	/**
	 * Draws Stones inside of pit
	 * param numStones Number of stones to draw
	 */
	@Override
	protected void placeStones(int numStones) {

		int pitWidth = (int) (this.getWidth() * RATIO);
		int pitHeight = (int) (this.getHeight() * RATIO);
		int dx = (getWidth() - pitWidth) / 2;
		int dy = (getHeight() - pitHeight) / 2;
		
		Rectangle2D.Float pitBounds = new Rectangle2D.Float((float)dx,(float) dy, 0.99f * (float) pitWidth,(float) 0.99 * pitHeight);

		double pitW = pitBounds.getBounds().getWidth();
		double pitH = pitBounds.getBounds().getHeight();
		

		int stoneWidth = this.stoneIcon.getIconWidth();
		int stoneHeight = this.stoneIcon.getIconHeight();

			
		for (int i = 0; i < numStones; i++){
			boolean locationFound = false;
			do{
				float x = (float) Math.random();
				float y = (float) Math.random();

				x *= pitW;
				y *= pitH;
				
				Ellipse2D.Float stoneBounds = new Ellipse2D.Float((float) (x+pitBounds.getBounds().getX()), (float) (y+pitBounds.getBounds().getY()), (float) (stoneWidth), (float) (stoneHeight));

				System.out.println(stoneBounds.getBounds());
				pitBounds.getBounds().setLocation(0, 0);
				Area pitArea = new Area(pitBounds);
				Area pendingStoneLocation = new Area(stoneBounds);
				Area intersectionArea = (Area) pitArea.clone();
				intersectionArea.add(pendingStoneLocation);
				intersectionArea.subtract(pitArea);
				
				if(intersectionArea.isEmpty() ){
					locationFound = true;
					Point2D p = new Point2D.Float((float) (x/pitW), (float) (y/pitH));
					stoneLocationsRelativeToSquareOfPossibleValues.add(p);
					System.out.println(p);
				}
				
			} while(!locationFound);
	
		}
	}
	
	/**
	 * Colors Background of Panel
	 */
	@Override
	protected void drawBackground(Graphics2D g2) {
		g2.setColor(BACKGROUND_FILL_COLOR);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
}

