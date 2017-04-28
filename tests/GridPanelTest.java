import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class GridPanelTest {
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setSize(600, 600);
		GridPanel grid = new GridPanel();
		frame.add(grid);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
	}	

}
