import javax.swing.*;
import javax.swing.text.Caret;
import java.awt.*;

public class ButtonPanels extends JPanel {
    public static RoundButton updateButton;
    public static RoundButton playButton;
    public static RoundButton stopButton;
    public static JComboBox songBox;
    public static JTextField lengthField;
    public static JTextField wheelField;

    public ButtonPanels(int type) {
        super();
        if (type == 1) {
            JPanel lengthPanel = new JPanel();
            lengthPanel.add(new Label("Length: "));
            lengthField = new JTextField();
            lengthField.setPreferredSize(new Dimension(100, 20));
            lengthField.setBorder(BorderFactory.createEmptyBorder());
            lengthPanel.add(lengthField);
            lengthPanel.setPreferredSize(new Dimension(200, 30));
            this.add(lengthPanel);

            JPanel wheelPanel = new JPanel();
            wheelPanel.add(new Label("Wheel: "));
            wheelField = new JTextField();
            wheelField.setPreferredSize(new Dimension(100, 20));
            wheelField.setBorder(BorderFactory.createEmptyBorder());
            wheelPanel.add(wheelField);
            wheelPanel.setPreferredSize(new Dimension(200, 30));
            this.add(wheelPanel);

            JPanel songPanel = new JPanel();
            String[] songs = {"Müslüm Baba - Paramparça", "Murat Göğebakan - Vurgunum", "Edip Akbaryam - Gittin Gideli",
                    "Ahmet Kaya - Nereden Bileceksiniz", "Fedon - Aşığınım", "Haluk Levent - Elfida", "Özdemir Erdoğan - Bana Ellerini Ver",
                    "Derya Yıldırım - Caddelerde Rüzgar", "Esmeray - Unutama Beni", "Ayla Dikmen - Anlamazdın", "Ayten Alpman - Tek Başına"};
            songBox = new JComboBox(songs);
            songBox.setBorder(BorderFactory.createEmptyBorder());
            songBox.setSelectedIndex(9);
            songPanel.add(songBox);
            songPanel.setPreferredSize(new Dimension(300, 30));
            this.add(songPanel);

            updateButton = new RoundButton("Update");
            updateButton.setFocusable(false);
            updateButton.setPreferredSize(new Dimension(150, 30));
            updateButton.setMnemonic('u');
            this.add(updateButton);

        } else if (type == 2) {
            playButton = new RoundButton("Play");
            playButton.setFocusable(false);
            playButton.setPreferredSize(new Dimension(150, 30));
            playButton.setMnemonic('p');
            this.add(playButton);

            stopButton = new RoundButton("Stop");
            stopButton.setFocusable(false);
            stopButton.setPreferredSize(new Dimension(150, 30));
            stopButton.setMnemonic('s');
            this.add(stopButton);

        }
    }
}
