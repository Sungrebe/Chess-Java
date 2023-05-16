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

	public ArrayList<String> getMoves() {
		ArrayList<String> bishopMoves = new ArrayList<String>();

		bishopMoves.addAll(getDiagonal1Moves());

		rank = getRank();
		file = getFile();

		bishopMoves.addAll(getDiagonal2Moves());

		rank = getRank();
		file = getFile();
		
		bishopMoves.addAll(getDiagonal3Moves());
		
		rank = getRank();
		file = getFile();
		
		bishopMoves.addAll(getDiagonal4Moves());

		return bishopMoves;
	}

}
