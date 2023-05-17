import java.util.ArrayList;

public class Queen extends ChessPiece {

	private Bishop b = new Bishop(getFile(), getRank(), getSide());
	private Rook r = new Rook(getFile(), getRank(), getSide());

	public Queen(char file, int rank, String side) {
		super("queen", file, rank, side);
	}

	public ArrayList<String> getDiagonal1Moves() {
		return b.getDiagonal1Moves();
	}

	public ArrayList<String> getDiagonal2Moves() {
		return b.getDiagonal1Moves();
	}

	public ArrayList<String> getDiagonal3Moves() {
		return b.getDiagonal1Moves();
	}

	public ArrayList<String> getDiagonal4Moves() {
		return b.getDiagonal1Moves();
	}

	public void resetRankAndFile() {
		b.resetRankAndFile();
	}

	public ArrayList<String> getMoves() {
		ArrayList<String> queenMoves = new ArrayList<String>();

		queenMoves.addAll(r.getMoves());
		queenMoves.addAll(b.getMoves());

		System.out.println(queenMoves);

		return queenMoves;
	}

}
