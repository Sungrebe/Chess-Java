import java.util.ArrayList;
import java.util.Arrays;

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
        int fileIndex = Arrays.binarySearch(column, getFile());
        int rank = getRank();
    

        //the spots in front of the queen
        for (int i = rank; i <= 8; i++){
            queenMoves.add(column[fileIndex] + (i));
        }

        //the spots behind the queen
        for (int i = rank; i >= 0; i--){
            queenMoves.add(column[fileIndex] + (i));
        }

       //the spot to the right of the queen
       for (int i = fileIndex; i < 8; i++){
            queenMoves.add(column[i] + rank);
       }
       //the spot to the left of the queen
       for (int i = fileIndex; i >= 0; i--){
            queenMoves.add(column[i] + rank);
       }

       // file decreasing, rank increasing
       while (fileIndex >= 1 && rank < 8) {
           queenMoves.add(column[fileIndex - 1] + (rank + 1));
           fileIndex--;
           rank++;
       }

       fileIndex = Arrays.binarySearch(column, getFile());
       rank = getRank();

       // file increasing, rank increasing
       while (fileIndex < 7 && rank < 8) {
           queenMoves.add(column[fileIndex + 1] + (rank + 1));
           fileIndex++;
           rank++;
       }

       fileIndex = Arrays.binarySearch(column, getFile());
       rank = getRank();

       // file decreasing, rank decreasing
       while (fileIndex >= 1 && rank >= 1) {
           queenMoves.add(column[fileIndex - 1] + (rank - 1));
           fileIndex--;
           rank--;
       }

       fileIndex = Arrays.binarySearch(column, getFile());
       rank = getRank();

       // file increasing, rank decreasing
       while (fileIndex < 7 && rank >= 1) {
           queenMoves.add(column[fileIndex + 1] + (rank - 1));
           fileIndex++;
           rank--;
       }
       
        return queenMoves;
    }
    
}
