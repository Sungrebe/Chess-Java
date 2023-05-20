import java.util.ArrayList;

/**
 * The king class handles the king chess pieces and their movement
 */
public class King extends ChessPiece {

	/**
	 * King constructor to create a king object
	 * @param file the alphabetical character corresponding to the column of the king
	 * @param rank the number corresponding to the row that the king is on
	 * @param side the side that the king is on
	 */
	public King(char file, int rank, String side) {
		super("king", file, rank, side);
	}
	
	
	/**
	 * This method returns an arraylist containing all the spaces that the king piece can move to
	 * @return an arraylist containing all the spaces that the king can move to
	 */
	public ArrayList<String> getMoves() {
		ArrayList<String> kingMoves = new ArrayList<String>();

		// Space in front of the king
        kingMoves.add(""+getFile() + (getRank() + 1));

		// Space behind the king
		kingMoves.add(""+getFile() + (getRank() - 1));

		kingMoves.add(""+(char) (getFile() - 1) + getRank()); // Space to the left of the king
		kingMoves.add(""+(char) (getFile() - 1) + (getRank() + 1)); // Top left diagonal
		kingMoves.add(""+(char) (getFile() - 1) + (getRank() - 1)); // Bottom left diagonal

		kingMoves.add(""+(char) (getFile() + 1) + getRank()); // Space to the right of the king
		kingMoves.add(""+(char) (getFile() + 1) + (getRank() + 1)); // Top right diagonal
		kingMoves.add(""+(char) (getFile() + 1) + (getRank() - 1)); // Bottom right diagonal

		return kingMoves;
	}

}
