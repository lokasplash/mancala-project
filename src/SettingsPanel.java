import java.awt.*;

import javax.swing.*;

/**
 * Panel to decide Settings of Game
 * @author Prem Panchal
 *
 */
public class SettingsPanel extends JPanel
{
	/**
	 * Creates Buttons and actionlisteners that mutate the gameModel reference.
	 */
	SettingsPanel(JFrame parentFrame) {
		this.setLayout(new GridLayout(0, 1));
		JPanel chooseStonesPanel = new JPanel();
		JTextArea question1 = new JTextArea();
		question1.setText("How Many Stones per pit?");
		chooseStonesPanel.add(question1);
		JToggleButton threeStones = new JToggleButton("3");
		JToggleButton fourStones = new JToggleButton("4");
		chooseStonesPanel.add(threeStones);
		threeStones.addActionListener(new SettingController.StartingStonesButtonListener(3));
		chooseStonesPanel.add(fourStones);
		fourStones.addActionListener(new SettingController.StartingStonesButtonListener(4));
		this.add(chooseStonesPanel);

		JPanel chooseColorPanel = new JPanel();
		JTextArea question2 = new JTextArea();
		question2.setText("What Color");
		JToggleButton pinkButton = new JToggleButton("Pink");
		JToggleButton blueButton = new JToggleButton("Blue");
		pinkButton.addActionListener(new SettingController.PitColorListener("pink"));
		blueButton.addActionListener(new SettingController.PitColorListener("blue"));
		chooseColorPanel.add(question2);
		chooseColorPanel.add(pinkButton);
		chooseColorPanel.add(blueButton);
		this.add(chooseColorPanel);

		JPanel chooseStoneTypePanel = new JPanel();
		JTextArea question3 = new JTextArea();
		question3.setText("Which Stone");
		JToggleButton drawn = new JToggleButton();
		drawn.setIcon(new DrawnStoneIcon(30));
		drawn.addActionListener(new SettingController.StoneIconListener("drawn"));
		JToggleButton white = new JToggleButton();
		white.setIcon(new WhiteStoneIcon(30));
		white.addActionListener(new SettingController.StoneIconListener("white"));
		JToggleButton yellow = new JToggleButton();
		yellow.setIcon(new YellowStoneIcon(30));
		yellow.addActionListener(new SettingController.StoneIconListener("yellow"));
		JToggleButton coin = new JToggleButton();
		coin.setIcon(new CoinIcon(30));
		coin.addActionListener(new SettingController.StoneIconListener("coin"));
		chooseStoneTypePanel.add(question3);
		chooseStoneTypePanel.add(drawn);
		chooseStoneTypePanel.add(white);
		chooseStoneTypePanel.add(yellow);
		chooseStoneTypePanel.add(coin);
		this.add(chooseStoneTypePanel);

		JButton start = new JButton("Start Game");
		this.add(start);
		start.addActionListener(new SettingController.StartGame(this, parentFrame));

	}
   
   
   
   @Override
	public Dimension getPreferredSize(){
		return new Dimension(600,200);
	}
}
