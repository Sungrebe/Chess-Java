import java.util.ArrayList;

public class Rook extends ChessPiece {

	public Rook(char file, int rank, String side) {
		super("rook", file, rank, side);
	}

	public ArrayList<String> getFrontSpaces() {
		ArrayList<String> frontSpaces = new ArrayList<String>();

		// Spaces in front of the rook
		for (int i = getRank() + 1; i <= 8; i++) {
			frontSpaces.add(""+getFile() + i);
		}

		return frontSpaces;
	}

	public ArrayList<String> getBackSpaces() {
		ArrayList<String> backSpaces = new ArrayList<String>();

		// Spaces behind the rook
		for (int i = getRank() - 1; i >= 1; i--) {
			backSpaces.add(""+getFile() + i);
		}

		return backSpaces;
	}

	public ArrayList<String> getRightSpaces() {
		ArrayList<String> rightSpaces = new ArrayList<String>();

		// Spaces to the right of the rook
		for (int i = getFile() + 1; i <= (int) 'H'; i++) {
			rightSpaces.add(""+(char) i + getRank());
		}

		return rightSpaces;
	}

	public ArrayList<String> getLeftSpaces() {
		ArrayList<String> leftSpaces = new ArrayList<String>();

		// Spaces to the left of the rook
		for (int i = getFile() - 1; i >= (int) 'A'; i--) {
			leftSpaces.add(""+(char) i + getRank());
		}

		return leftSpaces;
	}
	
	public ArrayList<String> getMoves() {
		ArrayList<String> rookMoves = new ArrayList<String>();
		rookMoves.addAll(getFrontSpaces());
		rookMoves.addAll(getBackSpaces());
		rookMoves.addAll(getRightSpaces());
		rookMoves.addAll(getLeftSpaces());

        return rookMoves;
	}

}
