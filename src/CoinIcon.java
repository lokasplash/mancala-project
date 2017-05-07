import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;


/**
 * Creates a StoneIcon using the coin image
 * @author Prem Panchal, Vincent Diep
 *
 */
public class CoinIcon extends StoneIcon.ImageStoneIcon{
	
	/**
	 * Constructor for an ImageStoneIcon
	 * @param size Size of the image <p> The image can be larger/smaller than the size since the image is rescaled.
	 * @param filename Location of image file
	 */
	CoinIcon(int size) {
		super(size, "images/coin_20.png");
	}
}
