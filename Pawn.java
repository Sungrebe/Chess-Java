import java.util.ArrayList;
/**
 * The Pawn class is responsible for the Pawn pieces
 */
public class Pawn extends Piece {

    /**
     * the Pawn constructor
     * @param coordinate the coordinate of the pawn
     * @param side the side that the pawn is on
     */
    public Pawn(String coordinate, String side) {
        super("pawn", coordinate, side);
    }

    /**
     * A method that determines whether a pawn has already been moved
     * @return true if the pawn has been moved, false if it has not been moved
     */
    private boolean hasBeenMoved() {
        if (getSide().equals("black")) {
            return Integer.parseInt(getCoordinate().substring(1, 2)) != 7;
        } else {
            return Integer.parseInt(getCoordinate().substring(1, 2)) != 2;
        }
    }

    /**
     * The moves method determines the spaces that the pawn can move to
     * @return an arraylist with the possible moves for the pawn
     */
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
