import java.util.ArrayList;

public class Rook extends ChessPiece {

	public Rook(char file, int rank, String side) {
		super("rook", file, rank, side);
	}

	public ArrayList<String> getMoves() {
		ArrayList<String> rookMoves = new ArrayList<String>();

		// Spaces in front of the rook
		for (int i = getRank(); i <= 8; i++) {
			rookMoves.add(""+getFile() + i);
		}

		// Spaces behind the rook
		for (int i = getRank(); i > 1; i--) {
			rookMoves.add(""+getFile() + i);
		}

		// Spaces to the right of the rook
		for (int i = getFile(); i <= (int) 'H'; i++) {
			rookMoves.add(""+(char) i + getRank());
		}

		// Spaces to the left of the rook
		for (int i = getFile(); i >= (int) 'A'; i--) {
			rookMoves.add(""+(char) i + getRank());
		}

        return rookMoves;
	}

}
