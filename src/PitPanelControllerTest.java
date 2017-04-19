import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

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
		
		
		label.addMouseListener(new Controller.PitListener(0, 1, panel));
		
		

		panel.add(label);
		System.out.println(label.getParent().getClass().getName());
		
		panel.addMouseListener(new Controller.PanelListener(0, 1));
		panel2.addMouseListener(new Controller.PitListener(0, 2, panel2));
		
		panel3.addMouseListener(new Controller.ShapeListener(1, 1, panel3.getShape()));

		testFrame.add(panel);
		testFrame.add(panel2);
		testFrame.add(panel3);

		testFrame.pack();
		testFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		testFrame.setVisible(true);
	}
}
