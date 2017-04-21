import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 * 
 * In this demo, side 0 is the top row
 * pits start at 1, going left to right
 * 
 * side[0], panel 1 clicked is printed when that pitpanel is clicked
 * side[0], pit 1 clicked is printed when the label is clicked
 * 
 * side[0], pit 2 clicked is printed when the pitpanel is clicked
 * 
 * for side[1], pit 1
 * 	the click point is printed
 * 	additionally, the ellipse object is printed when the user clicks inside the ellipse
 * 
 */
public class PitPanelControllerTest {
	
	/* Most code from PitPanel.java main() */
	public static void main(String[] args) {
		GameModel gameModel = new GameModel();
		Controller.setGameModel(gameModel);
		
		
		JFrame testFrame = new JFrame("Pit Panel Tester");
		testFrame.setLayout(new GridLayout(2,6));
		testFrame.setSize(400, 400);

		PitPanel panel = new PitPanel();
		panel.setSize(100,100);
		
		PitPanel panel2 = new PitPanel();
		panel2.setSize(100,100);
		
		PitPanel panel3 = new PitPanel();
		panel3.setSize(100,100);

		int randomAmount = (int) (Math.random() * 10);
		JLabel label = new JLabel(String.valueOf(randomAmount), JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.CENTER);
		label.setHorizontalAlignment(JLabel.CENTER);

		Font f = new Font("serif", Font.PLAIN, (int) ((int) (panel.getHeight()) * 0.6));
		label.setFont(f);
		
		
//		label.addMouseListener(new Controller.PitPanelListener(0, 1, panel.getShape()));
		
		

		panel.add(label);
		System.out.println(label.getParent().getClass().getName());
		
		panel.addMouseListener(new Controller.PitPanelListener(0, 1, panel.getShape()));
		panel2.addMouseListener(new Controller.PitPanelListener(0, 2, panel2.getShape()));
		
		panel3.addMouseListener(new Controller.PitPanelListener(1, 1, panel3.getShape()));

		testFrame.add(panel);
		testFrame.add(panel2);
		testFrame.add(panel3);

		testFrame.pack();
		testFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		testFrame.setVisible(true);
	}
}
