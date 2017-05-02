import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PlayerTurnPanel extends JPanel {
	
	private GameModel gamemodel;
	PlayerTurnPanel(GameModel m) {
		gamemodel = m;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
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
	}

}
