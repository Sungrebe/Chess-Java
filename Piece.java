import java.util.ArrayList;

abstract class Piece {

    private String type;
    private String coordinate;
    private String side;

    /**
     * The Piece constructor makes a new Piece
     * @param type the type of chess piece it is
     * @param coordinate the coordinate/space that the piece occupies
     * @param side the side (black or white) that the piece belongs to
     */
    public Piece(String type, String coordinate, String side) {
        this.type = type;
        this.coordinate = coordinate;
        this.side = side;
    }

    /**
     * a getter method for the type of piece it is
     * @return the type of piece the piece is
     */
    public String getType() {
        return type;
    }

    /**
     * a method that returns the coordinates of the piece
     * @return the coordinates comprised of a letter and a number denoting the location of the piece on the board
     */
    public String getCoordinate() {
        return coordinate;
    }

    /**
     * a method that returns the letter value of the coordinate
     * @return the letter/column of the piece
     */
    public String getFile(){
        return coordinate.substring(0, 1);
    }

    /**
     * a method that returns the numerical/row value of the coordinate of the piece
     * @return the number/row of the piece
     */
    public int getRank(){
        return Integer.parseInt(coordinate.substring(1, 2));
    }

    /**
     * a method that sets the coordinate of the piece
     * @param coordinate the coordinate to set the piece to
     */
    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * this method returns which side the piece is on
     * @return black if it is the computer side, white if it is the player's side
     */
    public String getSide() {
        return side;
    }

    /**
     * A method to determine the moves a piece can make
     * @return an arraylist with the moves that the piece can make
     */
    public abstract ArrayList<String> moves();
}
