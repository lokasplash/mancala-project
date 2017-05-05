import javax.swing.*;
import java.awt.*;

/**
 * The main view for the Mancala Game. Note many of these methods and classes don't exist yet, as they will be
 * implemented later.
 * @author Andrew Jong
 */
public class GameView {
	// Possibly add parameter of style
	GameView(GameModel gameModel) {
		JFrame frame = new JFrame("Mancala");
		frame.setLayout(new BorderLayout());

		// Player turn
		PlayerTurnPanel playerTurnPanel = new PlayerTurnPanel(gameModel);
		frame.add(playerTurnPanel, BorderLayout.NORTH);

		// Board Panel
//		BoardPanel boardPanel = new BoardPanel();
//
//		GridPanel gridPanel = new GridPanel();
//		MancalaPanel mancalaPanelP1 = new MancalaPanel();
//		
//		
//		MancalaPanel mancalaPanelP2 = new MancalaPanel();
//		boardPanel.left = mancalaPanelP2;
//		boardPanel.center = gridPanel;
//		boardPanel.right = mancalaPanelP1;


//		frame.add(boardPanel, BorderLayout.CENTER);

		// Undo/redo controls
		UndoRedoPanel undoRedoPanel = new UndoRedoPanel();
		frame.add(undoRedoPanel, BorderLayout.SOUTH);
		frame.pack();
		
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
//
//		// the change listener. TODO: Currently all the methods in each panel are unimplemented.
		gameModel.addChangeListener(changeEvent -> {
			BoardData boardData = gameModel.getCurrentBoardData();
			// set the correct turn
			playerTurnPanel.setPlayerTurn(boardData.PLAYER_1_TURN);
//			// set the board view
//			mancalaPanelP1.setMancala(boardData.PLAYER_1_MANCALA);
////			gridPanel.setP1Pits(boardData.PLAYER_1_PITS);
////			gridPanel.setP2Pits(boardData.PLAYER_2_PITS);
////			// set the undo/redo buttons as available or not based on undo history
////			undoRedoPanel.setUndoButton(gameModel.canUndo());
////			undoRedoPanel.setRedoPanel(gameModel.canRedo());
		});
	}
}
