import javax.swing.*;
import java.awt.*;

/**
 * @author Vincient Diep
 */
class ControllerTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameModel gameModel = new GameModel();
		Controller.setGameModel(gameModel);

		// set up undo button
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		JButton undoButton = new JButton("undo");
		undoButton.addActionListener(new Controller.UndoListener());
		frame.add(undoButton);

		// set up redo button
		JButton redoButton = new JButton("redo");
		redoButton.addActionListener(new Controller.RedoListener());
		frame.add(redoButton);

		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}