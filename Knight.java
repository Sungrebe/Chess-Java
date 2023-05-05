import java.awt.*;

public class Knight extends ChessPiece {

    public Knight(int pieceRow, int pieceCol, String side) {
        super(pieceRow, pieceCol, "knight", side);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        String fName = "";

        if (super.getSide().equals("black")) {
            fName = "assets/knight black.png";
        } else if (super.getSide().equals("white")) {
            fName = "assets/knight white.png";
        }

        Image cpImg = Toolkit.getDefaultToolkit().getImage(fName);
        g2d.drawImage(cpImg, 10, 10, 40, 40, new Color(0f, 0f, 0f, 0), this);
    }

    public BoardSpace[] getMoves() {
        // code goes here


        return null;
    }

    void move(BoardSpace toSpace) {
        if (toSpace instanceof ChessPiece) {
            // call capture
        } else {
            // move the piece
        }
    }
    
}
