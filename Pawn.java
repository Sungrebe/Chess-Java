import java.util.ArrayList;

/**
 * The pawn class handles the pawn chess pieces and their movement
 */
public class Pawn extends ChessPiece {

	/**
	 * Pawn constructor to create a pawn object
	 * @param file the alphabetical character corresponding to the column of the pawn
	 * @param rank the number corresponding to the row that the pawn is on
	 * @param side the side that the pawn is on
	 */
	public Pawn(char file, int rank, String side) {
		super("pawn", file, rank, side);
	}
	
	/**
	* checks if the piece has moved and if it has it returns true
	* @return true if the piece was moved, false if the piece was not moved
	*/
	public boolean hasMoved() {
		return (isBlack() && getRank() != 2) || (isWhite() && getRank() != 7);
	}

	/**
	 * This method returns an arraylist containing all the spaces that the pawn piece can move to
	 * @return an arraylist containing all the spaces that the pawn can move to
	 */
	public ArrayList<String> getMoves() {
		ArrayList<String> pawnMoves = new ArrayList<String>();

		// If the pawn hasn't been moved, then it can move two spaces at a time
		if (isWhite() && getRank() == 2) {
			pawnMoves.add(""+getFile() + (getRank() + 1));
			pawnMoves.add(""+getFile() + (getRank() + 2));
		} else if (isBlack() && getRank() == 7) {
			pawnMoves.add(""+getFile() + (getRank() - 1));
			pawnMoves.add(""+getFile() + (getRank() - 2));
		} else if (isWhite()) {
			// White pawns move up
			pawnMoves.add(""+getFile() + (getRank() + 1));
		} else {
			// Black pawns move down
			pawnMoves.add(""+getFile() + (getRank() - 1));
		}

		return pawnMoves;
	}

}
