import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

public class BusRoadPanel extends JPanel {

    final int PANEL_WIDTH, PANEL_HEIGHT;
    final int WINDOW_WIDTH;
    int busLength, wheelNo;
    int busX, busY;
    int xVelocity, yVelocity;
    public Timer timer;
    boolean flip;

    public BusRoadPanel(int PANEL_WIDTH, int PANEL_HEIGHT, int busLength, int wheelNo) {
        this.PANEL_WIDTH = PANEL_WIDTH;
        this.PANEL_HEIGHT = PANEL_HEIGHT;

        this.busLength = busLength;
        this.wheelNo = wheelNo;

        this.busX = 100;
        this.busY = 140;
        this.xVelocity = 20;
        this.yVelocity = 20;

        this.WINDOW_WIDTH = 50;

        this.flip = false;

        this.setBounds(0, 800 - PANEL_HEIGHT, PANEL_WIDTH, PANEL_HEIGHT);

        ButtonPanels.updateButton.addActionListener(new buttonListener());
        timer = new Timer(40, new MyListener());
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;

        g2D.setPaint(new Color(0x212121));
        g2D.fillRect(0, 300, this.getWidth(), this.getHeight() - 200);

        g2D.setPaint(new Color(0xD0CBCB));
        for (int i = 10; i <= this.getWidth(); i += 70)
            g2D.fillRect(i, this.getHeight() / 2 + 125, 30, 10);

        g2D.setPaint(new Color(0x462C26));
        g2D.fillRect(this.getWidth() - 200, 150, 40, 150);

        g2D.setPaint(new Color(0x23A435));
        g2D.fillOval(this.getWidth() - 227, 100, 100, 100);

        g2D.setPaint(new Color(0xFFF411));
        int[] busXs = {busX, busX + busLength - 80, busX + busLength - 80, busX + busLength - 30, busX + busLength - 30, busX};
        int[] busYs = {busY + 60, busY + 60, busY + 140, busY + 140, busY + 210, busY + 210};
        g2D.fillPolygon(busXs, busYs, 6);

        g2D.setPaint(new Color(0xFFFFAE00));
        g2D.fillRect(busX, busY + 150, busLength - 30, 20);

        g2D.setPaint(new Color(0x78BAE0));
        for (int i = busX + 15; i + WINDOW_WIDTH < busX + busLength - 80; i += WINDOW_WIDTH + 10) {
            g2D.fillRect(i, busY + 80, WINDOW_WIDTH, WINDOW_WIDTH);
        }

        g2D.setPaint(new Color(0x4B3636));
        int wheelD = busLength / (wheelNo * 2);
        int j = 0;
        for (int i = 0; i < wheelNo; i++) {
            g2D.fillOval(busX + j, busY + 210 - (wheelD / 2), wheelD, wheelD);
            j+= wheelD * 2;
        }
    }


    public class buttonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (ButtonPanels.lengthField.getText().isEmpty() || Integer.parseInt(ButtonPanels.lengthField.getText()) < 300 || busX + Integer.parseInt(ButtonPanels.lengthField.getText()) + 30
                    > PANEL_WIDTH) {
                JOptionPane.showMessageDialog(null, "Enter Valid Length", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            else  {
                busLength = Integer.parseInt(ButtonPanels.lengthField.getText());
                ButtonPanels.lengthField.setText("");
            }

            if (!ButtonPanels.wheelField.getText().isEmpty()) {
                wheelNo = Integer.parseInt(ButtonPanels.wheelField.getText());
                ButtonPanels.wheelField.setText("");
            }

            repaint();
        }
    }

    private class MyListener implements ActionListener {
        int count = 1;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (busX < 0 || busX + busLength - 20 >= PANEL_WIDTH)
                xVelocity *= -1;

            count++;
            if (count >= 10) {
                yVelocity *= -1;
                count -= 10 ;
            }

            busX += xVelocity;
            busY += yVelocity;

            repaint();
        }
    }
}
