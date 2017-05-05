import javax.swing.*;
import java.awt.*;

/**
 * Test the PitPanel
 * @author Andrew Jong
 */
class PitPanelTest {
	public static void main(String[] args) {
		JFrame testFrame = new JFrame("Pit Panel Tester");
		testFrame.setSize(400, 400);

		StoneIcon imageIcon = new StoneIcon.ImageStoneIcon(30,"images/white_stone.png");
		PitPanel panel = new PinkPitPanel(imageIcon, 4);
		panel.setSize(100,100);

		int randomAmount = (int) (Math.random() * 10);
		JLabel label = new JLabel(String.valueOf(randomAmount), JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.CENTER);
		label.setHorizontalAlignment(JLabel.CENTER);

		Font f = new Font("serif", Font.PLAIN, (int) ((int) (panel.getHeight()) * 0.6));
		label.setFont(f);

		panel.add(label);

		testFrame.add(panel);

		testFrame.pack();
		testFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		testFrame.setVisible(true);
	}
}