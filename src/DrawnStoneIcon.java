import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


/*
 *  A StoneIcon that is drawn using Java's graphics.
 * This does not resize but stays centered.
 */
public class DrawnStoneIcon extends StoneIcon{

	DrawnStoneIcon(int size) {
		super(size);
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g3 = (Graphics2D) g.create();
		
		JFrame f = (JFrame) SwingUtilities.getRoot(c);
		int newWidth = (int) (f.getWidth()* 0.03);
		int newHeight = (int) (f.getHeight() * 0.03);
		size  = Math.min(newWidth, newHeight);
		g3.setColor(Color.BLACK);
		g3.fillOval(x, y, size, size);
		g3.setColor(Color.BLUE);
		g3.fillOval(x+size/4, y+size/4, size/2, size/2);
		
	
		g3.dispose();
		
	}

	/**
	 * 
	 * @return an independent copy of the current DrawnStoneIcon as <b>Icon</b>
	 * Note that this returned icon does not resize.
	 */
	@Override
	public Icon getIcon() {
		return new Icon(){

			@Override
			public int getIconHeight() {
				// TODO Auto-generated method stub
				return size;
			}

			@Override
			public int getIconWidth() {
				// TODO Auto-generated method stub
				return size;
			}

			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
				Graphics2D g3 = (Graphics2D) g.create();
				g3.setColor(Color.BLACK);
				g3.fillOval(x, y, size, size);
				g3.setColor(Color.BLUE);
				g3.fillOval(x+size/4, y+size/4, size/2, size/2);
				
				
				g3.dispose();
			}
			
		};
	}
	
}