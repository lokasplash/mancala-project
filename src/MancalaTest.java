import javax.swing.*;
import java.awt.*;

/**
 * Class with the main method to start the program.
 * @author Andrew Jong
 */
public class MancalaTest {
	public static void main(String[] args) {
		GameModel model = new GameModel();

		JFrame frame = new JFrame("Mancala");
		frame.setSize(new Dimension(1000, 600));
		GameView view = new GameView(model);

//		frame.pack();

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
