import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
		JPanel chooseStonesPanel = new JPanel();
		JTextArea question1 = new JTextArea();
		question1.setText("How Many Stones per pit?");
		chooseStonesPanel.add(question1);
		JButton threeStones = new JButton("3");
		JButton fourStones = new JButton("4");
		chooseStonesPanel.add(threeStones);
		threeStones.addActionListener(new SettingController.StartingStonesButtonListener(3));
		chooseStonesPanel.add(fourStones);
		fourStones.addActionListener(new SettingController.StartingStonesButtonListener(4));
		this.add(chooseStonesPanel);

		JPanel chooseColorPanel = new JPanel();
		JTextArea question2 = new JTextArea();
		question2.setText("What Color");
		JButton pinkButton = new JButton("Pink");
		JButton blueButton = new JButton("Blue");
		pinkButton.addActionListener(new SettingController.PitColorListener("pink"));
		blueButton.addActionListener(new SettingController.PitColorListener("blue"));
		chooseColorPanel.add(question2);
		chooseColorPanel.add(pinkButton);
		chooseColorPanel.add(blueButton);
		this.add(chooseColorPanel);

		JPanel chooseStoneTypePanel = new JPanel();
		JTextArea question3 = new JTextArea();
		question3.setText("Which Stone");
		JButton drawn = new JButton();
		drawn.setIcon(new DrawnStoneIcon(30));
		drawn.addActionListener(new SettingController.StoneIconListener("drawn"));
		JButton white = new JButton();
		white.setIcon(new WhiteStoneIcon(30));
		white.addActionListener(new SettingController.StoneIconListener("white"));
		JButton yellow = new JButton();
		yellow.setIcon(new YellowStoneIcon(30));
		yellow.addActionListener(new SettingController.StoneIconListener("yellow"));
		JButton coin = new JButton();
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
