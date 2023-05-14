import java.util.ArrayList;

public class King extends ChessPiece {
	
	public King(char file, int rank, String side) {
		super("king", file, rank, side);
	}

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

		System.out.println(kingMoves);

		return kingMoves;
	}

}
