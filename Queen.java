import java.util.ArrayList;

public class Queen extends ChessPiece {

	public Queen(char file, int rank, String side) {
		super("queen", file, rank, side);
	}

	public ArrayList<String> getMoves() {
		ArrayList<String> queenMoves = new ArrayList<String>();

		Bishop b = new Bishop(getFile(), getRank(), getSide());
		Rook r = new Rook(getFile(), getRank(), getSide());

		queenMoves.addAll(b.getMoves());
		queenMoves.addAll(r.getMoves());

		return queenMoves;
	}

}
