import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MyFrame extends JFrame {

    private static final ImageIcon FRAME_ICON = new ImageIcon("res\\bus.png");
    private final int FRAME_WIDTH = 1500;
    private final int FRAME_HEIGHT = 800;


    private BusRoadPanel roadPanel;

    private Radio radio;

    public MyFrame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle("Bus");
        this.setIconImage(FRAME_ICON.getImage());
        this.setResizable(false);

        JPanel civPanel = new CivCivPanel();
        civPanel.setBounds(FRAME_WIDTH / 2 - 300, 50, 550, 200);
        this.add(civPanel);

        ButtonPanels inputPanel = new ButtonPanels(1);
        inputPanel.setBounds(0, 0, 300, 150);
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        this.add(inputPanel);

        ButtonPanels playStopPanel = new ButtonPanels(2);
        playStopPanel.setBounds(FRAME_WIDTH - 200, 0, 200, 150);
        playStopPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        ButtonPanels.playButton.addActionListener(e -> {
            roadPanel.timer.start();

            String[] name = ButtonPanels.songBox.getSelectedItem().toString().split(" - ");
            radio = new Radio(name[1]);
            radio.startSong();
            ButtonPanels.playButton.setEnabled(false);
        });
        ButtonPanels.stopButton.addActionListener(e -> {
            roadPanel.timer.stop();
            radio.stopSong();
            ButtonPanels.playButton.setEnabled(true);
        });
        this.add(playStopPanel);

        roadPanel = new BusRoadPanel(FRAME_WIDTH, 600, 400, 4);
        this.getContentPane().add(roadPanel);


        this.setLayout(null);
        this.setLocationRelativeTo(null); // Center the frame in screen
        this.setVisible(true);
    }
}
