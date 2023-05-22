import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

/**
 * The Board class handles the board and its pieces
 */
public class Board extends JComponent implements MouseListener {

    private Space[][] spaces;
    ArrayList<ChessPiece> blackPieces = new ArrayList<ChessPiece>(); // Black chess pieces
    ArrayList<ChessPiece> whitePieces = new ArrayList<ChessPiece>(); // White chess pieces

    // Determine player turns
    private boolean whiteToMove;
    private boolean blackToMove;

    private String whiteKingPos;
    private String blackKingPos;

    // Number of times the user has pressed the board
    private int numClicks = 0;

    // Information about the starting and ending coordinates that the user click
    private char sourceFile;
    private int sourceRank;
    private char destFile;
    private int destRank;

    // Kings can only castle if they haven't moved yet
    private boolean whiteCastlingValid = true;
    private boolean blackCastlingValid = true;

    private boolean blackInCheck = false;
    private boolean whiteInCheck = false;

    private String blackCheckingPiecePos = ""; // current white piece that is checking black
    private String whiteCheckingPiecePos = ""; // current black piece that is checking white
    
    private ChessPiece lastPieceCaptured; // Records the last chess piece that was captured (so that moves can be undone properly)

    /**
     * The Board construtor handles the layout of the board and its pieces
     */
    public Board() {
        spaces = new Space[8][8];

        whiteToMove = true;
        blackToMove = false;

        whiteKingPos = "E1"; 
        blackKingPos = "E8";

        spaces[0][0] = new Space(0, 0, new Rook('A', 8, "black"));
        spaces[0][1] = new Space(0, 1, new Knight('B', 8, "black"));
        spaces[0][2] = new Space(0, 2, new Bishop('C', 8, "black"));
        spaces[0][3] = new Space(0, 3, new Queen('D', 8, "black"));
        spaces[0][4] = new Space(0, 4, new King('E', 8, "black"));
        spaces[0][5] = new Space(0, 5, new Bishop('F', 8, "black"));
        spaces[0][6] = new Space(0, 6, new Knight('G', 8, "black"));
        spaces[0][7] = new Space(0, 7, new Rook('H', 8, "black"));

        spaces[1][0] = new Space(1, 0, new Pawn('A', 7, "black"));
        spaces[1][1] = new Space(1, 1, new Pawn('B', 7, "black"));
        spaces[1][2] = new Space(1, 2, new Pawn('C', 7, "black"));
        spaces[1][3] = new Space(1, 3, new Pawn('D', 7, "black"));
        spaces[1][4] = new Space(1, 4, new Pawn('E', 7, "black"));
        spaces[1][5] = new Space(1, 5, new Pawn('F', 7, "black"));
        spaces[1][6] = new Space(1, 6, new Pawn('G', 7, "black"));
        spaces[1][7] = new Space(1, 7, new Pawn('H', 7, "black"));

        for (int i = 0; i < 8; i++) {
            spaces[2][i] = new Space(2, i, null);
        }
        
        for (int i = 0; i < 8; i++) {
            spaces[3][i] = new Space(3, i, null);
        }

        for (int i = 0; i < 8; i++) {
            spaces[4][i] = new Space(4, i, null);
        }

        for (int i = 0; i < 8; i++) {
            spaces[5][i] = new Space(5, i, null);
        }

        spaces[6][0] = new Space(6, 0, new Pawn('A', 2, "white"));
        spaces[6][1] = new Space(6, 1, new Pawn('B', 2, "white"));
        spaces[6][2] = new Space(6, 2, new Pawn('C', 2, "white"));
        spaces[6][3] = new Space(6, 3, new Pawn('D', 2, "white"));
        spaces[6][4] = new Space(6, 4, new Pawn('E', 2, "white"));
        spaces[6][5] = new Space(6, 5, new Pawn('F', 2, "white"));
        spaces[6][6] = new Space(6, 6, new Pawn('G', 2, "white"));
        spaces[6][7] = new Space(6, 7, new Pawn('H', 2, "white"));

        spaces[7][0] = new Space(7, 0, new Rook('A', 1, "white"));
        spaces[7][1] = new Space(7, 1, new Knight('B', 1, "white"));
        spaces[7][2] = new Space(7, 2, new Bishop('C', 1, "white"));
        spaces[7][3] = new Space(7, 3, new Queen('D', 1, "white"));
        spaces[7][4] = new Space(7, 4, new King('E', 1, "white"));
        spaces[7][5] = new Space(7, 5, new Bishop('F', 1, "white"));
        spaces[7][6] = new Space(7, 6, new Knight('G', 1, "white"));
        spaces[7][7] = new Space(7, 7, new Rook('H', 1, "white"));

        addMouseListener(this);
    }

    /**
     * This method handles the painting of the board and its pieces
     * @param g the graphics object that handles the painting
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        for (int row = 0; row < spaces.length; row++) {
            for (int col = 0; col < spaces[0].length; col++) {
                if ((row + col) % 2 == 0) {
                    g2d.setColor(Color.decode("#F0E68C"));
                } else {
                    g2d.setColor(Color.decode("#98BF64"));
                }

                g2d.fillRect(col * 50, row * 50, 50, 50);

                if (spaces[row][col].getCp() != null) {
                    ChessPiece c = spaces[row][col].getCp();
                    Image cpImg = Toolkit.getDefaultToolkit()
                            .getImage("assets/" + c.getSide() + " " + c.getName() + ".png");

                    g2d.drawImage(cpImg, 2 + col * 50, 3 + row * 50, 45, 45, new Color(0f, 0f, 0f, 0), this);
                }
            }
        }
    }

    /**
     * This method validates the moves that chess pieces can make by handling blocking and special moves such as capturing
     * @param cpRow the row of the chess piece
     * @param cpCol the col of the chess piece
     * @param cp the chess piece itself
     * @return an arraylist containing valid moves for a given chess piece
     */
    public ArrayList<String> getValidMoves(int cpRow, int cpCol, ChessPiece cp) {
        ArrayList<String> cpMoves = cp.getMoves();

        if (cp.isPawn()) {
            Pawn p = (Pawn) cp;
            // Pawns can move diagonally if they can capture a piece from the opposing side
            // If so, add the diagonals to the list of possible moves
            if (p.isWhite()) {
                // If a white pawn is bounded in front, it cannot move
                // or two spaces in front
                if ((cpRow - 1 >= 0 && spaces[cpRow - 1][cpCol].getCp() != null)
                        || (!p.hasMoved() && cpRow - 2 >= 0 && spaces[cpRow - 2][cpCol].getCp() != null)) {
                    cpMoves.clear();
                } else {
                    cpMoves = p.getMoves();
                }

                if (cpRow - 1 >= 0 && cpCol - 1 >= 0 && spaces[cpRow - 1][cpCol - 1].getCp() != null) {
                    // If top left diagonal has a piece
                    cpMoves.add("" + (char) ((int) 'A' + cpCol - 1) + (8 - cpRow + 1));
                }

                if (cpRow - 1 >= 0 && cpCol + 1 <= 7 && spaces[cpRow - 1][cpCol + 1].getCp() != null) {
                    // If top right diagonal has a piece
                    cpMoves.add("" + (char) ((int) 'A' + cpCol + 1) + (8 - cpRow + 1));
                }
            } else if (cp.isBlack()) {
                // If a black pawn is bounded below, it cannot move
                if ((cpRow + 1 <= 7 && spaces[cpRow + 1][cpCol].getCp() != null)
                        || (!p.hasMoved() && cpRow + 2 <= 7 && spaces[cpRow + 2][cpCol].getCp() != null)) {
                    cpMoves.clear();
                } else {
                    cpMoves = cp.getMoves();
                }

                if (cpRow + 1 <= 7 && cpCol - 1 >= 0 && spaces[cpRow + 1][cpCol - 1].getCp() != null) {
                    // If bottom left diagonal has a piece
                    cpMoves.add("" + (char) ((int) 'A' + cpCol - 1) + (8 - cpRow - 1));
                }

                if (cpRow + 1 <= 7 && cpCol + 1 <= 7 && spaces[cpRow + 1][cpCol + 1].getCp() != null) {
                    // If bottom right diagonal has a piece
                    cpMoves.add("" + (char) ((int) 'A' + cpCol + 1) + (8 - cpRow - 1));
                }
            }
        } else if (!cp.isKnight()) {
            // All other pieces (except knights) cannot move if they bounded on all sides
            boolean leftBounded = cpCol - 1 >= 0 && spaces[cpRow][cpCol - 1].getCp() != null && spaces[cpRow][cpCol - 1].getCp().sameSide(cp);;
            boolean rightBounded = cpCol + 1 <= 7 && spaces[cpRow][cpCol + 1].getCp() != null && spaces[cpRow][cpCol + 1].getCp().sameSide(cp);;
            boolean topBounded = cpRow - 1 >= 0 && spaces[cpRow - 1][cpCol].getCp() != null && spaces[cpRow - 1][cpCol].getCp().sameSide(cp);;
            boolean bottomBounded = cpRow + 1 <= 7 && spaces[cpRow + 1][cpCol].getCp() != null && spaces[cpRow + 1][cpCol].getCp().sameSide(cp);;

            // Check if diagonals are bounded (this applies to queens, bishops, and kings)
            boolean topLeftDiagBounded = cpRow - 1 >= 0 && cpCol - 1 >= 0
                    && spaces[cpRow - 1][cpCol - 1].getCp() != null && spaces[cpRow - 1][cpCol - 1].getCp().sameSide(cp);
            boolean topRightDiagBounded = cpRow - 1 >= 0 && cpCol + 1 <= 7
                    && spaces[cpRow - 1][cpCol + 1].getCp() != null && spaces[cpRow - 1][cpCol + 1].getCp().sameSide(cp);;
            boolean bottomLeftDiagBounded = cpRow + 1 <= 7 && cpCol - 1 >= 0
                    && spaces[cpRow + 1][cpCol - 1].getCp() != null && spaces[cpRow + 1][cpCol - 1].getCp().sameSide(cp);;
            boolean bottomRightDiagBounded = cpRow + 1 <= 7 && cpCol + 1 <= 7
                    && spaces[cpRow + 1][cpCol + 1].getCp() != null && spaces[cpRow + 1][cpCol + 1].getCp().sameSide(cp);;

            boolean diagonalBounded = (topLeftDiagBounded || cpRow == 0) && (topRightDiagBounded || cpRow == 0)
                    && (bottomLeftDiagBounded || cpRow == 7) && (bottomRightDiagBounded || cpRow == 7);

            // If bounded by other pieces or the board, then clear the piece's moves
            if ((leftBounded || cpCol == 0)
                    && (rightBounded || cpCol == 7)
                    && (topBounded || cpRow == 0)
                    && (bottomBounded || cpRow == 7) && (cp.isRook() || diagonalBounded)
                    || (cp.isBishop() && diagonalBounded)) {
                cpMoves.clear();
            } else {
                cpMoves = cp.getMoves();
            }
        }

        if (cp.isKing()) {
            // Handle castling
            if (
                (cp.isWhite() && 
                whiteCastlingValid && 
                destRank == sourceRank &&
                destFile == sourceFile + 2 &&
                spaces[cpRow][cpCol + 1].getCp() == null && spaces[cpRow][cpCol + 2].getCp() == null) 
                || (cp.isBlack() && blackCastlingValid && destRank == sourceRank &&
                destFile == sourceFile + 2 && spaces[cpRow][cpCol + 1].getCp() == null
                && spaces[cpRow][cpCol + 2].getCp() == null)) {
                cpMoves.add("" + (char) ((int) 'A' + cpRow) + (8 - cpCol - 2));

                spaces[cpRow][cpCol + 2].setCp(spaces[cpRow][cpCol].getCp());
                spaces[cpRow][cpCol + 1].setCp(spaces[cpRow][cpCol + 3].getCp());
                spaces[cpRow][cpCol].removeCp();
                spaces[cpRow][cpCol + 3].removeCp();

                repaint();

                if (cp.isWhite())
                    whiteCastlingValid = false;
                if (cp.isBlack())
                    blackCastlingValid = false;

                switchTurns();
            } else if ((cp.isWhite() && whiteCastlingValid && destRank == sourceRank &&
            destFile == sourceFile - 2 && spaces[cpRow][cpCol - 1].getCp() == null
                    && spaces[cpRow][cpCol - 2].getCp() == null) ||
                    (cp.isBlack() && blackCastlingValid && destRank == sourceRank &&
                    destFile == sourceFile - 2 && spaces[cpRow][cpCol - 1].getCp() == null
                            && spaces[cpRow][cpCol - 2].getCp() == null)) {
                cpMoves.add("" + (char) ((int) 'A' + cpRow) + (8 - cpCol + 2));

                spaces[cpRow][cpCol - 2].setCp(spaces[cpRow][cpCol].getCp());
                spaces[cpRow][cpCol - 1].setCp(spaces[cpRow][cpCol - 4].getCp());
                spaces[cpRow][cpCol].removeCp();
                spaces[cpRow][cpCol - 4].removeCp();

                repaint();

                if (cp.isWhite())
                    whiteCastlingValid = false;
                if (cp.isBlack())
                    blackCastlingValid = false;

                switchTurns();
            }
        }

        if (cp.isBishop()) {
            Bishop b = (Bishop) cp;
            ArrayList<String> firstDiagMoves = b.getDiagonal1Moves();
            b.resetRankAndFile();
            ArrayList<String> secondDiagMoves = b.getDiagonal2Moves();
            b.resetRankAndFile();
            ArrayList<String> thirdDiagMoves = b.getDiagonal3Moves();
            b.resetRankAndFile();
            ArrayList<String> fourthDiagMoves = b.getDiagonal4Moves();

            int firstBlockedDiag1 = firstDiagMoves.size() - 1;
            int firstBlockedDiag2 = secondDiagMoves.size() - 1;
            int firstBlockedDiag3 = thirdDiagMoves.size() - 1;
            int firstBlockedDiag4 = fourthDiagMoves.size() - 1;

            for (int i = 0; i < firstDiagMoves.size(); i++) {
                String diag1Coord = firstDiagMoves.get(i);
                char diag1CoordFile = diag1Coord.charAt(0);
                int diag1CoordRank = Integer.parseInt(diag1Coord, 1, 2, 10);

                if (
                    spaces[8 - diag1CoordRank][(int) diag1CoordFile - 'A'].getCp() != null
                ) {
                    firstBlockedDiag1 = i;
                    break;
                }
            }

            for (int i = 0; i < secondDiagMoves.size(); i++) {
                String diag2Coord = secondDiagMoves.get(i);
                char diag2CoordFile = diag2Coord.charAt(0);
                int diag2CoordRank = Integer.parseInt(diag2Coord, 1, 2, 10);

                if (
                    spaces[8 - diag2CoordRank][(int) diag2CoordFile - 'A'].getCp() != null
                ) {
                    firstBlockedDiag2 = i;
                    break;
                }
            }

            for (int i = 0; i < thirdDiagMoves.size(); i++) {
                String diag3Coord = thirdDiagMoves.get(i);
                char diag3CoordFile = diag3Coord.charAt(0);
                int diag3CoordRank = Integer.parseInt(diag3Coord, 1, 2, 10);

                if (
                    spaces[8 - diag3CoordRank][(int) diag3CoordFile - 'A'].getCp() != null
                ) {
                    firstBlockedDiag3 = i;
                    break;
                }
            }

            for (int i = 0; i < fourthDiagMoves.size(); i++) {
                String diag4Coord = fourthDiagMoves.get(i);
                char diag4CoordFile = diag4Coord.charAt(0);
                int diag4CoordRank = Integer.parseInt(diag4Coord, 1, 2, 10);

                if (
                    spaces[8 - diag4CoordRank][(int) diag4CoordFile - 'A'].getCp() != null
                ) {
                    firstBlockedDiag4 = i;
                    break;
                }
            }

            if (firstBlockedDiag1 >= 0) {
                for (int i = firstDiagMoves.size() - 1; i >= firstBlockedDiag1 + 2; i--) {
                    firstDiagMoves.remove(i);
                }
            }

            if (firstBlockedDiag2 >= 0) {
                for (int i = secondDiagMoves.size() - 1; i >= firstBlockedDiag2 + 1; i--) {
                    secondDiagMoves.remove(i);
                }
            }

            if (firstBlockedDiag3 >= 0) {
                for (int i = thirdDiagMoves.size() - 1; i >= firstBlockedDiag3 + 1; i--) {
                    thirdDiagMoves.remove(i);
                }
            }

            if (firstBlockedDiag4 >= 0) {
                for (int i = fourthDiagMoves.size() - 1; i >= firstBlockedDiag4 + 1; i--) {
                    fourthDiagMoves.remove(i);
                }
            }

            cpMoves.clear();

            if (cp.getMoves().containsAll(firstDiagMoves))
                cpMoves.addAll(firstDiagMoves);

            if (cp.getMoves().containsAll(secondDiagMoves))
                cpMoves.addAll(secondDiagMoves);

            if (cp.getMoves().containsAll(thirdDiagMoves))
                cpMoves.addAll(thirdDiagMoves);

            if (cp.getMoves().containsAll(fourthDiagMoves))
                cpMoves.addAll(fourthDiagMoves);
        }

        if (cp.isRook()) {
            Rook r = (Rook) cp;
            ArrayList<String> frontSpaces = r.getFrontSpaces();
            ArrayList<String> backSpaces = r.getBackSpaces();
            ArrayList<String> leftSpaces = r.getLeftSpaces();
            ArrayList<String> rightSpaces = r.getRightSpaces();

            int firstBlockedFront = frontSpaces.size() - 1;
            int firstBlockedBack = backSpaces.size() - 1;
            int firstBlockedLeft = leftSpaces.size() - 1;
            int firstBlockedRight = rightSpaces.size() - 1;

            for (int i = 0; i < frontSpaces.size(); i++) {
                String frontCoord = frontSpaces.get(i);
                char frontCoordFile = frontCoord.charAt(0);
                int frontCoordRank = Integer.parseInt(frontCoord, 1, 2, 10);

                if (
                    spaces[8 - frontCoordRank][(int) frontCoordFile - 'A'].getCp() != null
                ) {
                    firstBlockedFront = i;
                    break;
                }
            }

            for (int i = 0; i < backSpaces.size(); i++) {
                String backCoord = backSpaces.get(i);
                char backCoordFile = backCoord.charAt(0);
                int backCoordRank = Integer.parseInt(backCoord, 1, 2, 10);

                if (
                    spaces[8 - backCoordRank][(int) backCoordFile - 'A'].getCp() != null
                ) {
                    firstBlockedBack = i;
                    break;
                }
            }

            for (int i = 0; i < leftSpaces.size(); i++) {
                String leftCoord = leftSpaces.get(i);
                char leftCoordFile = leftCoord.charAt(0);
                int leftCoordRank = Integer.parseInt(leftCoord, 1, 2, 10);

                if (
                    spaces[8 - leftCoordRank][(int) leftCoordFile - 'A'].getCp() != null
                ) {
                    firstBlockedLeft = i;
                    break;
                }
            }

            for (int i = 0; i < rightSpaces.size(); i++) {
                String rightCoord = rightSpaces.get(i);
                char rightCoordFile = rightCoord.charAt(0);
                int rightCoordRank = Integer.parseInt(rightCoord, 1, 2, 10);

                if (
                    spaces[8 - rightCoordRank][(int) rightCoordFile - 'A'].getCp() != null
                ) {
                    firstBlockedRight = i;
                    break;
                }
            }

            if (firstBlockedFront >= 0) {
                for (int i = frontSpaces.size() - 1; i >= firstBlockedFront + 1; i--) {
                    frontSpaces.remove(i);
                }
            }

            if (firstBlockedBack >= 0) {
                for (int i = backSpaces.size() - 1; i >= firstBlockedBack + 1; i--) {
                    backSpaces.remove(i);
                }
            }

            if (firstBlockedLeft >= 0) {
                for (int i = leftSpaces.size() - 1; i >= firstBlockedLeft + 1; i--) {
                    leftSpaces.remove(i);
                }
            }

            if (firstBlockedRight >= 0) {
                for (int i = rightSpaces.size() - 1; i >= firstBlockedRight + 1; i--) {
                    rightSpaces.remove(i);
                }
            }

            cpMoves.clear();

            if (cp.getMoves().containsAll(frontSpaces))
                cpMoves.addAll(frontSpaces);

            if (cp.getMoves().containsAll(backSpaces))
                cpMoves.addAll(backSpaces);

            if (cp.getMoves().containsAll(leftSpaces))
                cpMoves.addAll(leftSpaces);

            if (cp.getMoves().containsAll(rightSpaces))
                cpMoves.addAll(rightSpaces);
        }

        if (cp.isQueen()) {
            Queen q = (Queen) cp;
            ArrayList<String> firstDiagMoves = q.getDiagonal1Moves();
            q.resetRankAndFile();
            ArrayList<String> secondDiagMoves = q.getDiagonal2Moves();
            q.resetRankAndFile();
            ArrayList<String> thirdDiagMoves = q.getDiagonal3Moves();
            q.resetRankAndFile();
            ArrayList<String> fourthDiagMoves = q.getDiagonal4Moves();

            int firstBlockedDiag1 = firstDiagMoves.size() - 1;
            int firstBlockedDiag2 = secondDiagMoves.size() - 1;
            int firstBlockedDiag3 = thirdDiagMoves.size() - 1;
            int firstBlockedDiag4 = fourthDiagMoves.size() - 1;

            for (int i = 0; i < firstDiagMoves.size(); i++) {
                String diag1Coord = firstDiagMoves.get(i);
                char diag1CoordFile = diag1Coord.charAt(0);
                int diag1CoordRank = Integer.parseInt(diag1Coord, 1, 2, 10);

                if (
                    spaces[8 - diag1CoordRank][(int) diag1CoordFile - 'A'].getCp() != null
                ) {
                    firstBlockedDiag1 = i;
                    break;
                }
            }

            for (int i = 0; i < secondDiagMoves.size(); i++) {
                String diag2Coord = secondDiagMoves.get(i);
                char diag2CoordFile = diag2Coord.charAt(0);
                int diag2CoordRank = Integer.parseInt(diag2Coord, 1, 2, 10);

                if (
                    spaces[8 - diag2CoordRank][(int) diag2CoordFile - 'A'].getCp() != null
                ) {
                    firstBlockedDiag2 = i;
                    break;
                }
            }

            for (int i = 0; i < thirdDiagMoves.size(); i++) {
                String diag3Coord = thirdDiagMoves.get(i);
                char diag3CoordFile = diag3Coord.charAt(0);
                int diag3CoordRank = Integer.parseInt(diag3Coord, 1, 2, 10);

                if (
                    spaces[8 - diag3CoordRank][(int) diag3CoordFile - 'A'].getCp() != null
                ) {
                    firstBlockedDiag3 = i;
                    break;
                }
            }

            for (int i = 0; i < fourthDiagMoves.size(); i++) {
                String diag4Coord = fourthDiagMoves.get(i);
                char diag4CoordFile = diag4Coord.charAt(0);
                int diag4CoordRank = Integer.parseInt(diag4Coord, 1, 2, 10);

                if (
                    spaces[8 - diag4CoordRank][(int) diag4CoordFile - 'A'].getCp() != null
                ) {
                    firstBlockedDiag4 = i;
                    break;
                }
            }

            if (firstBlockedDiag1 >= 0) {
                for (int i = firstDiagMoves.size() - 1; i >= firstBlockedDiag1 + 1; i--) {
                    firstDiagMoves.remove(i);
                }
            }

            if (firstBlockedDiag2 >= 0) {
                for (int i = secondDiagMoves.size() - 1; i >= firstBlockedDiag2 + 1; i--) {
                    secondDiagMoves.remove(i);
                }
            }

            if (firstBlockedDiag3 >= 0) {
                for (int i = thirdDiagMoves.size() - 1; i >= firstBlockedDiag3 + 1; i--) {
                    thirdDiagMoves.remove(i);
                }
            }

            if (firstBlockedDiag4 >= 0) {
                for (int i = fourthDiagMoves.size() - 1; i >= firstBlockedDiag4 + 1; i--) {
                    fourthDiagMoves.remove(i);
                }
            }

            cpMoves.clear();

            if (cp.getMoves().containsAll(firstDiagMoves))
                cpMoves.addAll(firstDiagMoves);

            if (cp.getMoves().containsAll(secondDiagMoves))
                cpMoves.addAll(secondDiagMoves);

            if (cp.getMoves().containsAll(thirdDiagMoves))
                cpMoves.addAll(thirdDiagMoves);

            if (cp.getMoves().containsAll(fourthDiagMoves)) {
                cpMoves.addAll(fourthDiagMoves);
            }

                ArrayList<String> frontSpaces = q.getFrontSpaces();
                ArrayList<String> backSpaces = q.getBackSpaces();
                ArrayList<String> leftSpaces = q.getLeftSpaces();
                ArrayList<String> rightSpaces = q.getRightSpaces();
    
                int firstBlockedFront = frontSpaces.size() - 1;
                int firstBlockedBack = backSpaces.size() - 1;
                int firstBlockedLeft = leftSpaces.size() - 1;
                int firstBlockedRight = rightSpaces.size() - 1;
    
                for (int i = 0; i < frontSpaces.size(); i++) {
                    String frontCoord = frontSpaces.get(i);
                    char frontCoordFile = frontCoord.charAt(0);
                    int frontCoordRank = Integer.parseInt(frontCoord, 1, 2, 10);
    
                    if (
                        spaces[8 - frontCoordRank][(int) frontCoordFile - 'A'].getCp() != null
                    ) {
                        firstBlockedFront = i;
                        break;
                    }
                }
    
                for (int i = 0; i < backSpaces.size(); i++) {
                    String backCoord = backSpaces.get(i);
                    char backCoordFile = backCoord.charAt(0);
                    int backCoordRank = Integer.parseInt(backCoord, 1, 2, 10);
    
                    if (
                        spaces[8 - backCoordRank][(int) backCoordFile - 'A'].getCp() != null
                    ) {
                        firstBlockedBack = i;
                        break;
                    }
                }
    
                for (int i = 0; i < leftSpaces.size(); i++) {
                    String leftCoord = leftSpaces.get(i);
                    char leftCoordFile = leftCoord.charAt(0);
                    int leftCoordRank = Integer.parseInt(leftCoord, 1, 2, 10);
    
                    if (
                        spaces[8 - leftCoordRank][(int) leftCoordFile - 'A'].getCp() != null
                    ) {
                        firstBlockedLeft = i;
                        break;
                    }
                }
    
                for (int i = 0; i < rightSpaces.size(); i++) {
                    String rightCoord = rightSpaces.get(i);
                    char rightCoordFile = rightCoord.charAt(0);
                    int rightCoordRank = Integer.parseInt(rightCoord, 1, 2, 10);
    
                    if (
                        spaces[8 - rightCoordRank][(int) rightCoordFile - 'A'].getCp() != null
                    ) {
                        firstBlockedRight = i;
                        break;
                    }
                }
    
                if (firstBlockedFront >= 0) {
                    for (int i = frontSpaces.size() - 1; i >= firstBlockedFront + 1; i--) {
                        frontSpaces.remove(i);
                    }
                }
    
                if (firstBlockedBack >= 0) {
                    for (int i = backSpaces.size() - 1; i >= firstBlockedBack + 1; i--) {
                        backSpaces.remove(i);
                    }
                }
    
                if (firstBlockedLeft >= 0) {
                    for (int i = leftSpaces.size() - 1; i >= firstBlockedLeft + 1; i--) {
                        leftSpaces.remove(i);
                    }
                }
    
                if (firstBlockedRight >= 0) {
                    for (int i = rightSpaces.size() - 1; i >= firstBlockedRight + 1; i--) {
                        rightSpaces.remove(i);
                    }
                }
    
                if (cp.getMoves().containsAll(frontSpaces))
                    cpMoves.addAll(frontSpaces);
    
                if (cp.getMoves().containsAll(backSpaces))
                    cpMoves.addAll(backSpaces);
    
                if (cp.getMoves().containsAll(leftSpaces))
                    cpMoves.addAll(leftSpaces);
    
                if (cp.getMoves().containsAll(rightSpaces))
                    cpMoves.addAll(rightSpaces);
        }

        return cpMoves;
    }

    /**
     * This is the method that handles movement of the actual chess piece. It takes into account the new position based on which space the user
     * selects to move to.
     */
    public void movePiece() {
        int sourceRow = 8 - sourceRank;
        int sourceCol = (int) sourceFile - 'A';

        if (spaces[sourceRow][sourceCol].getCp() != null) {
            ChessPiece thisCp = spaces[sourceRow][sourceCol].getCp();

            if (whiteToMove && thisCp.isBlack()) {
                JOptionPane.showMessageDialog(null, "White to move");
            } else if (blackToMove && thisCp.isWhite()) {
                JOptionPane.showMessageDialog(null, "Black to move");
            } else {
                int destRow = 8 - destRank;
                int destCol = (int) destFile - 'A';

                //promotion of pieces to a random type of piece
                if (thisCp.isPawn() && thisCp.isWhite() && destRow == 0){
                    int randomPiece = (int)(Math.random()*4);
                    if (randomPiece == 0){
                        spaces[destRow][destCol] = new Space(destRow, destCol, new Queen(destFile, destRank, "white"));
                    } else if (randomPiece == 1){
                        spaces[destRow][destCol] = new Space(destRow, destCol, new Knight(destFile, destRank, "white"));
                    } else if (randomPiece == 2){
                        spaces[destRow][destCol] = new Space(destRow, destCol, new Rook(destFile, destRank, "white"));
                    } else {
                        spaces[destRow][destCol] = new Space(destRow, destCol, new Bishop(destFile, destRank, "white"));
                    }
                    spaces[sourceRow][sourceCol] = new Space(sourceRank, sourceFile, null);
                    repaint();
                    switchTurns();
                }
                if (thisCp.isPawn() && thisCp.isBlack() && destRow == 7){
                    int randomPiece = (int)(Math.random()*4);
                    if (randomPiece == 0){
                        spaces[destRow][destCol] = new Space(destRow, destCol, new Queen(destFile, destRank, "black"));
                    } else if (randomPiece == 1){
                        spaces[destRow][destCol] = new Space(destRow, destCol, new Knight(destFile, destRank, "black"));
                    } else if (randomPiece == 2){
                        spaces[destRow][destCol] = new Space(destRow, destCol, new Rook(destFile, destRank, "black"));
                    } else {
                        spaces[destRow][destCol] = new Space(destRow, destCol, new Bishop(destFile, destRank, "black"));
                    }
                    spaces[sourceRow][sourceCol] = new Space(sourceRank, sourceFile, null);
                    repaint();
                    switchTurns();
                }

                // If the piece is a king and it is trying to move to a space that would not
                // result in a castle, then castling cannot occur
                // for the rest of the game

                if ((thisCp.isKing() && destRow != sourceRow && destCol != sourceCol + 2)
                        || (thisCp.isKing() && destRow != sourceRow && destCol != sourceCol - 2)) {
                    if (thisCp.isWhite())
                        whiteCastlingValid = false;
                    if (thisCp.isBlack())
                        blackCastlingValid = false;
                }

                if (destRow == sourceRow && destCol > sourceCol) {
                    if ((thisCp.isKing() && destRow == sourceRow && destCol != sourceCol + 2)) {
                        if (thisCp.isWhite())
                            whiteCastlingValid = false;
                        if (thisCp.isBlack())
                            blackCastlingValid = false;
                    }
                } else if (destRow == sourceRow && destCol < sourceCol) {
                    if ((thisCp.isKing() && destRow == sourceRow && destCol != sourceCol - 2)) {
                        if (thisCp.isWhite())
                            whiteCastlingValid = false;
                        if (thisCp.isBlack())
                            blackCastlingValid = false;
                    }
                }

                ArrayList<String> validMoves = getValidMoves(sourceRow, sourceCol, thisCp);

                if (validMoves.contains("" + destFile + destRank)) {
                    // Check if destination space is occupied
                    // Also see if the occupying piece is on the same side as the moving piece (if
                    // so, then the piece cannot
                    // move there as it would be capturing a piece on its own side)

                    if (spaces[destRow][destCol].getCp() != null && spaces[destRow][destCol].getCp().isKing()) {
                        JOptionPane.showMessageDialog(null, "Cannot capture king");
                    } else {
                        if ((spaces[destRow][destCol].getCp() == null)
                                || (spaces[destRow][destCol].getCp() != null
                                        && !spaces[destRow][destCol].getCp().sameSide(thisCp))) {

                            if (spaces[destRow][destCol].getCp() != null) {
                                lastPieceCaptured = spaces[destRow][destCol].getCp();
                            } else {
                                lastPieceCaptured = null;
                            }

                            spaces[destRow][destCol].setCp(thisCp);
                            spaces[sourceRow][sourceCol].removeCp();
                            

                            if (whiteToMove) {
                                whiteToMove = true;
                                blackToMove = false;
                            }

                            if (blackToMove) {
                                blackToMove = true;
                                whiteToMove = false;
                            }
                        }

                        repaint();

                        // Switch turns between players
                        switchTurns();

                        // Update the blackPieces and whitePieces lists
                        updateChessPieces();
                    }
                }
            }
        }
    }
    
    /**
        * Update the white and black chess piece lists (this is necessary since the board's state is constantly changing as pieces are moved and captured)
    */
    public void updateChessPieces() {
        for (int row = 0; row < spaces.length; row++) {
            for (int col = 0; col < spaces[0].length; col++) {
                if (spaces[row][col].getCp() != null && spaces[row][col].getCp().isBlack()) {
                    blackPieces.add(spaces[row][col].getCp());
                } else if (spaces[row][col].getCp() != null && spaces[row][col].getCp().isWhite()) {
                    whitePieces.add(spaces[row][col].getCp());
                }
            }
        }
    }

    /**
     * This method swtiches the turns of the users, black switches to white and white switches to black
     */
    public void switchTurns() {
        if (whiteToMove) {
            whiteToMove = false;
            blackToMove = true;
        } else {
            whiteToMove = true;
            blackToMove = false;
        }
    }

    /**
     * This method checks whether a piece is causing check on the King of the opposing side
     */
    public void isCausingCheck() {
        // If another piece's moves contain either the white king or black king pos, and
        // that piece is on the opposite
        // side of the king being checked, then that would be considered check
        
        // Essentially our check algorithm is as follows: loop through the opposing side's pieces and search for a piece that contains the king's position
        // If there is such a piece, then the king is in check
        
        // NOTE: We weren't able to figure out an effective algorithm for checkmate, but our game does implicitly record checkmate.
        // When the player is unable to resolve any check, then that would be considered checkmate.

        for (ChessPiece whitePiece : whitePieces) {
            if (!whitePiece.isKing() &&
                getValidMoves(8 - whitePiece.getRank(), (int) whitePiece.getFile() - 'A', whitePiece).contains(blackKingPos)
            ) {
                blackInCheck = true;
                JOptionPane.showMessageDialog(null, "Black in check");
                break;
            } else {
                blackInCheck = false;
            }
        }

        for (ChessPiece blackPiece : blackPieces) {
            if (!blackPiece.isKing() &&
                getValidMoves(8 - blackPiece.getRank(), (int) blackPiece.getFile() - 'A', blackPiece).contains(whiteKingPos)
            ) {
                whiteInCheck = true;
                JOptionPane.showMessageDialog(null, "White in check");
                break;
            } else {
                whiteInCheck = false;
            }
        }
    }
    
    /**
        * Get the current positions of the white and black king to determine check
    */
    public void getKingPositions() {
        if (spaces[8 - sourceRank][(int) sourceFile - 'A'].getCp() != null &&
            spaces[8 - sourceRank][(int) sourceFile - 'A'].getCp().isKing()) {
            if (spaces[8 - sourceRank][(int) sourceFile - 'A'].getCp().isWhite()) {
                whiteKingPos = "" + destFile + destRank;
            } else if (spaces[8 - sourceRank][(int) sourceFile - 'A'].getCp().isBlack()) {
                blackKingPos = "" + destFile + destRank;
            }
        }
    }

    /**
     * This method allows for the move of a piece to be undone.
     * Precondition: The space at [8 - destRank, (int) destFile - 'A'] has a chess piece
     */
    public void undoMove() {
        // This method is used to prevent the player from making a move that would either put them in check or leave an existing check unresolved
        
        if (lastPieceCaptured == null) {
            spaces[8 - sourceRank][(int) sourceFile - 'A'].setCp(spaces[8 - destRank][(int) destFile - 'A'].getCp());
            spaces[8 - destRank][(int) destFile - 'A'].removeCp();
        } else {
            spaces[8 - sourceRank][(int) sourceFile - 'A'].setCp(spaces[8 - destRank][(int) destFile - 'A'].getCp());
            spaces[8 - destRank][(int) destFile - 'A'].setCp(lastPieceCaptured);
        }

        repaint();
    }

    /**
     * The mouse pressed method handles determining the source coordinate and end coordinates based on where the user presses
     * @param evt the mouse event of pressing
     */
    public void mousePressed(MouseEvent evt) {
        numClicks++;

        // If the number of clicks is odd, then the user has selected a starting piece
        // and is trying to move
        if (numClicks % 2 == 1) {
            sourceFile = (char) ((int) 'A' + evt.getX() / 50);
            sourceRank = 8 - evt.getY() / 50;
        } else if (numClicks % 2 == 0) {
            // If the number of clicks is even, then the user has selected a destination to
            // move the piece to
            destFile = (char) ((int) 'A' + evt.getX() / 50);
            destRank = 8 - evt.getY() / 50;

            getKingPositions();

            if (whiteInCheck || blackInCheck) {
                if (whiteInCheck) {
                    movePiece();

                    if (
                        spaces[8 - Integer.parseInt(whiteCheckingPiecePos, 1, 2, 10)][(int) whiteCheckingPiecePos.charAt(0) - 'A'].getCp() != null &&
                        spaces[8 - Integer.parseInt(whiteCheckingPiecePos, 1, 2, 10)][(int) whiteCheckingPiecePos.charAt(0) - 'A'].getCp().isWhite()
                        && !spaces[8 - Integer.parseInt(blackCheckingPiecePos, 1, 2, 10)][(int) blackCheckingPiecePos.charAt(0) - 'A'].getCp().isKing()
                    ) {
                        whiteInCheck = false;
                    } else {
                        isCausingCheck();

                        if (whiteInCheck) {
                            undoMove();
                            JOptionPane.showMessageDialog(null, "White still in check");

                            blackToMove = false;
                            whiteToMove = true;
                        } else {
                            whiteToMove = false;
                            blackToMove = true;
                        }
                    }
                }

                if (blackInCheck) {
                    movePiece();

                    if (
                        spaces[8 - Integer.parseInt(blackCheckingPiecePos, 1, 2, 10)][(int) blackCheckingPiecePos.charAt(0) - 'A'].getCp() != null &&
                        spaces[8 - Integer.parseInt(blackCheckingPiecePos, 1, 2, 10)][(int) blackCheckingPiecePos.charAt(0) - 'A'].getCp().isBlack()
                        && !spaces[8 - Integer.parseInt(blackCheckingPiecePos, 1, 2, 10)][(int) blackCheckingPiecePos.charAt(0) - 'A'].getCp().isKing()
                    ) {
                        blackInCheck = false;
                    } else {
                        isCausingCheck();

                        if (blackInCheck) {
                            undoMove();
                            JOptionPane.showMessageDialog(null, "Black still in check");

                            blackToMove = true;
                            whiteToMove = false;
                        } else {
                            whiteToMove = true;
                            blackToMove = false;
                        }
                    }
                }
            } else {
                movePiece();
                isCausingCheck();

                if (spaces[8 - destRank][(int) destFile - 'A'].getCp() != null &&
                    spaces[8 - destRank][(int) destFile - 'A'].getCp().isBlack() && blackInCheck) {
                    JOptionPane.showMessageDialog(null, "Black, you cannot put yourself in check");
                    undoMove();

                    blackToMove = true;
                    whiteToMove = false;
                }

                if (spaces[8 - destRank][(int) destFile - 'A'].getCp() != null &&
                    spaces[8 - destRank][(int) destFile - 'A'].getCp().isWhite() && whiteInCheck) {
                    JOptionPane.showMessageDialog(null, "White, you cannot put yourself in check");
                    undoMove();

                    blackToMove = true;
                    whiteToMove = false;
                }

                if (blackInCheck) {
                    blackCheckingPiecePos = ""+destFile + destRank;
                }

                if (whiteInCheck) {
                    whiteCheckingPiecePos = ""+destFile + destRank;
                }
            }
        } else if (numClicks % 3 == 0) {
            // If the number of clicks is divisible by three, then reset the click count to
            // 0 (so that the user can redo their move after making a mistake)
            numClicks = 0;
        }
    }
    
    // These must be added to any class that implements mouse listener (we didn't need them)

    public void mouseClicked(MouseEvent evt) {
    }

    public void mouseReleased(MouseEvent evt) {
    }

    public void mouseEntered(MouseEvent evt) {
    }

    public void mouseExited(MouseEvent evt) {
    }

    /**
     * The Space class handles the actual spaces of the chess board
     */
    class Space {

        private int row;
        private int col;

        private ChessPiece cp;

        /**
         * The space constructor creates a space instance
         * @param row the row of the space
         * @param col the column of the space
         * @param cp the chess piece of the space
         */
        public Space(int row, int col, ChessPiece cp) {
            this.row = row;
            this.col = col;
            this.cp = cp;
        }

        /**
         * Getter method for the chess piece
         * @return the chess piece
         */
        public ChessPiece getCp() {
            return cp;
        }

        /**
         * The chess piece setter method
         * @param newChessPiece the chess piece to set the chess piece to
         */
        public void setCp(ChessPiece newChessPiece) {
            newChessPiece.setFile((char) ((int) 'A' + col));
            newChessPiece.setRank(8 - row);
            cp = newChessPiece;
        }

        /**
         * This method removes the chess piece by setting it to null
         */
        public void removeCp() {
            cp = null;
        }

    }

}
