package stone_icons;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


/**
 * A StoneIcon that is drawn using Java's graphics.
 */
public class DrawnStoneIcon extends StoneIcon{

	public DrawnStoneIcon(int size) {
		super(size);
	}

	/**
	 * Base Icon image is a black circle with a blue center, subject to scaleX and scaleY factors
	 */
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g3 = (Graphics2D) g.create();
		
		g3.setColor(Color.BLACK);
		int width = (int) (size* getScaleX());
		int height = (int) (size*getScaleY());
		g3.fillOval(x, y, width, height );
		g3.setColor(Color.BLUE);
		g3.fillOval(x+width/4, y+height/4, width/2, height/2);
		
	
		g3.dispose();
		
	}

	/**
	 * 
	 * @return an independent copy of the current DrawnStoneIcon as <b>Icon</b>
	 * 
	 */
	@Override
	public Icon getIcon() {
		return new Icon(){

			@Override
			public int getIconHeight() {
				return size;
			}

			@Override
			public int getIconWidth() {
				return size;
			}

			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
				Graphics2D g3 = (Graphics2D) g.create();
				g3.setColor(Color.BLACK);
				int width = (int) (size* getScaleX());
				int height = (int) (size*getScaleY());
				g3.fillOval(x, y, width, height );
				g3.setColor(Color.BLUE);
				g3.fillOval(x+width/4, y+height/4, width/2, height/2);
				
			
				g3.dispose();
			}
			
		};
	}
	
}