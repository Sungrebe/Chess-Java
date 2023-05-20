import java.util.ArrayList;

/**
 * The bishop class handles the bishop chess pieces and their movement
 */
public class Bishop extends ChessPiece {

	private int rank = getRank();
	private int file = getFile();
	
	/**
	 * Bishop constructor to create a bishop object
	 * @param file the alphabetical character corresponding to the column of the bishop
	 * @param rank the number corresponding to the row that the bishop is on
	 * @param side the side that the bishop is on
	 */
	public Bishop(char file, int rank, String side) {
		super("bishop", file, rank, side);
	}

	/**
	 * This method determines which spaces the bishop can move to in the direction of the top left diagonal
	 * @return an arraylist with the moves along the top left diagonal of the bishop
	 */
	public ArrayList<String> getDiagonal1Moves() {
		ArrayList<String> diagonal1Moves = new ArrayList<String>();

		// Diagonal where file is decreasing and rank increasing
		while ((char) file - 1 >= 'A' && rank + 1 <= 8) {
			diagonal1Moves.add(""+(char) (file - 1) + (rank + 1));
			file--;
			rank++;
		}

		return diagonal1Moves;
	}

	/**
	 * This method determines which spaces the bishop can move to in the direction of the top right diagonal
	 * @return an arraylist with the moves along the top right diagonal of the bishop
	 */
	public ArrayList<String> getDiagonal2Moves() {
		ArrayList<String> diagonal2Moves = new ArrayList<String>();

		// Diagonal where file is increasing and rank increasing
		while ((char) file + 1 <= 'H' && rank + 1 <= 8) {
			diagonal2Moves.add(""+(char) (file + 1) + (rank + 1));
			file++;
			rank++;
		}

		return diagonal2Moves;
	}

	/**
	 * This method determines which spaces the bishop can move to in the direction of the bottom left diagonal
	 * @return an arraylist with the moves along the bottom left diagonal of the bishop
	 */
	public ArrayList<String> getDiagonal3Moves() {
		ArrayList<String> diagonal3Moves = new ArrayList<String>();

		// Diagonal where file is decreasing and rank decreasing
		while ((char) file - 1 >= 'A' && rank - 1 > 1) {
			diagonal3Moves.add(""+(char) (file - 1) + (rank - 1));
			file--;
			rank--;
		}

		return diagonal3Moves;
	}

	/**
	 * This method determines which spaces the bishop can move to in the direction of the bottom right diagonal
	 * @return an arraylist with the moves along the bottom right of the bishop
	 */
	public ArrayList<String> getDiagonal4Moves() {
		ArrayList<String> diagonal4Moves = new ArrayList<String>();

		// Diagonal where file is increasing and rank decreasing
		while ((char) file + 1 <= 'H' && rank - 1 > 1) {
			diagonal4Moves.add(""+(char) (file + 1) + (rank - 1));
			file++;
			rank--;
		}

		return diagonal4Moves;
	}

	/**
	 * This method resests the file (col) and rank (row)
	 */
	public void resetRankAndFile() {
		rank = getRank();
		file = getFile();
	}

	/**
	 * This method synthesizes all of the bishop moves and returns an arraylist with all available moves
	 * @return an arraylist containing all the bishop moves
	 */
	public ArrayList<String> getMoves() {
		ArrayList<String> bishopMoves = new ArrayList<String>();

		bishopMoves.addAll(getDiagonal1Moves());
        
		resetRankAndFile();

		bishopMoves.addAll(getDiagonal2Moves());

		resetRankAndFile();
		
		bishopMoves.addAll(getDiagonal3Moves());
		
		resetRankAndFile();
		
		bishopMoves.addAll(getDiagonal4Moves());

		return bishopMoves;
	}

}

/*
	public boolean isBlocked(destX, destY) {
		for (String[] spaceRow : spaces) {
			for (String space : spaceRow) {
				if (!space.equals("") && space.substring(0, 2).equals("Bb")) {

				}
			}
		}
	}

 * public boolean isValidMove(int srcX, int srcY, int destX, int destY) {
 * 	return Math.abs(destY - srcY) == Math.abs(destX - srcX) && !isBlocked(destX, destY);
 * }
 */