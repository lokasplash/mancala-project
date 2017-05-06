/**
 * Identifier for determining the side of the board.
 * P1 denotes the pits on Player 1's side of the board, while P2 denotes the pits on Player 2's side of the board.
 * Meant to be used as the first argument to the playerMove() method.
 */
public enum Side {
	P1(true), P2(false);

	private boolean p1Side;

	Side(boolean b) {
		p1Side = b;
	}

	public boolean isP1Side() {
		return p1Side;
	}
}
