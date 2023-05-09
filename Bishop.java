import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Bishop class is responsible for the Bishop chess pieces
 */
public class Bishop extends Piece {

    public Bishop(String coordinate, String side) {
        super("bishop", coordinate, side);
    }

    @Override
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
