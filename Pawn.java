import java.util.ArrayList;

public class Pawn extends ChessPiece {

	public Pawn(char file, int rank, String side) {
		super("pawn", file, rank, side);
	}

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
