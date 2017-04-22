import javax.swing.*;
import java.awt.*;

/**
 * The main view for the Mancala Game.
 * @author Andrew Jong
 */
public class GameView {
	// Possibly add parameter of style
	GameView(GameModel gameModel) {
		JFrame frame = new JFrame("Mancala");
		frame.setLayout(new BorderLayout());

		// Player turn
		PlayerTurnPanel playerTurnPanel = new PlayerTurnPanel();
		frame.add(playerTurnPanel, BorderLayout.NORTH);

		// Board Panel
		BoardPanel boardPanel = new BoardPanel();

		frame.add(boardPanel, BorderLayout.CENTER);

		// Undo/redo controls
		UndoRedoPanel undoRedoPanel = new UndoRedoPanel();
		frame.add(undoRedoPanel, BorderLayout.SOUTH);

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);

		// the change listener
		gameModel.addChangeListener(changeEvent -> {
			BoardData boardData = gameModel.getCurrentBoardData();
			playerTurnPanel.setPlayerTurn(boardData.PLAYER_1_TURN);
			boardPanel.update(boardData);
			undoRedoPanel.update(boardData);
		});
	}
}
