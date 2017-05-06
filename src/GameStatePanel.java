import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * Creates a Text Field stating player Turn
 * @author Prem Panchal
 */
public class GameStatePanel extends JPanel {
	private String stateMessage = "state not set";
	private Font font;
	private int fontSize = 14;

	/**
	 * Constructor, creates a Player Turn panel
	 */
	GameStatePanel() {
		this.setBackground(Color.ORANGE);
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
		font = font.deriveFont((float) (this.getWidth() * 0.1));
		g2.setFont(font);
		g2.drawString(stateMessage, 10, font.getSize());
	}

	/**
	 * Set the player's turn
	 * @param boardData
	 */
	public void setState(BoardData boardData) {
		if (boardData.GAME_FINISHED) {
			stateMessage = "Game finished!";
			String winner = (boardData.PLAYER_1_MANCALA > boardData.PLAYER_2_MANCALA) ? "Player 1" : "Player 2";
			stateMessage += " " + winner + " wins";
		} else {
			stateMessage = (boardData.PLAYER_1_TURN ? "Player 1's Turn" : "Player 2's turn");
		}
		this.repaint();
	}
}

