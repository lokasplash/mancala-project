import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;


/**
 * Controller class for View.
 * <p>
 * Contains various listeners for buttons, PitPanels, and general components
 * <p>
 * Controller class needs to be given an instance of the GameModel to manipulate.
 * <pre>
 * 	Ex:
 * 	GameModel gameModel = new GameModel();
 * 	Controller.setGameModel(gameModel);
 *
 * 	JLabel label = new JLabel(...);
 * 	label.addMouseListener(new Controller.PitPanelListener(Side.P1, 1, panel));
 * 	...
 * </pre>
 * @author Vincent Diep
 */
public class Controller {

	private static GameModel gameModel;

	/**
	 * Sets gameModel to default gameModel settings
	 */
	public static void setDefaultGameModel() {
		gameModel = new GameModel();
	}
	
	public static void setGameModel(GameModel m) {
		gameModel = m;
	}

	public static GameModel getGameModel() {
		return gameModel;
	}

	/**
	 * Undo move when undo button clicked
	 */
	public static class UndoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				gameModel.undo();
			} catch (GameModel.EmptyHistoryException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (GameModel.MaxUndosReachedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Redo move when redo button clicked
	 */
	public static class RedoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent a) {
			try {
				gameModel.redo();
			} catch (GameModel.EmptyHistoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Quits game when quit button clicked
	 */
	public static class QuitListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}
	}

	/**
	 * Listens to mouse press and mouse motion inside of a PitPanel
	 * <p>
	 * If mouse is clicked inside the PitPanel's shape, do playerMove()
	 * When mouse is over a pit, displays a tooltip containing the number of stones in the pit
	 */
	public static class PitPanelListener extends MouseAdapter {
		/** The Side the PitPanel is on, default Side.P1 */
		Side side = Side.P1;

		/** The pit number of the PitPanel, default 0 */
		int pitNumber = 0;

		PitPanel pitPanel;

		/** Initializes PitPanelListener with a side, pit number, and the corresponding PitPanel */
		public PitPanelListener(Side side, int pitNumber, PitPanel p) {
			this.side = side;
			this.pitNumber = pitNumber;
			pitPanel = p;
		}

		/** If mouse is clicked inside the PitPanel's shape, do playerMove() */
		@Override
		public void mousePressed(MouseEvent e) {
			Point clickPoint = e.getPoint();
			System.out.println(clickPoint.getX() + "," + clickPoint.getY());
			boolean correctSide = side.isP1Side() == gameModel.getCurrentBoardData().PLAYER_1_TURN;
			if (correctSide) {
				if (pitPanel.getShape().contains(clickPoint)) {
					System.out.println("side[" + side + "], pit " + pitNumber + " clicked");
					try {
						gameModel.playerMove(pitNumber);
					} catch (GameModel.GameFinishedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} else {
				System.out.println("Player cannot click me, I belong to the opponent.");
			}

		}

		/** When mouse is over a pit, displays a tooltip containing the number of stones in the pit */
		@Override
		public void mouseEntered(MouseEvent e) {
			Point clickPoint = e.getPoint();
			System.out.println("Moved to " + clickPoint.getX() + "," + clickPoint.getY());
			System.out.println("side[" + side + "], pit " + pitNumber + " entered");
			int stoneCount;

			if (side == Side.P1) {
				stoneCount = gameModel.getCurrentBoardData().PLAYER_1_PITS[pitNumber];
			} else {
				stoneCount = gameModel.getCurrentBoardData().PLAYER_2_PITS[pitNumber];
			}
			pitPanel.setToolTipText(stoneCount + " stones");
			pitPanel.setMouseHover(true);
			pitPanel.repaint();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			pitPanel.setToolTipText(null);
			pitPanel.setMouseHover(false);
			pitPanel.repaint();
		}
	}

	/**
	 * A more generic version of PitPanelListener
	 * When mouse pressed, runs playerMove(side,index)
	 */
	public static class ComponentListener extends MouseAdapter {

		/** The Side of the component that is being listened to, default Side.P1 */
		Side side = Side.P1;

		/** The pit number of the component that is being listened to, default 0 */
		int index = 0;

		/**
		 * Initializes ComponentListener with a side, pit number
		 * @param side  Player side
		 * @param index Pit number
		 */
		public ComponentListener(Side side, int index) {
			this.side = side;
			this.index = index;
		}

		/**
		 * Runs playerMove(side,index) when mouse pressed
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			Point clickPoint = e.getPoint();
			System.out.println(clickPoint.getX() + "," + clickPoint.getY());

			System.out.println("side[" + side + "], panel " + index + " clicked");
			try {
				boolean correctSide = side.isP1Side() == gameModel.getCurrentBoardData().PLAYER_1_TURN;
				if (correctSide)
					gameModel.playerMove(index);
			} catch (GameModel.GameFinishedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

}
