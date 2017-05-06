import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the BoardModel class.
 * @author Andrew Jong
 * @since 14 April 2017
 */
public class BoardModelTest {
	@Test
	void testPlayerMoveBadIndexArgumentException() {
		BoardModel model = new BoardModel(6, 4);
		try {
			model.playerMove(11);
		} catch (IllegalArgumentException e) {
			String expectedErrorMessage = "Requested index out of board bounds, must be between 0 and 5. Received " +
					11;
			assertTrue(expectedErrorMessage.equals(e.getMessage()));
		}
	}

	@Test
	void testYesTurnToggleAfterLandInCentralGrid() {
		BoardModel model = new BoardModel(6, 4);
		assertEquals(true, model.isPlayer1Turn());
		model.playerMove(0);
		assertEquals(false, model.isPlayer1Turn());
	}

	@Test
	void testNoContinueTurnWhenLandInOpponentMancala() {
		BoardModel model = new BoardModel(6, 8);
		assertEquals(true, model.isPlayer1Turn());
		model.playerMove(5);
		assertEquals(false, model.isPlayer1Turn());
	}

	@Test
	void testYesContinueTurnWhenLandInOwnMancala() {
		BoardModel model = new BoardModel(6, 4);
		assertEquals(true, model.isPlayer1Turn());
		model.playerMove(2);
		assertEquals(true, model.isPlayer1Turn());
	}

	@Test
	void testCorrectStonePlacementP1LandInP1Mancala() {
		BoardModel model = new BoardModel(6, 4);
		model.playerMove(2);

		int[] expectedP1Pits = {4, 4, 0, 5, 5, 5};
		assertTrue(Arrays.equals(expectedP1Pits, model.getPlayer1Pits()));

		int[] expectedP2Pits = {4, 4, 4, 4, 4, 4};
		assertTrue(Arrays.equals(expectedP2Pits, model.getPlayer2Pits()));

		int amountInMancala = model.getPlayer1Mancala();
		assertEquals(1, amountInMancala);
	}

	@Test
	void testCorrectStonePlacementP1MoveToP2Side() {
		BoardModel model = new BoardModel(6, 4);
		model.playerMove(3);

		int[] expectedP1Pits = {4, 4, 4, 0, 5, 5};
		assertTrue(Arrays.equals(expectedP1Pits, model.getPlayer1Pits()));

		int[] expectedP2Pits = {5, 4, 4, 4, 4, 4};
		assertTrue(Arrays.equals(expectedP2Pits, model.getPlayer2Pits()));

		int amountInMancala = model.getPlayer1Mancala();
		assertEquals(1, amountInMancala);
	}

	@Test
	void testStoneCaptureP1CaptureP2() {
		BoardModel model = new BoardModel(6, 4);
		model.playerMove(4);
		model.playerMove(0);
		model.playerMove(0);

		int[] expectedP1Pits = {0, 5, 5, 5, 0, 5};
		assertTrue(Arrays.equals(expectedP1Pits, model.getPlayer1Pits()));

		int[] expectedP2Pits = {0, 0, 5, 5, 5, 5};
		assertTrue(Arrays.equals(expectedP2Pits, model.getPlayer2Pits()));

		assertEquals(8, model.getPlayer1Mancala());
		assertEquals(0, model.getPlayer2Mancala());
	}
}