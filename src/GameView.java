import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

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
		
		
		Rectangle b = this.getBounds();
		 System.out.println("orig gameview size is "+b.width+","+ b.height);
		
		
//		this.addComponentListener(new ComponentListener(){
//
//			@Override
//			public void componentHidden(ComponentEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void componentMoved(ComponentEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void componentResized(ComponentEvent ev) {
//				// TODO Auto-generated method stub
//				double h = ev.getComponent().getHeight()/470d;
//				double w = ev.getComponent().getWidth()/1000d;
//				
//				System.out.println("resized " + w +","+h);
//				
//				stoneIcon.width = (int) (stoneIcon.size *w);
//				stoneIcon.height = (int) (stoneIcon.size*h);
//				ev.getComponent().repaint();
//				
//			}
//
//			@Override
//			public void componentShown(ComponentEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//		});
		 
		 boardPanel.addComponentListener(new ComponentAdapter(){
				@Override
				public void componentResized(ComponentEvent ev) {

					Dimension s = boardPanel.getSize();
					
//					double wScale = s.getWidth()/boardPanel.baseDimension.getWidth();
//					double hScale = s.getHeight()/boardPanel.baseDimension.getHeight();
//					
//					
//					stoneIcon.rescale( wScale, hScale);
					
					double wScale = s.getWidth()/boardPanel.baseDimension.getWidth();
					double hScale = s.getHeight()/boardPanel.baseDimension.getHeight();
					
					
					stoneIcon.rescale( wScale, hScale);
					
					
//						width = newWidth;
//						height = newHeight;
//						
//						Image newImage= originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_FAST);
//						imageIcon.setImage(newImage);
		//	
//						oldComponentBounds = newComponentBounds;
//						imageIcon.setImage(newImage);
					
					boardPanel.repaint();
//					boardPanel.baseDimension = s;
				}
		 });
	}
}
