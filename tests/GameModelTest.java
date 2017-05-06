import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by andrew on 4/15/17.
 */
class GameModelTest {
	@Test
	void testCorrectState1Undo() throws GameModel.EmptyHistoryException, GameModel.MaxUndosReachedException, GameModel.GameFinishedException {
		GameModel model = new GameModel();
		model.playerMove(Side.P1, 4);
		model.undo();

		BoardModel expectedStart = new BoardModel(6, 4);
		BoardData expectedBoardData = new BoardData(expectedStart);
		assertTrue(expectedBoardData.equals(model.getCurrentBoardData()));
	}

	@Test
	void testMaxUndosReachedException() throws Exception {
		GameModel model = new GameModel();
		int maxUndos = model.MAX_UNDOS_PER_TURN;

		boolean caughtSuccessfully = false;

		model.playerMove(Side.P1, 0);
		try {
			model.undo();
		} catch (GameModel.EmptyHistoryException e) {
			e.printStackTrace();
		} catch (GameModel.MaxUndosReachedException e) {
			throw e;
		}
		model.playerMove(Side.P1, 0);
		try {
			model.undo();
		} catch (GameModel.EmptyHistoryException e) {
			e.printStackTrace();
		} catch (GameModel.MaxUndosReachedException e) {
			throw e;
		}
		model.playerMove(Side.P1, 0);
		try {
			model.undo();
		} catch (GameModel.EmptyHistoryException e) {
			e.printStackTrace();
		} catch (GameModel.MaxUndosReachedException e) {
			throw e;
		}
		model.playerMove(Side.P1, 0);
		try {
			model.undo();
		} catch (GameModel.EmptyHistoryException e) {
			e.printStackTrace();
		} catch (GameModel.MaxUndosReachedException e) {
			caughtSuccessfully = true;
		}
		if (!caughtSuccessfully) throw new Exception("MaxUndosReachedException failed to throw");
	}
}