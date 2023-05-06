public class Piece {

    private String type;
    private String coordinate;
    private String side;

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

    public String getSide() {
        return side;
    }

}
