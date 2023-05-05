abstract class ChessPiece extends BoardSpace {

    private String type;
    private String side;

    public ChessPiece(int pieceRow, int pieceCol, String type, String side) {
        super(pieceRow, pieceCol);
        this.type = type;
        this.side = side;
    }

    public String getType() {
        return type;
    }

    public String getSide() {
        return side;
    }

    abstract void move(BoardSpace toSpace);

    public void capture(BoardSpace spaceToOccupy) {
        // if spaceToOccupy has a piece of the same side
            // Don't do anything
        // otherwise
            // Remove spaceToOccupy.removePiece();
            // Add the new piece spaceToOccupy.setPiece(this);
    }

    abstract BoardSpace[] getMoves();

}