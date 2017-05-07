import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;


/**
 * Controller class for View.
 * <p>
 * Contains various listeners for buttons, PitPanels, and general components
 * <p>
 * Controller class needs to be given an instance of the GameModel to manipulate.
 * <pre>
 * 	Ex:
 * 	GameModel gameModel = new GameModel();
 * 	Controller.setGameModel(gameModel);
 *
 * 	JLabel label = new JLabel(...);
 * 	label.addMouseListener(new Controller.PitPanelListener(Side.P1, 1, panel));
 * 	...
 * </pre>
 * @author Vincent Diep, Prem Panchal
 */
public class SettingController {

	private static int startingStones;
	private static boolean isPink;
	private static StoneIcon icon;
	private static GameView gameview;
	private static GameModel gamemodel;
	
	public static GameView getGameView() {
		return gameview;
	}
	
	public static GameModel getGameModel() {
		return gamemodel;
	}


	/**
	 * StartingStonesButtonListener is created with the number of stones each pit in the GameModel
	 * should have.
	 * <p> When its corresponding button is clicked, it will call gameModel.setStones(...)
	 * with the appropriate value
	 */
	public static class StartingStonesButtonListener implements ActionListener {
			int stones;
		StartingStonesButtonListener(int s) {
			stones = s;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			startingStones = stones;
			System.out.println(startingStones);
		}

	}
	
	public static class PitColorListener implements ActionListener {
		boolean pink;
		PitColorListener(String s) {
			if (s.compareTo("pink") == 0) {pink = true;}
			else {pink = false;}
		}
		
		@Override
		public void actionPerformed(ActionEvent ev) {
			isPink = pink;
			System.out.println(isPink);
		}
	}
	
	public static class StoneIconListener implements ActionListener {
		StoneIcon i;
		StoneIconListener(String s) {
			if (s.compareTo("drawn") == 0) { i = new DrawnStoneIcon(30);}
			else if(s.compareTo("white")==0) {i = new WhiteStoneIcon(30);}
			else if(s.compareTo("yellow")==0) {i = new YellowStoneIcon(30);}
			else {i = new CoinIcon(30);}
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			icon=i;
			System.out.println(icon.getClass());
			
		}
		
	}
	
	public static class StartGame implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			gamemodel = new GameModel(startingStones);
			gameview = new GameView(gamemodel, isPink, icon);
			Controller.setGameModel(gamemodel);
		}
		
	}

}
