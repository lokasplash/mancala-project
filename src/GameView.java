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

		GridPanel gridPanel = new GridPanel();
		StoneIcon imageIcon = new StoneIcon.ImageStoneIcon(30,"images/white_stone.png");
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
//			mancalaPanelP1.setMancala(boardData.PLAYER_1_MANCALA);
//
//			PitPanel[] p1Pits = (PitPanel[]) Arrays.stream(boardData.PLAYER_1_PITS).mapToObj(PitPanel::new).toArray();
//			gridPanel.setP1Pits(p1Pits);
//			PitPanel[] p2Pits = (PitPanel[]) Arrays.stream(boardData.PLAYER_1_PITS).mapToObj(PitPanel::new).toArray();
//			gridPanel.setP2Pits(p2Pits);
////			 set the undo/redo buttons as available or not based on undo history
//			undoRedoPanel.setUndoButton(gameModel.canUndo());
//			undoRedoPanel.setRedoButton(gameModel.canRedo());

		});
	}
}
