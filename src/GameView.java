import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

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
		frame.setSize(new Dimension(1000, 600));

		// Player turn
		PlayerTurnPanel playerTurnPanel = new PlayerTurnPanel(gameModel);
		frame.add(playerTurnPanel, BorderLayout.NORTH);

		// Board Panel
		BoardPanel boardPanel = new BoardPanel();
		
		/* Temporary placeholder */
			// left side mancala
				JPanel mancalaLeft = new JPanel();
				mancalaLeft.setPreferredSize(new Dimension(200,400));
				mancalaLeft.setBackground(java.awt.Color.GREEN);
				boardPanel.left.add(mancalaLeft);
				
				// right side mancala
				JPanel mancalaRight = new JPanel();
				mancalaRight.setPreferredSize(new Dimension(200,400));
				mancalaRight.setBackground(java.awt.Color.GREEN);
				boardPanel.right.add(mancalaRight);
				
				
				// board center
				JPanel boardCenter = new JPanel();
				boardCenter.setPreferredSize(new Dimension(600,400));
				boardCenter.setBackground(java.awt.Color.PINK);
				boardPanel.center.add(boardCenter);
		/* End temp placeholder */
//
//		GridPanel gridPanel = new GridPanel();
//		MancalaPanel mancalaPanelP1 = new MancalaPanel();
//		
//		
//		MancalaPanel mancalaPanelP2 = new MancalaPanel();
//		boardPanel.left = mancalaPanelP2;
//		boardPanel.center = gridPanel;
//		boardPanel.right = mancalaPanelP1;



		frame.add(boardPanel, BorderLayout.CENTER);

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
			// set the board view
//			mancalaPanelP1.setMancala(boardData.PLAYER_1_MANCALA);

//			PitPanel[] p1Pits = (PitPanel[]) Arrays.stream(boardData.PLAYER_1_PITS).mapToObj(PitPanel::new).toArray();
//			gridPanel.setP1Pits(p1Pits);
//			PitPanel[] p2Pits = (PitPanel[]) Arrays.stream(boardData.PLAYER_1_PITS).mapToObj(PitPanel::new).toArray();
//			gridPanel.setP2Pits(p2Pits);
			// set the undo/redo buttons as available or not based on undo history
			undoRedoPanel.setUndoButton(gameModel.canUndo());
			undoRedoPanel.setRedoButton(gameModel.canRedo());

		});
	}
}
