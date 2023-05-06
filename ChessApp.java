import java.awt.*;
import javax.swing.*;

public class ChessApp extends JFrame {

    public ChessApp(String title) {
        super(title);

        setLayout(new BorderLayout());
        add(new Board(), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        ChessApp ca = new ChessApp("Java Chess Game");
        ca.setSize(400, 428);
        ca.setResizable(false);
        ca.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ca.setVisible(true);
    }

}