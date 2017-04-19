import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;


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
	
	
	
	public static class PitPanelListener extends MouseAdapter {

		int side = 0;
		int index = 0;
		PitPanel pitPanel;
		
		public PitPanelListener(int side, int index, PitPanel p){
			this.side = side;
			this.index = index;
			pitPanel = p;
		}
		
		@Override
		public void mousePressed(MouseEvent e){
			Point clickPoint = e.getPoint();
			System.out.println(clickPoint.getX() +"," + clickPoint.getY());
			if(pitPanel.contains(clickPoint)){ // this if statement is irrelevant, just for testing
				System.out.println("side["+side+"], pit "+index+ " clicked");
				try {
					gameModel.playerMove(side, index);
				} catch (GameModel.GameFinishedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	
		public static class ComponentListener extends MouseAdapter {

			int side = 0;
			int index = 0;
			
			public ComponentListener(int side, int index){
				this.side = side;
				this.index = index;
			}
			
			@Override
			public void mousePressed(MouseEvent e){
				Point clickPoint = e.getPoint();
				System.out.println(clickPoint.getX() +"," + clickPoint.getY());
				
					System.out.println("side["+side+"], panel "+index+ " clicked");
					try {
						gameModel.playerMove(side, index);
					} catch (GameModel.GameFinishedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}

		}
		
		public static class PanelShapeListener extends MouseAdapter {

			int side = 0;
			int index = 0;
			Shape s;
			
			public PanelShapeListener(int side, int index, Shape s){
				this.side = side;
				this.index = index;
				this.s = s;
			}
			
			@Override
			public void mousePressed(MouseEvent e){
				Point clickPoint = e.getPoint();
				System.out.println(clickPoint.getX() +"," + clickPoint.getY());
				
				if(s.contains(clickPoint)){
				
					System.out.println(s+" was clicked");
				}
			}
		}
		
		
}
