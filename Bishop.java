import java.util.ArrayList;

/**
 * The Bishop class is responsible for the Bishop chess pieces
 */
public class Bishop extends Piece {

    public Bishop(String coordinate, String side) {
        super("bishop", coordinate, side);
    }

    @Override
    public ArrayList<String> moves() {
        ArrayList<String> bishopMoves = new ArrayList<String>();
        int coordX = Integer.parseInt(getCoordinate().substring(1, 2));
        String coordY = getCoordinate().substring(0, 1);

        bishopMoves.add("D3");

        return bishopMoves;
    }
    
}
