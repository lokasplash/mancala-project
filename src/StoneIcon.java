import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Icon to display image for the stone; Can be drawn from graphics, or use image file
 * @author Vincent Diep
 *
 */
public abstract class StoneIcon implements Icon {
	int size;
	
	StoneIcon(int size){
		this.size = size;
	}

	@Override
	public int getIconHeight() {
		return size;
	}

	@Override
	public int getIconWidth() {
		return size;
	}


	
	public static class DrawnStoneIcon extends StoneIcon{

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
	
	public static class ImageStoneIcon extends StoneIcon{
		ImageIcon imageIcon;

		ImageStoneIcon(int size, String filename) {
			super(size);
			imageIcon = new ImageIcon(filename);
			
			Image image = imageIcon.getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT);
			imageIcon.setImage(image);
		}

		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
			imageIcon.paintIcon(c, g, x, y);
		}
		
	}

}
