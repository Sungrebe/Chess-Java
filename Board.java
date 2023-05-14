import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Board extends JComponent implements MouseListener {
    
    private Space[][] spaces;

    // Determine player turns
    private boolean whiteToMove;
    private boolean blackToMove;

    // Get the current positions of the white king and the black king (to determine check and checkmate)
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

        spaces[2][0] = new Space(2, 0, null);
		spaces[2][1] = new Space(2, 1, null);
		spaces[2][2] = new Space(2, 2, null);
		spaces[2][3] = new Space(2, 3, null);
		spaces[2][4] = new Space(2, 4, null);
		spaces[2][5] = new Space(2, 5, null);
		spaces[2][6] = new Space(2, 6, null);
		spaces[2][7] = new Space(2, 7, null);

		spaces[3][0] = new Space(3, 0, null);
		spaces[3][1] = new Space(3, 1, null);
		spaces[3][2] = new Space(3, 2, null);
		spaces[3][3] = new Space(3, 3, null);
		spaces[3][4] = new Space(3, 4, null);
		spaces[3][5] = new Space(3, 5, null);
		spaces[3][6] = new Space(3, 6, null);
		spaces[3][7] = new Space(3, 7, null);

		spaces[4][0] = new Space(4, 0, null);
		spaces[4][1] = new Space(4, 1, null);
		spaces[4][2] = new Space(4, 2, null);
		spaces[4][3] = new Space(4, 3, null);
		spaces[4][4] = new Space(4, 4, null);
		spaces[4][5] = new Space(4, 5, null);
		spaces[4][6] = new Space(4, 6, null);
		spaces[4][7] = new Space(4, 7, null);

		spaces[5][0] = new Space(5, 0, null);
		spaces[5][1] = new Space(5, 1, null);
		spaces[5][2] = new Space(5, 2, null);
		spaces[5][3] = new Space(5, 3, null);
		spaces[5][4] = new Space(5, 4, null);
		spaces[5][5] = new Space(5, 5, null);
		spaces[5][6] = new Space(5, 6, null);
		spaces[5][7] = new Space(5, 7, null);

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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        for (int row = 0; row < spaces.length; row++) {
            for (int col = 0; col < spaces[0].length; col++) {
                if (row % 2 == 0) {
                    if (col % 2 == 0) g2d.setColor(Color.decode("#F0E68C"));
                    if (col % 2 != 0) g2d.setColor(Color.decode("#98BF64"));
                } else {
                    if (col % 2 == 0) g2d.setColor(Color.decode("#98BF64"));
                    if (col % 2 != 0) g2d.setColor(Color.decode("#F0E68C"));
                }
            
                g2d.fillRect(col * 50, row * 50, 50, 50);

                if (spaces[row][col].getCp() != null) {
                    ChessPiece c = spaces[row][col].getCp();
                    Image cpImg = Toolkit.getDefaultToolkit().getImage("assets/" + c.getName() + " " + c.getSide() + ".png");

                    g2d.drawImage(cpImg, 2 + col * 50, 3 + row * 50, 45, 45, new Color(0f, 0f, 0f, 0), this);
                }
            }
        }
    }

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
                    System.out.println("bounded in front");
                    cpMoves.clear();
                } else {
                    cpMoves = p.getMoves();
                }

                if (cpRow - 1 >= 0 && cpCol - 1 >= 0 && spaces[cpRow - 1][cpCol - 1].getCp() != null) {
                    // If top left diagonal has a piece
                    cpMoves.add(""+(char) ((int) 'A' + cpCol - 1) + (8 - cpRow + 1));
                } else if (cpRow - 1 >= 0 && cpCol + 1 <= 7 && spaces[cpRow - 1][cpCol + 1].getCp() != null) {
                    // If top right diagonal has a piece
                    cpMoves.add(""+(char) ((int) 'A' + cpCol + 1) + (8 - cpRow + 1));
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
                    cpMoves.add(""+(char) ((int) 'A' + cpCol - 1) + (8 - cpRow - 1));
                } else if (cpRow + 1 <= 7 && cpCol + 1 <= 7 && spaces[cpRow + 1][cpCol + 1].getCp() != null) {
                    // If bottom right diagonal has a piece
                    cpMoves.add(""+(char) ((int) 'A' + cpCol - 1) + (8 - cpRow - 1));
                }
            }
        } else if (!cp.isKnight()) {
            // All other pieces (except knights) cannot move if they bounded on all sides
            boolean leftBounded = cpCol - 1 >= 0 && spaces[cpRow][cpCol - 1].getCp() != null;
            boolean rightBounded = cpCol + 1 <= 7 && spaces[cpRow][cpCol + 1].getCp() != null;
            boolean topBounded = cpRow - 1 >= 0 && spaces[cpRow - 1][cpCol].getCp() != null;
            boolean bottomBounded = cpRow + 1 <= 7 && spaces[cpRow + 1][cpCol].getCp() != null;

            // Check if diagonals are bounded (this applies to queens, bishops, and kings)
            boolean topLeftDiagBounded = cpRow - 1 >= 0 && cpCol - 1 >= 0 && spaces[cpRow - 1][cpCol - 1].getCp() != null;
            boolean topRightDiagBounded = cpRow - 1 >= 0 && cpCol + 1 <= 7 && spaces[cpRow - 1][cpCol + 1].getCp() != null;
            boolean bottomLeftDiagBounded = cpRow + 1 <= 7 && cpCol - 1 >= 0 && spaces[cpRow + 1][cpCol - 1].getCp() != null;
            boolean bottomRightDiagBounded = cpRow + 1 <= 7 && cpCol + 1 <= 7 && spaces[cpRow + 1][cpCol + 1].getCp() != null;

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
                (cp.isWhite() && whiteCastlingValid && spaces[cpRow][cpCol + 1].getCp() == null && spaces[cpRow][cpCol + 2].getCp() == null) ||
                (cp.isBlack() && blackCastlingValid && spaces[cpRow][cpCol + 1].getCp() == null && spaces[cpRow][cpCol + 2].getCp() == null)) {
                    cpMoves.add(""+(char) ((int) 'A' + cpRow) + (8 - cpCol - 2));

                    spaces[cpRow][cpCol + 2].setCp(spaces[cpRow][cpCol].getCp());
                    spaces[cpRow][cpCol + 1].setCp(spaces[cpRow][cpCol + 3].getCp());
                    spaces[cpRow][cpCol].removeCp();
                    spaces[cpRow][cpCol + 3].removeCp();

                    repaint();

                    if (cp.isWhite()) whiteCastlingValid = false;
                    if (cp.isBlack()) blackCastlingValid = false;

                    switchTurns();
            } else if (
                (cp.isWhite() && whiteCastlingValid && spaces[cpRow][cpCol - 1].getCp() == null && spaces[cpRow][cpCol - 2].getCp() == null) ||
                (cp.isBlack() && blackCastlingValid && spaces[cpRow][cpCol - 1].getCp() == null && spaces[cpRow][cpCol - 2].getCp() == null)) {
                    cpMoves.add(""+(char) ((int) 'A' + cpRow) + (8 - cpCol + 2));

                    spaces[cpRow][cpCol - 2].setCp(spaces[cpRow][cpCol].getCp());
                    spaces[cpRow][cpCol - 1].setCp(spaces[cpRow][cpCol - 4].getCp());
                    spaces[cpRow][cpCol].removeCp();
                    spaces[cpRow][cpCol - 4].removeCp();

                    repaint();

                    if (cp.isWhite()) whiteCastlingValid = false;
                    if (cp.isBlack()) blackCastlingValid = false;

                    switchTurns();
            }
        }

        return cpMoves;
    }
    
    public void movePiece() {
        int sourceRow = 8 - sourceRank;
        int sourceCol = (int) sourceFile - 'A';

        if (spaces[sourceRow][sourceCol].getCp() != null) {
            ChessPiece thisCp = spaces[sourceRow][sourceCol].getCp();

            if (whiteToMove && thisCp.isBlack()) {
                System.out.println("White to move");
            } else if (blackToMove && thisCp.isWhite()) {
                System.out.println("Black to move");
            } else {
                int destRow = 8 - destRank;
                int destCol = (int) destFile - 'A';

                // If the piece is a king and it is trying to move to a space that would not result in a castle, then castling cannot occur
                // for the rest of the game
                if ((thisCp.isKing() && destRow != sourceRow && destCol != sourceCol + 2)
                || (thisCp.isKing() && destRow != sourceRow && destCol != sourceCol - 2)) {
                    if (thisCp.isWhite()) whiteCastlingValid = false;
                    if (thisCp.isBlack()) blackCastlingValid = false;
                }

                ArrayList<String> validMoves = getValidMoves(sourceRow, sourceCol, thisCp);

                if (validMoves.contains(""+destFile + destRank)) {
                    // Check if destination space is occupied
                    // Also see if the occupying piece is on the same side as the moving piece (if so, then the piece cannot
                    // move there as it would be capturing a piece on its own side)
                    
                    if ((spaces[destRow][destCol].getCp() == null)
                        || (spaces[destRow][destCol].getCp() != null && !spaces[destRow][destCol].getCp().sameSide(thisCp))
                    ) {
                        spaces[destRow][destCol].setCp(thisCp);
                        spaces[sourceRow][sourceCol].removeCp();

                        if (thisCp.isKing() && thisCp.isWhite()) whiteKingPos = ""+destFile + destRank;
                        if (thisCp.isKing() && thisCp.isBlack()) blackKingPos = ""+destFile + destRank;
                    }

                    repaint();

                    // Switch turns between players
                    switchTurns();
                }
            }
        }
    }

    private void switchTurns() {
        if (whiteToMove) {
            whiteToMove = false;
            blackToMove = true;
        } else {
            whiteToMove = true;
            blackToMove = false;
        }
    }

    private void isCausingCheck(char sourceF, int sourceR) {
        // If another piece's moves contain either the white king or black king pos, and that piece is on the opposite
        // side of the king being checked, then that would be considered check
        ChessPiece aCp = spaces[8 - sourceR][(int) sourceF - 'A'].getCp();

        if (aCp != null && !aCp.isKing()) {
            if (aCp.isBlack()) {
                System.out.println(aCp.getMoves().contains(whiteKingPos));
                if (aCp.getMoves().contains(whiteKingPos)) {
                    whiteInCheck = true;
                } else {
                    whiteInCheck = false;
                }
            } else if (aCp.isWhite()) {
                if (aCp.getMoves().contains(blackKingPos)) {
                    blackInCheck = true;
                } else {
                    blackInCheck = false;
                }
            }
        }
    }

    public void mousePressed(MouseEvent evt) {
        numClicks++;

        // If the number of clicks is odd, then the user has selected a starting piece and is trying to move
        if (numClicks % 2 == 1) {
            sourceFile = (char) ((int) 'A' + evt.getX() / 50);
            sourceRank = 8 - evt.getY() / 50;
        } else if (numClicks % 2 == 0) {
            // If the number of clicks is even, then the user has selected a destination to move the piece to
            destFile = (char) ((int) 'A' + evt.getX() / 50);
            destRank = 8 - evt.getY() / 50;

            if (whiteInCheck) {
                isCausingCheck(destFile, destRank);

                System.out.println("white in check: " + whiteInCheck);
                
                if (whiteInCheck) {
                    System.out.println("white, move out of check");
                } else {
                    movePiece();
                }
            } else {
                movePiece();
                isCausingCheck(destFile, destRank);
            }
        } else if (numClicks % 3 == 0) {
            // If the number of clicks is divisible by three, then reset the click count to 0 (so that the user can make a second move)
            numClicks = 0;
        }
    }

    public void mouseClicked(MouseEvent evt) {}

    public void mouseReleased(MouseEvent evt) {}

    public void mouseEntered(MouseEvent evt) {}

    public void mouseExited(MouseEvent evt) {}

    class Space {

        private int row;
        private int col;

		private ChessPiece cp;
		
		public Space(int row, int col, ChessPiece cp) {
            this.row = row;
            this.col = col;
			this.cp = cp;
		}

		public ChessPiece getCp() {
			return cp;
		}

        public void setCp(ChessPiece newChessPiece) {
            newChessPiece.setFile((char) ((int) 'A' + col));
            newChessPiece.setRank(8 - row);
            cp = newChessPiece;
        }

        public void removeCp() {
            cp = null;
        }

	}

}