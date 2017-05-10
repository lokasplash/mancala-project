import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

/**
 * A panel for drawing the pit.
 * @author Andrew Jong
 */
public class PinkPitPanel extends PitPanel {
	// the ratio of the size of the pit to the entire PinkPitPanel
	private static final double RATIO = 0.80;

	// weight of the outline of the pit
	private static final float STROKE_WEIGHT = 0.02f;
	private static final Color BACKGROUND_FILL_COLOR = Color.orange;
	private static final Color PIT_FILL_COLOR = Color.pink;
	private static final Color PIT_OUTLINE_COLOR = Color.black;

	// amount of overlap allowed for stones
	private static final double ALLOWED_STONE_OVERLAP = 0.05;

	PinkPitPanel(StoneIcon stoneType) {
		super(stoneType, 4);
	}

	PinkPitPanel(StoneIcon stoneType, int numStones) {
		super(stoneType, numStones);
	}

	@Override
	protected double getStoneRatio() {
		return 0.1;
	}

	/**
	 * Resize Pit
	 */
	@Override
	protected void updatePitSize() {
		int pitWidth = (int) (this.getWidth() * RATIO);
		int pitHeight = (int) (this.getHeight() * RATIO);
		int dx = (getWidth() - pitWidth) / 2;
		int dy = (getHeight() - pitHeight) / 2;
		System.out.println("updated pit size "+pitWidth+","+pitHeight);
		pit = new Ellipse2D.Double(dx, dy, pitWidth, pitHeight);
	}

	/**
	 * Draws Pit
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
	 * Draws Stones inside of Pit
	 * param NumStones Number of stones to draw
	 */
	@Override
	protected void placeStones(int numStones) {
		System.out.println("placing stones");

		Shape pitBounds = (Ellipse2D) this.getShape();
	
		double pitW = pitBounds.getBounds().getWidth();
		double pitH = pitBounds.getBounds().getHeight();

		
		
		System.out.println("pit bounds "+pitW+","+ pitH);
		System.out.println("pitpanel bounds "+this.getWidth()+","+ this.getHeight());

//		double stoneWidth = this.stoneIcon.getIconWidth()* this.stoneIcon.scaleX;
//		double stoneHeight = this.stoneIcon.getIconHeight()* this.stoneIcon.scaleY;
		double stoneWidth = this.stoneIcon.getIconWidth();
		double stoneHeight = this.stoneIcon.getIconHeight();
		
		stoneWidth *= this.stoneIcon.scaleX;
		stoneHeight *= this.stoneIcon.scaleY;
		
		System.out.println("scaleX is"+this.stoneIcon.scaleX);
		
		System.out.println("Inside PPP, stone size "+stoneWidth+","+stoneHeight);
		
		boolean adjustForScaling = false;
		if( !relativeStoneLocationsX.isEmpty()){
			adjustForScaling = true;
			System.out.println("adjusting for scaling");
		}
			
		for (int i = 0; i < numStones; i++){
			boolean locationFound = false;
			do{
				float x = (float) Math.random();
				float y = (float) Math.random();
				
//				x /= this.stoneIcon.scaleX;
//				y /= this.stoneIcon.scaleY;

//				x *= 80;
//				y *= 80;
				
//				x *= pitW;
//				y *= pitH;
				
//				Ellipse2D.Float stoneBounds = new Ellipse2D.Float((float) (x), (float) (y), (float) (stoneWidth), (float) (stoneHeight));
//
//				System.out.println("pending stone bounds "+stoneBounds.getBounds());
//				pitBounds.getBounds().setLocation(0, 0);
//				Area pitArea = new Area(pitBounds);
//				Area pendingStoneLocation = new Area(stoneBounds);
//				Area intersectionArea = (Area) pitArea.clone();
//				intersectionArea.add(pendingStoneLocation);
//				intersectionArea.subtract(pitArea);
//				
//				if(intersectionArea.isEmpty() ){
//					locationFound = true;
//					Point2D p = new Point2D.Float((float) (x/pitW), (float) (y/pitH));
//					relativeStoneLocations.add(p);
//					System.out.println(p);
//				}
				
				if(adjustForScaling){
					x = (float) relativeStoneLocationsX.get(i).getX();
					y = (float) relativeStoneLocationsX.get(i).getY();
				}


				float a = (float) (1f - stoneWidth/pitW);
				float b = (float) (1f - stoneHeight/pitH);
				
				if(!adjustForScaling){
				Ellipse2D.Double stoneEllipseBound = new Ellipse2D.Double(a*x*pitW, b*y*pitH, stoneWidth, stoneHeight);
				System.out.println("stoneEllipse "+a*x*pitW+","+ b*y*pitH+","+  stoneWidth+","+  stoneHeight);
				Ellipse2D.Double pitEllipseBound = new Ellipse2D.Double(0, 0, pitW*0.95, pitH*0.95);
				System.out.println("pitEllipse "+pitW+","+pitH);
				
				Area pitArea = new Area(pitEllipseBound);
				Area pendingStoneLocation = new Area(stoneEllipseBound);
				Area intersectionArea = (Area) pitArea.clone();
				intersectionArea.add(pendingStoneLocation);
				intersectionArea.subtract(pitArea);
				
					if(intersectionArea.isEmpty() ){
						locationFound = true;
						Point2D p = new Point2D.Float((float) (a*x), (float) (b*y));
						relativeStoneLocations.add(p);
						relativeStoneLocationsX.add(new Point2D.Float(x,y));
					}
				}
				
				else{
					System.out.println("a is "+a);
					locationFound = true;
					Point2D p = new Point2D.Float((float) (a*x), (float) (b*y));
					relativeStoneLocations.add(p);
					
					if(!adjustForScaling){
						relativeStoneLocationsX.add(new Point2D.Float(x,y));
					}
			
				}


				
			} while(!locationFound);
	
		}
	}

	/**
	 * Color panel Background
	 */
	@Override
	protected void drawBackground(Graphics2D g2) {
		g2.setColor(BACKGROUND_FILL_COLOR);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
}

