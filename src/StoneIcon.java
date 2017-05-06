import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Icon to display image for the stone; Can be drawn from graphics, or use image file
 * @author Vincent Diep
 *
 */
public abstract class StoneIcon implements Icon {
	int size;

	
	/**
	 * Constructor for StoneIcon
	 * @param size Size of the StoneIcon
	 */
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

		
	/**
	 * A StoneIcon that is drawn using Java's graphics
	 */
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
	
	/**
	 * A StoneIcon that displays an image
	 */
	public static class ImageStoneIcon extends StoneIcon{
		ImageIcon imageIcon;
		Image originalImage;
		Rectangle componentBounds = null;
		float ratioX;
		float ratioY;
		
		/**
		 * Constructor for an ImageStoneIcon
		 * @param size Size of the image <p> The image can be larger/smaller than the size since the image is rescaled.
		 * @param filename Location of image file
		 */
		ImageStoneIcon(int size, String filename) {
			super(size);
			
			imageIcon = new ImageIcon(filename);
			originalImage = imageIcon.getImage();
			
			ratioX = size/(float)imageIcon.getIconWidth();
			ratioY = size/ (float)imageIcon.getIconHeight();
			System.out.println(ratioX);
			
			Image image = originalImage.getScaledInstance(size, size, Image.SCALE_DEFAULT);
//			originalImage = originalImage.getScaledInstance(size, size, Image.SCALE_DEFAULT);
			imageIcon.setImage(image);
//			imageIcon.setImage(originalImage);
		}

		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
			Rectangle newBounds = c.getBounds();
			if(componentBounds == null || !(componentBounds.equals(newBounds))){
				
				int newWidth = (int) newBounds.getBounds().getWidth();
				int newHeight = (int) newBounds.getBounds().getHeight();
				
				System.out.println(c);
				System.out.println(newWidth);
				
				newWidth *= (float)size/100;
				newHeight *= (float)size/100;
				
//				int newSize = Math.min(newWidth, newHeight);
				Image newImage= originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_FAST);
//				Image newImage= originalImage.getScaledInstance(newSize, newSize, Image.SCALE_FAST);
				imageIcon.setImage(newImage);
				componentBounds = newBounds;
			}

			imageIcon.paintIcon(c, g, x, y);
		}
		
	}

}
