import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import stone_icons.DrawnStoneIcon;
import stone_icons.StoneIcon;

/**
 * 
 * @author Vincent Diep
 *
 */

public class StoneIconTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();

		frame.setLayout(new GridLayout(2,3));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(200, 200);
		
		StoneIcon drawnIcon = new DrawnStoneIcon(30);
		JLabel label = new JLabel(drawnIcon);
		frame.add(label);
		
		JLabel label2 = new JLabel(drawnIcon);
		
		frame.add(label2);
		
		StoneIcon imageIcon = new StoneIcon.ImageStoneIcon(30,"images/white_stone.png");
		//https://images.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.luckybowler.com%2Fimg%2Fassets%2Fproducts%2Fbrunswick-white-ball.png&f=1
		
		JLabel label3 = new JLabel(imageIcon);
		label3.setPreferredSize(new Dimension(30,30));
		frame.add(label3);
		
		JLabel label5 = new JLabel(new StoneIcon.ImageStoneIcon(30, "images/ballYellow_09.png"));
		frame.add(label5);
		
		JLabel label6 = new JLabel(new StoneIcon.ImageStoneIcon(50, "images/coin_20.png"));
		frame.add(label6);
		
		
		JLabel label7 = new JLabel(new StoneIcon.ImageStoneIcon(100, "images/ballYellow_09.png"));
		frame.add(label7);
		
//		frame.pack();
		frame.setVisible(true);

	}
}
