import java.util.ArrayList;

/**
 * The Queen class is responsible for the Queen chess pieces
 */
public class Queen extends Piece {

    public Queen(String coordinate, String side) {
        super("queen", coordinate, side);
    }

    public ArrayList<String> moves() {
        ArrayList<String> queenMoves = new ArrayList<String>();
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
        //the spots in front of the queen
        for (int i = coordX; i <= 8; i++){
            queenMoves.add(coordY + (i));
        }

        //the spots behind the queen
        for (int i = coordX; i >= 0; i--){
            queenMoves.add(coordY + (i));
        }

       //the spot to the right of the queen
       for (int i = coordYIndex; i < 8; i++){
            queenMoves.add(column[i] + coordX);
       }
       //the spot to the left of the queen
       for (int i = coordYIndex; i >= 0; i--){
            queenMoves.add(column[i] + coordX);
       }
        return queenMoves;
    }
    
}
