import java.util.ArrayList;

public abstract class ChessPiece {

	private String name;
	private char file;
	private int rank;
	private String side;

	public ChessPiece(String name, char file, int rank, String side) {
		this.name = name;
		this.file = file;
		this.rank = rank;
		this.side = side;
	}

	/** 
	* 
	* @return String name 
	*/
	public String getName() {
		return name;
	}
	
	/**
	* 
	* @param none
	* @return char file
	*/
	public char getFile() {
		return file;
	}
	
	/**
	* 
	* @param none
	* @return int rank
	*/
	public int getRank() {
		return rank;
	}

	public String getSide() {
		return side;
	}

	public boolean sameSide(ChessPiece other) {
		return side == other.getSide();
	}

	public boolean isBlack() {
		return side.equals("black");
	}

	public boolean isWhite() {
		return side.equals("white");
	}

	public boolean isPawn() {
		return name.equals("pawn");
	}
	
	public boolean isKnight() {
		return name.equals("knight");
	}

	public boolean isBishop() {
		return name.equals("bishop");
	}

	public boolean isQueen() {
		return name.equals("queen");
	}

	public boolean isKing() {
		return name.equals("king");
	}

	public boolean isRook() {
		return name.equals("rook");
	}

	public void setFile(char newFile) {
		file = newFile;
	}

	public void setRank(int newRank) {
		rank = newRank;
	}

	public abstract ArrayList<String> getMoves();

}
