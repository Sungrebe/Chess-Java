import java.util.ArrayList;

public class Bishop extends ChessPiece {

	private int rank = getRank();
	private int file = getFile();
	
	public Bishop(char file, int rank, String side) {
		super("bishop", file, rank, side);
	}

	public ArrayList<String> getDiagonal1Moves() {
		ArrayList<String> diagonal1Moves = new ArrayList<String>();

		// Diagonal where file is decreasing and rank increasing
		while ((char) file - 1 >= 'A' && rank + 1 < 8) {
			diagonal1Moves.add(""+(char) (file - 1) + (rank + 1));
			file--;
			rank++;
		}

		System.out.println("diagonal 1 moves (original): " + diagonal1Moves);

		return diagonal1Moves;
	}

	public ArrayList<String> getDiagonal2Moves() {
		ArrayList<String> diagonal2Moves = new ArrayList<String>();

		// Diagonal where file is increasing and rank increasing
		while ((char) file + 1 <= 'H' && rank + 1 < 8) {
			diagonal2Moves.add(""+(char) (file + 1) + (rank + 1));
			file++;
			rank++;
		}

		return diagonal2Moves;
	}

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

	public void resetRankAndFile() {
		rank = getRank();
		file = getFile();
	}

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