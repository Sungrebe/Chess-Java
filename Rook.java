import java.util.ArrayList;

/**
 * The rook class handles the rook chess pieces and their movement
 */
public class Rook extends ChessPiece {

	/**
	 * Rook constructor to create a rook object
	 * @param file the alphabetical character corresponding to the column of the rook
	 * @param rank the number corresponding to the row that the rook is on
	 * @param side the side that the rook is on
	 */
	public Rook(char file, int rank, String side) {
		super("rook", file, rank, side);
	}

	/**
	 * This method determines which spaces the rook can move to in front of the rook
	 * @return an arraylist with the moves in front of the rook
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
	 * This method determines which spaces the rook can move behind of the rook
	 * @return an arraylist with the moves behind the rook
	 */
	public ArrayList<String> getBackSpaces() {
		ArrayList<String> backSpaces = new ArrayList<String>();

		// Spaces behind the rook
		for (int i = getRank() - 1; i >= 1; i--) {
			backSpaces.add(""+getFile() + i);
		}

		return backSpaces;
	}

    /**
	 * This method determines which spaces the rook can move to the right of the rook
	 * @return an arraylist with the moves to the right of the rook
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
	 * This method determines which spaces the rook can move to the left of the rook
	 * @return an arraylist with the moves to the left of the rook
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
	 * This method returns an arraylist containing all the spaces that the rook piece can move to
	 * @return an arraylist containing all the spaces that the rook can move to
	 */
	public ArrayList<String> getMoves() {
		ArrayList<String> rookMoves = new ArrayList<String>();
		rookMoves.addAll(getFrontSpaces());
		rookMoves.addAll(getBackSpaces());
		rookMoves.addAll(getRightSpaces());
		rookMoves.addAll(getLeftSpaces());

        return rookMoves;
	}

}
