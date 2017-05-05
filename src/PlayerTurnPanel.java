<<<<<<< Updated upstream
=======
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
>>>>>>> Stashed changes
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
/**
 * Creates a Text Field stating player Turn
 * @author Prem Panchal
 *
 */
public class PlayerTurnPanel extends JPanel {
	
	private GameModel gamemodel;
<<<<<<< Updated upstream
=======
	
	private String playerString = "Player ";
	private String player = "1";
	
	Font font;
	int fontSize = 20;
	
>>>>>>> Stashed changes
	/*
	 * @param m gamemodel that knows which player turn it is
	 */
	PlayerTurnPanel(GameModel m) {
		gamemodel = m;
<<<<<<< Updated upstream
=======
		

		this.setBackground(Color.ORANGE);

		font = new Font("SansSerif", Font.BOLD, fontSize);
		
		this.setPreferredSize(new Dimension(200,200));

>>>>>>> Stashed changes
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
<<<<<<< Updated upstream
		final JTextArea PlayerTurn = new JTextArea(400,50);
		ChangeListener listener = new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				if(gamemodel.getCurrentBoardData().PLAYER_1_TURN)
				{
					PlayerTurn.setText("Player 1 Turn");
				}
				else
				{
					PlayerTurn.setText("Player 2 Turn");
				}
			}
		};
		gamemodel.addChangeListener(listener);
=======
		font = font.deriveFont((float) (this.getWidth()*0.1));
		g2.setFont(font);
		g2.drawString(playerString+player, 0, 0+font.getSize());
>>>>>>> Stashed changes
	}

	
	/**
	 * 
	 * @param isPlayer1 If it is player 1's turn, pass in true.
	 * If it is player 2's turn, pass in false.
	 */
	public void setPlayerTurn(boolean isPlayer1) {
		// TODO Auto-generated method stub
		if (isPlayer1){
			player = "1";
		}
		else{
			player = "2's";
		}
		player += " Turn";
		this.repaint();
		}
}

