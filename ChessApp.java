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
      * Recursive countdown (fun way to load the chess app GUI)
      * @param n - start of the countdown
    */
    public static void countdown(int n) {
        System.out.println(n);
        
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        if (n > 1) {
            countdown(n - 1);
        } else {
            System.out.println(0);
        }
    }

    /**
     * The main method that runs the game
     * @param args the arguments
     */
    public static void main(String[] args) {
        System.out.println("Loading...");
        countdown(10);
        
        ChessApp cApp = new ChessApp("Java Chess Game");
        cApp.setSize(400, 428);
        cApp.setResizable(false);
        cApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cApp.setVisible(true);
    }

}
