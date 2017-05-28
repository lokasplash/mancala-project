import javax.swing.*;

import stone_icons.StoneIcon;

/**
 * Test the PitPanel
 * @author Andrew Jong
 */
class PitPanelTest {
	public static void main(String[] args) {
		testPink();
		testBlue();
	}

	static void testPink() {
		JFrame testFrame = new JFrame("Pit Panel Tester");
		testFrame.setSize(400, 400);

		StoneIcon imageIcon = new StoneIcon.ImageStoneIcon(30, "images/white_stone.png");
		PitPanel panel = new PinkPitPanel(imageIcon);
		panel.setStones(400);
		panel.setSize(100,100);

		testFrame.add(panel);

		testFrame.pack();
		testFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		testFrame.setVisible(true);
	}

	static void testBlue() {
		JFrame testFrame2 = new JFrame("Pit Panel Tester");
		testFrame2.setSize(400, 400);

		StoneIcon imageIcon2 = new StoneIcon.ImageStoneIcon(10, "images/coin_20.png");
		PitPanel panel2 = new BluePitPanel(imageIcon2, 400);
		panel2.setSize(100, 100);
//
		testFrame2.add(panel2);

		testFrame2.pack();
		testFrame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		testFrame2.setVisible(true);
	}

}