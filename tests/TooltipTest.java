import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 * 
 * In this demo, tooltips should appear for each pit, revealing the number of stones in the pit.
 * In particular, the first (upper left corner) pit might not show a tooltip.
 * That is due to its label being on top of the pit and not having a listener attached.
 * 
 */
public class TooltipTest {
	
	/* Most code from PitPanel.java main() */
	public static void main(String[] args) {
		GameModel gameModel = new GameModel();
		Controller.setDefaultGameModel();
		
		
		JFrame testFrame = new JFrame("Tooltip Tester");
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
		
		
		label.addMouseListener(new Controller.PitPanelListener(Side.P1, 1, panel));
		
		panel.add(label);
		System.out.println(label.getParent().getClass().getName());
		
		panel.addMouseListener(new Controller.ComponentListener(Side.P1, 1));
		panel.addMouseMotionListener(new Controller.PitPanelListener(Side.P1, 1, panel));
		
		panel2.addMouseListener(new Controller.PitPanelListener(Side.P1, 2, panel2));
		panel2.addMouseMotionListener(new Controller.PitPanelListener(Side.P1, 2, panel2));
		
		
		
		panel3.addMouseListener(new Controller.PitPanelListener(Side.P2, 1, panel3));
		panel3.addMouseMotionListener(new Controller.PitPanelListener(Side.P2, 1, panel3));

		testFrame.add(panel);
		testFrame.add(panel2);
		testFrame.add(panel3);

		
		
		testFrame.pack();
		testFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		testFrame.setVisible(true);
	}
}
