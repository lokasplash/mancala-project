import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;

import javax.swing.JPanel;

import javafx.geometry.Rectangle2D;

/**
 * Creates a Text Field stating player Turn and victor when game ends
 * @author Prem Panchal
 */
public class GameStatePanel extends JPanel {
	private String stateMessage = "Player 1's Turn";
	private Font font;
	private int fontSize = 14;
	private double msgLength = 0;

	/**
	 * Constructor, creates a Player Turn panel
	 */
	GameStatePanel(boolean isPink) {
		if(isPink) {this.setBackground(Color.ORANGE);}
		else {this.setBackground(Color.decode("#f45f42"));}
		font = new Font("SansSerif", Font.BOLD, fontSize);
		this.setPreferredSize(new Dimension(200, 150));
	}

	@Override
	/**
	 * Draws the string for which player's turn
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		
		
		if(this.getWidth() > msgLength){
			font = font.deriveFont((float) (this.getHeight()* 0.5 ));
		}

		
		FontRenderContext frc = g2.getFontRenderContext();
		java.awt.geom.Rectangle2D bounds = font.getStringBounds(stateMessage, frc);
		msgLength = bounds.getWidth();
		g2.setFont(font);
		
		double x = (getWidth() - bounds.getWidth()) / 2;
		double y = (getHeight() - bounds.getHeight()) / 2;
		g2.drawString(stateMessage, (int) x, (int) (y-bounds.getY()));
	}

	/**
	 * Set the player's turn
	 * @param boardData
	 */
	public void setState(BoardData boardData) {
		if (boardData.GAME_FINISHED) {
			String winner = (boardData.PLAYER_1_MANCALA > boardData.PLAYER_2_MANCALA) ? "Player 1" : "Player 2";
			stateMessage = winner + " wins!";
		} else {
			stateMessage = (boardData.PLAYER_1_TURN ? "Player 1's Turn" : "Player 2's turn");
		}
		this.repaint();
	}
}

