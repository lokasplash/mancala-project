import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * The GameModel represents handles the game loop and state. It also handles updates to its internal state
 * and notifies its ChangeListeners of changes.
 *
 * @author Andrew Jong
 * @since 14 April 2017
 */
public class GameModel {

	/* === CONSTANTS === */

	private final int maxUndosPerGame = 3;
	private final int maxUndosPerTurn = 1;

	/* === ATTRIBUTES === */

	private Stack<BoardModel> undoHistory = new Stack<>();
	private Stack<BoardModel> redoHistory = new Stack<>();
	private BoardModel currentBoard;

	/* Keep track of the amount of undos taken per player */
	private int player1Undos = 0;
	private int player2Undos = 0;

	/* Determine if the game is finished */
	private boolean gameFinished = false;

	/* List of change Listeners for views */
	private List<ChangeListener> changeListeners = new LinkedList<>();


	/* === METHODS=== */

	/**
	 * Empty constructor that initializes the current board to a new BoardModel with default values.
	 */
	public GameModel() {
		currentBoard = new BoardModel();
	}

	/**
	 * Constructor to attach a single ChangeListener
	 * @param listener the ChangeListener
	 */
	public GameModel(ChangeListener listener) {
		this();
		addChangeListener(listener);
	}

	/**
	 * Constructor to attach several ChangeListeners as a list
	 * @param changeListeners the list of ChangeListeners
	 */
	public GameModel(List<ChangeListener> changeListeners) {
		this();
		this.changeListeners = changeListeners;
	}

	/**
	 * The main game loop.
	 */
	public void run() {
		// TODO: Possibility - welcome menu?

		while (!gameFinished) {
			// TODO: implement game running logic via Controllers

			currentBoard.playerMove(BoardModel.SIDE1, 4); // Example update board call

			// Example undo
			try {
				undo();
			} catch (EmptyHistoryException e) {
				// There is no more history on the stack
				System.out.println(e.getMessage());
			} catch (MaxUndosReachedException e) {
				// the player has reached the max amount of undos
				System.out.println(e.getMessage());
			}
			// Example redo
			try {
				redo();
			} catch (EmptyHistoryException e) {
				System.out.println(e.getMessage());
			}

			// Update all the attached change listeners
			updateListeners();

			// Determine if the game is finished by checking if all the pits are empty
			gameFinished = currentBoard.allPitsEmpty();
		}

		// TODO: Some ending stuff with the view saying the game is done and displaying the winner

	}

	/**
	 * A static nested class for the undo() and redo() methods if there is nothing left in the history.
	 */
	private static class EmptyHistoryException extends Exception {
		EmptyHistoryException(String message) {
			super(message);
		}
	}

	/**
	 * A static nested class for the undo() method if the player has reached the maximum number of undos.
	 */
	private static class MaxUndosReachedException extends Exception {
		MaxUndosReachedException(String message) {
			super(message);
		}
	}

	/**
	 * Undo by popping the undo history stack to replace the curent board.
	 * @throws EmptyHistoryException undo history is empty
	 */
	public void undo() throws EmptyHistoryException, MaxUndosReachedException {
		// TODO: Keep track of how many undos have been done to prevent more than 1 undo per turn

		if (undoHistory.isEmpty()) {
			throw new EmptyHistoryException("The undo history is empty. No more undos available.");
		}
		// If the current board is player 1's turn, then the person initiating the undo is player 2. And vice-versa
		boolean p1Undoing = !currentBoard.isPlayer1Turn();
		if ((p1Undoing && player1Undos >= maxUndosPerGame)
				|| (!p1Undoing && player2Undos >= maxUndosPerGame)) {
			throw new MaxUndosReachedException("The player has reached the maximum number of undos (" +
					maxUndosPerGame + ")");
		}

		// Increment the appropriate undo count
		if (p1Undoing) {
			player1Undos++;
		} else {
			player2Undos++;
		}
		// update undo history, current board, and redo history
		redoHistory.push(currentBoard);
		currentBoard = undoHistory.pop();
	}

	/**
	 * Undo by popping the redo history stack to replace the curent board.
	 * @throws EmptyHistoryException redo history is empty
	 */
	public void redo() throws EmptyHistoryException{
		if (redoHistory.isEmpty()) {
			throw new EmptyHistoryException("The redo history is empty. No more redos available.");
		}

		// If the current board is player 1's turn, then the person initiating the redo is still player 1. And
		// vice-versa
		// Decrement the appropriate undo count
		if (currentBoard.isPlayer1Turn()) {
			player1Undos--;
		} else {
			player2Undos--;
		}
		// update undo history, current board, and redo history
		undoHistory.push(currentBoard);
		currentBoard = redoHistory.pop();
	}

	/**
	 * Add a ChangeListener to be notified when internal data updates.
	 * @param listener the ChangeListener to add.
	 */
	public void addChangeListener(ChangeListener listener) {
		changeListeners.add(listener);
	}

	/**
	 * Calls the stateChanged() method of all attached listeners with a new ChangeEvent set to this.
	 */
	private void updateListeners() {
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listener : changeListeners) {
			listener.stateChanged(event);
		}
	}

	/* === GETTERS === */

	public int getMaxUndosPerGame() {
		return maxUndosPerGame;
	}

	public int getMaxUndosPerTurn() {
		return maxUndosPerTurn;
	}

	public BoardModel getCurrentBoard() {
		return currentBoard;
	}

	public int getPlayer1Undos() {
		return player1Undos;
	}

	public int getPlayer2Undos() {
		return player2Undos;
	}

	public boolean isGameFinished() {
		return gameFinished;
	}
}
