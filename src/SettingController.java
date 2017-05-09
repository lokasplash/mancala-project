import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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

	private static int startingStones = 4;
	private static boolean isPink = true;
	private static StoneIcon icon;
	private static GameView gameview;
	private static GameModel gamemodel;


	/**
	 *
	 * Handlings toggling off of non-selected butons.
	 * @param e the event source
	 * @param buttons buttons to toggle off
	 */
	private static void toggleButtons(ActionEvent e, JToggleButton[] buttons) {
		AbstractButton abstractButton = (AbstractButton) e.getSource();
		boolean selected = !abstractButton.getModel().isSelected();
		for (JToggleButton b : buttons) {
			b.setSelected(selected);
		}
	}


	/**
	 * Before the Gamemodel is created, it determines number of starting stones in pits
	 * <p> When its corresponding button is clicked, it will edit startingStones with the new Value
	 * 
	 */
	public static class StartingStonesButtonListener implements ActionListener {
		private int stones;
		private JToggleButton[] buttons;

		StartingStonesButtonListener(int s, JToggleButton... buttons) {
			stones = s;
			this.buttons = buttons;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			startingStones = stones;
			System.out.println(startingStones);
			toggleButtons(e, buttons);
		}
	}
	
	/**
	 * Determines what color Pitpanels will be used
	 * <p> When the button is clicked, it changes the boolean value that determines which pitpanel is used
	 *
	 */
	public static class PitColorListener implements ActionListener {
		boolean pink;
		private JToggleButton[] buttons;

		PitColorListener(String s, JToggleButton... buttons) {
			this.buttons = buttons;
			if (s.compareTo("pink") == 0) {pink = true;}
			else {pink = false;}
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			isPink = pink;
			System.out.println(isPink);
			toggleButtons(e, buttons);
		}
	}

	/**
	 * Determines what Icon is being implemented for the Mancala
	 * <p>When Button is clicked, the style of stoneIcon being implemented is changed
	 *
	 */
	public static class StoneIconListener implements ActionListener {
		StoneIcon i;
		private JToggleButton[] buttons;

		StoneIconListener(String s, JToggleButton... buttons) {
			this.buttons = buttons;
			if (s.compareTo("drawn") == 0) { i = new DrawnStoneIcon(30);}
			else if(s.compareTo("white")==0) {i = new WhiteStoneIcon(30);}
			else if(s.compareTo("yellow")==0) {i = new YellowStoneIcon(30);}
			else {i = new CoinIcon(30);}
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			icon=i;
			System.out.println(icon.getClass());
			toggleButtons(e, buttons);
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
			frameToSet.setVisible(false);
			frameToSet.add(gameview);
			frameToSet.setVisible(true);
		}
		
	}

}
