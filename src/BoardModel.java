import java.util.Arrays;

/**
 * The BoardModel represents the internal game state of the board and allows manipulation of the board via a player
 * move.
 *
 * @author Andrew Jong
 * @since 14 April 2017
 */
public class BoardModel {
	/* === STATIC CONSTANTS === */

	/** Specifier denoting the pits on Player 1's side of the board. Meant to be used as the first argument for the
	 * playerMove() method. */
	public static final int SIDE1 = 0;
	/** Specifier denoting the pits on Player 2's side of the board. Meant to be used as the first argument for the
	 * playerMove() method. */
	public static final int SIDE2 = 1;

	/* === ATTRIBUTES === */

	/* Number of pits on each player's side, should be consistent across all instances of BoardModel */
	private static int pitsPerSide = 6;

	/* Number of stones to initialize in each pit, default value is 4, should be consistent across all instances
	of BoardModel */
	private static int startingStonesPerPit = 4;

	/* Pits for each player */
	private int[] player1Pits = new int[pitsPerSide];
	private int[] player2Pits = new int[pitsPerSide];

	/* Player pits, also directly tied to score, initial value is 0 */
	private int player1Mancala = 0;
	private int player2Mancala = 0;

	/* Keep track of whose turn it is to determine which pits/mancala to place stones in */
	private boolean player1Turn = true;

	/* === METHODS === */

	/**
	 * Construct a BoardModel with the default values of pits per side (6) and starting stones per pit (4). The default
	 * values cannot be updated after object construction.
	 */
	public BoardModel() {
		initializePits();
	}

	/**
	 * Construct a BoardModel with the starting values.
	 * @param startingStonesPerPit number of starting stones in each pit.
	 */
	public BoardModel(int startingStonesPerPit) {
		BoardModel.startingStonesPerPit = startingStonesPerPit;
		initializePits();
	}
	/**
	 * Construct a BoardModel with the starting values.
	 * @param pitsPerSide number of pits per each player's side.
	 * @param startingStonesPerPit number of starting stones in each pit.
	 */
	public BoardModel(int pitsPerSide, int startingStonesPerPit) {
		BoardModel.pitsPerSide = pitsPerSide;
		BoardModel.startingStonesPerPit = startingStonesPerPit;
		initializePits();
	}

	/**
	 * Iniitizlie the player pits with the starting number of stones per pit.
	 */
	private void initializePits() {
		Arrays.fill(player1Pits, startingStonesPerPit);
		Arrays.fill(player2Pits, startingStonesPerPit);
	}

	/**
	 * Process a move for the current player. The player is determined via the board's internal state.
	 * @param side the side to start on, either BoardModel.SIDE1 or BoardModel.SIDE2
	 * @param index the index of the pit, from 0 to the number of pits per side (obtainable via the method
	 *                 getPitsPerSide()).
	 */
	public void playerMove(int side, int index){
		// Argument checking. side must have value 0 or 1.
		if (side != 0 && side != 1) {
			throw new IllegalArgumentException("Please pass in BoardModel.SIDE1 (0) or BoardModel.SIDE2 (1). " +
					"Received" + side);
		}
		// index must be within board bounds.
		if (index < 0 || index >= pitsPerSide) {
			throw new IllegalArgumentException("Requested index out of board bounds, must be between 0 and " +
					(pitsPerSide - 1) + ". Received " + index);
		}

		// Determine which side and which pit the stones will be picked from.
		boolean onSide1;
		int numStones;
		if (side == SIDE1) {
			onSide1 = true;
			// Pick up the stones from side 1
			numStones = player1Pits[index];
			player1Pits[index] = 0;
		} else {
			onSide1 = false;
			// Pick up the stones from side 1
			numStones = player2Pits[index];
			player2Pits[index] = 0;
		}
		// The pit to place stones in, starts at the pit after the index.
		int pitToPlace = index + 1;
		// Distribute the stones around the board
		for (; numStones > 0; numStones--) {
			// If no more pits on this side, put in mancala and toggle the state of onSide1.
			if (pitToPlace >= pitsPerSide) {
				// Add to stone to the mancala
				if (onSide1) player1Mancala++;
				else player1Mancala++;

				// Change side, -1 because will increment to 0 at end of loop.
				pitToPlace = -1;
				onSide1 = !onSide1;
			}
			// Otherwise add stones normally
			else {
				if (onSide1) player1Pits[pitToPlace] += 1;
				else player2Pits[pitToPlace] += 1;
			}
			// Move to the next pit
			pitToPlace++;
		}
		// Toggle player turn
		player1Turn = !player1Turn;
	}

	/**
	 * Determine whether all the pits on the board (excluding mancalas) are empty of stones.
	 * @return true if stones empty, false if has stones
	 */
	public boolean allPitsEmpty() {
		for (int i : player1Pits) if (i!=0) return false;
		for (int i : player2Pits) if (i!=0) return false;
		return true;
	}

	/* === GETTERS AND SETTERS === */

	/**
	 * Get the number of regular pits (not including mancalas) per each player's side of the board.
	 * @return number of pits per side.
	 */
	public int getPitsPerSide() {
		return pitsPerSide;
	}

	/**
	 * Get the number of starting stones in each pit.
	 * @return number of starting stones
	 */
	public int getStartingStonesPerPit() {
		return startingStonesPerPit;
	}

	/**
	 * Get the array representing the stones on player 1's side of the board
	 * @return array of pits
	 */
	public int[] getPlayer1Pits() {
		return player1Pits;
	}

	/**
	 * Get the array representing the stones on player 2's side of the board
	 * @return array of pits
	 */
	public int[] getPlayer2Pits() {
		return player2Pits;
	}

	/**
	 * Get the number of stones in player 1's mancala. Also represents player 1's score.
	 * @return number stones in mancala
	 */
	public int getPlayer1Mancala() {
		return player1Mancala;
	}

	/**
	 * Get the number of stones in player 2's mancala. Also represents player 2's score.
	 * @return number stones in mancala
	 */
	public int getPlayer2Mancala() {
		return player2Mancala;
	}

	/**
	 * Get if the current turn is player 1's turn.
	 * @return true if player 1 turn, false if player 2 turn
	 */
	public boolean isPlayer1Turn() {
		return player1Turn;
	}
}
