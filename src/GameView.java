import javax.swing.*;
import java.awt.*;

/**
 * The main view for the Mancala Game. Contains everything for a playable game of Mancala
 * @author Andrew Jong
 */
public class GameView extends JPanel {
	/**
	 * Constructor that creates default values of pink pit panel and white image stone icon.
	 * @param gameModel the game model this view is observing.
	 */
	GameView(GameModel gameModel) {
		this(gameModel, true, new StoneIcon.ImageStoneIcon(20, "images/white_stone.png"));
	}


	/**
	 * Constructor to create a GameView with specifications.
	 * @param gameModel the game model this view is observing.
	 * @param isPinkPitPanel whether the panel is pink (if false, then blue)
	 * @param stoneIcon the stone icon style
	 */
	GameView(GameModel gameModel, boolean isPinkPitPanel, StoneIcon stoneIcon) {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(1000,470));

		/* Setup the game state message */
		GameStatePanel gameStatePanel = new GameStatePanel(isPinkPitPanel);
		this.add(gameStatePanel, BorderLayout.NORTH);

		/* Setup the board */
		BoardPanel boardPanel = new BoardPanel();

		GridPanel gridPanel = new GridPanel(stoneIcon, gameModel.PITS_PER_SIDE, gameModel.STARTING_STONES_PER_PIT, isPinkPitPanel);
		gridPanel.setState(gameModel.getCurrentBoardData());

		PitPanel mancalaPanelP1 = isPinkPitPanel ? new PinkPitPanel(stoneIcon) : new BluePitPanel(stoneIcon);
		mancalaPanelP1.setEnabled(false);
		mancalaPanelP1.setStones(gameModel.getCurrentBoardData().PLAYER_1_MANCALA);

		PitPanel mancalaPanelP2 =  isPinkPitPanel ? new PinkPitPanel(stoneIcon) : new BluePitPanel(stoneIcon);
		mancalaPanelP2.setEnabled(false);
		mancalaPanelP2.setStones(gameModel.getCurrentBoardData().PLAYER_2_MANCALA);

		boardPanel.addLeft(mancalaPanelP1);
		boardPanel.addCenter(gridPanel);
		boardPanel.addRight(mancalaPanelP2);

		this.add(boardPanel, BorderLayout.CENTER);
		Font font = new Font("SansSerif", Font.BOLD, 48);
		JLabel P1MancalaCount = new JLabel();
		P1MancalaCount.setFont(font);
		P1MancalaCount.setText(String.valueOf(gameModel.getCurrentBoardData().PLAYER_1_MANCALA));
		this.add(P1MancalaCount, BorderLayout.WEST);
		JLabel P2MancalaCount = new JLabel();
		P2MancalaCount.setFont(font);
		P2MancalaCount.setText(String.valueOf(gameModel.getCurrentBoardData().PLAYER_2_MANCALA));
		this.add(P2MancalaCount, BorderLayout.EAST);

		/* Undo and Redo controls */
		UndoRedoPanel undoRedoPanel = new UndoRedoPanel();
		this.add(undoRedoPanel, BorderLayout.SOUTH);

		/* Change listener, attach to model */
		gameModel.addChangeListener(changeEvent -> {
			BoardData boardData = gameModel.getCurrentBoardData();
			// set the correct turn
			gameStatePanel.setState(boardData);
			// set the board view

			mancalaPanelP1.setStones(boardData.PLAYER_1_MANCALA);
			mancalaPanelP2.setStones(boardData.PLAYER_2_MANCALA);
			P1MancalaCount.setText(String.valueOf(boardData.PLAYER_1_MANCALA));
			P2MancalaCount.setText(String.valueOf(boardData.PLAYER_2_MANCALA));

			gridPanel.setState(boardData);

//			 set the undo/redo buttons as available or not based on undo history
			undoRedoPanel.setCanUndo(gameModel.canUndo());
			undoRedoPanel.setUndosLeft(gameModel.MAX_UNDOS_PER_TURN - gameModel.getNumUndosFromCurrentTurn());
			undoRedoPanel.setCanRedo(gameModel.canRedo());
			undoRedoPanel.setRedosLeft(gameModel.getRedoStackSize());

		});
	}
}
