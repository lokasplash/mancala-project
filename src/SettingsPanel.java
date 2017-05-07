import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Panel to decide How many stones the game starts with
 * @author Prem Panchal
 *
 */
public class SettingsPanel extends JPanel
{

	/**
	 * Creates Buttons and actionlisteners that mutate the gameModel reference.
	 */
   SettingsPanel(){
	   JPanel StoneNumber = new JPanel();
	   JTextArea question1 = new JTextArea();
	   question1.setText("How Many Stones per pit?");
	   StoneNumber.add(question1);
	   JButton threeStones = new JButton("3");
	   JButton fourStones = new JButton("4");
	   StoneNumber.add(threeStones);
	   threeStones.addActionListener(new SettingController.StartingStonesButtonListener(3));
	   StoneNumber.add(fourStones);
	   fourStones.addActionListener(new SettingController.StartingStonesButtonListener(4));
	   this.add(StoneNumber, BorderLayout.NORTH);
	   
	   JPanel PitColor = new JPanel();
	   JTextArea question2 = new JTextArea();
	   question2.setText("What Color");
	   JButton pinkButton = new JButton("Pink");
	   JButton blueButton = new JButton("Blue");
	   pinkButton.addActionListener(new SettingController.PitColorListener("pink"));
	   blueButton.addActionListener(new SettingController.PitColorListener("blue"));
	   PitColor.add(question2);
	   PitColor.add(pinkButton);
	   PitColor.add(blueButton);
	   this.add(PitColor, BorderLayout.CENTER);
	   
	   JPanel StoneIcon = new JPanel();
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
	   StoneIcon.add(question3);
	   StoneIcon.add(drawn);
	   StoneIcon.add(white);
	   StoneIcon.add(yellow);
	   StoneIcon.add(coin);
	   this.add(StoneIcon, BorderLayout.SOUTH);
	   
	   JButton start = new JButton("Start Game");
	   this.add(start);
	   start.addActionListener(new SettingController.StartGame());

   }
   
   
   
   @Override
	public Dimension getPreferredSize(){
		return new Dimension(600,200);
	}
}
