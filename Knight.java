import java.util.ArrayList;

public class Knight extends ChessPiece {

	public Knight(char file, int rank, String side) {
		super("knight", file, rank, side);
	}

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
