import java.util.ArrayList;

/**
 * The Rook class is responsible for the Rook chess pieces
 */
public class Rook extends Piece {

    /**
     * The constructor for the rook
     * @param coordinate the coordinate of the rook
     * @param side the side that the rook is on
     */
    public Rook(String coordinate, String side) {
        super("rook", coordinate, side);
    }
    
    /**
     * The moves method determines the moves that the rook can make
     * @return an arraylist with the possible moves that the rook can make
     */
    public ArrayList<String> moves() {
        ArrayList<String> rookMoves = new ArrayList<String>();
        //used to easily switch between column values
        String[] column = {"A", "B", "C", "D", "E", "F", "G", "H"};
        int coordX = Integer.parseInt(getCoordinate().substring(1, 2));
        String coordY = getCoordinate().substring(0, 1);
        int coordYIndex = 0;
        //use this to find the index value corresponding to the column letter
        for (int i = 0; i < column.length; i++){
            if (coordY.equals(column[i])){
                coordYIndex = i;
            } 
        }
        //the spots in front of the rook
        for (int i = coordX; i <= 8; i++){
            rookMoves.add(coordY + (i));
        }

        //the spots behind the rook
        for (int i = coordX; i >= 0; i--){
            rookMoves.add(coordY + (i));
        }

       //the spot to the right of the rook
       for (int i = coordYIndex; i < 8; i++){
            rookMoves.add(column[i] + coordX);
       }
       //the spot to the left of the rook
       for (int i = coordYIndex; i >= 0; i--){
            rookMoves.add(column[i] + coordX);
       }
        return rookMoves;
    }
    
}
