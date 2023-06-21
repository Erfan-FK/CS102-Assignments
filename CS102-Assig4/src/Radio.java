import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Radio {
    private String path = "src/Songs/";
    private boolean isStarted;
    File song;
    AudioInputStream audioStream;
    Clip clip;

    public Radio(String songName)  {
        this.path += songName + ".wav";
        isStarted = false;
        song = new File(path);
        try {
            audioStream = AudioSystem.getAudioInputStream(song);
            clip = AudioSystem.getClip();
        } catch (Exception e) {

        }
    }


    public void startSong() {
        stopSong();
        if (!isStarted) {
            try {
                clip.stop();
                clip.open(audioStream);
                clip.start();
                isStarted = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stopSong() {
        try {
            clip.stop();
            isStarted = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }
}
