import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Controller class for View with nested classes that extend ActionListener. Controller class has an instance of the
 * GameModel to manipulate.
 *
 * @author Vincent Diep
 */
public class Controller {

	private static GameModel gameModel;

	public static void setGameModel(GameModel g) {
		gameModel = g;
	}

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

	public static class RedoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				gameModel.redo();
			} catch (GameModel.EmptyHistoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static class QuitListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}
	}

	public static class PitPanelListener extends MouseAdapter {
		Side side = Side.P1;
		int pitNumber = 0;
		PitPanel pitPanel;

		public PitPanelListener(Side side, int pitNumber, PitPanel p) {
			this.side = side;
			this.pitNumber = pitNumber;
			pitPanel = p;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			Point clickPoint = e.getPoint();
			System.out.println(clickPoint.getX() + "," + clickPoint.getY());
			if (pitPanel.getShape().contains(clickPoint)) { // this if statement is irrelevant, just for testing
				System.out.println("side[" + side + "], pit " + pitNumber + " clicked");
				try {
					gameModel.playerMove(side, pitNumber);
				} catch (GameModel.GameFinishedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	public static class ComponentListener extends MouseAdapter {

		Side side = Side.P1;
		int index = 0;

		public ComponentListener(Side side, int index) {
			this.side = side;
			this.index = index;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			Point clickPoint = e.getPoint();
			System.out.println(clickPoint.getX() + "," + clickPoint.getY());

			System.out.println("side[" + side + "], panel " + index + " clicked");
			try {
				gameModel.playerMove(side, index);
			} catch (GameModel.GameFinishedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

}
