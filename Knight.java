import java.util.ArrayList;

/**
 * The knight class handles the knight chess pieces and their movement
 */
public class Knight extends ChessPiece {

    /**
	 * Knight constructor to create a knight object
	 * @param file the alphabetical character corresponding to the column of the knight
	 * @param rank the number corresponding to the row that the knight is on
	 * @param side the side that the knight is on
	 */
	public Knight(char file, int rank, String side) {
		super("knight", file, rank, side);
	}

    /**
	 * This method returns an arraylist containing all the spaces that the knight piece can move to
	 * @return an arraylist containing all the spaces that the knight can move to
	 */
	public ArrayList<String> getMoves() {
		ArrayList<String> knightMoves = new ArrayList<String>();

		if (getRank() + 2 <= 8) {
            knightMoves.add(""+(char) (getFile() - 1) + (getRank() + 2));
            knightMoves.add(""+(char) (getFile() + 1) + (getRank() + 2));
        }

        if (getRank() - 2 > 0) {
            knightMoves.add(""+(char) (getFile() - 1) + (getRank() - 2));
            knightMoves.add(""+(char) (getFile() + 1) + (getRank() - 2));
        }

        if (getRank() + 1 <= 8) {
            knightMoves.add(""+(char) (getFile() - 2)+ (getRank() + 1));
            knightMoves.add(""+(char) (getFile() + 2) + (getRank() + 1));
        }
        
        if (getRank() - 1 > 0) {
            knightMoves.add(""+(char) (getFile() - 2) + (getRank() - 1));
            knightMoves.add(""+(char) (getFile() + 2) + (getRank() - 1));
        }

        return knightMoves;
	}

}
