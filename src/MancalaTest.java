/**
 * Class with the main method to start the program.
 * @author Andrew Jong
 */
public class MancalaTest {
	public static void main(String[] args) {
		GameModel model = new GameModel();
		GameView view = new GameView(model);
	}
}
