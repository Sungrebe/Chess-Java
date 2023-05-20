import java.awt.*;
import javax.swing.*;

/**
 * The ChessApp class handles the chess game
 */
class ChessApp extends JFrame {

    /**
     * ChessApp constructor
     * @param title the title of the game
     */
    public ChessApp(String title) {
        super(title);

        setLayout(new BorderLayout());
        add(new Board(), BorderLayout.CENTER);
    }

    /**
     * The main method that runs the game
     * @param args the arguments
     */
    public static void main(String[] args) {
        ChessApp cApp = new ChessApp("Java Chess Game");
        cApp.setSize(400, 428);
        cApp.setResizable(false);
        cApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cApp.setVisible(true);
    }

}