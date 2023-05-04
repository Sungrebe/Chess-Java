import javax.swing.*;

public class ChessBoard extends JFrame {
    public ChessBoard(String title) {
        super(title);
    }

    public static void main(String[] args) {
        ChessBoard cb = new ChessBoard("Java Chess Game");
        cb.setSize(400, 400);
        cb.setVisible(true);
    }
}