import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(String coordinate, String side) {
        super("pawn", coordinate, side);
    }

    private boolean hasBeenMoved() {
        if (getSide().equals("black")) {
            return Integer.parseInt(getCoordinate().substring(1, 2)) != 7;
        } else {
            return Integer.parseInt(getCoordinate().substring(1, 2)) != 2;
        }
    }

    public ArrayList<String> moves() {
        ArrayList<String> pawnMoves = new ArrayList<String>();
        int coordX = Integer.parseInt(getCoordinate().substring(1, 2));
        String coordY = getCoordinate().substring(0, 1);

        if (hasBeenMoved()) {
            if (getSide().equals("black")) {
                if (coordX - 1 >= 0) {
                    pawnMoves.add(coordY + (coordX - 1));
                }
            } else {
                if (coordX + 1 <= 8) {
                    pawnMoves.add(coordY + (coordX + 1));
                }
            }
        } else {
            if (getSide().equals("black")) {
                pawnMoves.add(coordY + (coordX - 1));
                pawnMoves.add(coordY + (coordX - 2));
            } else {
                pawnMoves.add(coordY + (coordX + 1));
                pawnMoves.add(coordY + (coordX + 2));
            }
        }

        return pawnMoves;
    }
    
}
