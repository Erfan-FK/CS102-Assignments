import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageSorter extends JFrame implements KeyListener, ActionListener {

    private BufferedImage image;
    private int height;
    private int width;
    private JLabel imageLabel;
    private Timer timer;
    private static int column = 0;
    private static int row = 0;
    private String imagePath = "panda.jpg";

    public ImageSorter() throws IOException {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.imageLabel = new JLabel();

        loadImage(imagePath);
        displayImage();

        this.pack();
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        startAnimatedBubbleSort('d');
    }

    public void startAnimatedBubbleSort(char type) {
        this.timer = new Timer(100, e -> {
           if (type == 'h') {
                horizontalStep();
           } else if (type == 'v') {
               verticalStep();
           } else {
               diagonalStep();
           }
        });

        this.addKeyListener(this);
        timer.start();
    }

    public void diagonalStep() {
        if (row < height)
            horizontalStep();
        else
            verticalStep();

    }

    public void verticalStep() {
        if (column  >= width) {
            return;
        }

        for (int i = 0; i < height - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < height - i - 1; j++) {
                if (calculateLuminance(column, j) < calculateLuminance(column, j + 1)) {
                    swap(column, j, column, j + 1);
                    swapped = true;
                }
            }

            if (!swapped)
                break;
        }

        displayImage();
        column++;
    }

    public void horizontalStep() {
        if (row >= height) {
            return;
        }

        for (int i = 0; i < width - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < width - 1; j++) {
                if (calculateLuminance(j, row) < calculateLuminance(j + 1, row)) {
                    swap(j, row, j + 1, row);
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }

        displayImage();
        row++;
    }

    public void swap(int i1, int j1, int i2, int j2) {
        int color1 = this.image.getRGB(i1, j1);
        int color2 = this.image.getRGB(i2, j2);
        this.image.setRGB(i1, j1, color2);
        this.image.setRGB(i2, j2, color1);
    }

    public double calculateLuminance(int i, int j) {
        Color color = new Color(this.image.getRGB(i, j));
        double luminance;

        luminance =  (0.2126 * color.getRed() + 0.7152 * color.getGreen() + 0.0722 * color.getBlue());

        return luminance;
    }

    public void loadImage(String fileName) throws IOException {
        File imageFile = new File("src/images/" + fileName);
        this.image = ImageIO.read(imageFile);

        this.height = this.image.getHeight();
        this.width = this.image.getWidth();

    }

    public void displayImage() {
        this.remove(imageLabel);

        this.imageLabel.setIcon(new ImageIcon(image));
        this.add(imageLabel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_R) {
            reset();
        }

        if (e.getKeyCode() == KeyEvent.VK_H) {
            reset();
            startAnimatedBubbleSort('h');
        }

        if (e.getKeyCode() == KeyEvent.VK_D) {
            reset();
            startAnimatedBubbleSort('d');
        }

        if (e.getKeyCode() == KeyEvent.VK_V) {
            reset();
            startAnimatedBubbleSort('v');
        }

        int delay = timer.getDelay();

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            delay -= 10;

            if (delay < 10) {
                delay = 0;
                System.out.println("Too FAST! ARE YOU MCQUEEN?");
            }

        } else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            delay += 10;

            if (delay > 100) {
                delay = 100;
                System.out.println("Too SLOW! ARE YOU HZ. EYYUB ?");
            }
        }

        this.timer.setDelay(delay);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private void reset() {
        row = 0;
        column = 0;
        this.timer.stop();

        try {
        loadImage(imagePath);
        } catch (Exception e) {

        }
        displayImage();
    }
}
