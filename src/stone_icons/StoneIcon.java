package stone_icons;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Can be drawn from graphics, or use image file
 * <p>
 * <s>It automatically resizes based on the frame.</s>
 * To resize, use {@link #rescale(double, double)}
 * <p>
 * For get an independent copy, use {@link #getIcon()}
 * @author Vincent Diep
 *
 */
public abstract class StoneIcon implements Icon {
	protected int size;
	protected int width;
	protected int height;
	
	private double scaleX;
	private double scaleY;
	
	/**
	 * Constructor for StoneIcon
	 * @param size This determines the base size of the StoneIcon.
	 */
	StoneIcon(int size){
		this.size = size;
		width = size;
		height = size;
		setScaleX(1);
		setScaleY(1);
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
	 * 
	 * @return an independent copy of the current StoneIcon as <b>Icon</b>
	 * <p>
	 * <s>Note: this means that the returned copy will not resize.</s>
	 */
	public abstract Icon getIcon();
	
	public void rescale(double wScale, double hScale){
		setScaleX(wScale);
		setScaleY(hScale);
	}
	
	public double getScaleX() {
		return scaleX;
	}

	public void setScaleX(double scaleX) {
		this.scaleX = scaleX;
	}

	public double getScaleY() {
		return scaleY;
	}

	public void setScaleY(double scaleY) {
		this.scaleY = scaleY;
	}

	/**
	 * A StoneIcon that displays an image
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
		public ImageStoneIcon(int size, String filename) {
			super(size);	
			imageIcon = new ImageIcon(filename);
			originalImage = imageIcon.getImage();

			Image image = originalImage.getScaledInstance(size, size, Image.SCALE_DEFAULT);
			imageIcon.setImage(image);
		}

		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
			imageIcon.paintIcon(c, g, x, y);
		}
		
		/**
		 * 
		 * @return an independent copy of the current ImageStoneIcon as <b>ImageIcon</b>
		 * 
		 */
		@Override
		public Icon getIcon() {
			return new ImageIcon(originalImage.getScaledInstance(width, height, Image.SCALE_FAST));
		}
		
		@Override
		public void rescale(double wScale, double hScale){
			super.rescale(wScale, hScale);
				Image newImage= originalImage.getScaledInstance( (int) (width*getScaleX()), (int) (height*getScaleY()), Image.SCALE_FAST);
				imageIcon.setImage(newImage);
		}
		
	}

}
