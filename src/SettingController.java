import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;


/**
 * Controller class for Settings.
 * <p>
 * Contains various listeners for each setting
 * 	JLabel label = new JLabel(...);
 * 	label.addMouseListener(new Controller.PitPanelListener(Side.P1, 1, panel));
 * 	...
 * </pre>
 * @author Prem Panchal, Andrew Jong
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
	 * Before the Gamemodel is created, it determines number of starting stones in pits
	 * <p> When its corresponding button is clicked, it will edit startingStones with the new Value
	 * 
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
	
	/**
	 * Determines what color Pitpanels will be used
	 * <p> When the button is clicked, it changes the boolean value that determines which pitpanel is used
	 *
	 */
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
	
	/**
	 * Starts the game and creates the Gamemodel and Game View
	 * <p> Button is clicked and fields are created
	 *
	 */
	public static class StartGame implements ActionListener {

		private SettingsPanel settingsPanel;
		private JFrame frameToSet;

		public StartGame(SettingsPanel settingsPanel, JFrame frameToSet) {
			this.settingsPanel = settingsPanel;

			this.frameToSet = frameToSet;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			gamemodel = new GameModel(startingStones);
			Controller.setGameModel(gamemodel);
			gameview = new GameView(gamemodel, isPink, icon);
			settingsPanel.setVisible(false);
			frameToSet.add(gameview);
		}
		
	}

}
