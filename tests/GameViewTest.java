

public class GameViewTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GameModel gameModel = new GameModel();
		Controller.setGameModel(gameModel);
		
		GameView view = new GameView(gameModel);
		
		try {
			gameModel.playerMove(Side.P1, 0);
		} catch (GameModel.GameFinishedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
