//import org.junit.jupiter.api.Test;

import java.util.Arrays;

//import static org.junit.jupiter.api.Assertions.*;

import static org.junit.Assert.*;

import org.junit.Test;



/**
 * Tests for the BoardModel class.
 * @author Andrew Jong
 * @since 14 April 2017
 */
public class BoardModelTest {
	@Test
	void testPlayerMoveBadSideArgumentException() {
		BoardModel model = new BoardModel(6, 4);
		try {
			model.playerMove(2, 0);
		} catch (IllegalArgumentException e) {
			String expectedErrorMessage = "Please pass in BoardModel.SIDE1 (0) or BoardModel.SIDE2 (1). " +
					"Received " + 2;
			assertTrue(expectedErrorMessage.equals(e.getMessage()));
		}
	}

	@Test
	void testPlayerMoveBadIndexArgumentException() {
		BoardModel model = new BoardModel(6, 4);
		try {
			model.playerMove(BoardModel.SIDE2, 11);
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
		model.playerMove(BoardModel.SIDE1, 0);
		assertEquals(false, model.isPlayer1Turn());
	}

	@Test
	void testYesTurnToggleAfterLandInOpponentMancala() {
		BoardModel model = new BoardModel(6, 4);
		assertEquals(true, model.isPlayer1Turn());
		model.playerMove(BoardModel.SIDE2, 2);
		assertEquals(false, model.isPlayer1Turn());
	}

	@Test
	void testNoTurnToggleAfterLandInOwnMancala() {
		BoardModel model = new BoardModel(6, 4);
		assertEquals(true, model.isPlayer1Turn());
		model.playerMove(BoardModel.SIDE1, 2);
		assertEquals(true, model.isPlayer1Turn());
	}

	@Test
	void testCorrectStonePlacementP1LandInP1Mancala() {
		BoardModel model = new BoardModel(6, 4);
		model.playerMove(BoardModel.SIDE1, 2);

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
		model.playerMove(BoardModel.SIDE1, 3);

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
		model.playerMove(BoardModel.SIDE1, 4);
		model.playerMove(BoardModel.SIDE2, 0);
		model.playerMove(BoardModel.SIDE1, 0);

		int[] expectedP1Pits = {0, 5, 5, 5, 0, 5};
		assertTrue(Arrays.equals(expectedP1Pits, model.getPlayer1Pits()));

		int[] expectedP2Pits = {0, 0, 5, 5, 5, 5};
		assertTrue(Arrays.equals(expectedP2Pits, model.getPlayer2Pits()));

		assertEquals(8, model.getPlayer1Mancala());
		assertEquals(0, model.getPlayer2Mancala());
	}
}