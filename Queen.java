import java.util.ArrayList;

public class Queen extends ChessPiece {

	private int rank = getRank();
	private int file = getFile();

	public Queen(char file, int rank, String side) {
		super("queen", file, rank, side);
	}

	public ArrayList<String> getDiagonal1Moves() {
		ArrayList<String> diagonal1Moves = new ArrayList<String>();

		// Diagonal where file is decreasing and rank increasing
		while ((char) file >= 'B' && rank < 8) {
			diagonal1Moves.add(""+(char) (file - 1) + (rank + 1));
			file--;
			rank++;
		}

		return diagonal1Moves;
	}

	public ArrayList<String> getDiagonal2Moves() {
		ArrayList<String> diagonal2Moves = new ArrayList<String>();

		// Diagonal where file is increasing and rank increasing
		while ((char) file <= 'G' && rank < 8) {
			diagonal2Moves.add(""+(char) (file + 1) + (rank + 1));
			file++;
			rank++;
		}

		return diagonal2Moves;
	}

	public ArrayList<String> getDiagonal3Moves() {
		ArrayList<String> diagonal3Moves = new ArrayList<String>();

		// Diagonal where file is decreasing and rank decreasing
		while ((char) file >= 'B' && rank > 1) {
			diagonal3Moves.add(""+(char) (file - 1) + (rank - 1));
			file--;
			rank--;
		}

		return diagonal3Moves;
	}

	public ArrayList<String> getDiagonal4Moves() {
		ArrayList<String> diagonal4Moves = new ArrayList<String>();

		// Diagonal where file is increasing and rank decreasing
		while ((char) file <= 'G' && rank > 1) {
			diagonal4Moves.add(""+(char) (file + 1) + (rank - 1));
			file++;
			rank--;
		}

		return diagonal4Moves;
	}

	public ArrayList<String> getFrontSpaces() {
		ArrayList<String> frontSpaces = new ArrayList<String>();

		// Spaces in front of the queen
		for (int i = getRank(); i <= 8; i++) {
			frontSpaces.add(""+getFile() + i);
		}

		return frontSpaces;
	}

	public ArrayList<String> getBackSpaces() {
		ArrayList<String> backSpaces = new ArrayList<String>();

		// Spaces behind the queen
		for (int i = getRank(); i > 1; i--) {
			backSpaces.add(""+getFile() + i);
		}

		return backSpaces;
	}

	public ArrayList<String> getRightSpaces() {
		ArrayList<String> rightSpaces = new ArrayList<String>();

		// Spaces to the right of the queen
		for (int i = getFile(); i <= (int) 'H'; i++) {
			rightSpaces.add(""+(char) i + getRank());
		}

		return rightSpaces;
	}

	public ArrayList<String> getLeftSpaces() {
		ArrayList<String> leftSpaces = new ArrayList<String>();

		// Spaces to the left of the rook
		for (int i = getFile(); i >= (int) 'A'; i--) {
			leftSpaces.add(""+(char) i + getRank());
		}

		return leftSpaces;
	}

	public void resetRankAndFile() {
		rank = getRank();
		file = getFile();
	}

	public ArrayList<String> getMoves() {
		ArrayList<String> queenMoves = new ArrayList<String>();

		queenMoves.addAll(getDiagonal1Moves());
        
		resetRankAndFile();

		queenMoves.addAll(getDiagonal2Moves());

		resetRankAndFile();
		
		queenMoves.addAll(getDiagonal3Moves());
		
		resetRankAndFile();
		
		queenMoves.addAll(getDiagonal4Moves());

		queenMoves.addAll(getFrontSpaces());
		queenMoves.addAll(getBackSpaces());
		queenMoves.addAll(getRightSpaces());
		queenMoves.addAll(getLeftSpaces());

		return queenMoves;
	}

}
