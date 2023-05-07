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

    public String getType() {
        return type;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getSide() {
        return side;
    }

    public abstract ArrayList<String> moves();
}
