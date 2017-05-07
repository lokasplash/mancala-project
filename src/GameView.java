import javax.swing.*;
import java.awt.*;

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
		GameStatePanel gameStatePanel = new GameStatePanel();
		this.add(gameStatePanel, BorderLayout.NORTH);

		// Board Panel

		BoardPanel boardPanel = new BoardPanel();
		
		StoneIcon imageIcon = new StoneIcon.ImageStoneIcon(30,"images/white_stone.png");
		GridPanel gridPanel = new GridPanel(imageIcon, gameModel.PITS_PER_SIDE, gameModel.STARTING_STONES_PER_PIT);
		gridPanel.setState(gameModel.getCurrentBoardData());
		PitPanel mancalaPanelP1 = new PinkPitPanel(imageIcon); // TODO: Implement a MancalaPitPanel that's rounded rect
		mancalaPanelP1.setEnabled(false);
		mancalaPanelP1.setStones(gameModel.getCurrentBoardData().PLAYER_1_MANCALA);
		PitPanel mancalaPanelP2 = new PinkPitPanel(imageIcon);
		mancalaPanelP2.setEnabled(false);
		mancalaPanelP2.setStones(gameModel.getCurrentBoardData().PLAYER_2_MANCALA);

		boardPanel.addLeft(mancalaPanelP1);
		boardPanel.addCenter(gridPanel);
		boardPanel.addRight(mancalaPanelP2);

		this.add(boardPanel, BorderLayout.CENTER);


		// Undo/redo controls
		UndoRedoPanel undoRedoPanel = new UndoRedoPanel();
		this.add(undoRedoPanel, BorderLayout.SOUTH);

//		// the change listener. TODO: Currently all the methods in each panel are unimplemented.
		gameModel.addChangeListener(changeEvent -> {
			BoardData boardData = gameModel.getCurrentBoardData();
			// set the correct turn
			gameStatePanel.setState(boardData);
			// set the board view

			mancalaPanelP1.setStones(boardData.PLAYER_1_MANCALA);
			mancalaPanelP2.setStones(boardData.PLAYER_2_MANCALA);

			gridPanel.setState(boardData);

//			 set the undo/redo buttons as available or not based on undo history
			undoRedoPanel.setCanUndo(gameModel.canUndo());
			undoRedoPanel.setUndosLeft(gameModel.MAX_UNDOS_PER_TURN - gameModel.getNumUndosFromCurrentTurn());
			undoRedoPanel.setCanRedo(gameModel.canRedo());
			undoRedoPanel.setRedosLeft(gameModel.getRedoStackSize());

		});
	}
}
