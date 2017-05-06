import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class GridPanelTest {
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setSize(600, 200);
		StoneIcon imageIcon = new StoneIcon.ImageStoneIcon(30,"images/white_stone.png");
		GridPanel grid = new GridPanel(imageIcon);
		frame.add(grid);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
	}	

}
