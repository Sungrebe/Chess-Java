import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Bishop class is responsible for the Bishop chess pieces
 */
public class Bishop extends Piece {

    /**
     * The bishop constructor
     * @param coordinate the coordinate of the piece
     * @param side which side the piece belongs to
     */
    public Bishop(String coordinate, String side) {
        super("bishop", coordinate, side);
    }

    /**
     * the moves method determines the moves for the bishop
     * @return an arraylist containing the possible moves for the bishop
     */
    public ArrayList<String> moves() {
        ArrayList<String> bishopCoordinates = new ArrayList<String>();
        String[] columns = {"A", "B", "C", "D", "E", "F", "G", "H"};
        int fileIndex = Arrays.binarySearch(columns, getFile());
        int rank = getRank();

        // file decreasing, rank increasing
        while (fileIndex >= 1 && rank < 8) {
            bishopCoordinates.add(columns[fileIndex - 1] + (rank + 1));
            fileIndex--;
            rank++;
        }

        fileIndex = Arrays.binarySearch(columns, getFile());
        rank = getRank();

        // file increasing, rank increasing
        while (fileIndex < 7 && rank < 8) {
            bishopCoordinates.add(columns[fileIndex + 1] + (rank + 1));
            fileIndex++;
            rank++;
        }

        fileIndex = Arrays.binarySearch(columns, getFile());
        rank = getRank();

        // file decreasing, rank decreasing
        while (fileIndex >= 1 && rank >= 1) {
            bishopCoordinates.add(columns[fileIndex - 1] + (rank - 1));
            fileIndex--;
            rank--;
        }

        fileIndex = Arrays.binarySearch(columns, getFile());
        rank = getRank();

        // file increasing, rank decreasing
        while (fileIndex < 7 && rank >= 1) {
            bishopCoordinates.add(columns[fileIndex + 1] + (rank - 1));
            fileIndex++;
            rank--;
        }
        return bishopCoordinates;

    }
    
}
