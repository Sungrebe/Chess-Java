import java.util.ArrayList;

/**
 * The queen class handles the queen chess pieces and their movement
 */
public class Queen extends ChessPiece {

	private int rank = getRank();
	private int file = getFile();

	/**
	 * Queen constructor to create a queen object
	 * @param file the alphabetical character corresponding to the column of the queen
	 * @param rank the number corresponding to the row that the queen is on
	 * @param side the side that the queen is on
	 */
	public Queen(char file, int rank, String side) {
		super("queen", file, rank, side);
	}

	/**
	 * This method determines which spaces the queen can move to in the direction of the top left diagonal
	 * @return an arraylist with the moves along the top left diagonal of the queen
	 */
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

	/**
	 * This method determines which spaces the queen can move to in the direction of the top right diagonal
	 * @return an arraylist with the moves along the top right diagonal of the queen
	 */
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

	/**
	 * This method determines which spaces the queen can move to in the direction of the bottom left diagonal
	 * @return an arraylist with the moves along the bottom left diagonal of the queen
	 */
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

	/**
	 * This method determines which spaces the queen can move to in the direction of the bottom right diagonal
	 * @return an arraylist with the moves along the bottom right of the queen
	 */
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

	/**
	 * This method determines which spaces the queen can move to in front of the queen
	 * @return an arraylist with the moves in front of the queen
	 */
	public ArrayList<String> getFrontSpaces() {
		ArrayList<String> frontSpaces = new ArrayList<String>();

		// Spaces in front of the rook
		for (int i = getRank() + 1; i <= 8; i++) {
			frontSpaces.add(""+getFile() + i);
		}

		return frontSpaces;
	}

	/**
	 * This method determines which spaces the queen can move to behind of the queen
	 * @return an arraylist with the moves behind of the queen
	 */
	public ArrayList<String> getBackSpaces() {
		ArrayList<String> backSpaces = new ArrayList<String>();

		// Spaces behind the rook
		for (int i = getRank() - 1; i > 1; i--) {
			backSpaces.add(""+getFile() + i);
		}

		return backSpaces;
	}

	/**
	 * This method determines which spaces the queen can move to the right of the queen
	 * @return an arraylist with the to the right of the queen
	 */
	public ArrayList<String> getRightSpaces() {
		ArrayList<String> rightSpaces = new ArrayList<String>();

		// Spaces to the right of the rook
		for (int i = getFile() + 1; i <= (int) 'H'; i++) {
			rightSpaces.add(""+(char) i + getRank());
		}

		return rightSpaces;
	}

	/**
	 * This method determines which spaces the queen can move to the left of the queen
	 * @return an arraylist with the moves to the left of the queen
	 */
	public ArrayList<String> getLeftSpaces() {
		ArrayList<String> leftSpaces = new ArrayList<String>();

		// Spaces to the left of the rook
		for (int i = getFile() - 1; i >= (int) 'A'; i--) {
			leftSpaces.add(""+(char) i + getRank());
		}

		return leftSpaces;
	}

	/**
	 * This method resets the rank (row) and file (col) of the queen
	 */
	public void resetRankAndFile() {
		rank = getRank();
		file = getFile();
	}

	/**
	 * This method returns an arraylist containing all the spaces that the queen piece can move to
	 * @return an arraylist containing all the spaces that the queen can move to
	 */
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
