import java.util.ArrayList;

/**
 * ChessPiece is a class that handles the actual chess pieces
 */
public abstract class ChessPiece {

	private String name;
	private char file;
	private int rank;
	private String side;

	/**
	 * The ChessPiece constructor which creates objects of the class
	 * @param name the name of the chess piece
	 * @param file the col/file that the chess piece is at
	 * @param rank the rank/row that the chess piece is at
	 * @param side the side that the chess piece is on
	 */
	public ChessPiece(String name, char file, int rank, String side) {
		this.name = name;
		this.file = file;
		this.rank = rank;
		this.side = side;
	}

	/** 
	* Getter method for the name of the chess piece
	* @return String the name of the chess piece
	*/
	public String getName() {
		return name;
	}
	
	/**
	* Getter method for the file/col that the chess piece is at
	* @return char file/col that the chess piece is at
	*/
	public char getFile() {
		return file;
	}
	
	/**
	* Getter method for the rank/row that the chess piece is at
	* @return int rank/row that the chess piece is at
	*/
	public int getRank() {
		return rank;
	}

	/**
	 * Getter method for the side that the chess piece is on
	 * @return the side that the chess piece is on
	 */
	public String getSide() {
		return side;
	}

	/**
	 * This method checks whether pieces are on the same side
	 * @param other the other piece to check
	 * @return true if they are on the same side, false otherwise
	 */
	public boolean sameSide(ChessPiece other) {
		return side == other.getSide();
	}

	/**
	 * This method checks whether a piece is black
	 * @return true if it is, false if not
	 */
	public boolean isBlack() {
		return side.equals("black");
	}
	
	/**
	 * This method checks whether a piece is white
	 * @return true if it is, false if not
	 */
	public boolean isWhite() {
		return side.equals("white");
	}

	/**
	 * This method checks whether a piece is a pawn
	 * @return true if it is, false if not
	 */
	public boolean isPawn() {
		return name.equals("pawn");
	}
	
	/**
	 * This method checks whether a piece is a knight
	 * @return true if it is, false if not
	 */
	public boolean isKnight() {
		return name.equals("knight");
	}

	/**
	 * This method checks whether a piece is a bishop
	 * @return true if it is, false if not
	 */
	public boolean isBishop() {
		return name.equals("bishop");
	}

    /**
	 * This method checks whether a piece is a queen
	 * @return true if it is, false if not
	 */
	public boolean isQueen() {
		return name.equals("queen");
	}

	/**
	 * This method checks whether a piece is a king
	 * @return true if it is, false if not
	 */
	public boolean isKing() {
		return name.equals("king");
	}

	/**
	 * This method checks whether a piece is a rook
	 * @return true if it is, false if not
	 */
	public boolean isRook() {
		return name.equals("rook");
	}

	/**
	 * Setter method for the file/col of the piece
	 * @param newFile the file to set the file to
	 */
	public void setFile(char newFile) {
		file = newFile;
	}

	/**
	 * Setter method for the rank/row of the piece
	 * @param newRank the rank to set the rank to
	 */
	public void setRank(int newRank) {
		rank = newRank;
	}

	/**
	 * This method determines moves that a chess piece can make
	 * @return an arraylist with potential moves for a chess piece
	 */
	public abstract ArrayList<String> getMoves();

}
