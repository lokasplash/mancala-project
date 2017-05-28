import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import stone_icons.StoneIcon;
import stone_icons.YellowStoneIcon;


public class BoardPanelTest {
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setSize(500, 500);
//		frame.setLayout(new FlowLayout());
		
		
		
//		BoardPanel boardPanel = new BoardPanel();
//		
//		// left side mancala
//		JPanel mancalaLeft = new JPanel();
//		mancalaLeft.setPreferredSize(new Dimension(200,400));
//		mancalaLeft.setBackground(java.awt.Color.GREEN);
//		boardPanel.addLeft(mancalaLeft);
//		
////		PitPanel panel = new PitPanel();
////		panel.setSize(100,100);
////		boardPanel.left.add(panel);
//		
//		// right side mancala
//		JPanel mancalaRight = new JPanel();
//		mancalaRight.setPreferredSize(new Dimension(200,400));
//		mancalaRight.setBackground(java.awt.Color.GREEN);
//		boardPanel.addRight(mancalaRight);
//		
//		
//		// board center
//		JPanel boardCenter = new JPanel();
//		boardCenter.setPreferredSize(new Dimension(600,400));
//		boardCenter.setBackground(java.awt.Color.PINK);
//		boardPanel.addCenter(boardCenter);
//		
//		
//		frame.add(boardPanel);
//		frame.pack();
//		frame.setVisible(true);
		
		
		
		StoneIcon stoneIcon = new YellowStoneIcon(30);
		GameModel gameModel = new GameModel(4);
		boolean isPinkPitPanel = true;
		
		BoardPanel boardPanel = new BoardPanel();

		GridPanel gridPanel = new GridPanel(stoneIcon, gameModel.PITS_PER_SIDE, gameModel.STARTING_STONES_PER_PIT, isPinkPitPanel);
		gridPanel.setState(gameModel.getCurrentBoardData());

		PitPanel mancalaPanelP1 = isPinkPitPanel ? new PinkPitPanel(stoneIcon) : new BluePitPanel(stoneIcon);
		mancalaPanelP1.setEnabled(false);
		mancalaPanelP1.setStones(1);

		PitPanel mancalaPanelP2 =  isPinkPitPanel ? new PinkPitPanel(stoneIcon) : new BluePitPanel(stoneIcon);
		mancalaPanelP2.setEnabled(false);
		mancalaPanelP2.setStones(4);

		boardPanel.addLeft(mancalaPanelP1);
		boardPanel.addCenter(gridPanel);
		boardPanel.addRight(mancalaPanelP2);

		boardPanel.addComponentListener(new ComponentListener(){

			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentResized(ComponentEvent ev) {

				Dimension s = boardPanel.getSize();
				
//				double wScale = s.getWidth()/boardPanel.baseDimension.getWidth();
//				double hScale = s.getHeight()/boardPanel.baseDimension.getHeight();
//				
//				
//				stoneIcon.rescale( wScale, hScale);
				
				double wScale = s.getWidth()/boardPanel.baseDimension.getWidth();
				double hScale = s.getHeight()/boardPanel.baseDimension.getHeight();
				
				
				stoneIcon.rescale( wScale, hScale);
				
				
//					width = newWidth;
//					height = newHeight;
//					
//					Image newImage= originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_FAST);
//					imageIcon.setImage(newImage);
	//	
//					oldComponentBounds = newComponentBounds;
//					imageIcon.setImage(newImage);
				
				frame.repaint();
//				boardPanel.baseDimension = s;
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		frame.add(boardPanel, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}
}
