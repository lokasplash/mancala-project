import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Creates a White Stone Icon style for the Game
 * @author Prem Panchal, Vincent Diep
 *
 */
public class WhiteStoneIcon extends StoneIcon.ImageStoneIcon {
	
	/**
	 * Constructor for an ImageStoneIcon
	 * @param size Size of the image <p> The image can be larger/smaller than the size since the image is rescaled.
	 * @param filename Location of image file
	 */
	WhiteStoneIcon(int size) {
		super(size, "images/white_stone.png");

	}

	
}

