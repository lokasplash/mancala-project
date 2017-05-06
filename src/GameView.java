import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * The main view for the Mancala Game. Note many of these methods and classes don't exist yet, as they will be
 * implemented later.
 * @author Andrew Jong
 */
public class GameView extends JPanel {
	// Possibly add parameter of style
	GameView(GameModel gameModel) {

		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(1000,470));
		// Player turn
		PlayerTurnPanel playerTurnPanel = new PlayerTurnPanel(gameModel);
		this.add(playerTurnPanel, BorderLayout.NORTH);

		BoardPanel boardPanel = new BoardPanel();

		StoneIcon imageIcon = new StoneIcon.ImageStoneIcon(30,"images/white_stone.png");
		GridPanel gridPanel = new GridPanel(imageIcon, gameModel.PITS_PER_SIDE, gameModel.STARTING_STONES_PER_PIT);
		PitPanel mancalaPanelP1 = new PinkPitPanel(imageIcon); // TODO: Implement a MancalaPitPanel that's rounded rect
		mancalaPanelP1.setNumStones(gameModel.getCurrentBoardData().PLAYER_1_MANCALA);
		PitPanel mancalaPanelP2 = new PinkPitPanel(imageIcon);
		mancalaPanelP2.setNumStones(gameModel.getCurrentBoardData().PLAYER_2_MANCALA);

		boardPanel.left.add(mancalaPanelP2);
		boardPanel.center.add(gridPanel);
		boardPanel.right.add(mancalaPanelP1);

		this.add(boardPanel, BorderLayout.CENTER);
//		this.add(bp, BorderLayout.CENTER);

		// Undo/redo controls
		UndoRedoPanel undoRedoPanel = new UndoRedoPanel();
		this.add(undoRedoPanel, BorderLayout.SOUTH);

//		// the change listener. TODO: Currently all the methods in each panel are unimplemented.
		gameModel.addChangeListener(changeEvent -> {
			BoardData boardData = gameModel.getCurrentBoardData();
			// set the correct turn
			playerTurnPanel.setPlayerTurn(boardData.PLAYER_1_TURN);
			// set the board view
			mancalaPanelP1.setNumStones(boardData.PLAYER_1_MANCALA);

			gridPanel.setP1Pits(boardData.PLAYER_1_PITS);
			gridPanel.setP2Pits(boardData.PLAYER_2_PITS);
//			 set the undo/redo buttons as available or not based on undo history
			undoRedoPanel.setUndoButton(gameModel.canUndo());
			undoRedoPanel.setRedoButton(gameModel.canRedo());

		});
	}
}
