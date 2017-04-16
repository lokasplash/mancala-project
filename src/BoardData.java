import java.util.Arrays;

/**
 * A class that represents the data of the game board, with no functions to manipulate the board. All the
 * attributes of BoardData are immutable. Effectively this class is only for retrieving data.
 *
 * An object of this class should be constructed via the data from a BoardModel object.
 *
 * @author Andrew Jong
 */
public class BoardData {

	/* Pits for each player */
	public final int[] PLAYER_1_PITS;
	public final int[] PLAYER_2_PITS;

	/* Player pits, also directly tied to score, initial value is 0 */
	public final int PLAYER_1_MANCALA;
	public final int PLAYER_2_MANCALA;

	/* Keep track of whose turn it is to determine which pits/mancala to place stones in */
	public final boolean PLAYER_1_TURN;

	/**
	 * Constructor for BoardData that represents the data of a BoardModel. Uses the information of a
	 * BoardModel object.
	 * @param boardModel the BoardModel object to copy data from.
	 */
	public BoardData(BoardModel boardModel) {
		this.PLAYER_1_PITS = boardModel.getPlayer1Pits().clone();
		this.PLAYER_2_PITS = boardModel.getPlayer2Pits().clone();
		this.PLAYER_1_MANCALA = boardModel.getPlayer1Mancala();
		this.PLAYER_2_MANCALA = boardModel.getPlayer2Mancala();
		this.PLAYER_1_TURN = boardModel.isPlayer1Turn();
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		BoardData boardData = (BoardData) o;

		if (PLAYER_1_MANCALA != boardData.PLAYER_1_MANCALA) return false;
		if (PLAYER_2_MANCALA != boardData.PLAYER_2_MANCALA) return false;
		if (PLAYER_1_TURN != boardData.PLAYER_1_TURN) return false;
		if (!Arrays.equals(PLAYER_1_PITS, boardData.PLAYER_1_PITS)) return false;
		return Arrays.equals(PLAYER_2_PITS, boardData.PLAYER_2_PITS);
	}

	@Override
	public int hashCode() {
		int result = Arrays.hashCode(PLAYER_1_PITS);
		result = 31 * result + Arrays.hashCode(PLAYER_2_PITS);
		result = 31 * result + PLAYER_1_MANCALA;
		result = 31 * result + PLAYER_2_MANCALA;
		result = 31 * result + (PLAYER_1_TURN ? 1 : 0);
		return result;
	}
}
