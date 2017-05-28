package stone_icons;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;


/**
 * Creates a Yellow Stone Icon for the game
 * @author Prem Panchal, Vincent Diep
 *
 */
public class YellowStoneIcon extends StoneIcon.ImageStoneIcon {
	
	/**
	 * Constructor for an ImageStoneIcon
	 * @param size Size of the image <p> The image can be larger/smaller than the size since the image is rescaled.
	 */
	public YellowStoneIcon(int size) {
		super(size, "images/ballYellow_09.png");
	}

}
