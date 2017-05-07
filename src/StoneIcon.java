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
 * Can be drawn from graphics, or use image file
 * It automatically resizes based on its container.
 * @author Vincent Diep
 *
 */
public abstract class StoneIcon implements Icon {
	int size;
	int width;
	int height;

	
	/**
	 * Constructor for StoneIcon
	 * @param size This determines the base size of the StoneIcon.
	 */
	StoneIcon(int size){
		this.size = size;
		width = size;
		height = size;
	}


	@Override
	public int getIconHeight() {
		return height;
	}


	@Override
	public int getIconWidth() {
		return width;
	}

		
	/**
	 * A StoneIcon that is drawn using Java's graphics.
	 * This does not resize but stays centered.
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
	 * It automatically resizes itself according to its container.
	 */
	public static class ImageStoneIcon extends StoneIcon{
		ImageIcon imageIcon;
		Image originalImage;
		Rectangle oldComponentBounds = null;

		
		/**
		 * Constructor for an ImageStoneIcon
		 * @param size Base size <p> The file source image can be larger/smaller than size, since the image is rescaled.
		 * @param filename Location of image file
		 */
		ImageStoneIcon(int size, String filename) {
			super(size);
			
			imageIcon = new ImageIcon(filename);
			originalImage = imageIcon.getImage();
			

			Image image = originalImage.getScaledInstance(size, size, Image.SCALE_DEFAULT);
			imageIcon.setImage(image);
		}

		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
			
			Rectangle newComponentBounds = c.getBounds();
			if(oldComponentBounds == null || !(oldComponentBounds.equals(newComponentBounds))){
				
				int newWidth = (int) newComponentBounds.getBounds().getWidth();
				int newHeight = (int) newComponentBounds.getBounds().getHeight();
				
				
//				if(c instanceof PitPanel){
//					System.out.println("is PPanel");
//					PitPanel pitPanel = (PitPanel) c;
//					double ratio = pitPanel.getStoneRatio();
//					newWidth *= ratio;
//					newHeight *= ratio;
//				}
				
				// adjust according to base size
				newWidth *= (float)size/100;
				newHeight *= (float)size/100;
				
				width = newWidth;
				height = newHeight;
				
				Image newImage= originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_FAST);
				imageIcon.setImage(newImage);
				

				
				oldComponentBounds = newComponentBounds;
//				System.out.println(c);
//				System.out.println("Size of icon " + width+","+height);
				imageIcon.setImage(newImage);
				c.repaint();
			}

//			System.out.println("Paint at " + x +"," + y);
			
			imageIcon.paintIcon(c, g, x, y);
		}
		
	}

}
