import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import GameModel.GameFinishedException;


/**
 * 
 * Controller class for View with nested classes that extend ActionListener. Controller class has an instance of the GameModel to manipulate.
 *
 */
public class Controller {

	private static GameModel gameModel;
	
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

	
	public static void setGameModel(GameModel g){
		gameModel = g;
	}
	
	
	public static class UndoListener implements ActionListener{

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
	
	public static class QuitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public static class PitListener implements ActionListener{

		int side = 0;
		int index = 0;
		
		public PitListener(int side, int index){
			this.side = side;
			this.index = index;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				gameModel.playerMove(side, index);
			} catch (GameModel.GameFinishedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
}
