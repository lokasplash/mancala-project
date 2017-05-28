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

//		System.out.println("placing stones");

		Shape pitBounds = (RoundRectangle2D) this.getShape();
	
		double pitW = pitBounds.getBounds().getWidth();
		double pitH = pitBounds.getBounds().getHeight();

//		System.out.println("pit bounds "+pitW+","+ pitH);
//		System.out.println("pitpanel bounds "+this.getWidth()+","+ this.getHeight());

		double stoneWidth = this.stoneIcon.getIconWidth();
		double stoneHeight = this.stoneIcon.getIconHeight();
		
		stoneWidth *= this.stoneIcon.getScaleX();
		stoneHeight *= this.stoneIcon.getScaleY();
		
		System.out.println("scaleX is"+this.stoneIcon.getScaleX());
		
		System.out.println("Inside PPP, stone size "+stoneWidth+","+stoneHeight);
		
		boolean adjustForScaling = false;
		// if relative positions already exist, we need to scale these positions
		if( !stoneLocationsRelativeToSquareOfValidValues.isEmpty()){
			adjustForScaling = true;
			System.out.println("adjusting for scaling");
		}
			
		for (int i = 0; i < numStones; i++){
			boolean locationFound = false;
			do{
				/* 
				 *     
				 * ---------pitW------------   
				 *  
				 * ----------a---------               
				 * (0,0)
				 *  _______________________
				 * |                   |   |    |      |
				 * |                   |   |    |      |
				 * |                   |   |    |      |
				 * |                   |   |    b      | 
				 * |                   |   |    |     pitH
				 * |                   |   |    |      |
				 * |___________________|___|    |      |
				 * |                   |   |           |
				 * |___________________|___|           |
				 *                          (1,1)
				 * 
				 * Let us represent the pit as a square, such that possible values for X are between 0 and 1, and the possible values
				 * for Y are between 0 and 1.
				 * AKA The square of possible values
				 * 
				 * In short, an x value of 1 means that that the point will be somewhere along the right edge of the pit.
				 * A y value of 1 means that the point will be somewhere along the bottom edge of the pit.
				 *               
				 * In order to find a valid stone position, we have to determine the limits of where the stone can go.
				 * Keep in mind that the stone is drawn from its upper left corner.
				 * This means that if the stone is drawn with at x=1 on this coordinate system, the stone will appear to the right of the pit.
				 * Similarly for y=1, the stone will appear below the pit bottom.
				 * 
				 * The region bounded by a,b is the area where we can place the stone and still have it be drawn inside the pit.
				 * 
				 */
				float a = (float) (1f - stoneWidth/pitW);
				float b = (float) (1f - stoneHeight/pitH);
				

				
				/*
				 * We want to pick a point inside the region bounded by a,b
				 * So, again, we will consider the valid region of a,b to be a square of length 1
				 * AKA The square of valid values
				 * 
				 * Example:
				 * 
				 * ---------a-----------               
				 * (0,0)
				 *  ____________________
				 * |                    |   |
				 * |                    |   |
				 * |                    |   |
				 * |                    |   |
				 * |                    |   b
				 * |                    |   |
				 * |                    |   |
				 * |                    |   |
				 * |____________________|   |
				 * 
				 *                      (1,1)
				 *                      
				 * We can represent such a point inside with this valid region with variables x,y
				 */
				
				
				float x,y;
				
				if(!adjustForScaling){
					
					x = (float) Math.random();
					y = (float) Math.random();	
					
				// Check if stone area is inside pit area
				Ellipse2D.Double stoneEllipseBound = new Ellipse2D.Double(a*x*pitW, b*y*pitH, stoneWidth, stoneHeight);
				System.out.println("stoneEllipse "+a*x*pitW+","+ b*y*pitH+","+  stoneWidth+","+  stoneHeight);
				RoundRectangle2D.Double pitBound = new RoundRectangle2D.Double(0, 0, pitW, pitH,50,50);
				System.out.println("pitEllipse "+pitW+","+pitH);
				
				Area pitArea = new Area(pitBound);
				Area pendingStoneLocation = new Area(stoneEllipseBound);
				Area intersectionArea = (Area) pitArea.clone();
				intersectionArea.add(pendingStoneLocation);
				intersectionArea.subtract(pitArea);
				
					if(intersectionArea.isEmpty() ){
						locationFound = true;
						
						Point2D p = new Point2D.Float((float) (a*x), (float) (b*y));
//						System.out.println("a is "+a+", x is "+x+",b = "+b+",y = "+y);
						stoneLocationsRelativeToSquareOfPossibleValues.add(p);
						stoneLocationsRelativeToSquareOfValidValues.add(new Point2D.Float(x,y));
					}
				}
				
				else{
					// if positions relative to the valid square already exist, retrieve them
						x = (float) stoneLocationsRelativeToSquareOfValidValues.get(i).getX();
						y = (float) stoneLocationsRelativeToSquareOfValidValues.get(i).getY();
					
					System.out.println("a is "+a);
					locationFound = true;
					Point2D p = new Point2D.Float((float) (a*x), (float) (b*y));
					stoneLocationsRelativeToSquareOfPossibleValues.add(p);
			
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

