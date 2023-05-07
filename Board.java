import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The Board class is responsible for setting up the layout of the board along with
 * the chess pieces on the board
 */
public class Board extends JComponent implements MouseListener {

    private Space[][] spaces;
    private int numClicks = 0;
    private int startX = 0;
    private int startY = 0;
    private int endX = 0;
    private int endY = 0;
    /**
     * The Board constructor sets up the chess board with the appropriate pieces
     */
    public Board() {
        spaces = new Space[8][8];

        spaces[0][0] = new Space("A8", new Rook("A8", "black"));
        spaces[0][1] = new Space("B8", new Knight("B8", "black"));
        spaces[0][2] = new Space("C8", new Bishop("C8", "black"));
        spaces[0][3] = new Space("D8", new Queen("D8", "black"));
        spaces[0][4] = new Space("E8", new King("E8", "black"));
        spaces[0][5] = new Space("F8", new Bishop("F8", "black"));
        spaces[0][6] = new Space("G8", new Knight("G8", "black"));
        spaces[0][7] = new Space("H8", new Rook("H8", "black"));

        spaces[1][0] = new Space("A7", new Pawn("A7", "black"));
        spaces[1][1] = new Space("B7", new Pawn("B7", "black"));
        spaces[1][2] = new Space("C7", new Pawn("C7", "black"));
        spaces[1][3] = new Space("D7", new Pawn("D7", "black"));
        spaces[1][4] = new Space("E7", new Pawn("E7", "black"));
        spaces[1][5] = new Space("F7", new Pawn("F7", "black"));
        spaces[1][6] = new Space("G7", new Pawn("G7", "black"));
        spaces[1][7] = new Space("H7", new Pawn("H7", "black"));

        spaces[2][0] = new Space("A6", null);
        spaces[2][1] = new Space("B6", null);
        spaces[2][2] = new Space("C6", null);
        spaces[2][3] = new Space("D6", null);
        spaces[2][4] = new Space("E6", null);
        spaces[2][5] = new Space("F6", null);
        spaces[2][6] = new Space("G6", null);
        spaces[2][7] = new Space("H6", null);

        spaces[3][0] = new Space("A5", null);
        spaces[3][1] = new Space("B5", null);
        spaces[3][2] = new Space("C5", null);
        spaces[3][3] = new Space("D5", null);
        spaces[3][4] = new Space("E5", null);
        spaces[3][5] = new Space("F5", null);
        spaces[3][6] = new Space("G5", null);
        spaces[3][7] = new Space("H5", null);

        spaces[4][0] = new Space("A4", null);
        spaces[4][1] = new Space("B4", null);
        spaces[4][2] = new Space("C4", null);
        spaces[4][3] = new Space("D4", null);
        spaces[4][4] = new Space("E4", null);
        spaces[4][5] = new Space("F4", null);
        spaces[4][6] = new Space("G4", null);
        spaces[4][7] = new Space("H4", null);

        spaces[5][0] = new Space("A3", null);
        spaces[5][1] = new Space("B3", null);
        spaces[5][2] = new Space("C3", null);
        spaces[5][3] = new Space("D3", null);
        spaces[5][4] = new Space("E3", null);
        spaces[5][5] = new Space("F3", null);
        spaces[5][6] = new Space("G3", null);
        spaces[5][7] = new Space("H3", null);

        spaces[6][0] = new Space("A2", new Pawn("A2", "white"));
        spaces[6][1] = new Space("B2", new Pawn("B2", "white"));
        spaces[6][2] = new Space("C2", new Pawn("C2", "white"));
        spaces[6][3] = new Space("D2", new Pawn("D2", "white"));
        spaces[6][4] = new Space("E2", new Pawn("E2", "white"));
        spaces[6][5] = new Space("F2", new Pawn("F2", "white"));
        spaces[6][6] = new Space("G2", new Pawn("G2", "white"));
        spaces[6][7] = new Space("H2", new Pawn("H2", "white"));

        spaces[7][0] = new Space("A1", new Rook("A1", "white"));
        spaces[7][1] = new Space("B1", new Knight("B1", "white"));
        spaces[7][2] = new Space("C1", new Bishop("C1", "white"));
        spaces[7][3] = new Space("D1", new Queen("D1", "white"));
        spaces[7][4] = new Space("E1", new King("E1", "white"));
        spaces[7][5] = new Space("F1", new Bishop("F1", "white"));
        spaces[7][6] = new Space("G1", new Knight("G1", "white"));
        spaces[7][7] = new Space("H1", new Rook("H1", "white"));

        addMouseListener(this);
    }
    /**
     * paintComponent is a method that sets up the chess board with alternating green and yellow squares
     * @param g is the graphics object that paints the board
     */
    protected void paintComponent(Graphics g) {
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

                if (spaces[row][col].hasPiece()) {
                    Piece cpToDraw = spaces[row][col].getCp();
                    Image cpImg = Toolkit.getDefaultToolkit().getImage("assets/" + cpToDraw.getType() + " " + cpToDraw.getSide() + ".png");

                    g2d.drawImage(cpImg, 2 + col * 50, 3 + row * 50, 45, 45, new Color(0f, 0f, 0f, 0), this);
                }
            }
        }
    }
    /**
     * The Space class is responsible for movement of pieces and associating pieces with 
     * their coordinates on the chess board
     */
    class Space {

        private String coordinate;
        private Piece cp;
        
        /**
         * This is the Space constructor
         * @param coordinate the coordinate of the piece
         * @param cp the chess piece
         */
        public Space(String coordinate, Piece cp) {
            this.coordinate = coordinate;
            this.cp = cp;
        } 

        /**
         * This is the getter method for the chess piece
         * @return the chess piece
         */
        public Piece getCp() {
            return cp;
        }

        /**
         * This is the getter method for the coordinate
         * @return the coordinate
         */
        public String getCoordinate() {
            return coordinate;
        }

        /**
         * This method checks whether a space has a piece and returns true or false
         * @return true if the space has a piece and false if it doesn't
         */
        public boolean hasPiece() {
            return cp != null;
        }

        /**
         * The movePiece method moves a piece from one space to another
         * @param destX the x coordinate of the new space
         * @param destY the y coordinate of the new space
         */

        public void movePiece(int destX, int destY) {
            if (hasPiece()) {
                int sourceRow = getRow(this.coordinate);
                int sourceCol = getCol(this.coordinate);

                spaces[destX][destY].setPiece(cp);
                spaces[sourceRow][sourceCol].setPiece(null);

                repaint();
            } else {
                System.out.println("No piece to move");
            }
        }

        /**
         * The getRow method returns the row of the coordinate
         * @param coord the coordinate of the piece
         * @return the row of the piece
         */
        public int getRow(String coord) {
            int coordX = Integer.parseInt(coord.substring(1, 2));

            switch (coordX) {
                case 8:
                    return coordX - 8;
                case 7:
                    return coordX - 6;
                case 6:
                    return coordX - 4;
                case 5:
                    return coordX - 2;
                case 4:
                    return coordX;
                case 3:
                    return coordX + 2;
                case 2:
                    return coordX + 4;
                case 1:
                    return coordX + 6;
                default:
                    return -1;
            }
        }
        
        /**
         * The getCol method returns the column of the coordinate
         * @param coord the coordinate to return the column of
         * @return the column of the coordinate
         */
        public int getCol(String coord) {
            String coordY = coord.substring(0, 1);

            switch (coordY) {
                case "A":
                    return 0;
                case "B":
                    return 1;
                case "C":
                    return 2;
                case "D":
                    return 3;
                case "E":
                    return 4;
                case "F":
                    return 5;
                case "G":
                    return 6;
                case "H":
                    return 7;
                default:
                    return -1;
            }
        }

        /**
         * The method setPiece sets a chess piece to a new space
         * @param newPiece the piece to set to the space
         */
        public void setPiece(Piece newPiece) {
            cp = newPiece;
        }

    }

    /**
     * 
     */
    public void mouseClicked(MouseEvent evt) {
        // code goes here
    }

    /**
     * This method is used to move a piece when a player presses a piece and new space
     * @param evt the event of clicking the pieces and spaces
     */
    public void mousePressed(MouseEvent evt) {
        numClicks++;

        if (numClicks % 2 == 0) {
            endX = evt.getY() / 50;
            endY = evt.getX() / 50;

            spaces[startX][startY].movePiece(endX, endY);
        } else {
            startX = evt.getY() / 50;
            startY = evt.getX() / 50;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println("mouse released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

}