import javax.swing.*;
import java.awt.*;

public class CivCivPanel extends JPanel {

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2D = (Graphics2D) g;

        g2D.setPaint(new Color(0xFFFFAE00, true));
        g2D.setFont(new Font("Ink Free", Font.BOLD, 100));
        g2D.drawString("Sarı Civ Civ", 0, 100);
    }
}
