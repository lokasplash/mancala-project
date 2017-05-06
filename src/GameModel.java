import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.*;

/**
 * The GameModel represents handles the game loop and state. It also handles updates to its internal state
 * and notifies its ChangeListeners of changes.
 *
 * @author Andrew Jong
 * @since 14 April 2017
 */
public class GameModel {

	/** The maximum amount of undos a player can take per turn */
	final int MAX_UNDOS_PER_TURN = 3;

	/** The maximum undo depth allowed, ie how many moves back that can be undoed. */
	final int MAX_UNDO_DEPTH = 1;

	/* Number of pits on each player's side, should be consistent across all instances of BoardModel */
	final int PITS_PER_SIDE = 6;

	/* Number of stones to initialize in each pit, default value is 4, should be consistent across all instances
	of BoardModel */
	final int STARTING_STONES_PER_PIT;

	/* === ATTRIBUTES === */

	private Stack<BoardModel> undoHistory = new Stack<>();
	private Stack<BoardModel> redoHistory = new Stack<>();
	private BoardModel currentBoard;

	/* Keep track of the amount of numUndos taken per player */
	private int numUndos = 0;

	/* Determine if the game is finished */
	private boolean gameFinished = false;

	/* List of change Listeners for views */
	private List<ChangeListener> changeListeners = new LinkedList<>();


	/* === CONSTRUCTORS	 === */

	/**
	 * Empty constructor that initializes the current board to a new BoardModel with starting stones per pit as 4.
	 */
	public GameModel() {
		this(4);
	}

	/**
	 * Construct a GameModel with the number of starting stones per pit.
	 * @param startingStonesPerPit number of starting stones per pit.
	 */
	public GameModel(int startingStonesPerPit) {
		this.STARTING_STONES_PER_PIT = startingStonesPerPit;
		currentBoard = new BoardModel(PITS_PER_SIDE, startingStonesPerPit);
	}

	/**
	 * Constructor to attach a single ChangeListener
	 * @param listener the ChangeListener
	 */
	public GameModel(int startingStonesPerPit, ChangeListener listener) {
		this.STARTING_STONES_PER_PIT = startingStonesPerPit;
		addChangeListener(listener);
	}

	/**
	 * Constructor to attach several ChangeListeners as a list
	 * @param changeListeners the list of ChangeListeners
	 */
	public GameModel(int startingStonesPerPit, List<ChangeListener> changeListeners) {
		this.STARTING_STONES_PER_PIT = startingStonesPerPit;
		this.changeListeners = changeListeners;
	}

	/* === METHODS === */

	/**
	 * Calls BoardModel.playerMove(side, index) while updating the undo history and listeners.
	 *
	 * @param side the side to start on, either GameModel.SIDE1 or GameModel.SIDE2
	 * @param index the index of the pit, from 0 to the number of pits per side (obtainable via the method
	 *                 getPitsPerSide()).
	 * @throws GameFinishedException execpetion to be thrown if the game is finished. See isGameFinished() to get
	 * the state of game completion.
	 *
	 */
	public void playerMove(Side side, int index) throws GameFinishedException {
		if (gameFinished) throw new GameFinishedException();

		redoHistory.clear(); // Reset the redo history
		undoHistory.push(new BoardModel(currentBoard)); // Store the current board in undo history before updating.
		if (undoHistory.size() > MAX_UNDO_DEPTH) numUndos = 0;

		currentBoard.playerMove(side, index); // update the current board

		checkGameFinished();

		updateListeners();
	}

	private void checkGameFinished() {
		gameFinished = currentBoard.allPitsEmpty();
	}

	/**
	 * Exception to be thrown if the game is finished. If the game is finished, no additional moves can be made.
	 */
	static class GameFinishedException extends Exception{}

	/**
	 * A static nested class for the undo() and redo() methods if there is nothing left in the history.
	 */
	static class EmptyHistoryException extends Exception {
		EmptyHistoryException(String message) {
			super(message);
		}
	}

	/**
	 * A static nested class for the undo() method if the player has reached the maximum number of numUndos.
	 */
	static class MaxUndosReachedException extends Exception {}

	/**
	 * Undo by popping the undo history stack to replace the curent board.
	 * @throws EmptyHistoryException undo history is empty
	 */
	public void undo() throws EmptyHistoryException, MaxUndosReachedException {
		// make sure there are undos
		if (undoHistory.isEmpty()) throw new EmptyHistoryException("The undo history is empty.");
		// throw exception if max undos per turn reached
		if (numUndos >= MAX_UNDOS_PER_TURN) throw new MaxUndosReachedException();

		numUndos++;

		// update undo history, current board, and redo history
		redoHistory.push(new BoardModel(currentBoard));
		currentBoard = undoHistory.pop();
		updateListeners();
	}

	/**
	 * Undo by popping the redo history stack to replace the curent board.
	 * @throws EmptyHistoryException redo history is empty
	 */
	public void redo() throws EmptyHistoryException{
		if (redoHistory.isEmpty()) {
			throw new EmptyHistoryException("The redo history is empty. No more redos available.");
		}
		numUndos--;

		// update undo history, current board, and redo history
		undoHistory.push(new BoardModel(currentBoard));
		currentBoard = redoHistory.pop();
		updateListeners();
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

	/* === GETTERS & SETTERS=== */


	/**
	 * Get a representation of the BoardData of the board on the current turn.
	 * @return an instance of BoardData representing the state of the current board.
	 */
	public BoardData getCurrentBoardData(){
		return new BoardData(currentBoard);
	}

	/**
	 * Get the maximum number of undos allowed per turn.
	 * @return max undos per turn
	 */
	public int getMaxUndosPerTurn() {
		return MAX_UNDOS_PER_TURN;
	}

	/**
	 * Get the number of undos on the current turn.
	 * @return undos tallied so far
	 */
	public int getNumUndosFromCurrentTurn() {
		return numUndos;
	}

	/**
	 * Get whether the GameModel has undo history available and can undo.
	 * @return true if can undo, false if cannot
	 */
	public boolean canUndo() {
		return !undoHistory.isEmpty();
	}

	/**
	 * Get whether the GameModel has redo history available and can redo.
	 * @return true if can redo, false if cannot
	 */
	public boolean canRedo() {
		return !redoHistory.isEmpty();
	}

	/**
	 * Get whether the game state is finished when all the central pits are empty of stones.
	 * @return true if finished, false if not
	 */
	public boolean isGameFinished() {
		return gameFinished;
	}
}
