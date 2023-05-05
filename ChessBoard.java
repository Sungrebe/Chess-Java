import java.awt.*;
import javax.swing.*;

public class ChessBoard extends JFrame {

    JPanel boardPanel;
    BoardSpace[][] boardSpaces;

    public ChessBoard(String title) {
        super(title);

        boardSpaces = new BoardSpace[8][8];

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(8, 8));

        boardSpaces[0][0] = new Rook(0, 0, "black");
        boardSpaces[0][1] = new Knight(0, 1, "black");
        boardSpaces[0][2] = new Bishop(0, 2, "black");
        boardSpaces[0][3] = new Queen(0, 3, "black");
        boardSpaces[0][4] = new King(0, 4, "black");
        boardSpaces[0][5] = new Bishop(0, 5, "black");
        boardSpaces[0][6] = new Knight(0, 6, "black");
        boardSpaces[0][7] = new Rook(0, 7, "black");

        boardSpaces[1][0] = new Pawn(1, 0, "black");
        boardSpaces[1][1] = new Pawn(1, 1, "black");
        boardSpaces[1][2] = new Pawn(1, 2, "black");
        boardSpaces[1][3] = new Pawn(1, 3, "black");
        boardSpaces[1][4] = new Pawn(1, 4, "black");
        boardSpaces[1][5] = new Pawn(1, 5, "black");
        boardSpaces[1][6] = new Pawn(1, 6, "black");
        boardSpaces[1][7] = new Pawn(1, 7, "black");

        boardSpaces[6][0] = new Pawn(6, 0, "white");
        boardSpaces[6][1] = new Pawn(6, 1, "white");
        boardSpaces[6][2] = new Pawn(6, 2, "white");
        boardSpaces[6][3] = new Pawn(6, 3, "white");
        boardSpaces[6][4] = new Pawn(6, 4, "white");
        boardSpaces[6][5] = new Pawn(6, 5, "white");
        boardSpaces[6][6] = new Pawn(6, 6, "white");
        boardSpaces[6][7] = new Pawn(6, 7, "white");

        boardSpaces[7][0] = new Rook(7, 0, "white");
        boardSpaces[7][1] = new Knight(7, 1, "white");
        boardSpaces[7][2] = new Bishop(7, 2, "white");
        boardSpaces[7][3] = new Queen(7, 3, "white");
        boardSpaces[7][4] = new King(7, 4, "white");
        boardSpaces[7][5] = new Bishop(7, 5, "white");
        boardSpaces[7][6] = new Knight(7, 6, "white");
        boardSpaces[7][7] = new Rook(7, 7, "white");

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i > 1 && i < 6)
                    boardSpaces[i][j] = new BoardSpace(i, j);
                    
                boardPanel.add(boardSpaces[i][j]);
            }
        }

        setLayout(new BorderLayout());
        add(boardPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        ChessBoard cb = new ChessBoard("Java Chess Game");
        cb.setSize(500, 600);
        cb.setResizable(false);
        cb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cb.setVisible(true);
    }

}