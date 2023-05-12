import java.awt.*;
import javax.swing.*;

class ChessApp extends JFrame {

    public ChessApp(String title) {
        super(title);

        setLayout(new BorderLayout());
        add(new Board(), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        ChessApp cApp = new ChessApp("Java Chess Game");
        cApp.setSize(400, 428);
        cApp.setResizable(false);
        cApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cApp.setVisible(true);
    }

}