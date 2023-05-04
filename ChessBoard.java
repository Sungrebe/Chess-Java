import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ChessBoard extends JFrame {

    JPanel boardPanel;
    JPanel infoPanel;

    public ChessBoard(String title) {
        super(title);

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(8, 8));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boardPanel.add(new BoardSpace(i, j, null));
            }
        }

        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.X_AXIS));
        infoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel whiteInfoPanel = new JPanel();
        whiteInfoPanel.setLayout(new BoxLayout(whiteInfoPanel, BoxLayout.Y_AXIS));
        whiteInfoPanel.add(new JLabel("White"));
        whiteInfoPanel.add(new JLabel("Time Elapsed - 00:00 minutes"));

        JPanel blackInfoPanel = new JPanel();
        blackInfoPanel.setLayout(new BoxLayout(blackInfoPanel, BoxLayout.Y_AXIS));
        blackInfoPanel.add(new JLabel("Black"));
        blackInfoPanel.add(new JLabel("Time Elapsed - 00:00 minutes"));

        infoPanel.add(whiteInfoPanel);
        infoPanel.add(Box.createHorizontalGlue());
        infoPanel.add(blackInfoPanel);
        
        setLayout(new BorderLayout());
        add(boardPanel, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        ChessBoard cb = new ChessBoard("Java Chess Game");
        cb.setSize(500, 600);
        cb.setResizable(false);
        cb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cb.setVisible(true);
    }

}