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
public class PlayerTurnPanel extends JPanel {
	private String player = "1";
	private Font font;
	private int fontSize = 20;

	/**
	 * Constructor, creates a Player Turn panel
	 */
	PlayerTurnPanel() {
		this.setBackground(Color.ORANGE);
		font = new Font("SansSerif", Font.BOLD, fontSize);
		this.setPreferredSize(new Dimension(200, 200));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		font = font.deriveFont((float) (this.getWidth() * 0.1));
		g2.setFont(font);
		g2.drawString("Player " + player + "'s Turn", 0, font.getSize());
	}

	/**
	 * Set the player's turn
	 * @param isPlayer1 If it is player 1's turn, pass in true. If it is player 2's turn, pass in false.
	 */
	public void setPlayerTurn(boolean isPlayer1) {
		// TODO Auto-generated method stub
		if (isPlayer1) {
			player = "1";
		} else {
			player = "2";
		}
		this.repaint();
	}
}

