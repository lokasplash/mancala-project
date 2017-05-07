import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class YellowStoneIcon extends StoneIcon.ImageStoneIcon {
ImageIcon imageIcon;
	
	/**
	 * Constructor for an ImageStoneIcon
	 * @param size Size of the image <p> The image can be larger/smaller than the size since the image is rescaled.
	 * @param filename Location of image file
	 */
	YellowStoneIcon(int size) {
		super(size, null);
		imageIcon = new ImageIcon("images/ballYellow_09.png");
		
		Image image = imageIcon.getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT);
		imageIcon.setImage(image);
	}

	@Override
	public ImageIcon getImageIcon()
	{
		return imageIcon;
	}
}