import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel {

    private final int gridRow = 2;
    private final int gridCol = 2;
    private JButton[][] buttons;
    private static int row = 0;
    public static int score = 10;
    private static int grayPanels = 0;
    private static boolean isConstructFinished = false;
    private GameFrame frame;

    public GamePanel(int depth, GameFrame frame) {
        this.frame = frame;
        frame.setTitle("Score: " + score);

        buttons = new JButton[(int) Math.pow(2, GameFrame.depth * 2)][gridCol * 2];
        this.setLayout(new GridLayout(gridRow, gridCol, 2, 2));

        int column = 0;

        if (depth == 0) {
            for (int i = 0; i < gridRow * gridCol; i++) {
                JButton button = new JButton();

                int rowNo = row;

                buttons[row][column++] = button;
                button.setBackground(randomColorGenerator());
                button.addActionListener(e -> {
                    button.setBackground(randomColorGenerator());

                    if (checkColors(rowNo))
                        changeColors(rowNo);
                    else
                        updateScore();

                    if (score <= 0)
                        showGameOverDialog();

                });

                this.add(button);

                if (i == 3)
                    if (checkColors(rowNo))
                        changeColors(rowNo);
            }
            row++;
        } else {
            for (int i = 0; i < 4; i++)
                this.add(new GamePanel(depth - 1, frame));
            isConstructFinished = true;
        }

    }

    private Color randomColorGenerator() {
        Random random = new Random();

        int colorInt = random.nextInt(0, 3);

        if (colorInt == 0)
            return new Color(0xFA1A0D);
        else if (colorInt == 1)
            return new Color(0xABCE30);
        else
            return new Color(0x0E8BC4);
    }

    private boolean checkColors(int rowNo) {
        Color clickedColor = buttons[rowNo][0].getBackground();

        for (int i = 0; i < gridCol * gridRow; i++) {
            if (!buttons[rowNo][i].getBackground().equals(clickedColor)) {
                return false;
            }
        }
        return true;
    }

    private void changeColors(int rowNo) {
        for (int i = 0; i < gridCol * gridRow; i++) {
            buttons[rowNo][i].setBackground(Color.GRAY);
            buttons[rowNo][i].setEnabled(false);
        }

        grayPanels++;
        increaseScore(isConstructFinished);
        frame.setTitle("Score: " + score);
        checkWinner();
    }

    private void updateScore() {
            score--;
            frame.setTitle("Score: " + score);
    }

    private void showGameOverDialog() {
        int result = JOptionPane.showConfirmDialog(frame,"Game Over! Another Shot?", "Game Over", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            reset();
        }
        else
            frame.dispose();
    }

    private void reset() {
        frame.getContentPane().removeAll();
        this.removeAll();
        frame.repaint();

        score = 10;
        buttons = new JButton[(int) Math.pow(2, GameFrame.depth * 2)][gridCol * 2];
        row = 0;
        grayPanels = 0;
        isConstructFinished = false;

        GamePanel gamePanel = new GamePanel(GameFrame.depth, frame);
        frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
        frame.revalidate();
    }

    private void increaseScore(boolean isConstructFinished) {
        if (isConstructFinished) {
            score += 10;
        }
    }

    private void checkWinner() {
        if (grayPanels == (int) Math.pow(2, GameFrame.depth * 2))
            showWinDialog();
    }

    private void showWinDialog() {
        int result = JOptionPane.showConfirmDialog(frame,"HELL YEAH! Another Shot?", "YOU WIN", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            reset();
        }
        else
            frame.dispose();
    }
}
