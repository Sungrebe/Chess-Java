import java.util.ArrayList;

/**
 * The Knight class is responsible for the Knight chess pieces
 */
public class Knight extends Piece {

    public Knight(String coordinate, String side) {
        super("knight", coordinate, side);
    }

    public ArrayList<String> moves() {
        ArrayList<String> knightMoves = new ArrayList<String>();
        int coordX = Integer.parseInt(getCoordinate().substring(1, 2));
        String coordY = getCoordinate().substring(0, 1);

        String vertLeftY = "";
        String vertRightY = "";
        String horLeftY = "";
        String horRightY = "";

        if (coordY.equals("B")) {
            vertLeftY = "A";
        } else if (coordY.equals("C")) {
            vertLeftY = "B";
        } else if (coordY.equals("D")) {
            vertLeftY = "C";
        } else if (coordY.equals("E")) {
            vertLeftY = "D";
        } else if (coordY.equals("F")) {
            vertLeftY = "E";
        } else if (coordY.equals("G")) {
            vertLeftY = "F";
        } else if (coordY.equals("H")) {
            vertLeftY = "G";
        }

        if (coordY.equals("A")) {
            vertRightY = "B";
        } else if (coordY.equals("B")) {
            vertRightY = "C";
        } else if (coordY.equals("C")) {
            vertRightY = "D";
        } else if (coordY.equals("D")) {
            vertRightY = "E";
        } else if (coordY.equals("E")) {
            vertRightY = "F";
        } else if (coordY.equals("F")) {
            vertRightY = "G";
        } else if (coordY.equals("G")) {
            vertRightY = "H";
        }

        if (coordY.equals("C")) {
            horLeftY = "A";
        } else if (coordY.equals("D")) {
            horLeftY = "B";
        } else if (coordY.equals("E")) {
            horLeftY = "C";
        } else if (coordY.equals("F")) {
            horLeftY = "D";
        } else if (coordY.equals("G")) {
            horLeftY = "E";
        } else if (coordY.equals("H")) {
            vertLeftY = "F";
        }
        
        if (coordY.equals("A")) {
            horRightY = "C";
        } else if (coordY.equals("B")) {
            horRightY = "D";
        } else if (coordY.equals("C")) {
            horRightY = "E";
        } else if (coordY.equals("D")) {
            horRightY = "F";
        } else if (coordY.equals("E")) {
            horRightY = "G";
        } else if (coordY.equals("F")) {
            horRightY = "H";
        }

        if (coordX + 2 <= 8) {
            knightMoves.add(vertLeftY + (coordX + 2));
            knightMoves.add(vertRightY + (coordX + 2));
        }

        if (coordX - 2 >= 0) {
            knightMoves.add(vertLeftY + (coordX - 2));
            knightMoves.add(vertRightY + (coordX - 2));
        }

        knightMoves.add(horLeftY + (coordX + 1));
        knightMoves.add(horRightY + (coordX + 1));
        knightMoves.add(horLeftY + (coordX - 1));
        knightMoves.add(horRightY + (coordX - 1));

        return knightMoves;
    }

}
