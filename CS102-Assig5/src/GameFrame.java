import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public int width = 700;
    public int height = 700;
    public static int depth = 2;
    private GamePanel gamePanel;

    public GameFrame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setLayout(new BorderLayout());

        gamePanel = new GamePanel(depth, this);
        this.getContentPane().add(gamePanel, BorderLayout.CENTER);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
