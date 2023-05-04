import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class BoardSpace extends JComponent {

    private int row;
    private int col;
    private ChessPiece cp;

    public BoardSpace(int row, int col, ChessPiece cp) {
        this.row = row;
        this.col = col;
        this.cp = cp;

        setBorder(new LineBorder(Color.BLACK));
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public ChessPiece getPiece() {
        return cp;
    }

    public void setPiece(ChessPiece other) {
        cp = other;
    }

    public void removePiece() {
        cp = null;
    }
    
}
