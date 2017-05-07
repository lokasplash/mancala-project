import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartingStonesTest {
	public static void main(String[] args)
	{
		GameModel game = new GameModel();
		Controller.setDefaultGameModel();
		JFrame frame = new JFrame();
		frame.setSize(800, 600);
		
		JPanel stones = new SettingsPanel();
		frame.add(stones);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	
		
	}

}
