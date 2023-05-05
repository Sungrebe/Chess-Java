import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class BoardSpace extends JComponent {

    private int row;
    private int col;

    public BoardSpace(int row, int col) {
        this.row = row;
        this.col = col;

        setBorder(new LineBorder(Color.BLACK));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        Dimension size = getSize();

        if (row % 2 == 0) {
            if (col % 2 == 0) {
                g2.setColor(new Color(255, 183, 123, 100));
            } else {
                g2.setColor(new Color(102, 73, 44, 100));
            }
        } else {
            if (col % 2 == 0) {
                g2.setColor(new Color(102, 73, 44, 100));
            } else {
                g2.setColor(new Color(255, 183, 123, 100));
            }
        }

        g2.fillRect(0, 0, size.width, size.height);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

}
