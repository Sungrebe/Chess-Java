import java.util.ArrayList;

public class Bishop extends ChessPiece {
	
	public Bishop(char file, int rank, String side) {
		super("bishop", file, rank, side);
	}

	public ArrayList<String> getMoves() {
		ArrayList<String> bishopMoves = new ArrayList<String>();

		int rank = getRank();
		char file = getFile();

		// Diagonal where file is decreasing and rank increasing
		while ((char) file >= 'B' && rank < 8) {
			bishopMoves.add(""+(char) (file - 1) + (rank + 1));
			file--;
			rank++;
		}

		rank = getRank();
		file = getFile();

		// Diagonal where file is increasing and rank increasing
		while ((char) file <= 'G' && rank < 8) {
			bishopMoves.add(""+(char) (file + 1) + (rank + 1));
			file++;
			rank++;
		}

		rank = getRank();
		file = getFile();
		
		// Diagonal where file is decreasing and rank decreasing
		while ((char) file >= 'B' && rank > 1) {
			bishopMoves.add(""+(char) (file - 1) + (rank - 1));
			file--;
			rank--;
		}
		
		rank = getRank();
		file = getFile();
		
		// Diagonal where file is increasing and rank decreasing
		while ((char) file <= 'G' && rank > 1) {
			bishopMoves.add(""+(char) (file + 1) + (rank - 1));
			file++;
			rank--;
		}

		return bishopMoves;
	}

}
