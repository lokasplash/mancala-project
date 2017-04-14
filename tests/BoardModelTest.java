import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by andrew on 4/13/17.
 */
class BoardModelTest {
	@Test
	void testPlayerMove() {
		BoardModel model = new BoardModel();
		model.playerMove(BoardModel.SIDE1, 1);
		assertEquals(4, model);
		int amountInMancala = model.getPlayer1Mancala();
		assertEquals(1, amountInMancala);
	}


}