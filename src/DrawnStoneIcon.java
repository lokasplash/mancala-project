import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
	 * A StoneIcon that is drawn using Java's graphics
	 */
	public class DrawnStoneIcon extends StoneIcon{

		DrawnStoneIcon(int size) {
			super(size);
		}
	
		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
			Graphics2D g3 = (Graphics2D) g.create();
			
			g3.fillOval(x, y, size, size);
			g3.setColor(Color.BLUE);
			g3.fillOval(x+size/4, y+size/4, size/2, size/2);
			
			g3.setColor(Color.BLACK);
			g3.dispose();
			
		}
		
	}